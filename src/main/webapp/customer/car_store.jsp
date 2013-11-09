<%@page contentType="text/html"%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller" />
<%
    controller.action(request,response);
%>
<html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<H3>Bitte warten ...</H3>
</body>
</html>
