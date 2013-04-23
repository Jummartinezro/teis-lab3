package org.colswe.junitlab.logica.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.colswe.junitlab.logica.IControlFestivo;
import org.colswe.junitlab.modelo.Festivo;
import org.colswe.junitlab.modelo.Sistema;
import org.colswe.junitlab.modelo.TipoDia;
import org.colswe.junitlab.modelo.Intervalo;

/**
 * @author zergio
 *
 */
public class ControlFestivo implements IControlFestivo {

	private Sistema sistema = Sistema.getInstance();
	private static final int NUM_HORAS_FESTIVO_LABORABLE=16;
	private static final int NUM_HORAS_DIA_LABORAL=8;
	private static final int NUM_HORAS_SABADO=4;
	

	/**Metodo que retorna la unidad de persistencia
	 * @return unidad de persistencia sistema
	 */
	public Sistema getSistema() {
		return sistema;
	}

	/** Metodo que cuenta las horas en un intervalo, incluyendo los días de inicio y terminación 
	 * @param desde inicio del intervalo
	 * @param hasta fin del intervalo
	 * @return suma de las horas laborales en el intervalo ingresado
	 */
	public Integer contarHoras(Date desde, Date hasta) {
		Integer horas = null;
		if (fechasValidas(desde, hasta)) {
			horas = new Integer(0);
			Map<TipoDia, Integer> ret = obtenerDias(desde, hasta);
			horas += ret.get(TipoDia.FESTIVO_LABORA) * NUM_HORAS_FESTIVO_LABORABLE;
			horas += ret.get(TipoDia.FESTIVO_NO_LABORAL) * 0;
			horas += ret.get(TipoDia.NORMAL) * NUM_HORAS_DIA_LABORAL;
			horas += ret.get(TipoDia.SABADO) * NUM_HORAS_SABADO;
		}
		return horas;
	}

	/** Metodo que cuenta las horas en un mapa ingresado (Aún no implementado)
	 * @param info Mapa de Tipo día e integer (desconocido)
	 */
	public Integer contarHoras(Map<TipoDia, Integer> info) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Cuenta las horas laborales de un conjunto de intervalos de fechas
	 * @param intervalos intervalos de fechas
	 * @return numero de horas laborables totales en los intervalos.
	 * @return null si alguno de los intervalos es invalido
	 */
	public Integer contarHoras(ArrayList<Intervalo> intervalos) {
		Integer totalHoras = Integer.valueOf(0);
		for (Intervalo i : intervalos) {
			Date desde = i.getDesde();
			Date hasta = i.getHasta();
			if (!fechasValidas(desde, hasta)) {
				totalHoras=null;
				break;
			} else {
				totalHoras += contarHoras(desde, hasta);
			}
		}
		return totalHoras;
		
		
	}

	/** Metodo que da información sobre la validez de las fechas,
	 * que no sean fechas nulas, y que el desde no ocurra antes que el hasta (pueden ser iguales)
	 * 
	 * @param desde inicio del intervalo de tiempo (inclusivo)
	 * @param hasta terminación del intervalo (inclusivo)
	 * 
	 * @return 
	 */
	public boolean fechasValidas(Date desde, Date hasta) {
		if (desde == null || hasta == null) {
			return false;
		}
		return desde.before(hasta) || desde.equals(hasta);
	}

	/**
	 * Retorna el mapa con los días separados por tipo
	 * 
	 * @param desde
	 *            día inicial inclusive
	 * @param hasta
	 *            día final inclusive
	 */
	
	public Map<TipoDia, Integer> obtenerDias(Date desde, Date hasta) {
		HashMap<TipoDia, Integer> ret = new HashMap<TipoDia, Integer>();
		ret.put(TipoDia.FESTIVO_LABORA, 0);
		ret.put(TipoDia.FESTIVO_NO_LABORAL, 0);
		ret.put(TipoDia.NORMAL, 0);
		ret.put(TipoDia.SABADO, 0);

		if (!fechasValidas(desde, hasta)) {
			return ret;
		}
		Calendar cH = Calendar.getInstance();
		cH.setTime(hasta);
		cH.add(Calendar.DATE, 1);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(desde.getTime());
		int sabado = 0;
		int festivoL = 0;
		int festivoNL = 0;
		int normal = 0;

		while (cH.after(c)) {
			boolean festivo = false;
			for (Festivo f : sistema.getEntidades()) {
				if (f.getFecha().equals(c.getTime())) {
					festivo = true;
					if (f.isLaborable()) {
						festivoL++;
					} else {
						Calendar ct = Calendar.getInstance();
						ct.setTime(f.getFecha());
						festivoNL += ct.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 0
								: 1;
					}
				}
			}
			
			if (!festivo && c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				sabado++;
			} else if (!festivo
					&& c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				normal++;
			}
			c.add(Calendar.DATE, 1);
		}
		ret.clear();
		ret.put(TipoDia.FESTIVO_LABORA, festivoL);
		ret.put(TipoDia.FESTIVO_NO_LABORAL, festivoNL);
		ret.put(TipoDia.NORMAL, normal);
		ret.put(TipoDia.SABADO, sabado);

		return ret;
	}
}