package org.colswe.junitlab.logica.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.colswe.junitlab.logica.IControlFestivo;
import org.colswe.junitlab.modelo.Festivo;
import org.colswe.junitlab.modelo.Sistema;
import org.colswe.junitlab.modelo.TipoDia;

public class ControlFestivo implements IControlFestivo {

	public Sistema sistema = Sistema.getInstance();

	public Integer contarHoras(Date desde, Date hasta) {
		Integer horas = null;
		if (fechasValidas(desde, hasta)){
			horas = new Integer(0);
			Map<TipoDia, Integer> ret = obtenerDias(desde, hasta);
			horas += ret.get(TipoDia.FESTIVO_LABORA) * 16;
			horas += ret.get(TipoDia.FESTIVO_NO_LABORAL) * 0;
			horas += ret.get(TipoDia.NORMAL) * 8;
			horas += ret.get(TipoDia.SABADO) * 4;
		}
		return horas;
	}

	public Integer contarHoras(Map<TipoDia, Integer> info) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean fechasValidas(Date desde, Date hasta) {
		if (desde==null || hasta==null){
			return false;
		}
		return desde.before(hasta) ||  desde.equals(hasta);
	}

	/**
	 * Retorna el mapa con los d�as separados por tipo
	 * @param desde d�a inicial inclusive
	 * @param hasta d�a final inclusive
	 */
	public Map<TipoDia, Integer> obtenerDias(Date desde, Date hasta) {
		HashMap<TipoDia, Integer> ret = new HashMap<TipoDia, Integer>();
		ret.put(TipoDia.FESTIVO_LABORA, 0);
		ret.put(TipoDia.FESTIVO_NO_LABORAL, 0);
		ret.put(TipoDia.NORMAL, 0);
		ret.put(TipoDia.SABADO, 0);

		if (!fechasValidas(desde, hasta)){
			return ret;
		}
		Calendar cH = Calendar.getInstance();
		cH.setTime(hasta);
		cH.add(Calendar.DATE, 1);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(desde.getTime());
		int sabado=0;
		int festivoL=0;
		int festivoNL=0;
		int normal=0;

		while (cH.after(c)){
			boolean festivo = false;
			for (Festivo f : sistema.getEntidades()){
				if (f.getFecha().equals(c.getTime())){
					festivo = true;
					if (f.isLaborable()){
						festivoL++;
					}else{
						Calendar ct = Calendar.getInstance();
						ct.setTime(f.getFecha());
						festivoNL += ct.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 0 : 1;
					}
				}
			}
			if (!festivo && c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
				sabado++;
			}else if (!festivo && c.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY){
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
