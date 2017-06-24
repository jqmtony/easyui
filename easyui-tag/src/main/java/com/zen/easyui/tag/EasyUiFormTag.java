package com.zen.easyui.tag;

import com.zen.easyui.constant.EasyuiTagGlobalConstant;
import com.zen.easyui.util.MessageUtil;
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
public class EasyUiFormTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

    private String id; // from标识

    private String action; // 请求路径

    private String method = EasyuiTagGlobalConstant.FROM_METHOD;// 请求方法

    private Integer cols = EasyuiTagGlobalConstant.FROM_COLS;// 列数

    private String name;// from名称

    private String title;// 标题

    private String titleKey;

    private String styleClass;

    private String target;

    private String enctype;

    private String accept;

    private String acceptCharset;

    private String ondblclick; // 双击事件

    private String onhelp;// help事件

    private String onclick; // 点击事件

    private String onkeydown;

    private String onkeypress;

    private String onkeyup; // Fires when user select all rows

    private String onmousedown; // Fires when user unselect all rows

    private String onmousemove;

    private String onmouseout;

    private String onmouseover;

    private String onmouseup;

    private String onreset;

    private String onsubmit;

    private String style; // 自定义样式

    private boolean disable = false;//设置form表单只读，用于查看

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuilder htmlSb = new StringBuilder();
            htmlSb.append("<form   ");
            htmlSb.append(" id=\"").append(this.getId()).append("\"");
            if (!RegulationUtil.isEmpty(this.getName())) {
                htmlSb.append("   name= \"").append(this.getName()).append("\"\n");
            }
            if (!RegulationUtil.isEmpty(this.getStyle())) {
                htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
            }
            if (!RegulationUtil.isEmpty(this.getStyleClass())) {
                htmlSb.append(" class=\"").append(this.getStyleClass()).append("\"");
            }

            if (!RegulationUtil.isEmpty(this.getTitleKey()) && !RegulationUtil.isEmpty(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey()))) {
                htmlSb.append("   title= \"").append(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey())).append("\"\n");
            } else if (!RegulationUtil.isEmpty(this.getTitle())) {
                htmlSb.append("   title= \"").append(this.getTitle()).append("\"\n");
            }
            if (!RegulationUtil.isEmpty(this.getTarget())) {
                htmlSb.append("   target= \"").append(this.getTarget()).append("\"\n");
            }
            if (!RegulationUtil.isEmpty(this.getEnctype())) {
                htmlSb.append("   enctype= \"").append(this.getEnctype()).append("\"\n");
            }
            if (!RegulationUtil.isEmpty(this.getAccept())) {
                htmlSb.append("   accept= \"").append(this.getAccept()).append("\"\n");
            }
            if (!RegulationUtil.isEmpty(this.getAcceptCharset())) {
                htmlSb.append("   accept-charset= \"").append(this.getAcceptCharset()).append("\"\n");
            }
            if (!RegulationUtil.isEmpty(this.getAction())) {
                htmlSb.append("   action= \"").append(this.getAction()).append("\"\n");
            }

            htmlSb.append("   method= \"").append(this.getMethod()).append("\"\n");

            if (!RegulationUtil.isEmpty(this.getOnclick())) {
                htmlSb.append("   onclick=").append(this.getOnclick());
            }

            if (!RegulationUtil.isEmpty(this.getOndblclick())) {
                htmlSb.append("   ondblclick=").append(this.getOndblclick());
            }

            if (!RegulationUtil.isEmpty(this.getOnkeydown())) {
                htmlSb.append("   onkeydown=").append(this.getOnkeydown());
            }
            if (!RegulationUtil.isEmpty(this.getOnkeypress())) {
                htmlSb.append("   onkeypress=").append(this.getOnkeypress());
            }
            if (!RegulationUtil.isEmpty(this.getOnkeyup())) {
                htmlSb.append("   onkeyup=").append(this.getOnkeypress());
            }
            if (!RegulationUtil.isEmpty(this.getOnmousedown())) {
                htmlSb.append("   onmousedown=").append(this.getOnkeypress());
            }
            if (!RegulationUtil.isEmpty(this.getOnmousemove())) {
                htmlSb.append("   onmousemove=").append(this.getOnmousemove());
            }
            if (!RegulationUtil.isEmpty(this.getOnmouseout())) {
                htmlSb.append("   onmouseout=").append(this.getOnmouseout());
            }
            if (!RegulationUtil.isEmpty(this.getOnmouseover())) {
                htmlSb.append("   onmouseover=").append(this.getOnmouseover());
            }
            if (!RegulationUtil.isEmpty(this.getOnmouseup())) {
                htmlSb.append("   onmouseup=").append(this.getOnmouseup());
            }
            if (!RegulationUtil.isEmpty(this.getOnreset())) {
                htmlSb.append("   onreset=").append(this.getOnreset());
            }
            if (!RegulationUtil.isEmpty(this.getOnsubmit())) {
                htmlSb.append("   onsubmit=").append(this.getOnsubmit());
            }

            htmlSb.append(">");

            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            StringBuilder htmlSb = new StringBuilder();
            htmlSb.append("</form>  ");
            if (isDisable()) {//禁用form
                htmlSb.append("<script> ");
                htmlSb.append("$(document).ready(function(){");
                htmlSb.append("  disableForm('").append(this.getId()).append("');  ");
                htmlSb.append("}); ");
                htmlSb.append("</script>");
            }
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

}
