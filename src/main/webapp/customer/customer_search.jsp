<%@page contentType="text/html"
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.HtmlAddressFormatter"
    import="com.processive.error.ErrorHandler"
%>
<jsp:useBean class="com.processive.workshop.control.CustomerController" id="controller" />
<%
    controller.action(request,response);
%>

<%@page import="com.processive.workshop.control.AddressFinder"%><html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<div align="center">
<FORM action='customer_search.jsp' method="POST">
<INPUT type="hidden" name="action" value="searchCustomer">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD colspan="2" class='boxHead1'>Kunden</TD>
</TR>
<TR>
    <TD class='boxItem1'>Nachname</TD>
    <TD class='boxItem1'><INPUT type="text" name="lname" size="50"></TD>
</TR>
<TR>
    <TD class='boxItem1'>Vorname</TD>
    <TD class='boxItem1'><INPUT type="text" name="fname" size="50"></TD>
</TR>
<TR>
    <TD class='boxSubmit1' colspan='2'><input type="submit" value="suchen">
</TR>
</TABLE>
</FORM>
<br>
<%
    ErrorHandler eh = ErrorHandler.retrieve(session);
    
    if (eh!=null && eh.getLastError()!=null)
    {
        %><p class='error'><%= eh.getLastError() %><p><%
    }
%>
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<tr>
<td class='boxHead1' colspan='3'>Suchergebnisse</td>
</tr>
<%
    List res = (List)request.getAttribute("searchlist");

    if (res!=null && res.size()>0)
    {
%><h3><%= res.size() %> gefunden.</h3>
<%
        for(int i=0;i<res.size();++i)
        {
            Customer cust = (Customer)res.get(i);

            String style = "boxItem"+(i%2);

            List add = new AddressFinder().findAddressByCustomer(cust);
%>
<tr>
<td class='<%= style %>'><a href="customer_details.jsp?action=showCustomer&ID=<%= cust.getId() %>"><%= cust.getLastName() %></a></td>
<td class='<%= style %>'><%= cust.getFirstName() %></td>
<td class='<%= style %>'>
    <%  for(int j=0;j<add.size();++j)
        {
            Address ad = (Address)add.get(j);
            out.println(HtmlAddressFormatter.getAddressHTML(ad));
            if (j+1<add.size()) out.println("<br>");
        }
    %></td>
</tr>
<%      } 
    } %>
</TABLE>
<%
    if (eh!=null && eh.getLastError()!=null)
    {
        %><pre class='exception'><%= eh.getLastExceptionAsString() %></pre><%
        eh.clear();
    }
%>
</DIV>
</body>
</html>
