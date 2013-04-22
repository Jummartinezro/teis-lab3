package org.colswe.junitlab.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.colswe.junitlab.logica.imp.ControlFestivo;

import junit.framework.TestCase;

public class ControlFestivoTest extends TestCase {

	public ControlFestivoTest(String name) {
		super(name);
	}

	public void testFechasValidas(){
		List<Date[]> fechas = new ArrayList<Date[]>();
		Calendar d1 = Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		d1.setTimeInMillis(0);
		d2.setTimeInMillis(0);
		d1.set(Calendar.MONTH, Calendar.JANUARY);
		d2.set(Calendar.MONTH, Calendar.FEBRUARY);
		d1.set(Calendar.DATE, 1);
		d2.set(Calendar.DATE, 5);
		Calendar d3 = Calendar.getInstance();
		Calendar d4 = Calendar.getInstance();
		d3.setTimeInMillis(0);
		d4.setTimeInMillis(0);
		d3.set(Calendar.MONTH, Calendar.JANUARY);
		d4.set(Calendar.MONTH, Calendar.JANUARY);
		d3.set(Calendar.DATE, 1);
		d4.set(Calendar.DATE, 2);

		fechas.add(new Date[]{d1.getTime(), d2.getTime()});
		fechas.add(new Date[]{d3.getTime(), d4.getTime()});

		IControlFestivo controlFestivo = new ControlFestivo();
		for (Date[] d : fechas){
			assertTrue(controlFestivo.fechasValidas(d[0], d[1]));
		}
	}
	public void testFechasInvalidas(){
		List<Date[]> fechas = new ArrayList<Date[]>();
		Calendar d1 = Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		d1.setTimeInMillis(0);
		d2.setTimeInMillis(0);
		d1.set(Calendar.MONTH, Calendar.JANUARY);
		d2.set(Calendar.MONTH, Calendar.JANUARY);
		d1.set(Calendar.DATE, 20);
		d2.set(Calendar.DATE, 10);
		Calendar d3 = Calendar.getInstance();
		Calendar d4 = Calendar.getInstance();
		d3.setTimeInMillis(0);
		d4.setTimeInMillis(0);
		d3.set(Calendar.MONTH, Calendar.JANUARY);
		d4.set(Calendar.MONTH, Calendar.JANUARY);
		d4.set(Calendar.DATE, 1);
		d3.set(Calendar.DATE, 2);

		fechas.add(new Date[]{d1.getTime(), d2.getTime()});
		fechas.add(new Date[]{d3.getTime(), d4.getTime()});

		IControlFestivo controlFestivo = new ControlFestivo();
		for (Date[] d : fechas){
			assertFalse(controlFestivo.fechasValidas(d[0], d[1]));
		}

	}

}
