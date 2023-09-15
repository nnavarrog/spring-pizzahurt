/**
 * NO LICENCE
 * 
 * Proyecto obligatorio final.
 * Curso: Desarrollo de aplicaciones con Spring / Spring Boot
 * Universidad ORT 
 * Agosto 2023 - Octubre 2023
 * 
 * Docente: Juan Larrayoz
 * 
 * Authors: 
 *      Fourment, Juan
 *      Navarro Gutérrez, Nicolás
 *      Ortuzar, Martín
 */
package uy.edu.ort.obligatorio.pizzahurt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Clase con funciones utilitarias para la creación y manejo de fechas (Date) y
 * tiempos.
 * <br>
 * Los formatos de fecha / hora según el standar son los siguientes:
 * <ul>
 *      <li>yy | yyyy : Año de 2 o 4 dígitos</li>
 *      <li>MM : Número del mes, siendo 01 enero y 12 diciembre</li>
 *      <li>dd : Número correspondiente al día del mes</li>
 *      <li>HH : Hora del día en formato 24h</li>
 *      <li>mm : Minutos dentro de una hora (de 0 a 59)</li>
 * <ul>
 * 
 * @author nnavarro
 */
public final class DateUtils
{

    public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static final String TIME_FORMAT_HHMM = "HH:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
     *
     * @param date
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date)
    {
        XMLGregorianCalendar xmlCalendar = null;
        if (date != null)
        {
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(date);

            try
            {
                xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            }
            catch (DatatypeConfigurationException ex)
            {
                return null;
            }
        }
        return xmlCalendar;
    }

    /**
     * Converts XMLGregorianCalendar to java.util.Date in Java
     *
     * @param calendar
     * @return Date
     */
    public static Date toDate(XMLGregorianCalendar calendar)
    {
        if (calendar == null)
        {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    /**
     * Devuelve una nueva instancia de java.util.Date a partir de un String con
     * la fecha (sDate) con su respectivo formato (format).
     * <br>
     * Por ejemplo, d = getNewDateFromStr("30/01/1998", "dd/MM/yyyy")
     *
     * @param sDate
     * @param format
     * @return
     */
    public static Date getNewDateFromStr(String sDate, String format)
    {
        try
        {
            Date date = (new SimpleDateFormat(format)).parse(sDate);
            return date;
        }
        catch (ParseException ex)
        {
            return null;
        }
    }

    /**
     * Retorna un objeto Date con el valor de la fecha indicada en paràmetro
     * sDate.
     * <br><br>
     * El formato de fecha del parámetro puede ser dd/MM/yyyy o yyyy-MM-dd
     *
     * @param sDate
     * @return Date
     */
    public static Date getNewDateFromStr(String sDate)
    {
        Date date = getNewDateFromStr(sDate, DATE_FORMAT_DDMMYYYY);
        if (date == null)
        {
            date = getNewDateFromStr(sDate, DATE_FORMAT_YYYYMMDD);
        }
        return date;
    }

    /**
     * Retorna un String en el formato "yyyy-MM-dd" de la fecha actual;
     * @return
     */
    public static String getFormatedDateNow()
    {
        return getFormatedDate(new Date());
    }

    /**
     * Retorna un String a partir de una instancia de java.Util.Date (date)
     * en el formato "yyyy-MM-dd";
     * @param date
     * @return
     */
    public static String getFormatedDate(Date date)
    {
        return getFormatedDateTime(date, DATE_FORMAT_YYYYMMDD);
    }

    /**
     * Retorna un String a partir de una instancia de java.Util.Date (date)
     * en el formato indicado por parámetro;
     * @param date
     * @param format
     * @return
     */
    public static String getFormatedDateTime(Date date, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Devuelve un String con la fecha/hora en formato HH:mm a partir de una 
     * instancia de un objeto de tipo java.Util.Date.
     * @param date
     * @return
     */
    public static String getFormatedTime(Date date)
    {
        return getFormatedDateTime(date, TIME_FORMAT_HHMM);
    }

    /**
     * Devuelve un String con la fecha/hora actual en formato pasado por parámetro
     * @param format
     * @return
     */
    public static String getFormatedTimeNow(String format)
    {
        return getFormatedDateTime(new Date(), format);
    }

    /**
     * Devuelve un String con la fecha/hora actual en formato "HH:mm"
     * @return
     */
    public static String getFormatedTimeNow()
    {
        return getFormatedTime(new Date());
    }

    /**
     * Devuelve un string con formato yyyy-MM-dd HH:mm:ss del timestamp actual
     * @return
     */
    public static String getFormatedDateTimeNow()
    {
        return getFormatedDateTime(new Date(), DATE_TIME_FORMAT);
    }
}
