<%@page contentType="text/html"
    import="com.processive.workshop.control.WorkorderController"
    import="com.processive.workshop.model.hb.*"
    import="com.processive.error.ErrorHandler"
%>
<jsp:useBean class="com.processive.workshop.control.WorkorderController" id="controller" scope="session" />
<html>
<head><title>JSP Page</title></head>
<body>
<%
    controller.action(request,response);

    WorkOrder workorder = (WorkOrder)request.getAttribute("workorder");

    if (workorder==null) 
    {
        return;
    }
    
    String url = request.getParameter("forwardUrl");
    
    if (url==null)
        controller.forward(request,response,null);
    else
    {
        url = url+"&workorderID="+workorder.getId();
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
