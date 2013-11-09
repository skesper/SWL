package com.processive.workshop.control;

import java.math.BigInteger;

import org.hibernate.Query;
import org.hibernate.Session;

import com.processive.workshop.persistence.PersistenceLayer;

public class IdDispenser {

	public static synchronized int nextId(String type) {
		PersistenceLayer pl = PersistenceLayer.getInstance();
		
		Session session = pl.openSession();
		try {
			Query query = session.createQuery("select max(id) from "+type);
			
			Object o = query.uniqueResult();
			
			if (o==null) throw new RuntimeException("Select for max ID in "+type+" resulted null.");
			
			if (o instanceof Integer) {
				return ((Integer)o).intValue()+1;
			}
			if (o instanceof BigInteger) {
				return ((BigInteger)o).intValue()+1;
			}
			
			throw new RuntimeException("Unknown ID result ("+o.toString()+") for type "+type);
		} finally {
			pl.closeSession(session);
		}
	}
}
