package servlet;

import model.Content;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatHandle extends HttpServlet {

    private static String chatRoomName;
    private static String chatContent;
    private static String chatUsername;

    // Process the http POST of the form
    public void doSomething() throws IOException {
        ObjectifyService.run(new VoidWork() {
            @Override
            public void vrun() {
                if (chatContent != "") {
                try {
                    Content main = new Content(ChatHandle.chatRoomName, ChatHandle.chatContent, ChatHandle.chatUsername);
                    setUp();
                    ObjectifyService.ofy().save().entity(main).now();
                    tearDown();
                } catch (IOException ex) {
                    Logger.getLogger(ChatHandle.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String color = "";
        Cookie[] cookie = req.getCookies();
        for (Cookie c : cookie) {
            if (c.getName().equals("theme")) {
                color = c.getValue();
            }
        }

        chatRoomName = req.getParameter("roomName");
        chatContent = req.getParameter("message");
        chatUsername = req.getParameter("username");

        //room = new Room(roomName, content);
        doSomething();

        // Use Objectify to save the greeting and now() is used to make the call synchronously as we
        // will immediately get a new page using redirect and we want the data to be present.
        //ObjectifyService.ofy().save().entity(room).now();
        req.setAttribute("roomName", chatRoomName);
        req.setAttribute("username", chatUsername);
        req.setAttribute("cl", color);
        getServletContext().getRequestDispatcher("/WEB-INF/chatroom.jsp").forward(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String color = "";
        Cookie[] cookie = req.getCookies();
        for (Cookie c : cookie) {
            if (c.getName().equals("theme")) {
                color = c.getValue();
            }
        }

        chatRoomName = req.getParameter("roomName");
        chatContent = req.getParameter("message");
        chatUsername = req.getParameter("username");

        //room = new Room(roomName, content);
        doSomething();

        // Use Objectify to save the greeting and now() is used to make the call synchronously as we
        // will immediately get a new page using redirect and we want the data to be present.
        //ObjectifyService.ofy().save().entity(room).now();
        req.setAttribute("roomName", chatRoomName);
        req.setAttribute("username", chatUsername);
        req.setAttribute("cl", color);
        getServletContext().getRequestDispatcher("/WEB-INF/chatroom.jsp").forward(req, res);
    }
}
