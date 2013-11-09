/*
 * BaseController.java
 *
 * Created on 17. August 2003, 20:54
 */

package com.processive.control;

import javax.servlet.http.*;
import java.lang.reflect.Method;

import com.processive.log.Log;

/**
 *
 * @author  Administrator
 */
public class BaseController
{
    
    /** Creates a new instance of BaseController */
    public BaseController()
    {
    }
    
    public void action(HttpServletRequest req,HttpServletResponse resp)
    {
        String action = req.getParameter("action");
        
        if (action==null || action.equals(""))
        {
            Log.error("Action parameter was empty!");
            return;
        }
        
        Class[] params = {HttpServletRequest.class,HttpServletResponse.class};
        
        try
        {
            Method meth = this.getClass().getMethod(action,params);

            Object[] args = {req,resp};

            meth.invoke(this, args);
        }
        catch(Throwable t)
        {
            Log.error("Invocation error in action handler. Method: "+action,t);
        }
    }
}
