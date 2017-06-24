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
public class EasyUiAccordionTag extends BodyTagSupport {

    private static final long serialVersionUID = -4306262114383104919L;

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
