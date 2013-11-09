<%@page contentType="text/html"
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
%>
<jsp:useBean class="com.processive.workshop.control.WorkorderController" id="controller" />
<%
    controller.action(request,response);
%>
<jsp:useBean class="com.processive.workshop.model.hb.Customer" id="customer" scope="request" />
<jsp:useBean class="com.processive.workshop.model.hb.WorkOrder" id="workorder" scope="request" />

<%@page import="com.processive.workshop.control.BillFinder"%>
<%@page import="com.processive.workshop.control.AddressFinder"%>
<%@page import="com.processive.workshop.control.WorkItemFinder"%><html>
<head><title>JSP Page</title>
<link rel="stylesheet" href="../prc.css">
</head>
<body bgcolor='#EEEEEE'>
<h3>&nbsp;</h3>
<div align="center">
<TABLE width='650px' cellpadding='1px' cellspacing='1px' class="box1">
<TR>
    <TD class='boxHead1'>Auftrag</TD>
</TR>
<TR>
    <TD class='boxAction1'>
    <a href="#">Drucken</a>&nbsp;|&nbsp;
    <a href="../bill/bill_create.jsp?action=createBill&workorderID=<%= workorder.getId() %>">
<%
    List bills = new BillFinder().findBillsByWorkOrder(workorder);
    if (bills.size()==0)
    {
        %>Rechnung erzeugen<%
    }
    else
    {
        %>Rechnung anzeigen<%
    }%></a>
    </TD>
</TR>
<tr>
    <TD class='boxItem1' align="center" valign="middle">
        <TABLE width='600px' cellpadding="0px" cellspacing="0px">
        <TR>
            <TD><H3>&nbsp;</H3></TD>
        </TR>
        <tr>
            <TD colspan='3' class='boxItem1'><%     
    switch(customer.getGender())
    {
        case(1): out.print("Herr");break;
        case(2): out.print("Frau");break;
        case(3): out.print("Firma");break;
    }
%> 
<%
    switch(customer.getTitle())
    {
        case(1): out.print("Dr.");break;
        case(2): out.print("Prof.");break;
        case(3): out.print("Prof. Dr.");break;
        case(4): out.print("Pfarrer");break;
        case(5): out.print("Pastor");break;
    }
%> <%= customer.getFirstName() %> <%= customer.getLastName() %></td>
        </tr>
        <tr>
            <td colspan='3'><%  List adds = new AddressFinder().findAddressByCustomer(customer);
                    if (adds.size()>0)
                    {
                        Address ad = (Address)adds.get(0);
                        out.write(HtmlAddressFormatter.getAddressHTML(ad));
                    }
                %></td>
        </tr>
        <TR>
            <TD height='50px' colspan='3'>&nbsp;</TD>
        </TR>
        <TR>
            <TD colspan='3' class='info'>Datum: <%= HtmlFormatter.formatDate("dd. MMMM yyyy",new Date()) %></TD>
        </TR>
        <TR>
            <TD colspan='3' class='info'>Auftrag vom: <%= HtmlFormatter.formatDate("dd. MMMM yyyy",workorder.getOrderDate()) %></TD>
        </TR>
<% if (workorder.getKmState()!=null) { %>
        <TR>
            <TD colspan='3' class='info'>KM Stand: <%= HtmlFormatter.formatKmState(workorder.getKmState()) %></TD>
        </TR>
<% } %>
        <TR>
            <TD height='50px' colspan='3'>&nbsp;</TD>
        </TR>
        <TR>
            <TD>
            <TABLE cellpadding='1px' cellspacing='1px' width='550px' bgcolor='#000000'>
            <TR>
                <TD class='boxStatus1'>Bezeichnung</TD>
                <TD class='boxStatus1' align="right">Menge</TD>
                <TD class='boxStatus1' align="right">Einzelpreis</TD>
                <TD class='boxStatus1' align="right">Gesamtpreis</TD>
            </TR>
<%      List workitems = new WorkItemFinder().findWorkItemsForWorkOrder(workorder);
        double sum = 0.;
        double summwst = 0.;
        double vatMax = 0.;

        for(int i=0;i<workitems.size();++i)
        {
            WorkItem wi = (WorkItem)workitems.get(i);
            
            sum += wi.getPrice()*wi.getAmount();
            summwst += wi.getAmount()*wi.getPrice()*(1.+wi.getVat()/100.);
            if (vatMax<wi.getVat()) vatMax = wi.getVat();
%>          <TR>
                <TD class='boxItem1'><%= new String(wi.getDescription(), "ISO-8859-1") %></TD>
                <TD class='boxItem1' align="right"><%= HtmlFormatter.formatCount(wi.getAmount()) %></TD>
                <TD class='boxItem1' align="right"><%= HtmlFormatter.formatEuro(wi.getPrice()) %></TD>
                <TD class='boxItem1' align="right"><%= HtmlFormatter.formatEuro(wi.getAmount()*wi.getPrice()) %></TD>
            </TR>
<%
        }
%>
            <TR>
                <TD class='boxItem1' colspan="3" align="right"><b>Summe</b></TD>
                <TD class='boxItem1' align="right"><%= HtmlFormatter.formatEuro(sum) %></TD>
            </TR>
            <TR>
                <TD class='boxItem1' colspan="3" align="right"><b>Zuz&uuml;glich <%= HtmlFormatter.formatCount(vatMax) %>% MWSt.</b></TD>
                <TD class='boxItem1' align="right"><%= HtmlFormatter.formatEuro(summwst-sum) %></TD>
            </TR>
            <TR>
                <TD class='boxItem1' colspan="3" align="right"><b>Gesamt</b></TD>
                <TD class='boxItem1' align="right"><b><%= HtmlFormatter.formatEuro(summwst) %></b></TD>
            </TR>
            </TABLE>
            </TD>
        </TR>
        <TR>
            <TD height='50px' colspan='3'>&nbsp;</TD>
        </TR>
        </TABLE>
    </TD>
</TR>
<TR>
    <TD class='boxSubmit1' align="right">&nbsp;</TD>
</TR>
</TABLE>
</body>
</html>
