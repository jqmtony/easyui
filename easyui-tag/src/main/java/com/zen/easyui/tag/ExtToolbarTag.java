package com.zen.easyui.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class ExtToolbarTag extends BodyTagSupport {

    private static final long serialVersionUID = 598209081781510962L;

    private String id;

    private String style;

    private boolean showToolbarForm = true;//是否显示工具栏伸缩按钮

    private boolean showDefault = false;//默认伸缩

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuffer htmlSb = new StringBuffer();

            htmlSb.append("<script>var ").append(this.getId()).append(" = []; </script> \n");

            pageContext.getOut().write(htmlSb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            StringBuffer htmlSb = new StringBuffer();

            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

}