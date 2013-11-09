<%@page contentType="text/html" 
%>
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<form action='customer_store.jsp' method="POST">
<input type="hidden" name="action" value="storeCustomer">
<input type="hidden" name="forwardUrl" value='customer_details.jsp?action=showCustomer'>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Neuer Kunde</TD>
</TR>
<TR>
    <TD class='boxItem1'>Anrede</TD><TD class='boxItem1'><SELECT name='gender'>
	<OPTION value='0' ></OPTION>
	<OPTION value='1' >Herr</OPTION>
	<OPTION value='2' >Frau</OPTION>
	<OPTION value='3' >Firma</OPTION>
	</SELECT></TD>
</TR>
<TR>
    <TD class='boxItem1'>Titel</TD><TD class='boxItem1'><SELECT name='title'>
<OPTION value='0' ></OPTION>
<OPTION value='1' >Dr.</OPTION>
<OPTION value='2' >Prof.</OPTION>
<OPTION value='3' >Prof. Dr.</OPTION>
<OPTION value='4' >Pfarrer</OPTION>
<OPTION value='5' >Pastor</OPTION>
</SELECT></TD>
</TR>
<TR>
    <TD class='boxItem1'>Vorname</TD><TD class='boxItem1'><input type="text" name="fname" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Nachname</TD><TD class='boxItem1'><input type="text" name="lname" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Firma</TD><TD class='boxItem1'><input type="text" name="company" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Telefon</TD><TD class='boxItem1'><input type="text" name="phone" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Mobil</TD><TD class='boxItem1'><input type="text" name="mobilePhone" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Fax</TD><TD class='boxItem1'><input type="text" name="fax" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>E-Mail</TD><TD class='boxItem1'><input type="text" name="email" value="" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Strasse, Hausnummer</TD><TD class='boxItem1'>
	<input type="text" name="street1" value="" size="50"><input type="text" name="houseno" value="" size="5"><br>
	<input type="text" name="street2" value="" size="50">
	</TD>
</TR>
<TR>
    <TD class='boxItem1'>Land, PLZ, Stadt</TD><TD class='boxItem1'>
	<select name="country">
	<option value="de" selected>Deutschland</option>
	<option value="fr">Frankreich</option>
	<option value="at">Östereich</option>
	<option value="ch">Schweiz</option>
	<option value="">andere ...</option>
	</select>
	<input type="text" name="zip" value="" size="5">
	<input type="text" name="city" value="" size="45"></TD>
</TR>
<TR>
    <TD colspan='2' class='boxSubmit1'><input type="submit" value='Speichern'></TD>
</TR>
</TABLE>
</div>
</form>
</body>
</html>