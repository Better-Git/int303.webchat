package servlet;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import java.io.Closeable;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Content;

/**
 * Form Handling Servlet Most of the action for this sample is in
 * webapp/guestbook.jsp, which displays the {@link Greeting}'s. This servlet has
 * one method {@link #doPost(<#HttpServletRequest req#>,
 * <#HttpServletResponse resp#>)} which takes the form data and saves it.
 */
public class SignRoomServlet extends HttpServlet {

    public static Content testMain;
    public static String testRoomName;
    public static String testContent;
    public static String testUsername;
    // Process the http POST of the form

    public void doSomething() throws IOException {
        ObjectifyService.run(new VoidWork() {
            public void vrun() {
                Content main;
                UserService userService = UserServiceFactory.getUserService();
                User user = userService.getCurrentUser();  // Find out who the user is.

                String roomName = SignRoomServlet.testRoomName;
                String content = SignRoomServlet.testContent;
                String username = SignRoomServlet.testUsername;

                main = new Content(roomName, content, username);
                setUp();
                ObjectifyService.ofy().save().entity(main).now();
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
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Content main;

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();  // Find out who the user is.

        testRoomName = req.getParameter("roomName");
        testContent = req.getParameter("message");
        testUsername = req.getParameter("username");
        //room = new Room(roomName, content);
        doSomething();
        tearDown();
        // Use Objectify to save the greeting and now() is used to make the call synchronously as we
        // will immediately get a new page using redirect and we want the data to be present.

        //ObjectifyService.ofy().save().entity(main).now();
        resp.sendRedirect("/FormHandle?roomName=" + testRoomName + "&username=" + testUsername);
    }
}