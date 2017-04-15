package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.TriStringUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


/**
 * @author zj
 */
public class EasyUiFileBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = 540064338631167843L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String name; // 名称

    private String style;

    private boolean required = false; // 必输

    private boolean readonly = false;

    private String onClickButton;

    private String onChange;

    private String buttonText;//在文本框上附加的按钮显示的文本

    private String buttonIcon;//在文本框上附加的按钮显示的图标。

    private String buttonAlign = "right";//附加按钮位置。可用值有："left", "right"。

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();

        String tmpId = TriRegulation.isEmpty(this.getId()) ? this.getName() + TriStringUtil.random(2) : this.getId();

        htmlSb.append("<input ");
        htmlSb.append(" name=\"").append(this.getName()).append("\"");

        if (!TriRegulation.isEmpty(tmpId)) {
            htmlSb.append(" id=\"").append(tmpId).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        }
        htmlSb.append(">");

        if (!TriRegulation.isEmpty(tmpId)) {
            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("$(function(){\n");
            htmlSb.append("$('#").append(tmpId).append("').filebox({ \n");
            if (this.isRequired()) {
                htmlSb.append("required:").append(this.isRequired()).append(",\n");
            }
            if (this.isReadonly()) {
                htmlSb.append("readonly:").append(this.isReadonly()).append(",\n");
            }
            if (!TriRegulation.isEmpty(this.getOnChange())) {
                htmlSb.append("onChange:").append(this.getOnChange()).append(",\n");
            }
            if (!TriRegulation.isEmpty(this.getOnClickButton())) {
                htmlSb.append("onClickButton:").append(this.getOnClickButton()).append(",\n");
            }
            if (!TriRegulation.isEmpty(this.getButtonText())) {
                htmlSb.append("buttonText:\"").append(this.getButtonText()).append("\",\n");
            }
            if (!TriRegulation.isEmpty(this.getButtonIcon())) {
                htmlSb.append("buttonIcon:\"").append(this.getButtonIcon()).append("\",\n");
            }
            if (!TriRegulation.isEmpty(this.getButtonAlign())) {
                htmlSb.append("buttonAlign:\"").append(this.getButtonAlign()).append("\" \n");
            }
            htmlSb.append("   });\n");
            htmlSb.append("   });\n");
            htmlSb.append("</script>\n");

        }

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
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

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonIcon() {
        return buttonIcon;
    }

    public void setButtonIcon(String buttonIcon) {
        this.buttonIcon = buttonIcon;
    }

    public String getButtonAlign() {
        return buttonAlign;
    }

    public void setButtonAlign(String buttonAlign) {
        this.buttonAlign = buttonAlign;
    }

    public String getOnClickButton() {
        return onClickButton;
    }

    public void setOnClickButton(String onClickButton) {
        this.onClickButton = onClickButton;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

}
