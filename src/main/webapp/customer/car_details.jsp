<%@page contentType="text/html" 
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller" />
<%
    controller.action(request,response);
%>
<jsp:useBean class="com.processive.workshop.model.hb.Car" id="car" scope="request" />
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Fahrzeugdaten</TD>
</TR>
<TR>
    <TD class='boxItem1'>Kennzeichen</TD><TD class='boxInfo1'><%= HtmlFormatter.formatString(car.getLicenseNumber()) %> </TD>
</TR>
<TR>
    <TD class='boxItem1'>Hersteller / Schlüssel</TD><TD class='boxInfo1'>
    <%= HtmlFormatter.formatString(car.getVendor()) %> / <%= HtmlFormatter.formatString(car.getVendorCode()) %>
    </TD>
</TR>
<TR>
    <TD class='boxItem1'>Typ / Schlüssel</TD><TD class='boxInfo1'>
    <%= HtmlFormatter.formatString(car.getType()) %> / <%= HtmlFormatter.formatString(car.getTypeCode()) %>
    </TD>
</TR>
<TR>
    <TD class='boxItem1'>Fahrgestellnummer</TD><TD class='boxInfo1'>
	<%= HtmlFormatter.formatString(car.getVin()) %>
    </TD>
</TR>
<TR>
    <TD class='boxItem1'>Erstzulassung</TD><TD class='boxInfo1'>
	<%= HtmlFormatter.formatDate("dd. MMMM yyyy",car.getBuildDate()) %>
    </TD>
</TR>
<TR>
    <TD class='boxItem1'>Nächster T&Uuml;V Termin</TD><TD class='boxInfo1'>
    <%= HtmlFormatter.formatDate("MM/yyyy",car.getTuevDate()) %>
    </TD>
</TR>
<TR>
    <TD class='boxItem1'>Nächster ASU Termin</TD><TD class='boxInfo1'>
    <%= HtmlFormatter.formatDate("MM/yyyy",car.getAsuDate()) %>
    </TD>
</TR>
<TR>
    <TD class='boxItem1'>Bemerkungen</TD><TD class='boxInfo1'>
	<%= HtmlFormatter.formatString(new String(car.getDetails(), "ISO-8859-1")) %>
	</TD>
</TR>
<TR>
    <TD colspan='2' class='boxSubmit1'><a href="customer_details.jsp?action=showCustomer&ID=<%= car.getCustomerId() %>">fertig</a></TD>
</TR>
</TABLE>
</div>
</body>
</html>