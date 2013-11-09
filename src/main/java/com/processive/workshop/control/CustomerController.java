/*
 * CustomerController.java
 *
 * Created on 1. Juni 2004, 12:29
 */

package com.processive.workshop.control;

import com.processive.control.BaseController;

import javax.servlet.http.*;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import com.processive.log.Log;
import com.processive.error.ErrorHandler;
import com.processive.workshop.comparators.*;
import com.processive.workshop.model.hb.Address;
import com.processive.workshop.model.hb.Car;
import com.processive.workshop.model.hb.Customer;
import com.processive.workshop.persistence.PersistenceLayer;


/**
 * 
 * @author Stephan Kesper
 */
public class CustomerController extends BaseController {

	/** Creates a new instance of CustomerController */
	public CustomerController() {
	}

	private int interpretAsInt(String s) {
		if (s == null || s.equals(""))
			return -1;

		int i = -1;

		try {
			i = Integer.parseInt(s);
		} catch (Throwable t) {
			t.printStackTrace();
			i = -1;
		}

		return i;
	}

	private Date interpretAsDate(String s, String form) {
		SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat
				.getInstance();
		sdf.applyPattern(form);
		Date d = new Date(0);

		try {
			d = sdf.parse(s);
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return d;
	}

	public void storeCustomer(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int id = interpretAsInt(req.getParameter("ID"));
		Log.debug("ID Parameter : " + id);

		Customer cust = null;

		if (id < 0) {
			cust = new Customer();
			cust.setId(IdDispenser.nextId("Customer"));
		} else {
			try {
				cust = PersistenceLayer.getInstance().findById(Customer.class, id);
			} catch (Throwable t) {
				// TODO: What to do? Edited but not retrievable....
				t.printStackTrace();
				cust = new Customer();
			}
		}

		cust.setCompany(req.getParameter("company"));
		cust.setGender(interpretAsInt(req.getParameter("gender")));
		cust.setEmail(req.getParameter("email"));
		cust.setFax(req.getParameter("fax"));
		cust.setFirstName(req.getParameter("fname"));
		cust.setLastName(req.getParameter("lname"));
		cust.setMobilePhone(req.getParameter("mobilePhone"));
		cust.setPhone(req.getParameter("phone"));
		cust.setTitle(interpretAsInt(req.getParameter("title")));

		Log.debug("Trying to save customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		Log.debug("Given ID : " + cust.getId());

		try {
			PersistenceLayer.getInstance().save(cust);
			Log.trace("Saved customer with id " + cust.getId());
		} catch (Throwable t) {
			t.printStackTrace();

			erh.setLastError(
					"Das Speichern der Kunden Daten hat nicht geklappt...", t);

			return;
		}

		req.setAttribute("customer", cust);

		// Handle address !
		Address ad = new Address();

		try {
			ad.setCity(req.getParameter("city"));
			ad.setCountry(req.getParameter("country"));
			ad.setCustomerId(cust.getId());
			ad.setHouseNumber(req.getParameter("houseno"));
			ad.setStreet1(req.getParameter("street1"));
			ad.setStreet2(req.getParameter("street2"));
			ad.setZipCode(interpretAsInt(req.getParameter("zip")));

			PersistenceLayer.getInstance().save(ad);

		} catch (Throwable t) {
			t.printStackTrace();

			erh.setLastError(
					"Das Speichern der Adress Daten hat nicht geklappt...", t);

			return;
		}

	}

	public void updateCustomer(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int id = interpretAsInt(req.getParameter("customerID"));

		Customer cust = null;

		if (id < 0) {
			erh.setLastError("Kunden ID stimmt nicht!", null);
			return;
		} else {
			try {
				cust = PersistenceLayer.getInstance().findById(Customer.class, id);
			} catch (Throwable t) {
				erh.setLastError("Fehler beim Laden der Kundenstammdaten!", t);
				return;
			}
		}

		cust.setCompany(req.getParameter("company"));
		cust.setGender(interpretAsInt(req.getParameter("gender")));
		cust.setEmail(req.getParameter("email"));
		cust.setFax(req.getParameter("fax"));
		cust.setFirstName(req.getParameter("fname"));
		cust.setLastName(req.getParameter("lname"));
		cust.setMobilePhone(req.getParameter("mobilePhone"));
		cust.setPhone(req.getParameter("phone"));
		cust.setTitle(interpretAsInt(req.getParameter("title")));

		try {
			PersistenceLayer.getInstance().save(cust);
		} catch (Throwable t) {
			t.printStackTrace();

			erh.setLastError(
					"Das Speichern der Kunden Daten hat nicht geklappt...", t);

			return;
		}

		req.setAttribute("customer", cust);

	}

	public void storeCar(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());
		int id = interpretAsInt(req.getParameter("customerID"));
		int carId = interpretAsInt(req.getParameter("carID"));

		Customer cust = null;

		try {
			cust = PersistenceLayer.getInstance().findById(Customer.class, id);
		} catch (Throwable t) {
			// TODO: What to do? Edited but not retrievable....
			t.printStackTrace();

			erh.setLastError("Der Kunde mit der ID " + id
					+ " konnte nicht gefunden werden...", t);

			return;
		}

		if (cust == null) {
			Log.error("Could not find cutomer with id " + id);
			erh.setLastError("Der Kunde mit der ID " + id
					+ " konnte nicht gefunden werden...", null);
			return;
		}

		Car car = null;

		if (carId <= 0) {
			car = new Car();
			car.setId(IdDispenser.nextId("Car"));
		} else {
			try {
				car = PersistenceLayer.getInstance().findById(Car.class, carId);
			} catch (Throwable t) {
				erh
						.setLastError(
								"Fahrzeug konnte nicht in der Datenbank gefunden werden.",
								t);
				return;
			}
		}

		try {
			car.setAsuDate(interpretAsDate(req.getParameter("asu"), "MM/yyyy"));
			car.setBuildDate(interpretAsDate(req.getParameter("releasedate"),
					"dd.MM.yyyy"));
			car.setCustomerId(cust.getId());
			car.setDetails(req.getParameter("descr").getBytes("ISO-8859-1"));
			car.setVin(req.getParameter("vin"));
			
			String licNum = req.getParameter("lic1").trim()+" - "+req.getParameter("lic2").trim()+" "+req.getParameter("lic3").trim();
			licNum = licNum.toUpperCase();
			car.setLicenseNumber(licNum);
			car.setTuevDate(interpretAsDate(req.getParameter("tuv"),
							"MM/yyyy"));
			car.setType(req.getParameter("type"));
			car.setTypeCode(req.getParameter("typecode"));
			car.setVendor(req.getParameter("vendor"));
			car.setVendorCode(req.getParameter("vendorcode"));
			PersistenceLayer.getInstance().save(car);
		} catch (Throwable t) {
			t.printStackTrace();
			erh.setLastError(
					"Das Speicher der Fahrzeugdaten hat nicht geklappt...", t);
		}

		req.setAttribute("customer", cust);
	}

	public void deleteCar(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int id = interpretAsInt(req.getParameter("carID"));
		int customerId = interpretAsInt(req.getParameter("customerID"));

		if (id <= 0) {
			erh.setLastError("Fehler in der Fahrzeugbestimmung!", null);
			return;
		}

		try {
			Car car = PersistenceLayer.getInstance().findById(Car.class, id);

			PersistenceLayer.getInstance().delete(car);

			Customer customer = PersistenceLayer.getInstance().findById(Customer.class, customerId);
			req.setAttribute("customer", customer);
		} catch (Throwable t) {
			erh.setLastError("Fehler im Speichermodul...", t);
			return;
		}
	}

	public void showCar(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int id = interpretAsInt(req.getParameter("carID"));

		if (id < 0) {
			erh.setLastError("Fehler beim Laden der Fahrzeugdaten", null);
			return;
		}

		Car car = null;

		try {
			car = PersistenceLayer.getInstance().findById(Car.class, id);
			req.setAttribute("car", car);

			Customer cust = PersistenceLayer.getInstance().findById(Customer.class, car.getCustomerId());
			req.setAttribute("customer", cust);
		} catch (Throwable t) {
			erh.setLastError("Fehler beim Laden der Fahrzeugdaten", t);
		}
	}

	public void searchCustomer(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());
		String lname = req.getParameter("lname");
		String fname = req.getParameter("fname");

		if (lname==null || "".equals(lname)) {
			lname = "%";
		}
		
		if (fname==null || "".equals(fname)) {
			fname = "%";
		}
		

		if (lname != null && lname.indexOf("%") < 0)
			lname = lname + "%";
		if (fname != null && fname.indexOf("%") < 0)
			fname = fname + "%";

//		Criteria crit = new Criteria();
//
//		crit.add(CustomerPeer.LASTNAME, (Object) lname, Criteria.LIKE);
//
//		if (fname != null && !fname.equals("")) {
//			crit.and(CustomerPeer.FIRSTNAME, (Object) fname, Criteria.LIKE);
//		}

		Session session = PersistenceLayer.getInstance().openSession();
		Query query = session.createQuery("from Customer fetch all properties where lastName like :lname and firstName like :fname");
		query.setString("lname", lname);
		query.setString("fname", fname);
		
		try {
			List l = query.list();
			
			System.out.println("DEBUG: query for "+lname+" and "+fname+" resulted in "+l.size()+" hits.");

			Collections.sort(l, new CustomerComparator());

			req.setAttribute("searchlist", l);
		} catch (Throwable t) {
			t.printStackTrace();

			erh.setLastError("Es gab ein Problem beim Lesen der Daten...", t);
		} finally {
			if (session!=null) {
				PersistenceLayer.getInstance().closeSession(session);
			}
		}
	}

