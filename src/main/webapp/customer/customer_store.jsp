<%@page contentType="text/html"
    import="com.processive.workshop.control.CustomerController"
    import="com.processive.workshop.model.hb.Customer"
    import="com.processive.error.ErrorHandler"
%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller"/>
<html>
<head><title>JSP Page</title></head>
<body>
<%
    controller.action(request,response);

    Customer cust = (Customer)request.getAttribute("customer");

    if (cust==null) 
    {
        return;
    }
    
    String url = request.getParameter("forwardUrl");
    
    if (url==null)
        controller.forward(request,response,null);
    else
    {
        url = url+"&ID="+cust.getId();
        controller.forward(request,response,url);
    }
%>
<h3>Bitte warten ...</h3>
<% ErrorHandler eh = ErrorHandler.retrieve(session);

    if (eh!=null && eh.getLastError()!=null)
    {
        %><div class='error'><%= eh.getLastError() %></div>
        <pre>
<%
    }
%>
</body>
</html>
