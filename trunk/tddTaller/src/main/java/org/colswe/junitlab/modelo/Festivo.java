package org.colswe.junitlab.modelo;

import java.util.Date;

/**Clase que modela los días festivos
 * @author zergio
 *
 */
public class Festivo {

	private Date fecha;
	private boolean laborable;

	/**Obtiene la fecha del festivo
	 * @return fecha del festivo
	 */
	public Date getFecha() {
		return fecha;
	}
	/**Asigna una fecha al festivo
	 * @param fecha fecha a asignar
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	/**Metodo de información sobre un festivo
	 * @return retorna información sobre si el festivo es o no laborable
	 */
	public boolean isLaborable() {
		return laborable;
	}
	/**Asigna al festivo información de si es o no laboral
	 * @param laborable informacion a asignar
	 */
	public void setLaborable(boolean laborable) {
		this.laborable = laborable;
	}
}
