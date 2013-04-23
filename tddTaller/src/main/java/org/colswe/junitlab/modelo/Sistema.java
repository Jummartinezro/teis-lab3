package org.colswe.junitlab.modelo;

import java.util.ArrayList;
import java.util.Collection;

public final class Sistema {

	private Collection<Festivo> entidades;

	private static Sistema instancia;

	public static synchronized Sistema getInstance(){
		if (instancia == null){
			instancia = new Sistema();
		}
		return instancia;
	}

	private Sistema() {
		super();
		entidades = new ArrayList<Festivo>();
	}

	public Collection<Festivo> getEntidades() {
		return entidades;
	}

	public void setEntidades(Collection<Festivo> entidades) {
		this.entidades = entidades;
	}

}
