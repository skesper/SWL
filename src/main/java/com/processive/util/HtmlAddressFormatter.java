/*
 * AddressFormatter.java
 *
 * Created on 27. Februar 2004, 11:29
 */

package com.processive.util;

import com.processive.workshop.model.hb.Address;



/**
 *
 * @author  Stephan Kesper
 */
public class HtmlAddressFormatter
{
    
    /** Creates a new instance of AddressFormatter */
    public HtmlAddressFormatter()
    {
    }
    
    public static String getAddressHTML(Address ad)
    {
        if (ad==null) return "n/a";
        
        StringBuffer html = new StringBuffer();
        
        html.append("<table cellspacing='0px' cellpadding='0px'><tr><td colspan=\"3\" class=\"address\">");
        
        if (ad.getCountry()!=null && ad.getCountry().equals("de"))
        {
            html.append(ad.getStreet1());
            
            html.append(" ");
            html.append(ad.getHouseNumber());
            
            if (ad.getStreet2()!=null && !ad.getStreet2().equals(""))
            {
                html.append("<br>");
                html.append(ad.getStreet2());
            }
        }
        else 
        {
            html.append(ad.getHouseNumber());
            html.append(" ");
            html.append(ad.getStreet1());
            html.append("<br>");
            html.append(ad.getStreet2());
        }
        
        html.append("</td></tr><tr><td class=\"address\">");
        
        if (ad.getCountry()!=null && ad.getCountry().equals("de"))
        {
            html.append("D&nbsp;</td><td class=\"address\">");
            html.append(ad.getZipCode());
            html.append("&nbsp;</td><td class=\"address\">");
            html.append(ad.getCity());
        }
        else if (ad.getCountry()!=null && ad.getCountry().equals("en"))
        {
            html.append("UK&nbsp;</td><td class=\"address\">");
            html.append(ad.getCity());
            html.append("&nbsp;</td><td class=\"address\">");
            html.append(ad.getZipCode());
        }
        else if (ad.getCountry()!=null && ad.getCountry().equals("us"))
        {
            html.append(ad.getCity());
            html.append("&nbsp;</td><td class=\"address\">");
            html.append(ad.getZipCode());
            html.append("&nbsp;</td><td class=\"address\">");
            html.append("</td></tr><tr><td colspan=\"3\" class=\"address\">U.S.A.");
        }
        else
        {
            html.append("D</td><td class=\"address\">");
            html.append(ad.getZipCode());
            html.append("</td><td class=\"address\">");
            html.append(ad.getCity());
        }
        
        html.append("</td></tr></table>");
        
        return html.toString();
    }
}
