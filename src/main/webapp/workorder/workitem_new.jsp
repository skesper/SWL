<%@page contentType="text/html"
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
    import="com.processive.error.ErrorHandler"
%>
<jsp:useBean class="com.processive.workshop.control.WorkorderController" id="controller" />
<%
    controller.action(request,response);
%>
<jsp:useBean class="com.processive.workshop.model.hb.WorkOrder" id="workorder" scope="request" />

<%@page import="com.processive.workshop.control.WorkItemFinder"%><html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<div align="center">
<FORM action='workitem_new.jsp' method="POST">
<input type="hidden" name="action" value="storeWorkItem">
<input type="hidden" name="customerID" value='<%= request.getParameter("customerID")%>'>
<input type="hidden" name="workorderID" value='<%= request.getParameter("workorderID")%>'>
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="5" class='boxHead1'>Einzelposten</TD>
</TR>
<TR>
    <TD class="boxStatus1">Bezeichnung</TD>
    <TD class="boxStatus1">Menge</TD>
    <TD class="boxStatus1">Preis (ohne MWSt.)</TD>
    <TD class="boxStatus1">MWSt.</TD>
    <TD class="boxStatus1" align="center">Aktion</TD>
</TR>
<%
    List wi = new WorkItemFinder().findWorkItemsForWorkOrder(workorder);

    double sum = 0.;
    double summwst = 0.;

    for(int i=0;i<wi.size();++i)
    {
        WorkItem item = (WorkItem)wi.get(i); 

        String style = "boxItem"+(i%2);
%>
<TR>
    <TD class='<%= style %>'><%= new String(item.getDescription(), "ISO-8859-1") %></TD>
    <TD class='<%= style %>' align="right"><%= HtmlFormatter.formatCount(item.getAmount()) %></TD>
    <TD class='<%= style %>' align="right"><%= HtmlFormatter.formatEuro(item.getPrice()) %></TD>
    <TD class='<%= style %>' align="right"><%= HtmlFormatter.formatFloat(item.getVat()) %> %</TD>
    <TD class='<%= style %>' align="center"><a href="workitem_new.jsp?action=deleteWorkItem&customerID=<%= request.getParameter("customerID")%>&workorderID=<%= workorder.getId()%>&workitemID=<%= item.getId() %>"><IMG src='../images/trash.gif' border='0'></a></TD>
</TR>
<%      sum += item.getAmount()*item.getPrice();
        summwst += item.getAmount()*item.getPrice()*(1.+item.getVat()/100.);
    } %>
<TR>
    <TD class='boxItem1'><input type="text" name="descr" size="50"></TD>
    <TD class='boxItem1' align="right"><input type="text" name="amount" size="8"></TD>
    <TD class='boxItem1' align="right"><input type="text" name="price" size="8"></TD>
    <TD class='boxItem1' align="right"><SELECT name='vat'><OPTION value='19' selected>19 %</OPTION>
    <OPTION value='16'>16 %</OPTION>
    <OPTION value="7">7 %</OPTION></TD>
    <TD class='boxItem1'><input type="submit" value="Speichern"></TD>
</TR>
<TR>
    <TD class='boxItem1' colspan='2'>Summe</TD>
    <TD class='boxItem1' align="right"><%= HtmlFormatter.formatEuro(sum) %></TD>
    <TD class='boxItem1' align="right"><%= HtmlFormatter.formatEuro(summwst) %></TD>
    <TD class='boxItem1' align="right"><a href="../customer/customer_details.jsp?action=showCustomer&ID=<%= request.getParameter("customerID")%>">fertig</a></TD>
</TR>
<TR>
    <TD colspan='5' class='boxSubmit1'>&nbsp;</TD>
</TR>
</TABLE>
</form>
</div>
<%
    ErrorHandler eh = ErrorHandler.retrieve(session);
    
    if (eh!=null && eh.getLastError()!=null)
    {
        %><p class='error'><%= eh.getLastError() %><p>
          <pre class='exception'><%= eh.getLastExceptionAsString() %></pre><%
        eh.clear();
    }
%>

</body>
</html>
