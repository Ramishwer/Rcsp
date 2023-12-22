import db.dbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginD1")
public class LoginD1 extends HttpServlet {
     private static final Logger logger = Logger.getLogger(SendMailServlet.class.getName());

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // Get user input
        String Username =  request.getParameter("Username");;
        String Pass =request.getParameter("Pass");
        
//        String Username  = "iqbal@zestinsolutions.com";
//        String Pass ="00";
          
        String Dusername = "N/A";
        String Dpassword =  "N/A";
        boolean Status=false;
         
          String Login_data = "SELECT Username, Pass, Status FROM loginrbm   WHERE Username ='"+ Username +"' AND Pass = MD5('"+Pass+"')AND Status=true;";
      //    out.println(Login_data);
            dbcon db = new dbcon();
        db.getCon("VNS_RCS");
          
        
              ResultSet rs = db.getResult(Login_data);
        
        try { 
          //   out.println("try");
            if (rs.next()) {
                Dusername = rs.getString("Username");
                Dpassword = rs.getString("Pass");
                Status = rs.getBoolean("Status");
                 logger.info("Record found");
      //     out.println("if");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LoginD1.class.getName()).log(Level.SEVERE, null, ex);
           Dusername="SQLException";
            logger.info("SQLException");
        }
        
// out.println(Dusername+"Dusername"); out.println("Username"+Username); out.println("Dpassword"+Dpassword); out.println("Status"+Status);
        // Perform basic authentication (in a real-world scenario, you'd use a database)
        if (Dusername.equals(Username) && Dpassword != null && Dusername != null && Status == true) {
            // Authentication successful

            // Create a session cookie      
             HttpSession se12 = request.getSession();
             se12.setAttribute("Dusername", Dusername);
            Cookie userCookie = new Cookie("Dusername", Dusername);
            userCookie.setMaxAge(30 * 60); // Set cookie expiration time (in seconds)
            response.addCookie(userCookie);
                     logger.info("record found in db ");
            // Redirect to a welcome page
             out.println("Correct");
        }else if(Dusername.equals(Dpassword) && Dpassword != null && Dusername != null && Status == false){
           
           out.println("Under Verification ");
             logger.info("Under Verification");
        }
        
        else{
              out.println("NoAccount");
             logger.info("NoAccount");
        }
    }
}
