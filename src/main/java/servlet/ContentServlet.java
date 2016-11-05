package servlet;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

/**
 * Form Handling Servlet Most of the action for this sample is in
 * webapp/guestbook.jsp, which displays the {@link Greeting}'s. This servlet has
 * one method {@link #doPost(<#HttpServletRequest req#>,
 * <#HttpServletResponse resp#>)} which takes the form data and saves it.
 */
public class ContentServlet extends HttpServlet {

    // Process the http POST of the form
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Room room;

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();  // Find out who the user is.

        String roomName = req.getParameter("room");
        String content = req.getParameter("content");
        room = new Room(roomName, content);

        // Use Objectify to save the greeting and now() is used to make the call synchronously as we
        // will immediately get a new page using redirect and we want the data to be present.
        ObjectifyService.ofy().save().entity(room).now();

        resp.sendRedirect("/chatroom.jsp?room=" + room);
    }
}