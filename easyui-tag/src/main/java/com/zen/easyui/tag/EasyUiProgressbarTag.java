package com.zen.easyui.tag;

import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class EasyUiProgressbarTag extends BodyTagSupport {

    private static final long serialVersionUID = 4040423476954803162L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String sumValue; // 总大小

    private String currentValue; // 当前大小(标识：常量）

    private String refeshTime; // 默认刷新时间

    private String title; // 按钮文字

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuffer htmlSb = new StringBuffer();

            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("$(function(){ \n");

            htmlSb.append(" });\n");
            htmlSb.append("</script>\n");
            pageContext.getOut().write(htmlSb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

}
