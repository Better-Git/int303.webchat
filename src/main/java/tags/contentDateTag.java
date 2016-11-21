/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import com.googlecode.objectify.ObjectifyService;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import model.Content;
import servlet.LogOut;

/**
 *
 * @author OWNER
 */
public class contentDateTag extends SimpleTagSupport {

    
    
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        HttpSession hs = LogOut.req.getSession();
        List<Content> contents = ObjectifyService.ofy()
                    .load()
                    .type(Content.class) // We want only Contents
                    .ancestor(hs.getAttribute("keyRoom")) // Anyone in this room
                    .order("-date") // Most recent first - date is indexed.
                    //.limit(1000) // Only show 1000 of them.
                    .list();
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");
             for (Content c : contents) {
                        //pageContext.setAttribute("c", c);
                        SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy hh:mm a");
                        String d = dt.format(c.date);
                        //pageContext.setAttribute("date", d);
                
                out.println("<!-- Your content goes here -->");
                out.println("<div class='card mdl-shadow--2dp' style='margin: 0 auto 20px auto; width: 72%;'>");
                out.println("<div class='mdl-card__supporting-text' >");
                out.println("<%-- <ti:getTime /> --%>");
                out.println("<div style='font-size: 20px; margin-top: 15px;'>"
                        + "<b>${fn:escapeXml("+c.sender+")}&nbsp;&nbsp;:</b>&nbsp;&nbsp;"
                        + "${fn:escapeXml("+c.content+")}<br>"
                        + "<p style='text-size:5px;'>"+d+"</p>"
                        + "</div>"
                        + "</div>\n" +"</div>");

                    
                }
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in contentDateTag tag", ex);
        }
    }

    
    
}
