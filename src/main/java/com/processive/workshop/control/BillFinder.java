package com.processive.workshop.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.processive.workshop.model.hb.Bill;
import com.processive.workshop.model.hb.WorkOrder;
import com.processive.workshop.persistence.PersistenceLayer;

@SuppressWarnings("unchecked")
public class BillFinder {

	public List findBillsByWorkOrder(WorkOrder wo) {
		Session session = PersistenceLayer.getInstance().openSession();
		List result = new ArrayList();
		try {
			Query q = session.createQuery("from Bill where workorderId=:id");
			q.setInteger("id", wo.getId());
			
			Iterator it = q.iterate();
			
			while(it.hasNext()) {
				Bill item = (Bill) it.next();
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
