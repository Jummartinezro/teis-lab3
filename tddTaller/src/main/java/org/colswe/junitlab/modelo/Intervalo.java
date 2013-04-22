package org.colswe.junitlab.modelo;

import java.util.Date;

public class Intervalo {
	private Date desde;
	private Date hasta;
	
	public Intervalo(Date date1, Date date2){
		desde = date1;
		hasta = date2;
	}
	
	public Date getDesde() {
		return desde;
	}
	
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	
	public Date getHasta() {
		return hasta;
	}
	
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	

}