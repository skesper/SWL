<%@page contentType="text/html"
    import="com.processive.workshop.model.hb.*"
    import="java.util.*"
    import="com.processive.util.*"
%>
<jsp:useBean class="com.processive.workshop.control.BillController" id="controller" />
<%
    controller.action(request,response);

    Bill theBill = (Bill)request.getAttribute("bill");
    WorkOrder workorder = PersistenceLayer.getInstance().findById(WorkOrder.class, theBill.getWorkorderId());
    Customer customer = PersistenceLayer.getInstance().findById(Customer.class, workorder.getCustomerId());
    Address ad = new AddressFinder().findUniqueAddressByCustomer(customer);
%>
<jsp:useBean class="com.processive.workshop.model.hb.Bill" id="bill" scope="request" />

<%@page import="com.processive.workshop.persistence.PersistenceLayer"%>
<%@page import="com.processive.workshop.control.WorkItemFinder"%>
<%@page import="com.processive.workshop.control.AddressFinder"%><html>
<head><title>Rechnung Druckansicht</title>
<link rel="stylesheet" href="../prc.css">
<SCRIPT language='JavaScript'>
<!--
    window.print();
//-->
</SCRIPT>
</head>
<body bgcolor='#FFFFFF'>

        <TABLE width='600px' cellpadding="1px" cellspacing="1px" bgcolor='#ffffff'>
        <TR>
            <TD colspan='4' class='boxItem1'><img src="../images/bill_logo.gif" border="0"></TD>
        </TR>
        <TR>
            <TD class='boxItem1' width='450px'><%     
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
%> <%= customer.getFirstName() %> <%= customer.getLastName() %>
        <p>
<% 
			out.write(HtmlAddressFormatter.getAddressHTML(ad));
                %></td>
            <TD class='boxItem1'></TD><TD class='boxItem1'></TD> 
            <TD class='boxItem1' width='150px'>
            <UL>
            <LI>KFZ-Reparaturen aller Marken
            <LI>Unfallinstandsetzung
            <LI>Vermietung von Hebebühnen, Arbeitsplätzen und Werkzeugen
            <LI>Teilehandel
            <LI>T&Uuml;V - Vorf&uuml;hrung
            <LI>PKW, LKW und Off-Road-Fahrzeug Verleih
            </UL></TD>
        </TR>
        <TR>
            <TD colspan='4' class='boxItem1'>&nbsp;</TD>
        </TR>
        <TR>
            <TD class='boxItem1'><h3>Rechnung</h3>
            Rechnungsnummer: <%= theBill.getNumber() %>
            </TD>
            <TD colspan='3' class='boxItem1' align="right">Lahnstein, <%= HtmlFormatter.formatDate("dd. MMMM yyyy",new Date()) %></TD>
        </TR>
        <TR>
            <TD colspan='4' class='boxItem1'>Auftrag vom: <%= HtmlFormatter.formatDate("dd. MMMM yyyy",workorder.getOrderDate()) %></TD>
        </TR>
        <TR><%
       Car car = PersistenceLayer.getInstance().findById(Car.class, workorder.getCarId()); 
        %>
            <TD colspan='4' class='boxItem1'>Fahrzeug: <%= car.getLicenseNumber() %> - <%= car.getVendor()+" "+car.getType() %></TD>
        </TR>
<% if (workorder.getKmState()!=null) { %>
        <TR>
            <TD colspan='4' class='boxItem1'>KM Stand: <%= HtmlFormatter.formatKmState(workorder.getKmState()) %></TD>
        </TR>
<% } %>
        <TR>
            <TD height='50px' colspan='4' class='boxItem1'>&nbsp;</TD>
        </TR>
        <TR>
            <TD colspan="4" class='boxItem1'>
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
            if (wi.getVat()>vatMax) vatMax=wi.getVat();
            summwst += wi.getAmount()*wi.getPrice()*(1.+wi.getVat()/100.);
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
            <TD height='50px' colspan='4' class='boxItem1'>&nbsp;</TD>
        </TR>
        <TR>
            <TD height="50px" colspan='4' class='boxItem1'><b>Zahlbar sofort ohne Abzug</b></TD>
        </TR>
        <TR>
            <TD height="50px" colspan='4' class='boxItem1'>Steuernummer: 39/126/4010/7</TD>
        </TR>
        <TR>
            <TD colspan='4' class='small' valign="bottom" align="center">
            Im Machert 4, 56112 Lahnstein, Tel.: 0 26 21 / 6 11 03, Fax: 0 26 21 / 61 03 13</TD>
        </TR>
        <TR>
            <TD colspan='4' class='small' valign="bottom" align="center">
            Bankverbindung: Volksbank Lahnstein eG, BLZ: 570 928 00, Kto: 201 281 806</TD>
        </TR>
        </TABLE>
</body>
</html>
