/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.processive.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

import com.processive.workshop.model.hb.Car;
import com.processive.workshop.persistence.PersistenceLayer;

/**
 *
 * @author me
 */
public class Test {

    public static void main(String[] args) throws Exception {
        
    	PersistenceLayer pl = PersistenceLayer.getInstance();
        
    	Session sess = pl.openSession();
    	
        Query query = sess.createQuery("from Car");
        
        Iterator it = query.iterate();
        while(it.hasNext()) {
            System.out.println("Result: "+((Car)it.next()).getLicenseNumber());
        }
        
        pl.closeSession(sess);
        
        
    }
}
