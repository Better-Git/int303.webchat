package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet annotation class LoginServlet
public class FormHandle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get request parameters
        String rm = request.getParameter("room");
        String usr = request.getParameter("username");
        /*
        boolean prvt = (request.getParameter("mk-private") != null);
        String pswd = request.getParameter("password");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify = RecaptchaVerify.verify(gRecaptchaResponse);
        */

        // Check conditions
        // if (FormResponse.RoomValid(rm) && FormResponse.UserValid(usr)) {
            request.setAttribute("room", rm);
            request.setAttribute("username", usr);
            /*
            if (prvt && FormResponse.PassValid(pswd)) {
                request.setAttribute("mk-private", prvt);
                request.setAttribute("password", pswd);
            }
            */
            getServletContext().getRequestDispatcher("/WEB-INF/chatroom.jsp").forward(request, response);
            // System.out.println("Room : " + rm + " — user : " + usr + " — private : " + prvt + " — password : " + pswd + " — verify : " + verify);
        // } else {
            // request.setAttribute("verify", String.valueOf(verify));
            // getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        // }
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