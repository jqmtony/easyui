package com.zen.easyui.tag;

import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiDivTag extends BodyTagSupport {

    private static final long serialVersionUID = 598209081781510962L;

    private String id;

    private String style;

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuffer htmlSb = new StringBuffer();
            htmlSb.append("<div id=\"").append(this.getId()).append("\"");

            if (!RegulationUtil.isEmpty(this.getStyle())) {
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

}