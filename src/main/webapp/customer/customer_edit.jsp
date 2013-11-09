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
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<form action='customer_details.jsp' method="POST">
<input type="hidden" name="action" value="updateCustomer">
<input type="hidden" name="customerID" value="<%= customer.getId() %>">
<input type="hidden" name="forwardUrl" value='customer_details.jsp?action=showCustomer'>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Kundenstammdaten</TD>
</TR>
<TR>
    <TD class='boxItem1'>Anrede</TD><TD class='boxItem1'><SELECT name='gender'>
	<OPTION value='0' <% if (customer.getGender()==0) out.print("selected"); %>></OPTION>
	<OPTION value='1' <% if (customer.getGender()==1) out.print("selected"); %>>Herr</OPTION>
	<OPTION value='2' <% if (customer.getGender()==2) out.print("selected"); %>>Frau</OPTION>
	<OPTION value='3' <% if (customer.getGender()==3) out.print("selected"); %>>Firma</OPTION>
	</SELECT></TD>
</TR>
<TR>
    <TD class='boxItem1'>Titel</TD><TD class='boxItem1'><SELECT name='title'>
<OPTION value='0' <% if (customer.getTitle()==0) out.print("selected"); %>></OPTION>
<OPTION value='1' <% if (customer.getTitle()==1) out.print("selected"); %>>Dr.</OPTION>
<OPTION value='2' <% if (customer.getTitle()==2) out.print("selected"); %>>Prof.</OPTION>
<OPTION value='3' <% if (customer.getTitle()==3) out.print("selected"); %>>Prof. Dr.</OPTION>
<OPTION value='4' <% if (customer.getTitle()==4) out.print("selected"); %>>Pfarrer</OPTION>
<OPTION value='5' <% if (customer.getTitle()==5) out.print("selected"); %>>Pastor</OPTION>
</SELECT></TD>
</TR>
<TR>
    <TD class='boxItem1'>Vorname</TD><TD class='boxItem1'><input type="text" name="fname" value="<%= customer.getFirstName() %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Nachname</TD><TD class='boxItem1'><input type="text" name="lname" value="<%= customer.getLastName() %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Firma</TD><TD class='boxItem1'><input type="text" name="company" value="<%= customer.getCompany() %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Telefon</TD><TD class='boxItem1'><input type="text" name="phone" value="<%= customer.getPhone() %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Mobil</TD><TD class='boxItem1'><input type="text" name="mobilePhone" value="<%= customer.getMobilePhone() %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Fax</TD><TD class='boxItem1'><input type="text" name="fax" value="<%= customer.getFax() %>" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>E-Mail</TD><TD class='boxItem1'><input type="text" name="email" value="<%= customer.getEmail() %>" size="50"></TD>
</TR>
<TR>
    <TD colspan='2' class='boxSubmit1'><input type="submit" value='Speichern'></TD>
</TR>
</TABLE>
</div>
</form>
</body>
</html>