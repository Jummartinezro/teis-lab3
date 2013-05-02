package org.colswe.junitlab.logica.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.colswe.junitlab.logica.IControlFestivo;
import org.colswe.junitlab.modelo.Festivo;
import org.colswe.junitlab.modelo.Intervalo;
import org.colswe.junitlab.modelo.Sistema;
import org.colswe.junitlab.modelo.TipoDia;

/**
 * @author zergio
 * 
 */
public class ControlFestivo implements IControlFestivo {

	/**
	 * Instancia de la unidad de presistencia.
	*/
	private final Sistema sistema = Sistema.getInstance();

	/**
	 * Número de horas de trabajo de un día festivo laborable.
	 */
	private static final int NUM_HORAS_FESTIVO_LABORABLE = 16;
	/**
	 * Número de horas de trabajo de un día laboral común.
	 */
	private static final int NUM_HORAS_DIA_LABORAL = 8;
	/**
	 * Número de horas de trabajo de un sábado.
	 */
	private static final int NUM_HORAS_SABADO = 4;

	/**
	 * Metodo que retorna la unidad de persistencia.
	 * 
	 * @return unidad de persistencia sistema
	 */
	public Sistema getSistema() {
		return sistema;
	}

	/**
	 * Metodo que cuenta las horas en un intervalo, incluyendo los días de
	 * inicio y terminación.
	 * 
	 * @param desde
	 *            inicio del intervalo
	 * @param hasta
	 *            fin del intervalo
	 * @return suma de las horas laborales en el intervalo ingresado
	 */
	public Integer contarHoras(final Date desde, final Date hasta) {
		Integer horas = null;

		/*
		 * Se determina si las fechas ingresadas de comienzo y fin del intervalo
		 * son correctas luego se utiliza el método obtener días para obtener un
		 * mapa con las fechas finalmente se suman las horas de cada día en el
		 * interválo considerando tipo de día
		 */
		if (fechasValidas(desde, hasta)) {
			horas = Integer.valueOf(0);
			final Map<TipoDia, Integer> ret = obtenerDias(desde, hasta);
			horas += ret.get(TipoDia.FESTIVO_LABORA)
					* NUM_HORAS_FESTIVO_LABORABLE;
			horas += ret.get(TipoDia.FESTIVO_NO_LABORAL) * 0;
			horas += ret.get(TipoDia.NORMAL) * NUM_HORAS_DIA_LABORAL;
			horas += ret.get(TipoDia.SABADO) * NUM_HORAS_SABADO;
		}
		return horas;
	}

	/**
	 * Metodo que cuenta las horas en un mapa ingresado (Aún no implementado).
	 * 
	 * @param info Mapa de Tipo día y un Integer correspondiente segun el numero de dias
	 * @return suma de las horas laborales en el mapa ingresado
	 */
	public Integer contarHoras(final Map<TipoDia, Integer> info) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Cuenta las horas laborales de un conjunto de intervalos de fechas.
	 * 
	 * @param intervalos
	 *            intervalos de fechas
	 * @return numero de horas laborables totales en los intervalos 
	 *         o null si alguno de los intervalos es invalido
	 */
	public Integer contarHoras(final List<Intervalo> intervalos) {
		Integer totalHoras = Integer.valueOf(0);

		/*
		 * Se obtiene la cantidad de horas en cada intervalo usando
		 * contarHoras(desde, hasta) primero se revisa si las fechas límite del
		 * intervalo son válidas y si la condición se da se procede a sumar sus
		 * horas a un total.
		 */
		for (final Intervalo i : intervalos) {
			final Date desde = i.getDesde();
			final Date hasta = i.getHasta();
			if (!fechasValidas(desde, hasta)) {
				totalHoras = null;
				break;
			} else {
				totalHoras += contarHoras(desde, hasta);
			}
		}
		return totalHoras;

	}

	/**
	 * Metodo que da información sobre la validez de las fechas. Que no sean
	 * fechas nulas, y que el desde no ocurra antes que el hasta (pueden ser
	 * iguales)
	 * 
	 * @param desde
	 *            inicio del intervalo de tiempo (inclusivo)
	 * @param hasta
	 *            terminación del intervalo (inclusivo)
	 * 
	 * @return true si la desde es una fecha anterior a hasta
	 */
	public boolean fechasValidas(final Date desde, final Date hasta) {
		/*
		 * Se comprueba si las fechas no son valores nulos Luego se comprueba si
		 * las desde está antes de hasta
		 */

		if (desde == null || hasta == null) {
			return false;
		}
		return desde.before(hasta) || desde.equals(hasta);
	}

	/**
	 * Método que retorna el mapa con los días separados por tipo.
	 * 
	 * @param desde
	 *            día inicial inclusive
	 * @param hasta
	 *            día final inclusive
	 * @return mapa con los días entre las fecha desde y hasta
	 */

	public Map<TipoDia, Integer> obtenerDias(final Date desde, final Date hasta) {
		// Inicialización del mapa a retornar con los tipos de días en cero
		final HashMap<TipoDia, Integer> ret = new HashMap<TipoDia, Integer>();
		ret.put(TipoDia.FESTIVO_LABORA, 0);
		ret.put(TipoDia.FESTIVO_NO_LABORAL, 0);
		ret.put(TipoDia.NORMAL, 0);
		ret.put(TipoDia.SABADO, 0);
		// En caso de que desde y hasta sean inválidos se retorna un mapa vacío
		if (!fechasValidas(desde, hasta)) {
			return ret;
		}
		/*
		 * Se hace set del iterador c con fecha desde Se hace set del limite ch
		 * con fecha hasta agregandole un dia Se inicializan los contadores del
		 * tipo de dia (sabado, festivo laborable, festivo no laborable, dia
		 * normal)
		 */
		final Calendar cH = Calendar.getInstance();
		cH.setTime(hasta);
		cH.add(Calendar.DATE, 1);
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(desde.getTime());
		int sabado = 0;
		int festivoL = 0;
		int festivoNL = 0;
		int normal = 0;
		/*
		 * Iterador para contar la cantidad de dias de cada tipo Primero se
		 * revisa en sistema si la fecha del iterador c es un festivo (ciclo
		 * for) si la fecha es un festivo se procede a determinar si es
		 * laborable o domingo si es laborable se suma al contador de festivos
		 * laborables si no es laborable pero es domingo no se suma a los
		 * contadores de festivos Si la fecha no es un festivo se procede a
		 * determinar si es sabado o dia normal
		 */
		while (cH.after(c)) {
			boolean festivo = false;
			for (final Festivo f : sistema.getEntidades()) {
				if (f.getFecha().equals(c.getTime())) {
					festivo = true;
					if (f.isLaborable()) {
						festivoL++;
					} else {
						final Calendar ct = Calendar.getInstance();
						ct.setTime(f.getFecha());
						festivoNL += domingoNoLaborable(ct);
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

		// Se agrega al mapa cada tipo de día con su respectivo valor de
		// contador
		ret.clear();
		ret.put(TipoDia.FESTIVO_LABORA, festivoL);
		ret.put(TipoDia.FESTIVO_NO_LABORAL, festivoNL);
		ret.put(TipoDia.NORMAL, normal);
		ret.put(TipoDia.SABADO, sabado);
		return ret;
	}

	/**
	 * Método que retorna si un día es domingo.
	 * 
	 * @param ct
	 *            Calendar que tiene la fecha a revisar
	 * @return true si la fecha es domingo
	 */
	private int domingoNoLaborable(final Calendar ct) {
		return ct.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 0 : 1;
	}

}
