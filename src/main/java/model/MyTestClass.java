package model;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import java.io.Closeable;
import java.io.IOException;

public class MyTestClass {

    Closeable session;

    //@BeforeMethod
    public void setUp() {
        session = ObjectifyService.begin();
    }

    public void doAny() {
        ObjectifyService.run(new VoidWork() {
            public void vrun() {
                // anything you want here, you can now use ofy()
            }
        });
    }

    //@AfterMethod
    public void tearDown() throws IOException {
        session.close();
    }
}