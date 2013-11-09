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
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<form action='customer_store.jsp' method="POST">
<input type="hidden" name="action" value="storeCar">
<input type="hidden" name="customerID" value='<%= customer.getId() %>'>
<input type="hidden" name="forwardUrl" value='customer_details.jsp?action=showCustomer'>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Neues Fahrzeug f&uuml;r <%= customer.getFirstName()+" "+customer.getLastName() %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Kennzeichen</TD><TD class='boxItem1'>
    <input type="text" name="lic1" value="" size="4"> - 
    <input type="text" name="lic2" value="" size="4">&nbsp;
    <input type="text" name="lic3" value="" size="8"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Hersteller / Schlüssel</TD><TD class='boxItem1'>
	<input type="text" name="vendor" value="" size="25">
	<input type="text" name="vendorcode" value="" size="5">
	</TD>
</TR>
<TR>
    <TD class='boxItem1'>Typ / Schlüssel</TD><TD class='boxItem1'>
	<input type="text" name="type" value="" size="25">
	<input type="text" name="typecode" value="" size="5">
	</TD>
</TR>
<TR>
    <TD class='boxItem1'>Fahrgestellnummer</TD><TD class='boxItem1'>
	<input type="text" name="vin" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Erstzulassung<br>z.B. 11.03.1999</TD><TD class='boxItem1'>
	<input type="text" name="releasedate" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Nächster T&Uuml;V Termin<br>z.B. 08/2005</TD><TD class='boxItem1'><input type="text" name="tuv" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Nächster ASU Termin<br>z.B. 08/2005</TD><TD class='boxItem1'><input type="text" name="asu" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Bemerkungen</TD><TD class='boxItem1'>
	<textarea name="descr" rows="8" cols="45"></textarea>
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