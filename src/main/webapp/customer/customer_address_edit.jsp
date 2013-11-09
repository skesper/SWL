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
<jsp:useBean class="com.processive.workshop.model.hb.Address" id="address" scope="request" />
<jsp:useBean class="com.processive.workshop.model.hb.Customer" id="customer" scope="request" />
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<div align="center">
<FORM action="customer_store.jsp" method="POST">
<INPUT type="hidden" name='action' value='storeAddress'>
<INPUT type="hidden" name='customerID' value="<%= customer.getId() %>">
<INPUT type="hidden" name='addressID' value="<%= address.getId() %>">
<input type="hidden" name="forwardUrl" value='customer_details.jsp?action=showCustomer'>
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan='2' class='boxHead1'>Adress Daten</TD>
</TR>
<TR>
    <TD class='boxItem1'>Name</TD>
    <TD class='boxInfo1'><%= InputFieldFormer.formValue(customer.getFirstName())+" "+InputFieldFormer.formValue(customer.getLastName()) %></TD>
</TR>
<TR>
    <TD class='boxItem1'>Strasse / Hausnummer</TD>
    <TD class='boxInfo1'>
	<input type="text" name="street1" value="<%= InputFieldFormer.formValue(address.getStreet1()) %>" size="50"><input type="text" name="houseno" value="<%= InputFieldFormer.formValue(address.getHouseNumber()) %>" size="5"><br>
	<input type="text" name="street2" value="<%= InputFieldFormer.formValue(address.getStreet2()) %>" size="50">
</TD>
</TR><%
    String country = address.getCountry();
%><TR>
    <TD class='boxItem1'>Land PLZ Stadt</TD>
    <TD class='boxInfo1'>
	<select name="country">
	<option value="de" <% if (country!=null && country.equals("de")) out.print("selected"); %>>Deutschland</option>
	<option value="fr" <% if (country!=null && country.equals("fr")) out.print("selected"); %>>Frankreich</option>
	<option value="at" <% if (country!=null && country.equals("at")) out.print("selected"); %>>Östereich</option>
	<option value="ch" <% if (country!=null && country.equals("ch")) out.print("selected"); %>>Schweiz</option>
	<option value="" <% if (country!=null && country.equals("")) out.print("selected"); %>>andere ...</option>
	</select>
	<input type="text" name="zip" value="<%= address.getZipCode() %>" size="5">
	<input type="text" name="city" value="<%= InputFieldFormer.formValue(address.getCity()) %>" size="45"></TD>
</TR>
<TR>
    <TD colspan='2' class='boxSubmit1'><INPUT type="submit" value="Speichern"></TD>
</TR>
</TABLE>
</div>
</body>
</html>
