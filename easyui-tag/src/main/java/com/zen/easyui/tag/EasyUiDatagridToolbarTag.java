package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


public class EasyUiDatagridToolbarTag extends BodyTagSupport {

    private static final long serialVersionUID = 598209081781510962L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String style;

    private boolean showToolbarForm = true;//是否显示工具栏伸缩按钮

    private boolean showDefault = false;//默认伸缩

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
            StringBuffer htmlSb = new StringBuffer();
            if (isShowToolbarForm()) {
                htmlSb.append("<a href='#' class='easyui-linkbutton l-btn l-btn-plain' plain='true' iconcls='icon-updown' title='点击显示/隐藏查询工具栏' onclick='javaScript: showToolbarForm(\"" + id + "\");' >查询栏</a></div>\n");
            } else {
                htmlSb.append("</div>\n");
            }
            if (isShowDefault()) {
                htmlSb.append("<script>$(document).ready(function(){ $('#" + id + "').children('form').toggle();});  </script>\n");
            }
            pageContext.getOut().write(htmlSb.toString());
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

    public boolean isShowToolbarForm() {
        return showToolbarForm;
    }

    public void setShowToolbarForm(boolean showToolbarForm) {
        this.showToolbarForm = showToolbarForm;
    }

    public boolean isShowDefault() {
        return showDefault;
    }

    public void setShowDefault(boolean showDefault) {
        this.showDefault = showDefault;
    }


}