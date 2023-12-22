/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.rbm.samples.FirstAgent;
import db.dbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class send_Tester_Invite extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {

            String mobile = request.getParameter("mobile");
            String status = "pending";
            String rcs_status = "pending";
            String brand_name = "virtuoso-netsoft";
            String bot_id = "cQNtL2S2giu4oU3D";
            String template = "NA";
            HttpSession s = request.getSession(false);
            String username = (String) s.getAttribute("Dusername");
            int BasicMessageCount=1;
            out.println(username);
           

            out.close();

            if (mobile == null) {
                out.println("not null");
            } else {

                dbcon db = new dbcon();
                FirstAgent fa = new FirstAgent("+91" + mobile);
                fa.sendTesterInvite();
                try {
                    db.getCon("VNS_RCS");

                    String sql = "INSERT INTO tester_invite (mobile, status, rcs_status, Brands, Bots, Templates, DateRange, username,TransactionType,BasicMessageCount) VALUES ('" + mobile + "','" + status + "','" + rcs_status + "','" + brand_name + "','" + bot_id + "','" + template + "', NOW(), '" + username + "','Domastic','"+BasicMessageCount+"')";

                    int pst=db.setUpdate(sql);

                    System.out.println(pst);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
