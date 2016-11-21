package servlet;

import model.MyUser;
import model.Room;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

public class LogOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static String chatRoomName;

    private static String chatUsername;

    public static HttpServletRequest req;

    private static MyUser mine;

    // Process the http POST of the form
    public void doSomething() throws IOException {
        ObjectifyService.run(new VoidWork() {
            @Override
            public void vrun() {

                try {
                    /* Key<Room> theRoom = Key.create(Room.class, req.getParameter("roomName"));
                    List<MyUser> allUser = ObjectifyService.ofy()
                            .load()
                            .type(MyUser.class)
                            .ancestor(theRoom)
                            .order("-date")
                            .list();
                    int count = 0;
                    for (int i = 0; i < allUser.size(); i++) {
                        System.out.println("On Top if chatusername");
                        if (LogOut.chatUsername.equals(allUser.get(i).getuName())) {
                            System.out.println("In If"+count);
                            count++;
                            System.out.println("Count success");
                        }
                    }
                    System.out.println("for success");*/
                    // if (count == 0) {

                    System.out.println("Virtaul MyUser main In Deleting");
                    MyUser main = mine;
                    setUp();
                    ObjectifyService.ofy().delete().entity(main).now();
                    System.out.println("Delete Success");
                    tearDown();
                    System.out.println("tearDown Success");
                    //}
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession hs = request.getSession();
        
        LogOut.chatUsername = (String)hs.getAttribute("uname");
        LogOut.chatRoomName =(String) hs.getAttribute("rname");
        System.out.println("after getting parameter 2 value" + LogOut.chatUsername + LogOut.chatRoomName);
            
            
        Key<Room> theRoom = (Key<Room>)hs.getAttribute("keyRoom");
        List<MyUser> allUser = ObjectifyService.ofy()
                .load()
                .type(MyUser.class)
                .ancestor(theRoom)
                .order("-date")
                .list();
        int count = 0;
        //find mine
        for (int i = 0; i < allUser.size(); i++) {
            System.out.println("On Top if chatusername like the name");
            if (LogOut.chatUsername.equals(allUser.get(i).getuName())) {
                System.out.println("Before found user" + count);
                count++;
                mine = allUser.get(i);
                System.out.println("After found user");
            }
        }
//        if(count == 0){
//            
//        }
        
        //        System.out.println("Before myUser:" + request.getAttribute("myUser"));
//        mine = (MyUser) request.getAttribute("myUser");
        System.out.println(mine);
        System.out.println("Do after mine");
        doSomething();
        System.out.println("after do someting");
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
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