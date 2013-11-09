/*
 * CarAsuComparator.java
 *
 * Created on 14. Juni 2004, 12:34
 */

package com.processive.workshop.comparators;

import java.util.Comparator;

import com.processive.workshop.model.hb.Car;


/**
 *
 * @author  Stephan Kesper
 */
public class CarAsuComparator implements Comparator
{
    
    /** Creates a new instance of CarAsuComparator */
    public CarAsuComparator()
    {
    }
    
    public int compare(Object o1, Object o2)
    {
        if (o1==null || o2==null) return 0;
        
        Car c1 = (Car)o1;
        Car c2 = (Car)o2;
        
        if (c1.getAsuDate()==null && c2.getAsuDate()==null) return 0;
        if (c1.getAsuDate()==null) return -1;
        if (c2.getAsuDate()==null) return 1;
        
        return c1.getAsuDate().compareTo(c2.getAsuDate());
    }
    
}
