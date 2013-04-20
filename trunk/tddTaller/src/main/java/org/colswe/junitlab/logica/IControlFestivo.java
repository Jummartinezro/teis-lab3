package org.colswe.junitlab.logica;

import java.util.Date;
import java.util.Map;

import org.colswe.junitlab.modelo.TipoDia;

public interface IControlFestivo {

	Integer contarHoras(Date desde, Date hasta);

	boolean fechasValidas(Date desde, Date hasta);

	Map<TipoDia, Integer> obtenerDias(Date desde, Date hasta);

	Integer contarHoras(Map<TipoDia, Integer> info);
}
