/*
 * CarTuevComparator.java
 *
 * Created on 7. Juni 2004, 11:50
 */

package com.processive.workshop.comparators;

import java.util.Comparator;

import com.processive.workshop.model.hb.Car;


/**
 *
 * @author  Stephan Kesper
 */
public class CarTuevComparator implements Comparator
{
    
    /** Creates a new instance of CarTuevComparator */
    public CarTuevComparator()
    {
    }
    
    public int compare(Object o1, Object o2)
    {
        if (o1==null || o2==null) return 0;
        
        Car c1 = (Car)o1;
        Car c2 = (Car)o2;
        
        if (c1.getTuevDate()==null && c2.getTuevDate()==null) return 0;
        if (c1.getTuevDate()==null) return -1;
        if (c2.getTuevDate()==null) return 1;
        
        return c1.getTuevDate().compareTo(c2.getTuevDate());
    }
    
}
