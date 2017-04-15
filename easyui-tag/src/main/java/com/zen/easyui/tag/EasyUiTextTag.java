package com.zen.easyui.tag;

import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.TriStringUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * @author hexuming
 */
public class EasyUiTextTag extends BodyTagSupport {
    private static final long serialVersionUID = -4795604324752399473L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String name; // 名称

    private String value; // 默认值

    private String tooltip; // 提示

    private String type = "text"; // 文本框类型。可用值有："text"和"password","hidden"

    private boolean showonly = false; //只用来显示 合并以下属性 class='input-label-style' readonly=true

    private static final String showClass = "input-label-style";

    private boolean required = false; // 必须输入

    private boolean readonly = false; // 必须输入

    private String validType; // 特殊验证类型

    private String style; // 样式、宽度、高度 默认长度100  目前该属性不起作用，无法使用

    private int multilineHeight = 80;// 多行编辑时的高度。

    private String width = "150";// 组件的宽度。

    private String height;// 组件的高度。

    private String prompt;// 在输入框显示提示消息。

    private boolean multiline = false;// 定义是否是多行文本框。

    private boolean editable = true;// 定义用户是否可以直接在该字段内输入文字。

    private boolean disabled = false;// 定义是否禁用该字段。

    private String iconCls;// 在文本框显示背景图标。

    private String iconAlign;// 背景图标的位置。可用值有："left", "right"。

    private String iconWidth;// 图标宽度。

    private String buttonText;// 文本框附加按钮显示的文本内容。

    private String buttonIcon;// 文本框附加按钮显示的图标。

    private String buttonAlign;// 附加按钮的位置。可用值有："left", "right"。

    private String missingMessage; // 验证框为空时，提示字符

    private String missingMessageKey; // 验证框为空时，提示字符

    private String invalidMessage; // 不合格时的提示字符

    private String invalidMessageKey; // 不合格时的提示字符ID
    //事件
    private String onclick; //点击事件

    private String onblur; // 焦点事件

    private String onchange;

    private String onclickButton;// 在用户点击按钮的时候触发。

    private String onclickIcon;// 在用户点击图标的时候触发。

    private String onenterKey;//按下回车事件

