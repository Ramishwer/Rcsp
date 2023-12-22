 

<%@include file="session.jsp" %>
<%@page import="java.util.logging.Logger"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="db.dbcon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="session.jsp" %>


<%

    // Create a logger instance
    Logger logger = Logger.getLogger("templatetrafficJSP");

    try {
     //   String username="ram";

//      HttpSession session1 = request.getSession();
//        String username = (String) session1.getAttribute("username");
//
//        if (username == null || username.isEmpty()) {
//            // If the username is not available in the session, handle it (e.g., redirect to login)
//         //   response.sendRedirect("login.jsp");
//        } else {
        String fdate = request.getParameter("fromd");
        String tdate = request.getParameter("tod");
        String BrandN = request.getParameter("BrandN");
        String BotN = request.getParameter("BotN");
        String templateN = request.getParameter("template");

//    String sql = "SELECT DISTINCT TemplatesColumn FROM traffic_dashboard WHERE DateRangeColumn BETWEEN '" + fdate + "' AND '" + tdate + "' AND BotsColumn LIKE '" + BotN + "' AND BrandsColumn LIKE '" + BrandN + "' AND username = '" + username + "';";
//traffic
//tester_invite
        // String sql = "select distinct Templates from My_Billing where DateRange  between  '" + fdate + "' and '" + tdate + "' and Bots like '" + BotN + "' and username = '"+username+"';";
        String sql = "select distinct Templates from tester_invite where DateRange  between  '" + fdate + " 00:00:00' and '" + tdate + " 23:59:59' and Bots like '" + BotN + "' and username = '" + username + "';";

        dbcon db = new dbcon();
        db.getCon("VNS_RCS");
        ResultSet rs = db.getResult(sql);
        JSONArray jsonArray = new JSONArray();
        JSONObject obj = new JSONObject();          // BotsColumn
        while (rs.next()) {
            jsonArray.put(rs.getString(1));

        }

        obj.put("Array1", jsonArray);
        out.print(obj);

        db.closeConection();

        // Log a message indicating the successful execution of the code
        logger.info("Query executed successfully.");
        // }
    } catch (Exception e) {
        // Log the exception details
        logger.severe("An error occurred: " + e.getMessage());
        // Print the stack trace for debugging purposes
        e.printStackTrace();
        // Send an error response to the client
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
    } finally {
        // Log a message indicating the end of the block
        logger.info("End processing.");
    }
%>
