/**
 *
 */
package org.colswe.junitlab.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.assertEquals;

import org.colswe.junitlab.logica.imp.ControlFestivo;
import org.colswe.junitlab.modelo.Festivo;
import org.colswe.junitlab.modelo.Intervalo;

import junit.framework.TestCase;

/**
 * @author juanmanuelmartinezromero
 *
 */
public class ControlFestivoContarHorasTest extends TestCase {

    public ControlFestivoContarHorasTest(String name) {
        super(name);
    }

    /**
     * Test que verifica si alguna de las dos fechas (desde y hasta), o ambas,
     * no son válidas.
     *
     * @param desdeNull, guarda una fecha desde, tipo null.
     * @param hastaNull, guarda una fecha hasta, tipo null.
     * @param desde, fecha de inicio en el intervalo a contar horas.
     * @param hasta, fecha de terminación en el intervalo a contar horas.
     */
    public void testFechasInvalidas() {
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

    /**
     * Test que prueba el funcionamiento del método contarHoras si las dos
     * fechas son iguales.
     *
     * @param festivos, arreglo en donde se almacenan los festivos declarados
     * para la prueba.
     * @param desde, fecha de inicio en el intervalo a contar horas.
     * @param hasta, fecha de terminación en el intervalo a contar horas.
     */
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
        cf.getSistema().setEntidades(festivos);

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

    /**
     * Test que prueba el funcionamiento del método contarHoras cuando se somete
     * a ciertas condiciones que podrían causar el mal funcionamiento del
     * procedimiento.
     *
     * @param festivos, arreglo en donde se almacenan los festivos declarados
     * para la prueba.
     * @param desde, fecha de inicio en el intervalo a contar horas.
     * @param hasta, fecha de terminación en el intervalo a contar horas.
     */
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
        cf.getSistema().setEntidades(festivos);

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

    /**
     * Test que prueba que alguno o los dos intervalos de fechas no son válidos.
     */
    public void testIntervaloNoValido() {

        ControlFestivo cf = new ControlFestivo();

        Calendar fecha1 = Calendar.getInstance();
        fecha1.setTimeInMillis(0);
        fecha1.set(Calendar.YEAR, 2013);
        fecha1.set(Calendar.MONTH, Calendar.JANUARY);
        fecha1.set(Calendar.DATE, 4);
        Date date1 = fecha1.getTime();


        Calendar fecha2 = Calendar.getInstance();
        fecha2.setTimeInMillis(0);
        fecha2.set(Calendar.YEAR, 2013);
        fecha2.set(Calendar.MONTH, Calendar.JANUARY);
        fecha2.set(Calendar.DATE, 10);
        Date date2 = fecha2.getTime();

        Calendar fecha3 = Calendar.getInstance();
        fecha3.setTimeInMillis(0);
        fecha3.set(Calendar.YEAR, 2013);
        fecha3.set(Calendar.MONTH, Calendar.JANUARY);
        fecha3.set(Calendar.DATE, 15);
        Date date3 = fecha3.getTime();

        Calendar fecha4 = Calendar.getInstance();
        fecha4.setTimeInMillis(0);
        fecha4.set(Calendar.YEAR, 2013);
        fecha4.set(Calendar.MONTH, Calendar.JANUARY);
        fecha4.set(Calendar.DATE, 23);
        Date date4 = fecha4.getTime();

        //Prueba un intervalo válido y el otro inválido.

        ArrayList<Intervalo> intTest1 = new ArrayList<Intervalo>();

        Intervalo valido1 = new Intervalo(date1, date2);
        intTest1.add(valido1);

        Intervalo invalido1 = new Intervalo(date4, date3);
        intTest1.add(invalido1);

        assertEquals(null, cf.contarHoras(intTest1));

        //Prueba un intervalo válido y el otro inválido, en distinto orden.

        ArrayList<Intervalo> intTest2 = new ArrayList<Intervalo>();

        Intervalo invalido2 = new Intervalo(date2, date1);
        intTest2.add(invalido2);

        Intervalo valido2 = new Intervalo(date3, date4);
        intTest2.add(valido2);

        assertEquals(null, cf.contarHoras(intTest2));

        //Prueba ambos intervalos inválidos.

        ArrayList<Intervalo> intTest3 = new ArrayList<Intervalo>();

        Intervalo invalido3 = new Intervalo(date2, date1);
        intTest3.add(invalido3);

        Intervalo invalido4 = new Intervalo(date4, date3);
        intTest3.add(invalido4);

        assertEquals(null, cf.contarHoras(intTest3));

    }

    /**
     * Test que prueba el funcionamiento del método contarHoras cuando los dos
     * intervalos son iguales. Se prueba con dos intervalos y en cada uno se
     * incluyen festivos laborables, no laborables, sábados, sábados festivos
     * laborables, domingos festivos laborables y días comunes.
     *
     * @param festivos, arreglo en donde se almacenan los festivos declarados
     * para la prueba.
     */
    public void testIntervalosIguales() {

        List<Festivo> festivos = new ArrayList<Festivo>();

        //Festivos No Laborables
        Festivo festivoNoLab1 = produceFestivo(2013, Calendar.APRIL, 2, false);
        Festivo festivoNoLab2 = produceFestivo(2013, Calendar.APRIL, 3, false);
        Festivo festivoNoLab3 = produceFestivo(2013, Calendar.APRIL, 29, false);
        Festivo festivoNoLab4 = produceFestivo(2013, Calendar.APRIL, 30, false);
        festivos.add(festivoNoLab1);
        festivos.add(festivoNoLab2);
        festivos.add(festivoNoLab3);
        festivos.add(festivoNoLab4);

        //Festivos Laborables
        Festivo festivoLab1 = produceFestivo(2013, Calendar.APRIL, 9, true);
        Festivo festivoLab2 = produceFestivo(2013, Calendar.APRIL, 10, true);
        Festivo festivoLab3 = produceFestivo(2013, Calendar.APRIL, 16, true);
        Festivo festivoLab4 = produceFestivo(2013, Calendar.APRIL, 19, true);
        festivos.add(festivoLab1);
        festivos.add(festivoLab2);
        festivos.add(festivoLab3);
        festivos.add(festivoLab4);

        //Sábados Festivos No Laborables
        Festivo sabadoFestivoNoLab = produceFestivo(2013, Calendar.APRIL, 13, false);
        festivos.add(sabadoFestivoNoLab);

        //Sábados Festivos Laborables
        Festivo sabadoFestivoLab = produceFestivo(2013, Calendar.APRIL, 6, true);
        festivos.add(sabadoFestivoLab);

        //Domingos Festivos Laborables
        Festivo domingoFestivoLab1 = produceFestivo(2013, Calendar.APRIL, 7, true);
        Festivo domingoFestivoLab2 = produceFestivo(2013, Calendar.APRIL, 28, true);
        festivos.add(domingoFestivoLab1);
        festivos.add(domingoFestivoLab2);

        ControlFestivo cf = new ControlFestivo();
        cf.getSistema().setEntidades(festivos);

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);
        Calendar fechaC = Calendar.getInstance();
        fechaC.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.APRIL);
        fechaA.set(Calendar.DATE, 1);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.APRIL);
        fechaB.set(Calendar.DATE, 15);

        fechaC.set(Calendar.YEAR, 2013);
        fechaC.set(Calendar.MONTH, Calendar.APRIL);
        fechaC.set(Calendar.DATE, 30);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();
        Date fecha3 = fechaC.getTime();

        Intervalo intervalo1 = new Intervalo(fecha1, fecha2);
        Intervalo intervalo2 = intervalo1;

        ArrayList<Intervalo> listIntervalos1 = new ArrayList<Intervalo>();
        listIntervalos1.add(intervalo1);
        listIntervalos1.add(intervalo2);

        assertEquals(new Integer(234), cf.contarHoras(listIntervalos1));

        Intervalo intervalo3 = new Intervalo(fecha2, fecha3);
        Intervalo intervalo4 = intervalo3;

        ArrayList<Intervalo> listIntervalos2 = new ArrayList<Intervalo>();
        listIntervalos2.add(intervalo3);
        listIntervalos2.add(intervalo4);

        assertEquals(new Integer(240), cf.contarHoras(listIntervalos2));

    }

    /**
     * Test que prueba el funcionamiento del método contarHoras cuando se
     * ingresan dos intervalos entre los cuales existen fechas comunes.
     *
     * @param festivos, arreglo en donde se almacenan los festivos declarados
     * para la prueba.
     */
    public void testCruceDeFechas() {

        List<Festivo> festivos = new ArrayList<Festivo>();

        //Festivos No Laborables
        Festivo festivoNoLab1 = produceFestivo(2013, Calendar.MAY, 1, false);
        Festivo festivoNoLab2 = produceFestivo(2013, Calendar.MAY, 9, false);
        Festivo festivoNoLab3 = produceFestivo(2013, Calendar.MAY, 14, false);
        Festivo festivoNoLab4 = produceFestivo(2013, Calendar.MAY, 24, false);
        festivos.add(festivoNoLab1);
        festivos.add(festivoNoLab2);
        festivos.add(festivoNoLab3);
        festivos.add(festivoNoLab4);

        //Festivos Laborables
        Festivo festivoLab1 = produceFestivo(2013, Calendar.MAY, 3, true);
        Festivo festivoLab2 = produceFestivo(2013, Calendar.MAY, 7, true);
        Festivo festivoLab3 = produceFestivo(2013, Calendar.MAY, 17, true);
        Festivo festivoLab4 = produceFestivo(2013, Calendar.MAY, 22, true);
        festivos.add(festivoLab1);
        festivos.add(festivoLab2);
        festivos.add(festivoLab3);
        festivos.add(festivoLab4);

        //Sábados Festivos No Laborables
        Festivo sabadoFestivoNoLab = produceFestivo(2013, Calendar.MAY, 11, false);
        festivos.add(sabadoFestivoNoLab);

        //Sábados Festivos Laborables
        Festivo sabadoFestivoLab = produceFestivo(2013, Calendar.MAY, 25, true);
        festivos.add(sabadoFestivoLab);

        //Domingos Festivos Laborables
        Festivo domingoFestivoLab1 = produceFestivo(2013, Calendar.MAY, 12, true);
        Festivo domingoFestivoLab2 = produceFestivo(2013, Calendar.MAY, 26, true);
        festivos.add(domingoFestivoLab1);
        festivos.add(domingoFestivoLab2);

        ControlFestivo cf = new ControlFestivo();
        cf.getSistema().setEntidades(festivos);

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);
        Calendar fechaC = Calendar.getInstance();
        fechaC.setTimeInMillis(0);
        Calendar fechaD = Calendar.getInstance();
        fechaD.setTimeInMillis(0);
        Calendar fechaE = Calendar.getInstance();
        fechaE.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.MAY);
        fechaA.set(Calendar.DATE, 1);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.MAY);
        fechaB.set(Calendar.DATE, 10);

        fechaC.set(Calendar.YEAR, 2013);
        fechaC.set(Calendar.MONTH, Calendar.MAY);
        fechaC.set(Calendar.DATE, 22);

        fechaD.set(Calendar.YEAR, 2013);
        fechaD.set(Calendar.MONTH, Calendar.MAY);
        fechaD.set(Calendar.DATE, 30);

        fechaE.set(Calendar.YEAR, 2013);
        fechaE.set(Calendar.MONTH, Calendar.MAY);
        fechaE.set(Calendar.DATE, 11);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();
        Date fecha3 = fechaC.getTime();
        Date fecha4 = fechaD.getTime();
        Date fecha5 = fechaE.getTime();

        Intervalo intervalo1 = new Intervalo(fecha1, fecha3);
        Intervalo intervalo2 = new Intervalo(fecha2, fecha4);

        ArrayList<Intervalo> listIntervalos1 = new ArrayList<Intervalo>();
        listIntervalos1.add(intervalo1);
        listIntervalos1.add(intervalo2);

        assertEquals(new Integer(332), cf.contarHoras(listIntervalos1));

        Intervalo intervalo3 = new Intervalo(fecha2, fecha4);
        Intervalo intervalo4 = new Intervalo(fecha1, fecha3);

        ArrayList<Intervalo> listIntervalos2 = new ArrayList<Intervalo>();
        listIntervalos2.add(intervalo3);
        listIntervalos2.add(intervalo4);

        assertEquals(new Integer(332), cf.contarHoras(listIntervalos2));

        Intervalo intervalo5 = new Intervalo(fecha1, fecha4);
        Intervalo intervalo6 = new Intervalo(fecha2, fecha3);

        ArrayList<Intervalo> listIntervalos3 = new ArrayList<Intervalo>();
        listIntervalos3.add(intervalo5);
        listIntervalos3.add(intervalo6);

        assertEquals(new Integer(332), cf.contarHoras(listIntervalos3));

        Intervalo intervalo7 = new Intervalo(fecha2, fecha3);
        Intervalo intervalo8 = new Intervalo(fecha1, fecha4);

        ArrayList<Intervalo> listIntervalos4 = new ArrayList<Intervalo>();
        listIntervalos4.add(intervalo7);
        listIntervalos4.add(intervalo8);

        assertEquals(new Integer(332), cf.contarHoras(listIntervalos4));

        Intervalo intervalo9 = new Intervalo(fecha1, fecha2);
        Intervalo intervalo10 = new Intervalo(fecha2, fecha3);

        ArrayList<Intervalo> listIntervalos5 = new ArrayList<Intervalo>();
        listIntervalos5.add(intervalo9);
        listIntervalos5.add(intervalo10);

        assertEquals(new Integer(168), cf.contarHoras(listIntervalos5));

        Intervalo intervalo11 = new Intervalo(fecha3, fecha4);
        Intervalo intervalo12 = new Intervalo(fecha2, fecha3);

        ArrayList<Intervalo> listIntervalos6 = new ArrayList<Intervalo>();
        listIntervalos6.add(intervalo11);
        listIntervalos6.add(intervalo12);

        assertEquals(new Integer(188), cf.contarHoras(listIntervalos6));

        Intervalo intervalo13 = new Intervalo(fecha1, fecha2);
        Intervalo intervalo14 = new Intervalo(fecha5, fecha3);

        ArrayList<Intervalo> listIntervalos7 = new ArrayList<Intervalo>();
        listIntervalos7.add(intervalo13);
        listIntervalos7.add(intervalo14);

        assertEquals(new Integer(160), cf.contarHoras(listIntervalos7));

        Intervalo intervalo15 = new Intervalo(fecha5, fecha3);
        Intervalo intervalo16 = new Intervalo(fecha1, fecha2);

        ArrayList<Intervalo> listIntervalos8 = new ArrayList<Intervalo>();
        listIntervalos8.add(intervalo15);
        listIntervalos8.add(intervalo16);

        assertEquals(new Integer(160), cf.contarHoras(listIntervalos8));

    }

    public void testViernesDeQuincenaFestivoNoLaborable() {

        List<Festivo> festivos = new ArrayList<Festivo>();

        //Festivo No Laborable
        Festivo festivoNoLab = produceFestivo(2013, Calendar.JUNE, 14, false);
        festivos.add(festivoNoLab);

        ControlFestivo cf = new ControlFestivo();
        cf.getSistema().setEntidades(festivos);

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.JUNE);
        fechaA.set(Calendar.DATE, 10);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.JUNE);
        fechaB.set(Calendar.DATE, 17);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();

        assertEquals(new Integer(44), cf.contarHoras(fecha1, fecha2));
    }

    public void testViernesDeQuincenaFestivoLaborable() {

        List<Festivo> festivos = new ArrayList<Festivo>();

        //Festivo Laborable
        Festivo festivoLab = produceFestivo(2013, Calendar.JUNE, 28, true);
        festivos.add(festivoLab);

        ControlFestivo cf = new ControlFestivo();
        cf.getSistema().setEntidades(festivos);

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.JUNE);
        fechaA.set(Calendar.DATE, 26);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.JULY);
        fechaB.set(Calendar.DATE, 3);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();

        assertEquals(new Integer(60), cf.contarHoras(fecha1, fecha2));
    }

    public void testLimitesDeIntervaloViernesDeQuincena() {

        ControlFestivo cf = new ControlFestivo();

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);
        Calendar fechaC = Calendar.getInstance();
        fechaB.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.MAY);
        fechaA.set(Calendar.DATE, 31);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.JUNE);
        fechaB.set(Calendar.DATE, 12);

        fechaC.set(Calendar.YEAR, 2013);
        fechaC.set(Calendar.MONTH, Calendar.JUNE);
        fechaC.set(Calendar.DATE, 14);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();
        Date fecha3 = fechaC.getTime();

        assertEquals(new Integer(76), cf.contarHoras(fecha1, fecha2));
        assertEquals(new Integer(18), cf.contarHoras(fecha2, fecha3));
        assertEquals(new Integer(86), cf.contarHoras(fecha1, fecha3));
    }
    
    public void testViernesDeQuincenaMes28Dias() {

        ControlFestivo cf = new ControlFestivo();

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.FEBRUARY);
        fechaA.set(Calendar.DATE, 25);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.MARCH);
        fechaB.set(Calendar.DATE, 3);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();

        assertEquals(new Integer(38), cf.contarHoras(fecha1, fecha2));
    }
    
    public void testViernesDeQuincenaMes29Dias() {

        ControlFestivo cf = new ControlFestivo();

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2012);
        fechaA.set(Calendar.MONTH, Calendar.FEBRUARY);
        fechaA.set(Calendar.DATE, 26);

        fechaB.set(Calendar.YEAR, 2012);
        fechaB.set(Calendar.MONTH, Calendar.MARCH);
        fechaB.set(Calendar.DATE, 3);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();

        assertEquals(new Integer(38), cf.contarHoras(fecha1, fecha2));
    }
    
     public void testViernesDeQuincenaMes30Dias() {

        ControlFestivo cf = new ControlFestivo();

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.JUNE);
        fechaA.set(Calendar.DATE, 27);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.JULY);
        fechaB.set(Calendar.DATE, 3);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();

        assertEquals(new Integer(38), cf.contarHoras(fecha1, fecha2));
    }
    
     public void testViernesDeQuincenaMes31Dias() {

        ControlFestivo cf = new ControlFestivo();

        Calendar fechaA = Calendar.getInstance();
        fechaA.setTimeInMillis(0);
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTimeInMillis(0);

        fechaA.set(Calendar.YEAR, 2013);
        fechaA.set(Calendar.MONTH, Calendar.JULY);
        fechaA.set(Calendar.DATE, 28);

        fechaB.set(Calendar.YEAR, 2013);
        fechaB.set(Calendar.MONTH, Calendar.AUGUST);
        fechaB.set(Calendar.DATE, 3);

        Date fecha1 = fechaA.getTime();
        Date fecha2 = fechaB.getTime();

        assertEquals(new Integer(38), cf.contarHoras(fecha1, fecha2));
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
