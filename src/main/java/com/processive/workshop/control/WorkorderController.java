/*
 * WorkorderController.java
 *
 * Created on 4. Juni 2004, 11:50
 */

package com.processive.workshop.control;

import com.processive.control.BaseController;
import com.processive.log.Log;
import com.processive.workshop.model.hb.Customer;
import com.processive.workshop.model.hb.WorkItem;
import com.processive.workshop.model.hb.WorkOrder;
import com.processive.workshop.persistence.PersistenceLayer;
import com.processive.error.ErrorHandler;

import javax.servlet.http.*;

import java.util.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Stephan Kesper
 */
public class WorkorderController extends BaseController {

	/** Creates a new instance of WorkorderController */
	public WorkorderController() {
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

	private float interpretAsFloat(String s) {
		if (s == null || s.equals(""))
			return -1;

		float i = -1;

		try {
			if (s.indexOf(",") >= 0) {
				s = s.replaceAll(",", ".");
			}

			i = Float.parseFloat(s);
		} catch (Throwable t) {
			t.printStackTrace();
			i = 0.F;
		}

		return i;
	}

	private Date interpretAsDate(String s, String form) {
		SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat
				.getInstance();
		sdf.applyPattern(form);
		Date d = null;

		try {
			d = sdf.parse(s);
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return d;
	}

	public void storeWorkOrder(HttpServletRequest req,HttpServletResponse resp)
    {
        ErrorHandler erh = ErrorHandler.retrieve(req.getSession());
        
        int id = interpretAsInt(req.getParameter("ID"));
        
        WorkOrder wo = null;
        
        if (id<=0)
        {
            wo = new WorkOrder();
            wo.setId(IdDispenser.nextId("WorkOrder"));
        }
        else
        {
            try
            {
                wo = PersistenceLayer.getInstance().findById(WorkOrder.class, id);
            }
            catch(Throwable t)
            {
                t.printStackTrace();
                erh.setLastError("Konnte nicht den Auftrag mit der ID "+id+" lesen.",t);
                return;
            }
        }
        
        try
        {
            wo.setCarId(interpretAsInt(req.getParameter("carId")));
            wo.setCustomerId(interpretAsInt(req.getParameter("customerID")));
            wo.setDescription(req.getParameter("descr").getBytes("ISO-8859-1"));
            wo.setDueDate(interpretAsDate(req.getParameter("duedate"),"dd.MM.yyyy"));
            wo.setOfferPrice(req.getParameter("offer"));
            wo.setOrderDate(interpretAsDate(req.getParameter("orderdate"),"dd.MM.yyyy"));
            wo.setKmState(req.getParameter("kmstand"));
            
            PersistenceLayer.getInstance().save(wo);
            
            req.setAttribute("workorder",wo);
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            erh.setLastError("Konnte den Auftrag nicht speichern.",t);
        }
    }

	public void storeWorkItem(HttpServletRequest req, HttpServletResponse resp) {
		ErrorHandler erh = ErrorHandler.retrieve(req.getSession());

		int id = interpretAsInt(req.getParameter("workorderID"));

		WorkItem wi = new WorkItem();
		wi.setId(IdDispenser.nextId("WorkItem"));

		try {
			wi.setDescription(req.getParameter("descr").getBytes(Charset.forName("ISO-8859-1")));
			wi.setAmount(interpretAsFloat(req.getParameter("amount")));
			wi.setPrice(interpretAsFloat(req.getParameter("price")));
			wi.setVat(interpretAsFloat(req.getParameter("vat")));
			wi.setWorkOrderId(id);

			PersistenceLayer.getInstance().save(wi);

			WorkOrder wo = PersistenceLayer.getInstance().findById(WorkOrder.class, wi.getWorkOrderId());
			req.setAttribute("workorder", wo);
		} catch (Throwable t) {
			t.printStackTrace();
			erh.setLastError("Konnte den Einzelposten nicht speichern.", t);
		}
	}

	public void showWorkorder(HttpServletRequest req, HttpServletResponse resp) {
		int workorderID = interpretAsInt(req.getParameter("workorderID"));

		if (workorderID < 0) {
			ErrorHandler.retrieve(req.getSession()).setLastError(
					"Auftrag konnte nicht geladen werden...", null);
			return;
		}

		try {
			WorkOrder wo = PersistenceLayer.getInstance().findById(WorkOrder.class, workorderID);
			Customer cust = PersistenceLayer.getInstance().findById(Customer.class, wo.getCustomerId());

			req.setAttribute("workorder", wo);
			req.setAttribute("customer", cust);
		} catch (Throwable t) {
			ErrorHandler.retrieve(req.getSession()).setLastError(
					"Auftrag konnte nicht geladen werden...", t);
			return;
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

	public void editWorkItems(HttpServletRequest req, HttpServletResponse resp) {
		int workorderID = interpretAsInt(req.getParameter("workorderID"));

		if (workorderID < 0) {
			ErrorHandler.retrieve(req.getSession()).setLastError(
					"Auftrag konnte nicht geladen werden...", null);
			return;
		}

		try {
			WorkOrder wo = PersistenceLayer.getInstance().findById(WorkOrder.class, workorderID);

			req.setAttribute("workorder", wo);
		} catch (Throwable t) {
			ErrorHandler.retrieve(req.getSession()).setLastError(
					"Auftrag konnte nicht geladen werden...", t);
			return;
		}
	}

	public void deleteWorkItem(HttpServletRequest req, HttpServletResponse resp) {
		int workorderID = interpretAsInt(req.getParameter("workorderID"));
		int workitemID = interpretAsInt(req.getParameter("workitemID"));

		try {
			Log.trace("Trying to retrieve work item with id " + workitemID);

			WorkItem wi = PersistenceLayer.getInstance().findById(WorkItem.class, workitemID);

			PersistenceLayer.getInstance().delete(wi);
			
		} catch (Throwable t) {
			ErrorHandler.retrieve(req.getSession()).setLastError(
					"Eintrag konnte nicht gelöscht werden...", t);
		}

		try {
			WorkOrder wo = PersistenceLayer.getInstance().findById(WorkOrder.class, workorderID);

			req.setAttribute("workorder", wo);
		} catch (Throwable t) {
			ErrorHandler.retrieve(req.getSession()).setLastError(
					"Auftrag konnte nicht geladen werden...", t);
		}
	}

}