	public void searchCars(HttpServletRequest req, HttpServletResponse resp) {

		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		String licnum = req.getParameter("licnum");

		if (licnum != null && licnum.indexOf("%") < 0)
			licnum = licnum + "%";

//		Criteria crit = new Criteria();
//
//		crit.add(CarPeer.LICENSE_NUMBER, (Object) licnum, Criteria.LIKE);
		Session session = PersistenceLayer.getInstance().openSession();
		Query query = session.createQuery("from Car where licenseNumber like :licnum");
		query.setString("licnum", licnum);

		try {
			List l = query.list();

			Collections.sort(l, new CarLicenseComparator());
			
			req.setAttribute("searchlist", l);
		} catch (Throwable t) {
			t.printStackTrace();

			erh.setLastError("Es gab ein Problem beim Lesen der Daten...", t);
		} finally {
			if (session!=null) {
				PersistenceLayer.getInstance().closeSession(session);
			}
		}

	}
	
	public Customer findCustomer(int id) {
		
		Customer customer = PersistenceLayer.getInstance().findById(Customer.class, id);

		return customer;
	}

	public void storeAddress(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int id = interpretAsInt(req.getParameter("addressID"));
		int customerID = interpretAsInt(req.getParameter("customerID"));

		Address address = null;
		Customer customer = null;

		if (id <= 0) {
			address = new Address();
			address.setId(IdDispenser.nextId("Address"));
		} else {
			try {
				address = PersistenceLayer.getInstance().findById(Address.class,id);
			} catch (Throwable t) {
				erh.setLastError(
						"Konnte die Adresse nicht aus der Datenbank lesen...",
						t);
				return;
			}
		}

		address.setStreet1(req.getParameter("street1"));
		address.setStreet2(req.getParameter("street2"));
		address.setHouseNumber(req.getParameter("houseno"));
		address.setCountry(req.getParameter("country"));
		address.setZipCode(interpretAsInt(req.getParameter("zip")));
		address.setCity(req.getParameter("city"));

		try {
			customer = PersistenceLayer.getInstance().findById(Customer.class, customerID);
			address.setCustomerId(customerID);
			req.setAttribute("customer", customer);

			PersistenceLayer.getInstance().save(address);
		} catch (Throwable t) {
			erh.setLastError("Konnte die Adresse nicht speichern...", t);
			return;
		}
	}

