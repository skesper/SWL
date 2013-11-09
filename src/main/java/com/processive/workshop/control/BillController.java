/*
 * BillController.java
 *
 * Created on 10. Juni 2004, 13:49
 */

package com.processive.workshop.control;

import com.processive.control.BaseController;
import com.processive.error.ErrorHandler;
import com.processive.workshop.model.hb.Bill;
import com.processive.workshop.model.hb.WorkOrder;
import com.processive.workshop.persistence.PersistenceLayer;

import javax.servlet.http.*;

import org.hibernate.Query;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.*;

/**
 * 
 * @author Stephan Kesper
 */
public class BillController extends BaseController {

	/** Creates a new instance of BillController */
	public BillController() {
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

	public void createBill(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int workorderID = interpretAsInt(req.getParameter("workorderID"));

		if (workorderID < 0) {
			erh.setLastError("Habe Auftrag nicht gefunden...", null);
			return;
		}

		try {
			PersistenceLayer pl = PersistenceLayer.getInstance();

//			WorkOrder wo = pl.findById(WorkOrder.class, workorderID);

			Session session = pl.openSession();
			Query query = session.createQuery("from Bill where workorderId=:id");
			query.setInteger("id", workorderID);

			List l = query.list();

			if (l.size() > 0) {
				Bill bill = (Bill) l.get(0);
				req.setAttribute("bill", bill);
				return;
			}

			int max = getBillNumber();

			if (max < 0) {
				erh.setLastError(
								"Es gab einen Fehler bei der Erzeugung der Rechnungsnummer.",
								null);
				return;
			}

			Bill bill = new Bill();

			bill.setId(IdDispenser.nextId("Bill"));
			bill.setBillDate(new Date());
			bill.setBillStatus(0);
			bill.setNumber(max + 1);
			bill.setWorkorderId(workorderID);

			pl.save(bill);
			
			System.out.println("DEBUG: Created bill with id "+bill.getId());

			req.setAttribute("bill", bill);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private synchronized int getBillNumber() throws Throwable {
		PersistenceLayer pl = PersistenceLayer.getInstance();
		Session session = pl.openSession();

		Query q = pl.createQuery(session, "select max(number) from Bill");

		Object o = q.uniqueResult();

		if (o == null)
			return -1;

		if (o instanceof Integer) {
			return ((Integer) o).intValue();
		} else if (o instanceof BigInteger) {
			return ((BigInteger) o).intValue();
		} else {
			throw new RuntimeException("Unexpected return value: "
					+ o.toString());
		}
	}
}
