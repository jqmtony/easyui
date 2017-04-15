package com.fcbox.easyui.tag;

import com.fcbox.easyui.constant.GlobalConstant;
import com.fcbox.easyui.util.TriRegulation;
import com.fcbox.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * @author hexuming
 */
public class EasyUiValidateTextTag extends BodyTagSupport {
    private static final long serialVersionUID = -4795604324752399473L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String name; // 名称

    private String value; // 默认值

    private String tooltip; // 提示

    private String type = "text"; // 类型

    private boolean required = false; // 必须输入

    private boolean readonly = false; // 必须输入

    private String validType; // 特殊验证类型

    private String data_options;// 扩展原生功能,如支持多类型验证data-options="validType:['email','length[0,20]']

    private String style; // 样式、宽度、高度

    private String missingMessage; // 验证框为空时，提示字符

    private String missingMessageKey; // 验证框为空时，提示字符

    private String invalidMessage; // 不合格时的提示字符

    private String invalidMessageKey; // 不合格时的提示字符ID

    private String onblur; // 焦点事件

    private String onclick;

    private String onchange;

    private String onmouseout;

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<input class=\"easyui-validatebox").append("\"");

        htmlSb.append(" name=\"").append(this.getName()).append("\"");

        if (!TriRegulation.isEmpty(this.getId())) {
            htmlSb.append(" id=\"").append(this.getId()).append("\"");
        }
        if (this.isReadonly()) {
            htmlSb.append(" readonly=\"").append(this.isReadonly()).append("\"");
        }
        if (this.isRequired()) {
            htmlSb.append(" required=\"").append(this.isRequired()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getData_options())) {
            htmlSb.append(" data-options=\"").append(this.getData_options()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getType())) {
            htmlSb.append(" type=\"").append(this.getType()).append("\"");
        } else {
            htmlSb.append(" type=\"text\" ");
        }
        if (this.isReadonly()) {
            this.setStyle(!TriRegulation.isEmpty(this.getStyle()) ? this.getStyle() + GlobalConstant.WEB_DISABLE_STYLE : GlobalConstant.WEB_DISABLE_STYLE);
        }
        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getValidType())) {
            htmlSb.append(" validType=\"").append(this.getValidType()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getOnblur())) {
            htmlSb.append("  onblur=\"").append(this.onblur).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getOnclick())) {
            htmlSb.append("  onclick=\"").append(this.onclick).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getOnchange())) {
            htmlSb.append("  onChange=\"").append(this.getOnchange()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getOnmouseout())) {
            htmlSb.append("  onmouseout=\"").append(this.getOnmouseout()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append(" value=\"").append(this.getValue()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getTooltip())) {
            htmlSb.append(" title=\"").append(this.getTooltip()).append("\"");
        }

        String keyStr = "";
        if (!TriRegulation.isEmpty(this.getInvalidMessageKey())) {
            keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getInvalidMessageKey());

            if (TriRegulation.isEmpty(keyStr)) {
                keyStr = TriRegulation.isEmpty(this.getInvalidMessage()) ? "" : this.getInvalidMessage();
            }
        } else {
            keyStr = TriRegulation.isEmpty(this.getInvalidMessage()) ? "" : this.getInvalidMessage();
        }
        if (!TriRegulation.isEmpty(keyStr)) {
            htmlSb.append(" invalidMessage=\"").append(keyStr).append("\"");
        }

        if (!TriRegulation.isEmpty(this.getMissingMessageKey())) {
            keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getMissingMessageKey());

            if (TriRegulation.isEmpty(keyStr)) {
                keyStr = TriRegulation.isEmpty(this.getMissingMessage()) ? "" : this.getMissingMessage();
            }
        } else {
            keyStr = TriRegulation.isEmpty(this.getMissingMessage()) ? "" : this.getMissingMessage();
        }

        if (!TriRegulation.isEmpty(keyStr)) {
            htmlSb.append(" missingMessage=\"").append(this.getMissingMessage()).append("\"");
        }

        htmlSb.append(">");

        if (!TriRegulation.isEmpty(this.getId()) && !TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("$(function(){\n");
            htmlSb.append("if (isEmpty(getValue2TagId(\"").append(this.getId()).append("\",'input'))) {\n");
            htmlSb.append("setValue2TagId(\"").append(this.getId()).append("\",'input','").append(this.getValue()).append("');\n");
            htmlSb.append("}\n");
            htmlSb.append("   });\n");
            htmlSb.append("</script>\n");

        }
        // if (!TriRegulation.isEmpty(this.getId()) && this.isFocus()) {
        // htmlSb.append("<script type=\"text/javascript\">\n");
        // htmlSb.append("$(function(){\n");
        // htmlSb.append("$(\"#").append(this.getId()).append("\").focus(function() {\n");
        // htmlSb.append("   $(\"#").append(this.getId()).append("\").val($(\"#").append(this.getId()).append("\").val());\n");
        // htmlSb.append("   });\n");
        // htmlSb.append("$(\"#").append(this.getId()).append("\").focus();\n");
        // htmlSb.append(" });\n");
        // htmlSb.append("</script>\n");
        //
        // }

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        // return SKIP_BODY;
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOnblur() {
        return onblur;
    }

    public void setOnblur(String onblur) {
        this.onblur = onblur;
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

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getInvalidMessage() {
        return invalidMessage;
    }

    public void setInvalidMessage(String invalidMessage) {
        this.invalidMessage = invalidMessage;
    }

    public String getInvalidMessageKey() {
        return invalidMessageKey;
    }

    public void setInvalidMessageKey(String invalidMessageKey) {
        this.invalidMessageKey = invalidMessageKey;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public String getOnmouseout() {
        return onmouseout;
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public String getData_options() {
        return data_options;
    }

    public void setData_options(String data_options) {
        this.data_options = data_options;
    }

    public String getMissingMessage() {
        return missingMessage;
    }

    public void setMissingMessage(String missingMessage) {
        this.missingMessage = missingMessage;
    }

    public String getMissingMessageKey() {
        return missingMessageKey;
    }

    public void setMissingMessageKey(String missingMessageKey) {
        this.missingMessageKey = missingMessageKey;
    }

}
