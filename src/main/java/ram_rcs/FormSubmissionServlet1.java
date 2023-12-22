package ram_rcs;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import db.dbcon;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Ram Ishwer Kumar
 */
//@WebServlet("/FormSubmissionServlet1")
@MultipartConfig(maxFileSize = 33554432) // 16MB
public class FormSubmissionServlet1 extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FormSubmissionServlet1.class.getName());

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
            out.println("<title>Servlet FormSubmissionServlet1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormSubmissionServlet1 at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

//         // Get the session and retrieve the username
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
//
//        // Check if the username is available in the session
//        if (username == null || username.isEmpty()) {
//            // Redirect to login page or handle the case where the user is not logged in
//            response.sendRedirect("login.jsp");
//            return;
//        }
        String username = "ram";

        dbcon db = new dbcon();
        db.getCon("VNS_RCS"); // Update the database name accordingly

        Part filePart = request.getPart("image");
        String fileName = getFileName(filePart);
        //  out.println("fileName" + fileName);
        LOGGER.log(Level.INFO, "File uploaded successfully: {0}", fileName);

        String uploadDir = "/usr/local/apache-tomcat-9.0.84/webapps/RCS3/src/main/webapp/image";

        // Save the file to the server
        Path filePath = Paths.get(uploadDir, fileName);

        String filepath_dh = uploadDir + fileName;
        // out.println("filepath_dh"+filepath_dh);

        try ( InputStream input = filePart.getInputStream()) {
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Part filePart1 = request.getPart("bannerimage");
        String fileName1 = getFileName(filePart1);
        //out.println("fileName" + fileName1);
        LOGGER.log(Level.INFO, "File uploaded successfully: {0}", fileName1);

        String uploadDir1 = "/usr/local/apache-tomcat-9.0.84/webapps/RCS3/src/main/webapp/image";

        // Save the file to the server
        Path filePath1 = Paths.get(uploadDir1, fileName1);

        String filepath_dh1 = uploadDir1 + fileName1;

        // out.println("filepath_dh"+filepath_dh);
        try ( InputStream input1 = filePart1.getInputStream()) {
            Files.copy(input1, filePath1, StandardCopyOption.REPLACE_EXISTING);
        }

        FormData formData = new FormData();

        formData.setPhoneNumbers(request.getParameterValues("primaryPhoneNumber"));
        formData.setDevelopmentPlatform(request.getParameter("developmentPlatform"));
        formData.setChatbotWebhook(request.getParameter("chatbotwebhook"));
        formData.setEmailAddresses(request.getParameterValues("emailAddress"));
        formData.setWebsiteAddresses(request.getParameterValues("websiteUrl"));
        formData.setTermsOfUseUrl(request.getParameter("urlterm"));
        formData.setPrivacyPolicyUrl(request.getParameter("urlprivacy"));
        formData.setLanguagesSupported(request.getParameter("languagessupport"));
        formData.setBotName(request.getParameter("botname"));
        // formData.setBotMessageTypes(request.getParameterValues("botMessageType"));
        formData.setMessageType(request.getParameter("botMessageType"));

        formData.setBrandName(request.getParameter("brandName"));
        formData.setShortDescription(request.getParameter("shortdescription"));
        formData.setFavcolors(request.getParameter("favcolors"));

        formData.setWebsiteInput(request.getParameter("websiteInput"));
        formData.setBannerWebsiteInput(request.getParameter("bannerwebsiteInput"));

        String concatenatedPhoneNumbers = String.join(", ", formData.getPhoneNumbers());

        String DevelopmentPlatform = formData.getDevelopmentPlatform();
        String ChatbotWebhook = formData.getChatbotWebhook();

        String concatenatedemailAddresses = String.join(", ", formData.getEmailAddresses());

        String TermsOfUseUrl = formData.getTermsOfUseUrl();

        String PrivacyPolicyUrl = formData.getPrivacyPolicyUrl();
        String LanguagesSupported = formData.getLanguagesSupported();
        //String BotMessageTypes = String.join(",", formData.getBotMessageTypes());
        //  String BotMessageTypes=formData.getMessageType();
        String concatenatedBotMessageTypes = formData.getMessageType();

        out.println("concatenatedBotMessageTypes:" + concatenatedBotMessageTypes);

        String BrandName = formData.getBrandName();
        String ShortDescription = formData.getShortDescription();

        String concatenatedWebsiteUrls = String.join(", ", formData.getWebsiteAddresses());
        String Favcolors = formData.getFavcolors();
        String imageurl = formData.getWebsiteInput();
        String baneerurl = formData.getBannerWebsiteInput();
        String botname = formData.getBotName();

        String agree = request.getParameter("agree");

        // Check if the 'agree' parameter is not null and is equal to "on"
        if (agree != null && agree.equals("on")) {
            response.getWriter().println("Form submitted successfully");
        } else {
            response.getWriter().println("Form not submitted successfully");
        }

        try {

            String insertQuery = "INSERT INTO rbm_table (phone_number, development_platform, chatbot_webhook, email_address, "
                    + "terms_of_use_url, privacy_policy_url, languages_supported,bot_message_types, brand_name, "
                    + "short_description, website_url,color,url_data,banner_url_data,image_data,banner_image_data,bot_name,form_checkbox,username) VALUES ('" + concatenatedPhoneNumbers + "','" + DevelopmentPlatform + "','" + ChatbotWebhook + "','" + concatenatedemailAddresses + "','" + TermsOfUseUrl + "','" + PrivacyPolicyUrl + "','" + LanguagesSupported + "','" + concatenatedBotMessageTypes + "','" + BrandName + "','" + ShortDescription + "','" + concatenatedWebsiteUrls + "','" + Favcolors + "','" + imageurl + "','" + baneerurl + "','" + filepath_dh + "','" + filepath_dh1 + "','" + botname + "','" + agree + "','" + username + "')";

            int rows_affected = db.setUpdate(insertQuery);
            LOGGER.log(Level.INFO, "Rows affected: {0}", rows_affected);

            out.println(rows_affected);
            // Close the connection bannerInputStream
            //   connection.close();website_url,color,url_data,image_data,banner_url_data,banner_image_data
            out.println("<h2>Data successfully inserted into the database!</h2>");

            // request.getRequestDispatcher("mypage.jsp").forward(request, response);
        } catch (Exception e) {
            //   out.println("<h2>Error: " + e.getMessage() + "</h2>");
            LOGGER.log(Level.SEVERE, "Error: {0}", e.getMessage());

            e.printStackTrace();
        } finally {
            // Close resources, e.g., close the db connection
            db.closeConection();
        }
    }

    public String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");

            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

// Use the retrieved username in your database query
//            String insertQuery = "INSERT INTO rbm_table (phone_number, development_platform, chatbot_webhook, email_address, "
//                    + "terms_of_use_url, privacy_policy_url, languages_supported,bot_message_types, brand_name, "
//                    + "short_description, website_url,color,url_data,banner_url_data,image_data,banner_image_data,bot_name,form_checkbox, username) "
//                    + "VALUES ('" + concatenatedPhoneNumbers + "','" + DevelopmentPlatform + "','" + ChatbotWebhook + "','"
//                    + concatenatedemailAddresses + "','" + TermsOfUseUrl + "','" + PrivacyPolicyUrl + "','" + LanguagesSupported + "','"
//                    + concatenatedBotMessageTypes + "','" + BrandName + "','" + ShortDescription + "','" + concatenatedWebsiteUrls + "','"
//                    + Favcolors + "','" + imageurl + "','" + baneerurl + "','" + filepath_dh + "','" + filepath_dh1 + "','" + botname + "','"
//                    + agree + "','" + username + "')";
