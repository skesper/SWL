package com.processive.workshop.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.processive.workshop.model.hb.WorkItem;
import com.processive.workshop.model.hb.WorkOrder;
import com.processive.workshop.persistence.PersistenceLayer;

@SuppressWarnings("unchecked")
public class WorkItemFinder {

	public List findWorkItemsForWorkOrder(WorkOrder wo) {
		return findWorkItemsForWorkOrderId(wo.getId());
	}

	public List findWorkItemsForWorkOrderId(int id) {
		
		Session session = PersistenceLayer.getInstance().openSession();
		List result = new ArrayList();
		try {
			Query q = session.createQuery("from WorkItem where workOrderId=:id");
			q.setInteger("id", id);
			
			Iterator it = q.iterate();
			
			while(it.hasNext()) {
				WorkItem item = (WorkItem) it.next();
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
