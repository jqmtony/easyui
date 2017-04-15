package com.fcbox.easyui.tag;

import com.fcbox.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


/**
 * @author hexuming
 */
public class EasyUiMenuTag extends BodyTagSupport {

    private static final long serialVersionUID = -8408069492551446646L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String style; // 样式

    private boolean fit;

    @Override
    public int doStartTag() throws JspException {

        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<div class=\"easyui-menu\" ");
        htmlSb.append(" id=\"").append(this.getId()).append("\" ");
        // htmlSb.append(" fit=\"").append(this.isFit()).append("\" ");

        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\" ");
        }

        htmlSb.append(">");

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {

        try {
            pageContext.getOut().write("</div>");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
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
