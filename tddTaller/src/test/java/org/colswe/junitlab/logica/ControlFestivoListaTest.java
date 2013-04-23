package org.colswe.junitlab.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.colswe.junitlab.logica.imp.ControlFestivo;
import org.colswe.junitlab.modelo.Festivo;
import org.colswe.junitlab.modelo.TipoDia;

public class ControlFestivoListaTest extends TestCase {

	public ControlFestivoListaTest(String name) {
		super(name);
	}

	public void testObtenerDias(){
		List<Festivo> festivos = new ArrayList<Festivo>();
		Festivo festivo = produceFestivo(Calendar.JANUARY, 2, false);
		Festivo festivo1 = produceFestivo(Calendar.JANUARY, 8, true);
		Festivo festivo2 = produceFestivo(Calendar.JANUARY, 30, true);
		festivos.add(festivo);
		festivos.add(festivo1);
		festivos.add(festivo2);

		ControlFestivo cf = new ControlFestivo();
		cf.getSistema().setEntidades(festivos);

		Calendar hasta = Calendar.getInstance();
		Calendar desde = Calendar.getInstance();
		hasta.setTimeInMillis(0);
		desde.setTimeInMillis(0);
		desde.set(Calendar.YEAR, 2012);
		hasta.set(Calendar.YEAR, 2012);

		desde.set(Calendar.MONTH, Calendar.JANUARY);
		desde.set(Calendar.DATE, 1);
		hasta.set(Calendar.MONTH, Calendar.JANUARY);
		hasta.set(Calendar.DATE, 30);

		Map<TipoDia, Integer> dias = cf.obtenerDias(desde.getTime(), hasta.getTime() );

		assertEquals(new Integer(2), dias.get(TipoDia.FESTIVO_LABORA));
		assertEquals(new Integer(1), dias.get(TipoDia.FESTIVO_NO_LABORAL));
		assertEquals(new Integer(19), dias.get(TipoDia.NORMAL));
		assertEquals(new Integer(4), dias.get(TipoDia.SABADO));
	}

	private Festivo produceFestivo(int month, int date, boolean laborable) {
		Festivo festivo = new Festivo();
		Calendar fecha = Calendar.getInstance();
		fecha.setTimeInMillis(0);
		fecha.set(Calendar.YEAR, 2012);
		fecha.set(Calendar.MONTH, month);
		fecha.set(Calendar.DATE, date);
		festivo.setFecha(fecha.getTime());
		festivo.setLaborable(laborable);
		return festivo;
	}

}
