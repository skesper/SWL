/*
 * ErrorHandler.java
 *
 * Created on 4. Juni 2004, 11:52
 */

package com.processive.error;

import com.processive.log.Log;

import javax.servlet.http.HttpSession;
import java.io.*;

/**
 *
 * @author  Stephan Kesper
 */
public class ErrorHandler
{
    private String lastError = null;
    private Throwable lastException = null;
    
    /** Creates a new instance of ErrorHandler */
    private ErrorHandler()
    {
    }
    
    public String getLastError()
    {
        return lastError;
    }
    
    public Throwable getLastException()
    {
        return lastException;
    }
    
    public String getLastExceptionAsString()
    {
        if (lastException==null) 
        {
            System.out.println("lastException is null...");
            return "";
        }
        
        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            
            lastException.printStackTrace(pw);
            
            pw.flush();
            pw.close();
            sw.flush();
            return sw.toString();
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
        return "";
    }
    
    
    public void setLastError(String error,Throwable t)
    {
        Log.trace("overwriting last Error. Old message: "+lastError);
        lastError = error;
        lastException = t;
    }
    
    public void clear()
    {
        Log.trace("cleaning last error.");
        lastError = null;
    }
    
    public static ErrorHandler retrieve(HttpSession session)
    {
        if (session==null) return null;
        
        Object o = session.getAttribute("PRC_SWL_ERROR_HANDLER");
        
        if (o==null)
        {
            ErrorHandler eh = new ErrorHandler();
            session.setAttribute("PRC_SWL_ERROR_HANDLER",eh);
            return eh;
        }
        
        return (ErrorHandler)o;
    }
}
