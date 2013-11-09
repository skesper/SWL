/*
 * HtmlFormatter.java
 *
 * Created on 3. Juni 2004, 11:52
 */

package com.processive.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

import javax.swing.text.NumberFormatter;

/**
 *
 * @author  Stephan Kesper
 */
public class HtmlFormatter
{
    private static SimpleDateFormat sdf = (SimpleDateFormat)SimpleDateFormat.getDateInstance();
    
    /** Creates a new instance of HtmlFormatter */
    public HtmlFormatter()
    {
    }
    
    public static String formatString(String s)
    {
        if (s==null) return "k./A.";
        
        return s;
    }
    
    public static String formatEmail(String s)
    {
        if (s==null) return "k./A.";
        
        return "<a href=\"mailto:"+s+"\">"+s+"</a>";
    }
    
    public static String formatFloat(float f)
    {
        return formatFloat((double)f);
    }
    
    private static NumberFormat numf = NumberFormat.getInstance(Locale.getDefault());
    
    public static String formatFloat(double d)
    {
        numf.setMaximumFractionDigits(2);
        numf.setMinimumFractionDigits(2);
        
        return numf.format(d);
    }
    
    public static String formatCount(double d)
    {
        int n = (int)d;
        
        if (d-n > 0D)
        {
            return formatFloat(d);
        }
        
        return Integer.toString(n);
    }
    
    public static String formatEuro(double d)
    {
        return formatFloat(d)+"&nbsp;&euro;";
    }
    
    public static String formatDateNow(String form)
    {
        HtmlFormatter.sdf.applyPattern(form);
        
        return sdf.format(new Date());
    }
    
    public static String formatDate(String form,Date d)
    {
        if (d==null) return "k./A.";
        
        HtmlFormatter.sdf.applyPattern(form);
        
        return sdf.format(d);
    }
    
    public static String formatKmState(String km) {
    	if (km==null) return "k./A.";
    	
    	int kmi = 0;
    	
    	try {
    		kmi = Integer.parseInt(km);
    	
	    	if (kmi<0) return km;
	    	
	    	NumberFormat form = NumberFormat.getIntegerInstance();
	    	form.setParseIntegerOnly(true);
	    	form.setMaximumFractionDigits(0);
	    	form.setGroupingUsed(true);
	    	NumberFormatter nf = new NumberFormatter(form);
    	
			return nf.valueToString(kmi)+" km";
		} catch (Exception e) {
			return km+" km";
		}
    }
}
