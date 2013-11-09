/*
 * DateReader.java
 *
 * Created on 1. März 2004, 13:20
 */

package com.processive.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.processive.log.Log;

/**
 *
 * @author  Stephan Kesper
 */
public class DateReader
{
    
    /** Creates a new instance of DateReader */
    public DateReader()
    {
    }
    
    public static Date parseDate(String date,String format)
    {
        SimpleDateFormat sdf = (SimpleDateFormat)SimpleDateFormat.getDateInstance();
        
        if (format!=null) sdf.applyPattern(format);
        
        Date d = null;
        
        try
        {
            d = sdf.parse(date);
        }
        catch(ParseException pe)
        {
            Log.error("Could not parse date "+date,pe);
        }
        
        return d;
    }
    
    public static String formatDate(Date d,String format)
    {
        if (d==null) return "";
        
        SimpleDateFormat sdf = (SimpleDateFormat)SimpleDateFormat.getDateInstance();
        
        if (format!=null) sdf.applyPattern(format);
        
        return sdf.format(d);
    }
}
