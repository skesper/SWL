package com.processive.workshop.persistence;

import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.processive.workshop.model.hb.Car;


public class PersistenceLayer {

	private static PersistenceLayer me = null;
	private SessionFactory factory;
	
	public static synchronized PersistenceLayer getInstance() {
		if (me==null) {
			me = new PersistenceLayer();
		}
		return me;
	}
	
	private PersistenceLayer() {
		AnnotationConfiguration ac = new AnnotationConfiguration();
		
		ac.addAnnotatedClass(com.processive.workshop.model.hb.Address.class);
		ac.addAnnotatedClass(com.processive.workshop.model.hb.Bill.class);
		ac.addAnnotatedClass(com.processive.workshop.model.hb.Car.class);
		ac.addAnnotatedClass(com.processive.workshop.model.hb.Customer.class);
		ac.addAnnotatedClass(com.processive.workshop.model.hb.WorkItem.class);
		ac.addAnnotatedClass(com.processive.workshop.model.hb.WorkOrder.class);
		
		ac.configure();
		
		factory = ac.buildSessionFactory();
		
	}
	
	public Query createQuery(Session session, String sql) {
		Query q = session.createQuery(sql);
		return q;
	}
	
	
	public Session openSession() {
		return this.factory.openSession();
	}
	
	public void closeSession(Session session) {
		session.close();
	}
	
	public void close() {
		this.factory.close();
	}
	
	public void save(Object o) {
		Session s = this.openSession();
		Transaction trans = s.beginTransaction();
		try {
			s.saveOrUpdate(o);
			trans.commit();
		} catch(HibernateException e) {
			trans.rollback();
			throw e;
		} finally {
			this.closeSession(s);
		}
	}

	public void delete(Object o) {
		Session s = this.openSession();
		Transaction trans = s.beginTransaction();
		try {
			s.delete(o);
			trans.commit();
		} catch(HibernateException e) {
			trans.rollback();
			throw e;
		} finally {
			this.closeSession(s);
		}
	}
	

	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> clazz, int id) {
		
		Session s = openSession();
		
		T result = (T) s.get(clazz, id);
		
		Hibernate.initialize(result);
		
		this.closeSession(s);
		
		return result;
	}
	
	public static void main(String[] args) {
		PersistenceLayer pl = PersistenceLayer.getInstance();
		Session session = pl.factory.openSession();
		
		Query query = pl.createQuery(session, "from Car");
		
		Iterator it = query.iterate();
		
		while(it.hasNext()) {
			Car car = (Car)it.next();
			
			System.out.println(car.getLicenseNumber());
		}
		
		Hibernate.close(it);
		
		session.close();
	}
}
