 


<%@page import="java.util.logging.Logger"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="db.dbcon"%>

<%@include file="session.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%
    // Create a logger instance
    Logger logger = Logger.getLogger("BrandtrafficJSP");

    try {
    // String username="ram";

//        // Get the username from the session
//        HttpSession session1 = request.getSession();
//        String username = (String) session1.getAttribute("Dusername");
//
//        if (username == null || username.isEmpty()) {
//            // If the username is not available in the session, handle it (e.g., redirect to login)
//         //   response.sendRedirect("login.jsp");
//        } else {

            String fdate = request.getParameter("fromd");
            String tdate = request.getParameter("tod");

            // Get the username from the session
//    HttpSession session1 = request.getSession();
//    String username = (String) session1.getAttribute("username");
//
//    String sql = "select distinct BrandsColumn from traffic_dashboard where DateRangeColumn between  '" + fdate + "' and '" + tdate + "' and username = '" + username + "';";
//    
         //   String sql = "select distinct Brands from My_Billing where DateRange  between  '" + fdate + "' and '" + tdate + "' and username='"+username+"';";
//tester_invite
   
       String sql = "select distinct Brands from tester_invite where DateRange  between  '" + fdate + " 00:00:00' and '" + tdate + " 23:59:59' and username='"+username+"';";


            dbcon db = new dbcon();
            db.getCon("VNS_RCS");
            ResultSet rs = db.getResult(sql);
            JSONArray jsonArray = new JSONArray();
            JSONObject obj = new JSONObject();
            while (rs.next()) {
                jsonArray.put(rs.getString(1));

            }

            obj.put("Array1", jsonArray);
            out.print(obj);

            db.closeConection();

            // Log a message indicating the successful execution of the code
            logger.info("Query executed successfully.");
    //  }
    } catch (Exception e) {
        // Log the exception details
        logger.severe("An error occurred: " + e.getMessage());
        // Print the stack trace for debugging purposes
        e.printStackTrace();
        // Send an error response to the client
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
%>

