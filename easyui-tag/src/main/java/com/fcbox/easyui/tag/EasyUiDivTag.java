package com.fcbox.easyui.tag;

import com.fcbox.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * @author hexuming
 */
public class EasyUiDivTag extends BodyTagSupport {

    private static final long serialVersionUID = 598209081781510962L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String style;

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuffer htmlSb = new StringBuffer();
            htmlSb.append("<div id=\"").append(this.getId()).append("\"");

            if (!TriRegulation.isEmpty(this.getStyle())) {
                htmlSb.append(" style=\"").append(this.getStyle()).append("\" ");
            }

            htmlSb.append(">\n");

            pageContext.getOut().write(htmlSb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write("</div>\n");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}