package org.colswe.junitlab.modelo;

import java.util.Date;

/**
 * Clase que modela los días festivos
 * 
 * @author zergio
 * 
 */
public class Festivo {
	/**
	 * Fecha del día festivo
	 */
	private Date fecha;
	/**
	 * Indicador de si es festivo laborable
	 */
	private boolean laborable;

	/**
	 * Obtiene la fecha del festivo
	 * 
	 * @return fecha del festivo
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Asigna una fecha al festivo
	 * 
	 * @param laFecha
	 *            fecha a asignar
	 */
	public void setFecha(final Date laFecha) {
		this.fecha = laFecha;
	}

	/**
	 * Metodo de información sobre un festivo
	 * 
	 * @return retorna información sobre si el festivo es o no laborable
	 */
	public boolean isLaborable() {
		return laborable;
	}

	/**
	 * Asigna al festivo información de si es o no laboral
	 * 
	 * @param diaLaborable
	 *            informacion a asignar
	 */
	public void setLaborable(final boolean diaLaborable) {
		this.laborable = diaLaborable;
	}
}
