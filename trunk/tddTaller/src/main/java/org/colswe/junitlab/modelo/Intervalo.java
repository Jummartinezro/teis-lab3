package org.colswe.junitlab.modelo;

import java.util.Date;

/**
 * Modela un intervalo de fechas.
 * 
 * @author zergio
 */
public class Intervalo {
    /** Fecha de inicio del intervalo. */
    private Date desde;
    /** Fecha de fin del intervalo. */
    private Date hasta;

    /**
     * Constructor de intervalo.
     * 
     * @param date1
     *            fecha de inicio
     * @param date2
     *            fecha final
     */
    public Intervalo(final Date date1, final Date date2) {
        desde = date1;
        hasta = date2;
    }

    /**
     * Obtiene la fecha de inicio.
     * 
     * @return fecha de inicio
     */
    public Date getDesde() {
        return desde;
    }

    /**
     * Asigna la fecha de inicio.
     * 
     * @param fechaDesde
     *            fecha de inicio
     */
    public void setDesde(final Date fechaDesde) {
        this.desde = fechaDesde;
    }

    /**
     * Obtiene la fecha final.
     * 
     * @return fecha final
     */
    public Date getHasta() {
        return hasta;
    }

    /**
     * Asigna la fecha final.
     * 
     * @param fechaHasta
     *            fecha final
     */
    public void setHasta(final Date fechaHasta) {
        this.hasta = fechaHasta;
    }

}
