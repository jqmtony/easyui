package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class EasyUiAccordionTag extends BodyTagSupport {

    private static final long serialVersionUID = -4306262114383104919L;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // Accoutdion标识;

    private String style; // 自定义样式

    private boolean fit = true; // 是否自动大小

    private boolean border = true; // 有无边框

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<div class=\"easyui-accordion\" ");
        htmlSb.append(" id=\"").append(this.getId()).append("\" ");
        htmlSb.append(" fit=\"").append(this.isFit()).append("\" ");
        htmlSb.append(" border=\"").append(this.isBorder()).append("\" ");

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

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
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

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

}
