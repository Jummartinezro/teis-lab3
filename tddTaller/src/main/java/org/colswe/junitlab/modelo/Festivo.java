package org.colswe.junitlab.modelo;

import java.util.Date;

public class Festivo {

	private Date fecha;
	private boolean laborable;

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isLaborable() {
		return laborable;
	}
	public void setLaborable(boolean laborable) {
		this.laborable = laborable;
	}
}
