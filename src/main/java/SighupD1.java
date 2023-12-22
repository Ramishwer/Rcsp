/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import db.dbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

/**
 *
 * @author Admin
 */
public class SighupD1 extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SendMailServlet.class.getName());


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SighupD1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SighupD1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();

//        String LogDet = "abc";
//        String LogPass = "123";
//        String LogOTp = "000000";
        //  request.getParameter("Username");
        String LogDet = request.getParameter("Username");;
        String LogPass = request.getParameter("Pass");;
        String LogOTp = request.getParameter("OTP_VAL");;
        //  String Generated_otp = "N/A";
      logger.info(LogDet);
        logger.info(LogOTp);

           // String ParaOTp="000000";
          HttpSession se12 = request.getSession(false);
         String ParaOTp = (String) se12.getAttribute("Genotp");
         
        
         String Login_data = "insert into loginrbm (Username ,Pass) values ('" + LogDet + "',MD5('"+ LogPass +"'))";
      //  "insert into LoginRbm (Username ,Pass) values ('iqbal@zestinsolutions.com',PassMD5('123456'))";
        dbcon db = new dbcon();
        
        db.getCon("VNS_RCS");
        //   select Username ,Pass from LoginRbm where Username = 'iqbal@zestinsolutions.com' and Pass=MD5('Iqbal');
        
            if(LogOTp.equals(ParaOTp)){
        try {

            int i = db.setUpdate(Login_data);
            out.print("Rows updated:");
             logger.info("Rows updated:");
          
        } catch (Exception e) {
            out.println(e);
            out.print("Error In Updation of Record");
            logger.info("Error In Updation of Record");
        }
    }else{
               out.print("Wrong OTP"); 
               logger.info("Wrong OTP");
               
            }
    
        db.closeConection();
        logger.info("Connection closed");

    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description"; 
    }// </editor-fold>

}
