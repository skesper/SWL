package com.processive.workshop.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.processive.workshop.model.hb.Address;
import com.processive.workshop.model.hb.Customer;
import com.processive.workshop.persistence.PersistenceLayer;

@SuppressWarnings("unchecked")
public class AddressFinder {

	public List findAddressByCustomer(Customer customer) {
		Session session = PersistenceLayer.getInstance().openSession();
		List result = new ArrayList();
		try {
			Query q = session.createQuery("from Address where customerId=:id");
			q.setInteger("id", customer.getId());
			
			Iterator it = q.iterate();
			
			while(it.hasNext()) {
				Address item = (Address) it.next();
				Hibernate.initialize(item);
				result.add(item);
			}
			
			Hibernate.close(it);
		} finally {
			PersistenceLayer.getInstance().closeSession(session);
		}
		
		return result;
		
	}
	
	public Address findUniqueAddressByCustomer(Customer customer) {
		Session session = PersistenceLayer.getInstance().openSession();
		Address result = null;
		try {
			Query q = session.createQuery("from Address where customerId=:id");
			q.setInteger("id", customer.getId());
			
			List l = q.list();
			if (l.size()>0) {
				result = (Address) l.get(0);
			}
			
		} finally {
			PersistenceLayer.getInstance().closeSession(session);
		}
		
		return result;
		
	}
}
