package servlet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class TimeTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MMMM dd, yyyy 'at' h:mm a", Locale.ENGLISH);
        JspWriter out = getJspContext().getOut();
        out.println(ft.format(now));
    }
}