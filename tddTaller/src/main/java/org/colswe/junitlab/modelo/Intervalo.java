package org.colswe.junitlab.modelo;

import java.util.Date;

/**Modela un intervalo de fechas
 * @author zergio
 *
 */
public class Intervalo {
	/**
	 * Fecha de inicio
	 */
	private Date desde;
	/**
	 * Fecha final
	 */
	private Date hasta;
	
	/**Constructor de intervalo
	 * @param date1 fecha de inicio
	 * @param date2 fecha final
	 */
	public Intervalo(Date date1, Date date2){
		desde = date1;
		hasta = date2;
	}
	
	/**Obtiene la fecha de inicio
	 * @return fecha de inicio
	 */
	public Date getDesde() {
		return desde;
	}
	
	/**Asigna la fecha de inicio
	 * @param desde fecha de inicio
	 */
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	
	/**Obtiene la fecha final
	 * @return fecha final
	 */
	public Date getHasta() {
		return hasta;
	}
	
	/**Asigna la fecha final
	 * @param hasta fecha final
	 */
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	

}