/*
 * Log.java
 *
 * Created on 17. August 2003, 18:00
 */

package com.processive.log;


import java.util.Date;

/**
 *
 * @author  Administrator
 */
public class Log
{
    
    /** Creates a new instance of Log */
    public Log()
    {
    }
    
    public static void debug(String msg)
    {
        trace("debug",msg);
    }
    
    public static void warn(String msg)
    {
        trace("warn",msg);
    }
    
    public static void error(String msg)
    {
        trace("error",msg);
    }
    
    public static void fatal(String msg)
    {
        trace("fatal",msg);
    }
    
    public static void trace(String msg)
    {
        trace("trace",msg);
    }
    
    public static void debug(String msg,Throwable t)
    {
        trace("debug",msg,t);
    }
    
    public static void warn(String msg,Throwable t)
    {
        trace("warn",msg,t);
    }
    
    public static void error(String msg,Throwable t)
    {
        trace("error",msg,t);
    }
    
    public static void fatal(String msg,Throwable t)
    {
        trace("fatal",msg,t);
    }
    
    public static void trace(String msg,Throwable t)
    {
        trace("trace",msg,t);
    }

    private static void trace(String type,String msg)
    {
        Date now = new Date();
        
        System.out.println(now.toString()+" ["+type+"] :"+msg);
    }
    
    private static void trace(String type,String msg,Throwable t)
    {
        Date now = new Date();
        
        System.out.println(now.toString()+" ["+type+"] :"+msg);
        t.printStackTrace();
    }
}
