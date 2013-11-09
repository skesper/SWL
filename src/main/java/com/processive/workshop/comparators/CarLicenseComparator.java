package com.processive.workshop.comparators;

import java.util.Comparator;

import com.processive.workshop.model.hb.Car;



public class CarLicenseComparator implements Comparator {

    public int compare(Object o1, Object o2)
    {
        if (o1==null || o2==null) return 0;
        
        Car c1 = (Car)o1;
        Car c2 = (Car)o2;
        
        if (c1.getLicenseNumber()==null || c2.getLicenseNumber()==null) return 0;
        
        return c1.getLicenseNumber().compareTo(c2.getLicenseNumber());
    }
}
