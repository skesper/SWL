/*
 * InputFieldFormer.java
 *
 * Created on 13. Juni 2004, 19:21
 */

package com.processive.util;

/**
 *
 * @author  Stephan Kesper
 */
public class InputFieldFormer
{
    
    /** Creates a new instance of InputFieldFormer */
    private InputFieldFormer()
    {
    }
    
    public static String formValue(String s)
    {
        if (s==null) return "";
        return s;
    }
    
    public static String formInputField(String type,String bean,String field,String value)
    {
        StringBuffer sb = new StringBuffer();
        
        sb.append("<input type=\"");
        sb.append(type);
        sb.append("\" name=\"");
        sb.append(bean);
        sb.append(".");
        sb.append(field);
        sb.append("\" value=\"");
        sb.append(value);
        sb.append("\">");
        
        return sb.toString();
    }
}