    private String onkeypress;//按键事件

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        String tmpId = TriRegulation.isEmpty(this.getId()) ? this.getName() + TriStringUtil.random(2) : this.getId();
        htmlSb.append("<input name=\"").append(this.getName()).append("\"\n");
        htmlSb.append(" id=\"").append(tmpId).append("\"\n");
        if (this.showonly) {//显示白板
            htmlSb.append("class=\"").append(showClass).append("\"\n");
            htmlSb.append("readonly=\"true\"\n");
            if (!TriRegulation.isEmpty(this.getValue())) {
                htmlSb.append(" value=\"").append(this.getValue()).append("\"");
            }
        }
        if ("hidden".equalsIgnoreCase(this.getType())) {
            htmlSb.append("type=\"hidden\"\n");
            if (!TriRegulation.isEmpty(this.getValue())) {
                htmlSb.append(" value=\"").append(this.getValue()).append("\"");
            }
        }
        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"\n");
        }

        htmlSb.append(">\n");
        //白板显示
        if (!this.isShowonly() && !"hidden".equalsIgnoreCase(this.getType())) {
            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("$(function(){\n");
            htmlSb.append("$('#").append(tmpId).append("').textbox({ \n");
            if (this.isRequired()) {
                htmlSb.append("required:").append(this.isRequired()).append(",");
            }
            if (this.isReadonly()) {
                htmlSb.append("readonly:").append(this.isReadonly()).append(",");
            }
            if (!TriRegulation.isEmpty(this.getWidth())) {
                htmlSb.append("width:\"" + this.getWidth()).append("\",");
            }
            if (!TriRegulation.isEmpty(this.getHeight())) {
                htmlSb.append("height:\"" + this.getHeight()).append("\",");
            }
            if (!TriRegulation.isEmpty(this.getValidType())) {
                if (this.getValidType().indexOf("[") == 0) {
                    htmlSb.append(" validType:").append(this.getValidType()).append(",");
                } else {
                    htmlSb.append(" validType:\"").append(this.getValidType()).append("\",");
                }
            }
            if (!TriRegulation.isEmpty(this.getValue())) {
                htmlSb.append(" value:\"").append(this.getValue()).append("\",");
            }
            if (!TriRegulation.isEmpty(this.getPrompt())) {
                htmlSb.append(" prompt:\"").append(this.getPrompt()).append("\",");
            }

            if (this.isMultiline()) {
                htmlSb.append(" multiline:").append(this.isMultiline()).append(",");
                if (TriRegulation.isEmpty(this.getHeight())) {
                    htmlSb.append("height:\"50").append("\",");
                }
            }
            if (!this.isEditable()) {
                htmlSb.append(" editable:").append(this.isEditable()).append(",");
            }
            if (this.isDisabled()) {
                htmlSb.append(" disabled:").append(this.isDisabled()).append(",");
            }
            if (!TriRegulation.isEmpty(this.getIconCls())) {
                htmlSb.append(" iconCls:\"").append(this.getIconCls()).append("\",");
            }

            if (!TriRegulation.isEmpty(this.getIconAlign())) {
                htmlSb.append(" iconAlign:\"").append(this.getIconAlign()).append("\",");
            }

            if (!TriRegulation.isEmpty(this.getIconWidth())) {
                htmlSb.append(" iconWidth:\"").append(this.getIconWidth()).append("\",");
            }

            if (!TriRegulation.isEmpty(this.getButtonText())) {
                htmlSb.append(" buttonText:\"").append(this.getButtonText()).append("\",");
            }

            if (!TriRegulation.isEmpty(this.getButtonIcon())) {
                htmlSb.append(" buttonIcon:\"").append(this.getButtonIcon()).append("\",");
            }

            if (!TriRegulation.isEmpty(this.getButtonAlign())) {
                htmlSb.append(" buttonAlign:\"").append(this.getButtonAlign()).append("\",");
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
                htmlSb.append(" invalidMessage:\"").append(keyStr).append("\",");
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
                htmlSb.append(" missingMessage:\"").append(this.getMissingMessage()).append("\",");
            }
            if (!TriRegulation.isEmpty(this.getOnchange())) {
//      htmlSb.append("   onChange:function(){\n");
//      htmlSb.append(this.getOnChange()).append("(newValue, oldValue);},\n");
                htmlSb.append("   onChange:").append(this.getOnchange()).append(",\n");
            }
            if (!TriRegulation.isEmpty(this.getOnclickIcon())) {
                htmlSb.append("   onClickIcon:").append(this.getOnclickIcon()).append(",\n");
            }
            if (!TriRegulation.isEmpty(this.getOnclickButton())) {
                htmlSb.append("   onClickButton:").append(this.getOnclickButton()).append(",\n");
            }

            htmlSb.append(" type:\"").append(this.getType()).append("\"");
            htmlSb.append("})\n");
            //htmlSb.append("alert('create text');\n");
            //设置背景颜色
            if (this.isReadonly()) {
                htmlSb.append("$('input',$('#").append(tmpId).append("').next('span')).addClass('textbox-disabled-style');\n");
            }
            if (!TriRegulation.isEmpty(this.getOnclick())) {
                htmlSb.append("$('input',$('#").append(tmpId).append("').next('span')).click(function(){\n");
                if (this.getOnclick().indexOf("(") > 0) {
                    htmlSb.append(this.getOnclick()).append(";})\n");
                } else {
                    htmlSb.append(this.getOnclick()).append("(this);})\n");
                }
            }
            if (!TriRegulation.isEmpty(this.getOnblur())) {
                htmlSb.append("$('input',$('#").append(tmpId).append("').next('span')).blur(function(){\n");
                if (this.getOnblur().indexOf("(") > 0) {
                    htmlSb.append(this.getOnblur()).append(";})\n");
                } else {
                    htmlSb.append(this.getOnblur()).append("(this);})\n");
                }
            }
            if (!TriRegulation.isEmpty(this.getOnenterKey())) {
                htmlSb.append("$('#").append(tmpId).append("').textbox('textbox').keydown(function(e){\n");
                htmlSb.append("if (e.keyCode == 13) {\n");
                if (this.getOnenterKey().indexOf("(") > 0) {
                    htmlSb.append(this.getOnenterKey()).append(";}\n");
                } else {
                    htmlSb.append(this.getOnenterKey()).append("(this);}\n");
                }
                htmlSb.append("})\n");
            }

            if (!TriRegulation.isEmpty(this.getOnkeypress())) {
                htmlSb.append("$('#").append(tmpId).append("').textbox('textbox').keypress(function(e){\n");
                //htmlSb.append("alert(e);  \n");
                htmlSb.append(this.getOnkeypress()).append("(e);   \n");
                htmlSb.append("})\n");
            }

            htmlSb.append(" });\n");
            htmlSb.append("</script>\n");
        }
        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }

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


    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        if (!TriRegulation.isEmpty(width)) {
            this.width = width;
        }
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public boolean isMultiline() {
        return multiline;
    }

    public void setMultiline(boolean multiline) {
        this.multiline = multiline;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getIconAlign() {
        return iconAlign;
    }

    public void setIconAlign(String iconAlign) {
        this.iconAlign = iconAlign;
    }

    public String getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(String iconWidth) {
        this.iconWidth = iconWidth;
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

    public boolean isShowonly() {
        return showonly;
    }

    public void setShowonly(boolean showonly) {
        this.showonly = showonly;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public String getOnclickButton() {
        return onclickButton;
    }

    public void setOnclickButton(String onclickButton) {
        this.onclickButton = onclickButton;
    }

    public String getOnclickIcon() {
        return onclickIcon;
    }

    public void setOnclickIcon(String onclickIcon) {
        this.onclickIcon = onclickIcon;
    }

    public String getOnenterKey() {
        return onenterKey;
    }

    public void setOnenterKey(String onenterKey) {
        this.onenterKey = onenterKey;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static String getShowclass() {
        return showClass;
    }

    public String getOnkeypress() {
        return onkeypress;
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }


}
