<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="db.dbcon"%>

<%@include file="session.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%

    // Create a logger instance for the current class (you can customize the name)
    Logger logger = Logger.getLogger("TrafficDashboardJSP");

    try {
 //String username="ram";
//        HttpSession session1 = request.getSession();
//        String username = (String) session1.getAttribute("username");
//
//        if (username == null || username.isEmpty()) {
//           // response.sendRedirect("login.jsp");
//        } else {
            String fdate = request.getParameter("startDate1");

            //  out.println("fdate" + fdate);
            String tdate = request.getParameter("endDate1");
            String BrandN = request.getParameter("BrandList1");
            //  out.println("BrandN" + BrandN);

            String BotN = request.getParameter("BotList1");
            String List = request.getParameter("listby1");

//
//    String sql = "";
//
//    if (List.equals("ALL")) {
//        sql = "SELECT * FROM My_Billing WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND Bots LIKE '" + BotN + "' AND username = '" + username + "';";
//    } else {
//        sql = "SELECT * FROM My_Billing WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND Bots LIKE '" + BotN + "' AND username = '" + username + "' GROUP BY '" + List + "';";
//    }
//
//      String sql="";
//      
//      if(List.equals("ALL")){
//           sql = "select * from My_Billing where DateRange between '" + fdate + "' and '" + tdate + "' and  Brands like '" + BrandN + "' and Bots like '" + BotN + "';";
//
//    }
//    else{


 //tester_invite 
          //  String sql = "select * from My_Billing where DateRange  between '" + fdate + "' and '" + tdate + "' and  Brands like '" + BrandN + "' and Bots like '" + BotN + "' group by '" + List + "' and username='"+username+"';";

      String sql = "select SUBSTRING(DateRange, 1, 10) as DateRange,Brands,Bots,Templates,MessagesSubmittedColumn,MessagesSentColumn,MessagesReadColumn,MessagesFailedColumn,MessagesRevokedColumn,DeliveryRateColumn,ReadRateColumn,P2AResponsesColumn,P2AResponseRateColumn,P2AMessagesColumn from tester_invite where DateRange  between '" + fdate + " 00:00:00' and '" + tdate + " 23:59:59' and  Brands like '" + BrandN + "' and Bots like '" + BotN + "' group by '" + List + "' and username='"+username+"';";

          
            //  }
            //  String sql = " select * from traffic_dashboard where DateRangeColumn  between '" + fdate + "' and '" + tdate + "' and  BrandsColumn like '" + BrandN + "' and BotsColumn like '" + BotN + "' group by '" + List + "';";
            dbcon db = new dbcon();
            db.getCon("VNS_RCS");
            ResultSet rs = db.getResult(sql);

            while (rs.next()) {

                String DateRangeColumn = rs.getString("DateRange");
                String BrandsColumn = rs.getString("Brands");
                String BotsColumn = rs.getString("Bots");
                String TemplatesColumn = rs.getString("Templates");
                int MessagesSubmittedColumn = rs.getInt("MessagesSubmittedColumn");
                int MessagesSentColumn = rs.getInt("MessagesSentColumn");
                int MessagesReadColumn = rs.getInt("MessagesReadColumn");
                String MessagesFailedColumn = rs.getString("MessagesFailedColumn");
                String MessagesRevokedColumn = rs.getString("MessagesRevokedColumn");
                String DeliveryRateColumn = rs.getString("DeliveryRateColumn");
                String ReadRateColumn = rs.getString("ReadRateColumn");
                String P2AResponsesColumn = rs.getString("P2AResponsesColumn");
                String P2AResponseRateColumn = rs.getString("P2AResponseRateColumn");
                String P2AMessagesColumn = rs.getString("P2AMessagesColumn");

                if (List.equals("DateRanget")) {
//
%>
<!-- Logging -->
<% logger.info("Processing DateRanget");%>

<table id="myTable" class="col-md-12 table-bordered table-striped table-condensed cf">


    <thead class="cf">
        <tr>
            <th>Date</th>
            <th>Messages Submitted</th>
            <th class="numeric">Messages Sent</th>
            <th class="numeric">Messages Read</th>
            <th class="numeric">Messages Failed</th>
            <th class="numeric">Messages Revoked</th>
            <th class="numeric">Delivery Rate</th>
            <th class="numeric">Messages Revoked</th>
            <th class="numeric">Read Rate</th>
            <th class="numeric">P2A Responses</th>  
            <th class="numeric">P2A Messages</th>
        </tr>
    </thead>

    <tbody>

        <tr>
            <td data-title="Code"><%=DateRangeColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSubmittedColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSentColumn%></td>
            <td data-title="Code"><%=MessagesReadColumn%></td>
            <td data-title="Code"><%=MessagesFailedColumn%></td>
            <td data-title="Code"><%=MessagesRevokedColumn%></td>

            <td data-title="Code"><%=DeliveryRateColumn%></td>
            <td data-title="Code"><%=ReadRateColumn%></td>
            <td data-title="Code"><%=P2AResponsesColumn%></td>
            <td data-title="Code"><%=P2AResponseRateColumn%></td>
            <td data-title="Code"><%=P2AMessagesColumn%></td>


        </tr>

    </tbody>
</table>
<%
} else if (List.equals("Brandst")) {

%>

<!-- Logging -->
<% logger.info("Processing Brandst");%>

<table id="myTable1" class="col-md-12 table-bordered table-striped table-condensed cf">


    <thead class="cf">
        <tr>
            <th>Brands</th>
            <th>Messages Submitted</th>
            <th class="numeric">Messages Sent</th>
            <th class="numeric">Messages Read</th>
            <th class="numeric">Messages Failed</th>
            <th class="numeric">Messages Revoked</th>

            <th class="numeric">Delivery Rate</th>
            <th class="numeric">Messages Revoked</th>
            <th class="numeric">Read Rate</th>
            <th class="numeric">P2A Responses</th>  
            <th class="numeric">P2A Messages</th>
        </tr>
    </thead>

    <tbody>

        <tr>
            <td data-title="Code"><%=BrandsColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSubmittedColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSentColumn%></td>
            <td data-title="Code"><%=MessagesReadColumn%></td>
            <td data-title="Code"><%=MessagesFailedColumn%></td>
            <td data-title="Code"><%=MessagesRevokedColumn%></td>

            <td data-title="Code"><%=DeliveryRateColumn%></td>
            <td data-title="Code"><%=ReadRateColumn%></td>
            <td data-title="Code"><%=P2AResponsesColumn%></td>
            <td data-title="Code"><%=P2AResponseRateColumn%></td>
            <td data-title="Code"><%=P2AMessagesColumn%></td>


        </tr>

    </tbody>
</table>
<%
} else if (List.equals("Botst")) {

%>
<!-- Logging -->
<% logger.info("Processing Botst");%>
<table id="myTable2" class="col-md-12 table-bordered table-striped table-condensed cf">


    <thead class="cf">
        <tr>
            <th>Bots</th>
            <th>Messages Submitted</th>
            <th class="numeric">Messages Sent</th>
            <th class="numeric">Messages Read</th>
            <th class="numeric">Messages Failed</th>
            <th class="numeric">Messages Revoked</th>

            <th class="numeric">Delivery Rate</th>
            <th class="numeric">Messages Revoked</th>
            <th class="numeric">Read Rate</th>
            <th class="numeric">P2A Responses</th>  
            <th class="numeric">P2A Messages</th>
        </tr>
    </thead>

    <tbody>

        <tr>
            <td data-title="Code"><%=BotsColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSubmittedColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSentColumn%></td>
            <td data-title="Code"><%=MessagesReadColumn%></td>
            <td data-title="Code"><%=MessagesFailedColumn%></td>
            <td data-title="Code"><%=MessagesRevokedColumn%></td>

            <td data-title="Code"><%=DeliveryRateColumn%></td>
            <td data-title="Code"><%=ReadRateColumn%></td>
            <td data-title="Code"><%=P2AResponsesColumn%></td>
            <td data-title="Code"><%=P2AResponseRateColumn%></td>
            <td data-title="Code"><%=P2AMessagesColumn%></td>


        </tr>

    </tbody>
</table>
<%
} else if (List.equals("Templates")) {
%> 
<!-- Logging -->
<% logger.info("Processing Templates");%>
<table id="myTable3" class="col-md-12 table-bordered table-striped table-condensed cf">


    <thead class="cf">
        <tr>
            <th>Templates</th>
            <th>Messages Submitted</th>
            <th class="numeric">Messages Sent</th>
            <th class="numeric">Messages Read</th>
            <th class="numeric">Messages Failed</th>
            <th class="numeric">Messages Revoked</th>

            <th class="numeric">Delivery Rate</th>
            <th class="numeric">Messages Revoked</th>
            <th class="numeric">Read Rate</th>
            <th class="numeric">P2A Responses</th>  
            <th class="numeric">P2A Messages</th>
        </tr>
    </thead>

    <tbody>

        <tr>
            <td data-title="Code"><%=TemplatesColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSubmittedColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSentColumn%></td>
            <td data-title="Code"><%=MessagesReadColumn%></td>
            <td data-title="Code"><%=MessagesFailedColumn%></td>
            <td data-title="Code"><%=MessagesRevokedColumn%></td>

            <td data-title="Code"><%=DeliveryRateColumn%></td>
            <td data-title="Code"><%=ReadRateColumn%></td>
            <td data-title="Code"><%=P2AResponsesColumn%></td>
            <td data-title="Code"><%=P2AResponseRateColumn%></td>
            <td data-title="Code"><%=P2AMessagesColumn%></td>


        </tr>

    </tbody>
</table>
<%
} else if (List.equals("ALL")) {

%>
<!-- Logging -->
<% logger.info("Processing ALL");%>
<table id="myTable4" class="col-md-12 table-bordered table-striped table-condensed cf">


    <thead class="cf">
        <tr>
            <th>Date</th>
            <th>Bots</th>
            <th>Brands</th>
            <th>Templates</th>
            <th>Messages Submitted</th>
            <th class="numeric">Messages Sent</th>
            <th class="numeric">Messages Read</th>
            <th class="numeric">Messages Failed</th>
            <th class="numeric">Messages Revoked</th>

            <th class="numeric">Delivery Rate</th>
            <th class="numeric">Messages Revoked</th>
            <th class="numeric">Read Rate</th>
            <th class="numeric">P2A Responses</th>  
            <th class="numeric">P2A Messages</th>
        </tr>
    </thead>

    <tbody>

        <tr>
            <td data-title="Code"><%=DateRangeColumn%></td><!-- comment -->
            <td data-title="Code"><%=BrandsColumn%></td><!-- comment -->
            <td data-title="Code"><%=BotsColumn%></td><!-- comment -->
            <td data-title="Code"><%=TemplatesColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSubmittedColumn%></td><!-- comment -->
            <td data-title="Code"><%=MessagesSentColumn%></td>
            <td data-title="Code"><%=MessagesReadColumn%></td>
            <td data-title="Code"><%=MessagesFailedColumn%></td>
            <td data-title="Code"><%=MessagesRevokedColumn%></td>

            <td data-title="Code"><%=DeliveryRateColumn%></td>
            <td data-title="Code"><%=ReadRateColumn%></td>
            <td data-title="Code"><%=P2AResponsesColumn%></td>
            <td data-title="Code"><%=P2AResponseRateColumn%></td>
            <td data-title="Code"><%=P2AMessagesColumn%></td>


        </tr>

    </tbody>
</table>
<%
} else {
%>
<center>No Records Found</center>
    <%
                    }
                }

                db.closeConection();
          // }
        } catch (Exception e) {
            // Log any exceptions using the logger
            logger.severe("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    %>

