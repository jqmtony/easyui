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
public class EasyUiMenuTag extends BodyTagSupport {

    private static final long serialVersionUID = -8408069492551446646L;

    private String id;

    private String style; // 样式

    private boolean fit;

    @Override
    public int doStartTag() throws JspException {

        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<div class=\"easyui-menu\" ");
        htmlSb.append(" id=\"").append(this.getId()).append("\" ");
        // htmlSb.append(" fit=\"").append(this.isFit()).append("\" ");

        if (!RegulationUtil.isEmpty(this.getStyle())) {
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

}
