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
<jsp:useBean class="com.processive.workshop.model.hb.Car" id="car" scope="request" />
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<form action='customer_store.jsp' method="POST">
<input type="hidden" name="action" value="storeCar">
<input type="hidden" name="customerID" value='<%= customer.getId() %>'>
<input type="hidden" name="carID" value='<%= car.getId() %>'>
<input type="hidden" name="forwardUrl" value='customer_details.jsp?action=showCustomer'>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Fahrzeug Daten</TD>
</TR>
<TR>
    <TD class='boxItem1'>Kennzeichen</TD><TD class='boxItem1'><input type="text" name="license" value="<%= InputFieldFormer.formValue(car.getLicenseNumber()) %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Hersteller / Schlüssel</TD><TD class='boxItem1'>
	<input type="text" name="vendor" value="<%= InputFieldFormer.formValue(car.getVendor()) %>" size="25">
	<input type="text" name="vendorcode" value="<%= InputFieldFormer.formValue(car.getVendorCode()) %>" size="5">
	</TD>
</TR>
<TR>
    <TD class='boxItem1'>Typ / Schlüssel</TD><TD class='boxItem1'>
	<input type="text" name="type" value="<%= InputFieldFormer.formValue(car.getType()) %>" size="25">
	<input type="text" name="typecode" value="<%= InputFieldFormer.formValue(car.getTypeCode()) %>" size="5">
	</TD>
</TR>
<TR>
    <TD class='boxItem1'>Fahrgestellnummer</TD><TD class='boxItem1'>
	<input type="text" name="vin" value="<%= InputFieldFormer.formValue(car.getVin()) %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Erstzulassung<br>z.B. 11.03.1999</TD><TD class='boxItem1'>
	<input type="text" name="releasedate" value="<%= HtmlFormatter.formatDate("dd.MM.yyyy",car.getBuildDate()) %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Nächster T&Uuml;V Termin<br>z.B. 08/2005</TD><TD class='boxItem1'><input type="text" name="tuv" value="<%= HtmlFormatter.formatDate("MM/yyyy",car.getTuevDate()) %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Nächster ASU Termin<br>z.B. 08/2005</TD><TD class='boxItem1'><input type="text" name="asu" value="<%= HtmlFormatter.formatDate("MM/yyyy",car.getAsuDate()) %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Bemerkungen</TD><TD class='boxItem1'>
	<textarea name="descr" rows="8" cols="45"><%= InputFieldFormer.formValue(new String(car.getDetails(),"ISO-8859-1")) %></textarea>
	</TD>
</TR>
<TR>
    <TD colspan='2' class='boxSubmit1'><input type="submit" value='Speichern'></TD>
</TR>
</TABLE>
</div>
</form>
</body>
</html>