	public void showAddress(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int id = interpretAsInt(req.getParameter("adid"));
		int customerID = interpretAsInt(req.getParameter("ID"));

		Address ad = null;
		Customer cust = null;

		try {
			cust = PersistenceLayer.getInstance().findById(Customer.class, customerID);
			req.setAttribute("customer", cust);

			if (id > 0) {
				ad = PersistenceLayer.getInstance().findById(Address.class, id);
				req.setAttribute("address", ad);
			}
		} catch (Throwable t) {
			erh.setLastError(
					"Konnte die Adresse nicht aus der Datenbank lesen...", t);
			return;
		}

		if (ad == null && id > 0) {
			erh
					.setLastError(
							"Konnte die Adresse nicht aus der Datenbank lesen...",
							null);
			return;
		}

		return;
	}

	public void showCustomer(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());
		Customer cust = null;

		int id = interpretAsInt(req.getParameter("ID"));

		if (id < 0) {
			cust = new Customer();
		} else {
			try {
				cust = PersistenceLayer.getInstance().findById(Customer.class, id);
			} catch (Throwable t) {
				// TODO: What to do? Edited but not retrievable....
				cust = new Customer();
				erh.setLastError("Der Kunde mit der ID " + id
						+ " konnte nicht gefunden werden...", t);
			}
		}

