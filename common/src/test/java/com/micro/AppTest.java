package com.micro;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) throws ParseException {
        compareTwoDates("Mon Feb 06 00:00:00 GMT+08:00 2023");
        compareTwoDates("Sun Jan 08 00:00:00 GMT+08:00 2023");
    }

    public static void compareTwoDates(String time) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(sf.parse(time)));
    }

    public static Calendar parseCalendarDayFormat( String strDate ) {
        if( strDate == null || strDate.trim().length() == 0 ) {
            return null;
        }
        String pattern = "yyyy-MM-dd";
        return parseCalendarFormat( strDate, pattern );
    }

    public static Calendar parseCalendarFormat( String strDate, String pattern ) {
        Calendar cal = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern( pattern );
        try {
            sdf.parse( strDate );
            cal = sdf.getCalendar();
        }
        catch ( Exception e ) { }
        return cal;
    }
}
