<%@page contentType="text/html"
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
    import="com.processive.workshop.comparators.*"
%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller" />
<%
    controller.action(request,response);
%>
<jsp:useBean class="com.processive.workshop.model.hb.Customer" id="customer" scope="request" />

<%@page import="com.processive.workshop.control.AddressFinder"%>
<%@page import="com.processive.workshop.control.CarFinder"%>
<%@page import="com.processive.workshop.control.WorkOrderFinder"%>
<%@page import="com.processive.workshop.persistence.PersistenceLayer"%><html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
<script src="../js/commons.js" type="text/javascript"></script>
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Kunden Informationen</TD>
</TR>
<TR>
    <TD colspan="2" class='boxAction1'>
        <a href="customer_edit.jsp?action=showCustomer&ID=<%= customer.getId() %>">Bearbeiten</a>&nbsp;|
        <a href="customer_address_edit.jsp?action=showAddress&ID=<%= customer.getId() %>">Neue Adresse</a>
    </TD>
</TR>
<TR>
    <TD class='boxItem1'>Anrede</TD><TD class='boxInfo1'>
<%
    switch(customer.getGender())
    {
        case(1): out.print("Herr");break;
        case(2): out.print("Frau");break;
        case(3): out.print("Firma");break;
    }
%>
</TD>
</TR>
<TR>
    <TD class='boxItem1'>Titel</TD><TD class='boxInfo1'>
<%
    switch(customer.getTitle())
    {
        case(1): out.print("Dr.");break;
        case(2): out.print("Prof.");break;
        case(3): out.print("Prof. Dr.");break;
        case(4): out.print("Pfarrer");break;
        case(5): out.print("Pastor");break;
    }
%>
</TD>
</TR>
<TR>
    <TD class='boxItem1'>Vorname</TD><TD class='boxInfo1'><%= customer.getFirstName() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Nachname</TD><TD class='boxInfo1'><%= customer.getLastName() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Firma</TD><TD class='boxInfo1'><%= customer.getCompany() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Telefon</TD><TD class='boxInfo1'><%= customer.getPhone() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Mobil</TD><TD class='boxInfo1'><%= customer.getMobilePhone() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Fax</TD><TD class='boxInfo1'><%= customer.getFax() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>E-Mail</TD><TD class='boxInfo1'><%= HtmlFormatter.formatEmail(customer.getEmail()) %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Adresse(n)</TD><TD class='boxInfo1'>
<table cellpadding='1px' cellspacing='1px' bgcolor='#FFFFFF'>
<%
    List add = new AddressFinder().findAddressByCustomer(customer);

    for(int i=0;i<add.size();++i)
    {
%><tr><td bgcolor='#CCFF33'><%
        Address ad = (Address)add.get(i);
        out.println(HtmlAddressFormatter.getAddressHTML(ad));
        out.println("</td><td bgcolor='#CCFF33'><a href=\"customer_address_edit.jsp?action=showAddress&ID="+customer.getId()+"&adid="+ad.getId()+"\">bearbeiten</a>");
%></td></tr>
<%
    }
%>
</table></TD>
</TR>
<TR>
    <TD colspan='2' class='boxSubmit1'>&nbsp;</TD>
</TR>
</TABLE>
<p>
<% List cars = new CarFinder().findCarsByCustomer(customer);
%>
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="4" class='boxHead1'>Registrierte Fahrzeuge</TD>
</TR>
<TR>
    <TD colspan="4" class='boxAction1'>
        <a href="car_new.jsp?action=showCustomer&ID=<%= customer.getId() %>">Neues Fahrzeug</a>
    </TD>
</TR>
<TR>
    <TD class='boxStatus1'>Kennzeichen</TD>
    <TD class='boxStatus1'>Hersteller</TD>
    <TD class='boxStatus1'>Typ</TD>
    <TD class='boxStatus1' align="center">Aktion</TD>
</TR>
<%
    for(int i=0;i<cars.size();++i)
    {
        Car car = (Car)cars.get(i);
        String style = "boxItem"+(i%2);
%>
<TR>
<TD class='<%= style %>'><%= car.getLicenseNumber() %></TD>
<TD class='<%= style %>'><%= car.getVendor() %></TD>
<TD class='<%= style %>'><%= car.getType() %></TD>
    <TD class='<%= style %>' align="center">
        <a href="car_details.jsp?action=showCar&carID=<%= car.getId() %>&customerID=<%= customer.getId() %>"><IMG src='../images/list.gif' border='0'></a>
        <a href="car_edit.jsp?action=showCar&carID=<%= car.getId() %>&customerID=<%= customer.getId() %>"><IMG src='../images/edit.gif' border='0'></a>
        <!-- <a href="javascript:askForDeletion('das Fahrzeug und alle Aufträge','customer_details.jsp?action=deleteCar&carID=<%= car.getId() %>&customerID=<%= customer.getId() %>');"><IMG src='../images/trash.gif' border='0'></a> -->
    </TD>
</TR>
<%  } %>
<TR>
    <TD colspan='4' class='boxSubmit1'>&nbsp;</TD>
</TR>
</TABLE>
<p>
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="4" class='boxHead1'>Auftr&auml;ge</TD>
</TR>
<TR>
    <TD colspan="4" class='boxAction1'>
        <a href="../workorder/workorder_new.jsp?action=showCustomer&ID=<%= customer.getId() %>">Neuer Auftrag</a>
    </TD>
</TR>
<TR>
    <TD class='boxStatus1'>Datum</TD>
    <TD class='boxStatus1'>KFZ</TD>
    <TD class='boxStatus1'>Beschreibung</TD>
    <TD class='boxStatus1' align="center">Aktion</TD>
</TR>
<%
    List workorders = new WorkOrderFinder().findWorkOrdersByCustomer(customer);

    Collections.sort(workorders,new WorkOrderDateComparator());

    for(int i=0;i<workorders.size();++i)
    {
        WorkOrder wo = (WorkOrder)workorders.get(i);
        String style = "boxItem"+(i%2);
        
        Car car = PersistenceLayer.getInstance().findById(Car.class, wo.getCarId());
%>
<TR>
    <TD class='<%= style %>'><%= HtmlFormatter.formatDate("dd. MMMM yyyy",wo.getOrderDate()) %></TD>
    <TD class='<%= style %>'><%= car.getLicenseNumber() %> </TD>
    <TD class='<%= style %>'><%= new String(wo.getDescription()) %></TD>
    <TD class='<%= style %>' align="center">
        <a href="../workorder/workorder_details.jsp?action=showWorkorder&workorderID=<%= wo.getId() %>"><IMG src='../images/list.gif' border='0'></a>
        <a href="../workorder/workitem_new.jsp?action=editWorkItems&customerID=<%= customer.getId() %>&workorderID=<%= wo.getId() %>"><IMG src='../images/edit.gif' border='0'></a>
    </TD>
</TR>
<%
    }
%>
<TR>
    <TD colspan='4' class='boxSubmit1'>&nbsp;</TD>
</TR>
</TABLE>
</div>
</body>
</html>
