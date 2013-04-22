/**
 * 
 */
package org.colswe.junitlab.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.colswe.junitlab.logica.imp.ControlFestivo;
import org.colswe.junitlab.modelo.Festivo;

import junit.framework.TestCase;

/**
 * @author juanmanuelmartinezromero
 *
 */
public class ControlFestivoContarHorasTest extends TestCase {

	public ControlFestivoContarHorasTest(String name) {
		super(name);
	}
	
	public void testFechasInvalidas(){
		ControlFestivo cf = new ControlFestivo();
		
		Date desdeNull = null;
		Date hastaNull = null;
		
		Calendar desde = Calendar.getInstance();
		Calendar hasta = Calendar.getInstance();
		desde = Calendar.getInstance();
		hasta = Calendar.getInstance();
		desde.setTimeInMillis(0);
		hasta.setTimeInMillis(0);
		desde.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.YEAR, 2012);
		
		assertEquals(null, cf.contarHoras(desdeNull, hastaNull));
		assertEquals(null, cf.contarHoras(desdeNull, hasta.getTime()));
		assertEquals(null, cf.contarHoras(desde.getTime(), hastaNull));
		assertEquals(null, cf.contarHoras(desde.getTime(), hasta.getTime()));

	}

	public void testFechasIguales() {

		List<Festivo> festivos = new ArrayList<Festivo>();
		
		//Festivo No Laborable
		Festivo festivoNoLab = produceFestivo(2012, Calendar.JANUARY, 4, false);
		festivos.add(festivoNoLab);
		
		//Festivo Laborable
		Festivo festivoLab = produceFestivo(2012, Calendar.JANUARY, 8, true);
		festivos.add(festivoLab);

		//Día Sábado Festivo Laborable
		Festivo sabadoFestivoLab = produceFestivo(2012, Calendar.JANUARY, 7, true);
		festivos.add(sabadoFestivoLab);

		//Día Sábado Festivo No Laborable
		Festivo sabadoFestivoNoLab = produceFestivo(2012, Calendar.JANUARY, 28, false);
		festivos.add(sabadoFestivoNoLab);
		
		//Día Domingo Festivo Laborable
		Festivo domingoFestivoLab = produceFestivo(2012, Calendar.JANUARY, 1, true);
		festivos.add(domingoFestivoLab);
		
		ControlFestivo cf = new ControlFestivo();
		cf.sistema.setEntidades(festivos);
		
		Calendar desde = Calendar.getInstance();
		Calendar hasta = Calendar.getInstance();
		desde.setTimeInMillis(0);
		desde.setTimeInMillis(0);
		
		//Festivo No Laborable	
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 4);
		hasta = desde;
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Festivo Laborable
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 8);
		hasta = desde;
		assertEquals(new Integer(16), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Día Normal
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 24);
		hasta = desde;
		assertEquals(new Integer(8), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Día Sábado
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 14);
		hasta = desde;
		assertEquals(new Integer(4), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Día Domingo
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 22);
		hasta = desde;
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Día Sábado Festivo Laborable
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 7);
		hasta = desde;
		assertEquals(new Integer(16), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Dia Sábado Festivo No Laborable
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 28);
		hasta = desde;
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Día Domingo Festivo Laborable
		desde.set(Calendar.YEAR, 2012);
		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 1);
		hasta = desde;
		assertEquals(new Integer(16), cf.contarHoras(desde.getTime(), hasta.getTime()));		
		
	}
	
	public void testFuncionalidadEsperada() {
		List<Festivo> festivos = new ArrayList<Festivo>();
		//Festivos No Laborables
		Festivo festivoNoLab1 = produceFestivo(2013, Calendar.APRIL, 2, false);
		Festivo festivoNoLab2 = produceFestivo(2013, Calendar.APRIL, 3, false);
		Festivo festivoNoLab3 = produceFestivo(2013, Calendar.APRIL, 29, false);
		Festivo festivoNoLab4 = produceFestivo(2013, Calendar.APRIL, 30, false);
		Festivo festivoNoLab5 = produceFestivo(2013, Calendar.MAY, 1, false);
		festivos.add(festivoNoLab1);
		festivos.add(festivoNoLab2);
		festivos.add(festivoNoLab3);
		festivos.add(festivoNoLab4);
		festivos.add(festivoNoLab5);
		
		//Festivos Laborables
		Festivo festivoLab1 = produceFestivo(2013, Calendar.APRIL, 9, true);
		Festivo festivoLab2 = produceFestivo(2013, Calendar.APRIL, 10, true);
		Festivo festivoLab3 = produceFestivo(2013, Calendar.APRIL, 16, true);
		Festivo festivoLab4 = produceFestivo(2013, Calendar.APRIL, 17, true);
		Festivo festivoLab5 = produceFestivo(2013, Calendar.APRIL, 18, true);
		Festivo festivoLab6 = produceFestivo(2013, Calendar.APRIL, 19, true);
		festivos.add(festivoLab1);
		festivos.add(festivoLab2);
		festivos.add(festivoLab3);
		festivos.add(festivoLab4);
		festivos.add(festivoLab5);
		festivos.add(festivoLab6);
		
		//Sabados Festivos No Laborables
		Festivo festivoSabadoNoLab = produceFestivo(2013, Calendar.APRIL, 13, false);
		festivos.add(festivoSabadoNoLab);
		
		//Sabados Festivos Laborables
		Festivo festivoSabadoLab = produceFestivo(2013, Calendar.APRIL, 6, true);
		festivos.add(festivoSabadoLab);
		
		//Domingos Festivos Laborables
		Festivo festivoDomingoLab1 = produceFestivo(2013, Calendar.APRIL, 7, true);
		Festivo festivoDomingoLab2 = produceFestivo(2013, Calendar.APRIL, 14, true);
		festivos.add(festivoDomingoLab1);
		festivos.add(festivoDomingoLab2);

		ControlFestivo cf = new ControlFestivo();
		cf.sistema.setEntidades(festivos);

		Calendar hasta = Calendar.getInstance();
		Calendar desde = Calendar.getInstance();
		hasta.setTimeInMillis(0);
		desde.setTimeInMillis(0);
		

		//Dos festivos no laborables seguidos
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 1);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 4);
		assertEquals(new Integer(16), cf.contarHoras(desde.getTime(), hasta.getTime()));


		//Dos festivos laborables seguidos
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 8);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 11);
		assertEquals(new Integer(48), cf.contarHoras(desde.getTime(), hasta.getTime()));
		

		//Fechas limites festivos no laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 2);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 3);
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Fechas limites festivos laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 9);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 10);
		assertEquals(new Integer(32), cf.contarHoras(desde.getTime(), hasta.getTime()));

		//Rango que incluye un día sábado festivo laborable y un domingo festivo laborable
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 5);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 8);
		assertEquals(new Integer(48), cf.contarHoras(desde.getTime(), hasta.getTime()));

		//Rango que incluye un día sábado festivo no laborable y un domingo festivo laborable
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 12);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 15);
		assertEquals(new Integer(32), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Rango con solo fechas festivas laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 16);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 19);
		assertEquals(new Integer(64), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Rango con solo fechas festivas no laborables
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 29);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.MAY);
		hasta.set(Calendar.DATE, 1);
		assertEquals(new Integer(0), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
		//Rango con solo fechas comunes
		desde.set(Calendar.YEAR, 2013);
		desde.set(Calendar.MONTH, Calendar.APRIL);
		desde.set(Calendar.DATE, 22);
		hasta.set(Calendar.YEAR, 2013);
		hasta.set(Calendar.MONTH, Calendar.APRIL);
		hasta.set(Calendar.DATE, 25);
		assertEquals(new Integer(32), cf.contarHoras(desde.getTime(), hasta.getTime()));
		
	}
	
	public void testIntervaloNoValido(){
		
		
	}
	
	public void testInetrvalosIguales(){
		
	}
	
	public void testCruceDeFechas(){
		
	}
	
	private Festivo produceFestivo(int year, int month, int date, boolean laborable) {
		Festivo festivo = new Festivo();
		Calendar fecha = Calendar.getInstance();
		fecha.setTimeInMillis(0);
		fecha.set(Calendar.YEAR, year);
		fecha.set(Calendar.MONTH, month);
		fecha.set(Calendar.DATE, date);
		festivo.setFecha(fecha.getTime());
		festivo.setLaborable(laborable);
		return festivo;
	}
}
