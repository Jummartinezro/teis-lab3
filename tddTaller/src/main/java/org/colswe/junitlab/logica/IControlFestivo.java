package org.colswe.junitlab.logica;

import java.util.Date;
import java.util.Map;

import org.colswe.junitlab.modelo.TipoDia;

/**
 * Interface con firmas abstractas.
 * 
 * @author zergio
 */
public interface IControlFestivo {
	/**
	 * Cuenta las horas en un intervalo determinado.
	 * 
	 * @param desde
	 *            comienzo del intervalo
	 * @param hasta
	 *            fin del intervalo
	 * @return horas contadas laborales en este intervalo
	 */
	Integer contarHoras(Date desde, Date hasta);

	/**
	 * Metodo que valida que las fechas sean correctas.
	 * 
	 * @param desde
	 *            inicio del intervalo
	 * @param hasta
	 *            fin del intervalo
	 * @return validez (true) o invalidez (false) de las fechas
	 */
	boolean fechasValidas(Date desde, Date hasta);

	/**
	 * Metodo que obtiene un mapa con los tipos de días encontrados. entre dos
	 * fechas ingresadas.
	 * 
	 * @param desde
	 *            inicio del intervalo temporal
	 * @param hasta
	 *            fin del intervalo temporal
	 * @return mapa con los tipos de días encontrados entre dos fechas
	 */
	Map<TipoDia, Integer> obtenerDias(Date desde, Date hasta);

	/**
	 * Metodo que cuenta las horas en un mapa.
	 * 
	 * @param info
	 *            Mapa con un tipo de dia (Implementados en la clase TipoDia)
	 * @return suma de horas ingresadas
	 */
	Integer contarHoras(Map<TipoDia, Integer> info);
}
