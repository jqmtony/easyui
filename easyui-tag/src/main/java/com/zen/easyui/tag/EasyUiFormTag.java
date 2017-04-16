package com.zen.easyui.tag;

import com.zen.easyui.constant.GlobalConstant;
import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


public class EasyUiFormTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // from标识

    private String action; // 请求路径

    private String method = GlobalConstant.FROM_METHOD;// 请求方法

    private Integer cols = GlobalConstant.FROM_COLS;// 列数

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
            if (!TriRegulation.isEmpty(this.getName())) {
                htmlSb.append("   name= \"").append(this.getName()).append("\"\n");
            }
            if (!TriRegulation.isEmpty(this.getStyle())) {
                htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
            }
            if (!TriRegulation.isEmpty(this.getStyleClass())) {
                htmlSb.append(" class=\"").append(this.getStyleClass()).append("\"");
            }

            if (!TriRegulation.isEmpty(this.getTitleKey()) && !TriRegulation.isEmpty(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey()))) {
                htmlSb.append("   title= \"").append(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey())).append("\"\n");
            } else if (!TriRegulation.isEmpty(this.getTitle())) {
                htmlSb.append("   title= \"").append(this.getTitle()).append("\"\n");
            }
            if (!TriRegulation.isEmpty(this.getTarget())) {
                htmlSb.append("   target= \"").append(this.getTarget()).append("\"\n");
            }
            if (!TriRegulation.isEmpty(this.getEnctype())) {
                htmlSb.append("   enctype= \"").append(this.getEnctype()).append("\"\n");
            }
            if (!TriRegulation.isEmpty(this.getAccept())) {
                htmlSb.append("   accept= \"").append(this.getAccept()).append("\"\n");
            }
            if (!TriRegulation.isEmpty(this.getAcceptCharset())) {
                htmlSb.append("   accept-charset= \"").append(this.getAcceptCharset()).append("\"\n");
            }
            if (!TriRegulation.isEmpty(this.getAction())) {
                htmlSb.append("   action= \"").append(this.getAction()).append("\"\n");
            }

            htmlSb.append("   method= \"").append(this.getMethod()).append("\"\n");

            if (!TriRegulation.isEmpty(this.getOnclick())) {
                htmlSb.append("   onclick=").append(this.getOnclick());
            }

            if (!TriRegulation.isEmpty(this.getOndblclick())) {
                htmlSb.append("   ondblclick=").append(this.getOndblclick());
            }

            if (!TriRegulation.isEmpty(this.getOnkeydown())) {
                htmlSb.append("   onkeydown=").append(this.getOnkeydown());
            }
            if (!TriRegulation.isEmpty(this.getOnkeypress())) {
                htmlSb.append("   onkeypress=").append(this.getOnkeypress());
            }
            if (!TriRegulation.isEmpty(this.getOnkeyup())) {
                htmlSb.append("   onkeyup=").append(this.getOnkeypress());
            }
            if (!TriRegulation.isEmpty(this.getOnmousedown())) {
                htmlSb.append("   onmousedown=").append(this.getOnkeypress());
            }
            if (!TriRegulation.isEmpty(this.getOnmousemove())) {
                htmlSb.append("   onmousemove=").append(this.getOnmousemove());
            }
            if (!TriRegulation.isEmpty(this.getOnmouseout())) {
                htmlSb.append("   onmouseout=").append(this.getOnmouseout());
            }
            if (!TriRegulation.isEmpty(this.getOnmouseover())) {
                htmlSb.append("   onmouseover=").append(this.getOnmouseover());
            }
            if (!TriRegulation.isEmpty(this.getOnmouseup())) {
                htmlSb.append("   onmouseup=").append(this.getOnmouseup());
            }
            if (!TriRegulation.isEmpty(this.getOnreset())) {
                htmlSb.append("   onreset=").append(this.getOnreset());
            }
            if (!TriRegulation.isEmpty(this.getOnsubmit())) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEnctype() {
        return enctype;
    }

    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }

    public String getOndblclick() {
        return ondblclick;
    }

    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    public String getOnhelp() {
        return onhelp;
    }

    public void setOnhelp(String onhelp) {
        this.onhelp = onhelp;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOnkeydown() {
        return onkeydown;
    }

    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }

    public String getOnkeypress() {
        return onkeypress;
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    public String getOnkeyup() {
        return onkeyup;
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    public String getOnmousedown() {
        return onmousedown;
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public String getOnmousemove() {
        return onmousemove;
    }

    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    public String getOnmouseout() {
        return onmouseout;
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public String getOnmouseover() {
        return onmouseover;
    }

    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    public String getOnmouseup() {
        return onmouseup;
    }

    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    public String getOnreset() {
        return onreset;
    }

    public void setOnreset(String onreset) {
        this.onreset = onreset;
    }

    public String getOnsubmit() {
        return onsubmit;
    }

    public void setOnsubmit(String onsubmit) {
        this.onsubmit = onsubmit;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptCharset() {
        return acceptCharset;
    }

    public void setAcceptCharset(String acceptCharset) {
        this.acceptCharset = acceptCharset;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }


}
