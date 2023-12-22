<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="db.dbcon" %>
<%@ page contentType="application/json" pageEncoding="UTF-8" %>

<%@include file="session.jsp" %>
<%
   
            
    
    // Create a logger instance for the current class (you can customize the name)
    Logger logger = Logger.getLogger("BillingDashboardJSP");

    try {

//        HttpSession session1 = request.getSession();
//        String username = (String) session1.getAttribute("Dusername");
//
//        
//
//        // Check if the username is available in the session
//        if (username == null || username.isEmpty()) {
//            // If not, redirect to the login page
//           // response.sendRedirect("login.jsp");
//        } else {
       
            String fdate = request.getParameter("fromd");
            String tdate = request.getParameter("tod");
            String BrandN = request.getParameter("BrandN");
            String BotN = request.getParameter("BotList");
            String List = request.getParameter("listby");
            // String username = request.getParameter("username");
//tester_invite
        

//        String sql = "";
//
//        if (List.equals("ALL")) {
//            sql = "SELECT * FROM My_Billing WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND Bots LIKE '" + BotN + "';";
//        } else {



          //  String sql = "SELECT * FROM My_Billing WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND Bots LIKE '" + BotN + "' GROUP BY '" + List + "' and username='"+username+"';";
           
    //  String sql = "SELECT * FROM tester_invite WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND Bots LIKE '" + BotN + "' GROUP BY '" + List + "' and username='"+username+"';";
      String sql = "SELECT SUBSTRING(DateRange, 1, 10) as DateRange,TransactionType,sum(BasicMessageCount) as BasicMessageCount,A2PSingleMessageCount,A2PConversationCount,P2AConversationCount,Brands,Bots FROM tester_invite "
      + "WHERE DateRange BETWEEN '" + fdate + 
      " 00:00:00' AND '" + tdate + " 23:59:59' AND Brands LIKE '" + BrandN + "' AND Bots LIKE '" + BotN + "' GROUP BY '" + List + "' "
      + "and username='"+username+"';";
      

//select  SUBSTRING(Time, 1, 10)as DateRange,Msg,Usr from ChatP;  

//      }

//     if (List.equals("ALL")) {
//        sql = "SELECT * FROM billing_dashboard WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND bots LIKE '" + BotN + "' AND username = '" + username + "';";
//    } else {
//        sql = "SELECT * FROM billing_dashboard WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND bots LIKE '" + BotN + "' GROUP BY '" + List + "' AND username = '" + username + "';";
//    }
//if (List.equals("ALL")) {
//    sql ="SELECT * FROM billing_dashboard WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND bots LIKE '" + BotN + "';";
//} else {
//    sql = "SELECT * FROM billing_dashboard WHERE DateRange BETWEEN '" + fdate + "' AND '" + tdate + "' AND Brands LIKE '" + BrandN + "' AND bots LIKE '" + BotN + "' GROUP BY " + List + ";";
//}
            dbcon db = new dbcon();
            db.getCon("VNS_RCS");
            ResultSet rs = db.getResult(sql);

            List<Map<String, Object>> dataList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("DateRange", rs.getString("DateRange"));
                data.put("TransactionType", rs.getString("TransactionType"));
                data.put("BasicMessageCount", rs.getInt("BasicMessageCount"));
                data.put("A2PSingleMessageCount", rs.getInt("A2PSingleMessageCount"));
                data.put("A2PConversationCount", rs.getInt("A2PConversationCount"));
                data.put("P2AConversationCount", rs.getInt("P2AConversationCount"));
                data.put("Brands", rs.getString("Brands"));
                data.put("Bots", rs.getString("Bots"));

                dataList.add(data);
            }

            db.closeConection();

            // Convert dataList to JSON using Gson sum(BasicMessageCount),sum
            Gson gson = new Gson();
            String jsonData = gson.toJson(dataList);

            // Write JSON data to the response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonData);
            // Log a message indicating the successful execution
            logger.info("Successfully retrieved and processed billing data.");
     //   }
    } catch (Exception e) {
        // Log any exceptions using the logger
        logger.severe("An error occurred: " + e.getMessage());
        e.printStackTrace();
    }
%>

