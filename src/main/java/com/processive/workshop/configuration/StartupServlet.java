/*
 * StartupServlet.java
 *
 * Created on 28. September 2003, 17:48
 */

package com.processive.workshop.configuration;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import com.processive.workshop.persistence.PersistenceLayer;

import java.io.*;
import java.net.URL;


/**
 *
 * @author  Administrator
 */
public class StartupServlet extends HttpServlet
{
    
    /** Creates a new instance of StartupServlet */
    public StartupServlet()
    {
    }
    
    public void init() 
        throws ServletException
    {
    	// That should initiate all initializations.
    	PersistenceLayer.getInstance();
    }
    
}
