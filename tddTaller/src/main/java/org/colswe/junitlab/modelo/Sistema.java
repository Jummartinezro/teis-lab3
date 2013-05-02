package org.colswe.junitlab.modelo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase que se encarga de la persistencia en sesión
 * 
 * @author zergio
 * 
 */
public final class Sistema {

	/**
	 * Colección de entidades en el modelo.
	 */
	private Collection<Festivo> entidades;

	/**
	 * Instancia de la persistencia.
	 */
	private static Sistema instancia;

	/**Metodo que retorna un objeto de tipo singleton con la persistencia.
	 * 
	 * @return clase de persistencia
	 */
	public static synchronized Sistema getInstance() {
		if (instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}

	/**
	 * Constructor  de sistema con un arreglo de festivos vacio.
	 */
	private Sistema() {
		super();
		entidades = new ArrayList<Festivo>();
	}

	/**Metodo que obtiene los festivos en la colección.
	 * 
	 * @return festivos en la persistencia
	 */
	public Collection<Festivo> getEntidades() {
		return entidades;
	}

	/**
	 * Asigna una colección de festivos a la coleccion
	 * 
	 * @param lasEntidades
	 *            festivos a agregar
	 */
	public void setEntidades(final Collection<Festivo> lasEntidades) {
		this.entidades = lasEntidades;
	}

}
