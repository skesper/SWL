<%@page contentType="text/html"
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller" />
<%
    controller.action(request,response);
%>
<jsp:useBean class="com.processive.workshop.model.hb.Customer" id="customer" scope="request" />

<%@page import="com.processive.workshop.control.CarFinder"%><html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<div align="center">
<FORM action='store.jsp' method="POST">
<input type="hidden" name="action" value="storeWorkOrder">
<input type="hidden" name="customerID" value='<%= customer.getId() %>'>
<input type="hidden" name="forwardUrl" value="workitem_new.jsp?customerID=<%= customer.getId() %>">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Neuer Auftrag von <%= customer.getFirstName()+" "+customer.getLastName() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Fahrzeug</TD>
    <TD class='boxItem1'><SELECT name='carId'>
<%
    List cars = new CarFinder().findCarsByCustomer(customer);

    for(int i=0;i<cars.size();++i)
    {
        Car car = (Car)cars.get(i);

%>  <OPTION value='<%= car.getId() %>'><%= car.getLicenseNumber()+" - "+car.getType() %></OPTION>
<%  } %>
    </SELECT>
</TR>
<TR>
    <TD class='boxItem1'>Auftragsdatum</TD>
    <TD class='boxItem1'><INPUT type="text" name='orderdate' size='50' value='<%= HtmlFormatter.formatDateNow("dd.MM.yyyy") %>'></TD>
</TR>
<TR>
    <TD class='boxItem1'>Fertigstellung</TD>
    <TD class='boxItem1'><INPUT type="text" name='duedate' size='50' value='<%= HtmlFormatter.formatDateNow("dd.MM.yyyy") %>'></TD>
</TR>
<TR>
    <TD class='boxItem1'>Angebot</TD>
    <TD class='boxItem1'><INPUT type="text" name='offer' size='50'>&nbsp;&euro;</TD>
</TR>
<TR>
    <TD class='boxItem1'>Aktueller km Stand</TD>
    <TD class='boxItem1'><INPUT type="text" name='kmstand' size='50'>&nbsp;km</TD>
</TR>
<TR>
    <TD class='boxItem1'>Bemerkungen</TD>
    <TD class='boxItem1'><textarea name="descr" rows="8" cols="45"></textarea></TD>
</TR>
<TR>
    <TD colspan='2' class='boxSubmit1'><input type="submit" value="Speichern"></TD>
</TR>
</TABLE>
</FORM>
</div>
</body>
</html>
