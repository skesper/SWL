package com.processive.workshop.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.processive.workshop.model.hb.Car;
import com.processive.workshop.model.hb.Customer;
import com.processive.workshop.persistence.PersistenceLayer;

@SuppressWarnings("unchecked")
public class CarFinder {

	public List findCarsByCustomer(Customer customer) {
		Session session = PersistenceLayer.getInstance().openSession();
		List result = new ArrayList();
		try {
			Query q = session.createQuery("from Car where customerId=:id");
			q.setInteger("id", customer.getId());
			
			Iterator it = q.iterate();
			
			while(it.hasNext()) {
				Car item = (Car) it.next();
				Hibernate.initialize(item);
				result.add(item);
			}
			
			Hibernate.close(it);
		} finally {
			PersistenceLayer.getInstance().closeSession(session);
		}
		
		return result;

	}
}
