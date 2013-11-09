<%@page contentType="text/html" 
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
%>
<html>
<head><title>KFZ Suche</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<FORM action='car_search_result.jsp' method="POST">
<INPUT type="hidden" name="action" value="searchCars">
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Fahrzeug</TD>
</TR>
<TR>
    <TD class='boxItem1'>Kennzeichen</TD>
    <TD class='boxItem1'><INPUT type="text" name="licnum" size="50"></TD>
</TR>
<TR>
    <TD class='boxSubmit1' colspan='2'><input type="submit" value="suchen">
</TR>
</TABLE>
</div>
</FORM>
</body>
</html>