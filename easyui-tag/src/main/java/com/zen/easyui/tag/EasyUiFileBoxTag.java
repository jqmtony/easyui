package com.zen.easyui.tag;

import com.zen.easyui.util.RandomUtil;
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
public class EasyUiFileBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = 540064338631167843L;

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

        String tmpId = RegulationUtil.isEmpty(this.getId()) ? this.getName() + RandomUtil.random(2) : this.getId();

        htmlSb.append("<input ");
        htmlSb.append(" name=\"").append(this.getName()).append("\"");

        if (!RegulationUtil.isEmpty(tmpId)) {
            htmlSb.append(" id=\"").append(tmpId).append("\"");
        }
        if (!RegulationUtil.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        }
        htmlSb.append(">");

        if (!RegulationUtil.isEmpty(tmpId)) {
            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("$(function(){\n");
            htmlSb.append("$('#").append(tmpId).append("').filebox({ \n");
            if (this.isRequired()) {
                htmlSb.append("required:").append(this.isRequired()).append(",\n");
            }
            if (this.isReadonly()) {
                htmlSb.append("readonly:").append(this.isReadonly()).append(",\n");
            }
            if (!RegulationUtil.isEmpty(this.getOnChange())) {
                htmlSb.append("onChange:").append(this.getOnChange()).append(",\n");
            }
            if (!RegulationUtil.isEmpty(this.getOnClickButton())) {
                htmlSb.append("onClickButton:").append(this.getOnClickButton()).append(",\n");
            }
            if (!RegulationUtil.isEmpty(this.getButtonText())) {
                htmlSb.append("buttonText:\"").append(this.getButtonText()).append("\",\n");
            }
            if (!RegulationUtil.isEmpty(this.getButtonIcon())) {
                htmlSb.append("buttonIcon:\"").append(this.getButtonIcon()).append("\",\n");
            }
            if (!RegulationUtil.isEmpty(this.getButtonAlign())) {
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

}
