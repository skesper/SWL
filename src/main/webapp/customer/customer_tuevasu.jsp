<%@page contentType="text/html"
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
    import="com.processive.workshop.comparators.*"
%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller" />

<%@page import="com.processive.workshop.persistence.PersistenceLayer"%><html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<DIV align="center">
<%
    List tuevs = controller.searchTuevCars();
    List asus = controller.searchAsuCars();

%>
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="3" class='boxHead1'>Kunden mit f&auml;lligem T&Uuml;V</TD>
</TR>
<%
    for(int i=0;i<tuevs.size();++i)
    {
        Car car = (Car)tuevs.get(i);
        Date now = new Date();
        Customer customer = PersistenceLayer.getInstance().findById(Customer.class, car.getCustomerId());

        %>
<TR>
    <TD class='boxItem1'><a href="customer_details.jsp?action=showCustomer&ID=<%= customer.getId() %>">
    <%= customer.getFirstName()+" "+customer.getLastName() %></a></TD>
    <TD class='boxItem1'><%= car.getLicenseNumber() %></TD>
    <TD class='boxItem1'><% 
        if (car.getTuevDate().before(now))
        {
            out.print("<div class='boxError1'>");
        }
        out.print(HtmlFormatter.formatDate("MMMM yyyy",car.getTuevDate()));
        if (car.getTuevDate().before(now))
        {
            out.print("</div>");
        }
%></TD>
</TR>
<%
    }
%>
</TABLE>
<br>
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="3" class='boxHead1'>Kunden mit f&auml;lliger ASU</TD>
</TR>
<%
    for(int i=0;i<asus.size();++i)
    {
        Car car = (Car)asus.get(i);
        Date now = new Date();
        Customer customer = PersistenceLayer.getInstance().findById(Customer.class, car.getCustomerId());

        %>
<TR>
    <TD class='boxItem1'><a href="customer_details.jsp?action=showCustomer&ID=<%= customer.getId() %>">
    <%= customer.getFirstName()+" "+customer.getLastName() %></a></TD>
    <TD class='boxItem1'><%= car.getLicenseNumber() %></TD>
    <TD class='boxItem1'><% 
        if (car.getAsuDate().before(now))
        {
            out.print("<div class='boxError1'>");
        }
        out.print(HtmlFormatter.formatDate("MMMM yyyy",car.getAsuDate()));
        if (car.getAsuDate().before(now))
        {
            out.print("</div>");
        }
%></TD>
</TR>
<%
    }
%>
</TABLE>
</DIV>
</body>
</html>
