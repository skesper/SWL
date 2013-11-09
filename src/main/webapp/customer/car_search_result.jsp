<%@page contentType="text/html" 
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller" />
<%
    controller.action(request,response);
%>
<%@page import="com.processive.error.ErrorHandler"%>
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<br>
<%
    ErrorHandler eh = ErrorHandler.retrieve(session);
    
    if (eh!=null && eh.getLastError()!=null)
    {
        %><p class='error'><%= eh.getLastError() %><p><%
    }
%>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="3" class='boxHead1'>Fahrzeuge</TD>
</TR>
<%
    List res = (List)request.getAttribute("searchlist");

    if (res!=null && res.size()>0) {
    	
    	for(int i=0;i<res.size();++i) {
    		Car car = (Car)res.get(i);
    		
    		Customer cust = controller.findCustomer(car.getCustomerId());
    		
    		String customerName = "Nicht verfügbar";
    		
    		if (cust!=null) {
    			customerName = cust.getFirstName()+" "+cust.getLastName();
    		}
%>
<TR>
    <TD class='boxItem1'><%= i+1 %></TD>
    <TD class='boxItem1'><%= car.getLicenseNumber() %></TD>
    <TD class='boxItem1'><a href="customer_details.jsp?action=showCustomer&ID=<%= cust.getId() %>"><%= customerName %></a></TD>
</TR>
<%  	}
    }%>
</TABLE>
</div>
<br><br>
</body>
</html>