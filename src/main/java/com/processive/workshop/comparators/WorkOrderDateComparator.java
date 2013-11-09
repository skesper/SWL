/*
 * WorkOrderDateComparator.java
 *
 * Created on 7. Juni 2004, 10:03
 */

package com.processive.workshop.comparators;


import java.util.Comparator;

import com.processive.workshop.model.hb.WorkOrder;

/**
 *
 * @author  Stephan Kesper
 */
public class WorkOrderDateComparator implements Comparator
{
    
    /** Creates a new instance of WorkOrderDateComparator */
    public WorkOrderDateComparator()
    {
    }
    
    public int compare(Object o1, Object o2)
    {
        if (o1==null || o2==null) return 0;
        
        WorkOrder w1 = (WorkOrder)o1;
        WorkOrder w2 = (WorkOrder)o2;
        
        if (w1.getOrderDate()==null && w2.getOrderDate()==null) return 0;
        if (w1.getOrderDate()==null) return -1;
        if (w2.getOrderDate()==null) return 1;
        
        return w2.getOrderDate().compareTo(w1.getOrderDate());
    }
    
}