		req.setAttribute("customer", cust);
	}

	public List searchTuevCars() {
		
		PersistenceLayer pl = PersistenceLayer.getInstance();
		Session session = pl.openSession();
		
		Query query = session.createQuery("from Car where tuevDate is not null and tuevDate<:tuev");
		

		Date start = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.add(cal.MONTH, 2);
		Date end = cal.getTime();

		query.setDate("tuev", end);

		List l = null;

		Date nullDate = new Date(100000);

		try {
			l = query.list();
			Iterator it = l.iterator();
			while(it.hasNext()) {
				Car car = (Car)it.next();
				if (car.getAsuDate().before(nullDate)) {
					it.remove();
				}
			}
			Collections.sort(l, new CarTuevComparator());
		} catch (Throwable t) {
			Log.error("Error on searching TUV candidates...", t);
			l = new ArrayList();
		} finally {
			PersistenceLayer.getInstance().closeSession(session);
		}

		return l;
	}

	public List searchAsuCars() {
		PersistenceLayer pl = PersistenceLayer.getInstance();
		Session session = pl.openSession();
		
		Query query = session.createQuery("from Car where asuDate is not null and asuDate<:asu");
		

		Date start = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.add(cal.MONTH, 2);
		Date end = cal.getTime();

		query.setDate("asu", end);

		List l = null;

		Date nullDate = new Date(100000);
		
		try {
			l = query.list();
			Iterator it = l.iterator();
			while(it.hasNext()) {
				Car car = (Car)it.next();
				if (car.getAsuDate().before(nullDate)) {
					it.remove();
				}
			}

			Collections.sort(l, new CarAsuComparator());
		} catch (Throwable t) {
			Log.error("Error on searching ASU candidates...", t);
			l = new ArrayList();
		} finally {
			PersistenceLayer.getInstance().closeSession(session);
		}

		return l;
	}

	public static class CustomerComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			Customer c1 = (Customer) o1;
			Customer c2 = (Customer) o2;

			if (c1 == null || c2 == null)
				return 0;

			int cmp = 0;

			if (c1.getLastName() != null && c2.getLastName() != null)
				cmp = c1.getLastName().compareTo(c2.getLastName());

			if (cmp != 0)
				return cmp;

			if (c1.getFirstName() != null && c2.getFirstName() != null)
				cmp = c1.getFirstName().compareTo(c2.getFirstName());

			return cmp;
		}

	}

	public void forward(HttpServletRequest req, HttpServletResponse resp,
			String furl) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());
		String url = null;

		if (furl == null)
			url = req.getParameter("forwardUrl");
		else
			url = furl;

		if (url == null || url.equals(""))
			return;

		try {
			resp.sendRedirect(url);
		} catch (Throwable t) {
			t.printStackTrace();
			erh
					.setLastError(
							"Es gab ein Problem bei der automatischen Weiterleitung...",
							t);
		}
	}
}
