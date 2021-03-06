package model;

import com.googlecode.objectify.ObjectifyService;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP
 * is run. This is required to let JSP's access Ofy.
 */
public class OfyHelper implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup
        // request.
        ObjectifyService.register(Content.class);
        ObjectifyService.register(Room.class);
        ObjectifyService.register(MyUser.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}