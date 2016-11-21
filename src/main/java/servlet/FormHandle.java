package servlet;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Content;
import model.MyUser;
import model.Room;

public class FormHandle extends HttpServlet {

    private static String chatRoomName;

    private static String chatUsername;

    private static HttpServletRequest req;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get request parameters
        req = request;
        chatRoomName = request.getParameter("roomName");
        chatUsername = request.getParameter("username");
        System.out.println(chatRoomName + chatUsername);
        request.setAttribute("roomName", chatRoomName);
        request.setAttribute("username", chatUsername);
        System.out.println("Before doSomething success");
        doSomething();
        System.out.println("doSomething success");
        HttpSession hs = request.getSession(true);
        hs.setAttribute(chatUsername, chatUsername);
        getServletContext().getRequestDispatcher("/WEB-INF/chatroom.jsp").forward(request, response);
    }

    // Process the http POST of the form
    public void doSomething() throws IOException {
        ObjectifyService.run(new VoidWork() {
            @Override
            public void vrun() {

                try {
                    Key<Room> theRoom = Key.create(Room.class, req.getParameter("roomName"));
                    List<MyUser> allUser = ObjectifyService.ofy()
                            .load()
                            .type(MyUser.class)
                            .ancestor(theRoom)
                            .order("-date")
                            .list();
                    int count = 0;
                    for (int i = 0; i < allUser.size(); i++) {
                        System.out.println("On Top if chatusername");
                        if (FormHandle.chatUsername.equals(allUser.get(i).getuName())) {
                            System.out.println("In If"+count);
                            count++;
                            System.out.println("Count success");
                        }
                    }
                    System.out.println("for success");
                    if (count == 0) {
                        System.out.println("Creat MyUser main");
                        MyUser main = new MyUser(FormHandle.chatUsername, FormHandle.chatRoomName);
                        setUp();
                        ObjectifyService.ofy().save().entity(main).now();
                        tearDown();
                        System.out.println("tearDown Success");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ChatHandle.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
    Closeable session;

    //@BeforeMethod
    public void setUp() {
        session = ObjectifyService.begin();
    }

    //@AfterMethod
    public void tearDown() throws IOException {
        session.close();
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
