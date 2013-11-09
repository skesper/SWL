package com.processive.workshop.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.processive.workshop.model.hb.Customer;
import com.processive.workshop.model.hb.WorkOrder;
import com.processive.workshop.persistence.PersistenceLayer;

public class WorkOrderFinder {
	public List findWorkOrdersByCustomer(Customer customer) {
		Session session = PersistenceLayer.getInstance().openSession();
		List result = new ArrayList();
		try {
			Query q = session.createQuery("from WorkOrder where customerId=:id");
			q.setInteger("id", customer.getId());
			
			Iterator it = q.iterate();
			
			while(it.hasNext()) {
				WorkOrder item = (WorkOrder) it.next();
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
