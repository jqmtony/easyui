package com.zen.easyui.tag;

import com.zen.easyui.util.MessageUtil;
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
public class EasyUiTextTag extends BodyTagSupport {
    private static final long serialVersionUID = -4795604324752399473L;

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
        String tmpId = RegulationUtil.isEmpty(this.getId()) ? this.getName() + RandomUtil.random(2) : this.getId();
        htmlSb.append("<input name=\"").append(this.getName()).append("\"\n");
        htmlSb.append(" id=\"").append(tmpId).append("\"\n");
        if (this.showonly) {//显示白板
            htmlSb.append("class=\"").append(showClass).append("\"\n");
            htmlSb.append("readonly=\"true\"\n");
            if (!RegulationUtil.isEmpty(this.getValue())) {
                htmlSb.append(" value=\"").append(this.getValue()).append("\"");
            }
        }
        if ("hidden".equalsIgnoreCase(this.getType())) {
            htmlSb.append("type=\"hidden\"\n");
            if (!RegulationUtil.isEmpty(this.getValue())) {
                htmlSb.append(" value=\"").append(this.getValue()).append("\"");
            }
        }
        if (!RegulationUtil.isEmpty(this.getStyle())) {
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
            if (!RegulationUtil.isEmpty(this.getWidth())) {
                htmlSb.append("width:\"" + this.getWidth()).append("\",");
            }
            if (!RegulationUtil.isEmpty(this.getHeight())) {
                htmlSb.append("height:\"" + this.getHeight()).append("\",");
            }
            if (!RegulationUtil.isEmpty(this.getValidType())) {
                if (this.getValidType().indexOf("[") == 0) {
                    htmlSb.append(" validType:").append(this.getValidType()).append(",");
                } else {
                    htmlSb.append(" validType:\"").append(this.getValidType()).append("\",");
                }
            }
            if (!RegulationUtil.isEmpty(this.getValue())) {
                htmlSb.append(" value:\"").append(this.getValue()).append("\",");
            }
            if (!RegulationUtil.isEmpty(this.getPrompt())) {
                htmlSb.append(" prompt:\"").append(this.getPrompt()).append("\",");
            }

            if (this.isMultiline()) {
                htmlSb.append(" multiline:").append(this.isMultiline()).append(",");
                if (RegulationUtil.isEmpty(this.getHeight())) {
                    htmlSb.append("height:\"50").append("\",");
                }
            }
            if (!this.isEditable()) {
                htmlSb.append(" editable:").append(this.isEditable()).append(",");
            }
            if (this.isDisabled()) {
                htmlSb.append(" disabled:").append(this.isDisabled()).append(",");
            }
            if (!RegulationUtil.isEmpty(this.getIconCls())) {
                htmlSb.append(" iconCls:\"").append(this.getIconCls()).append("\",");
            }

            if (!RegulationUtil.isEmpty(this.getIconAlign())) {
                htmlSb.append(" iconAlign:\"").append(this.getIconAlign()).append("\",");
            }

            if (!RegulationUtil.isEmpty(this.getIconWidth())) {
                htmlSb.append(" iconWidth:\"").append(this.getIconWidth()).append("\",");
            }

            if (!RegulationUtil.isEmpty(this.getButtonText())) {
                htmlSb.append(" buttonText:\"").append(this.getButtonText()).append("\",");
            }

            if (!RegulationUtil.isEmpty(this.getButtonIcon())) {
                htmlSb.append(" buttonIcon:\"").append(this.getButtonIcon()).append("\",");
            }

            if (!RegulationUtil.isEmpty(this.getButtonAlign())) {
                htmlSb.append(" buttonAlign:\"").append(this.getButtonAlign()).append("\",");
            }


            String keyStr = "";
            if (!RegulationUtil.isEmpty(this.getInvalidMessageKey())) {
                keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getInvalidMessageKey());

                if (RegulationUtil.isEmpty(keyStr)) {
                    keyStr = RegulationUtil.isEmpty(this.getInvalidMessage()) ? "" : this.getInvalidMessage();
                }
            } else {
                keyStr = RegulationUtil.isEmpty(this.getInvalidMessage()) ? "" : this.getInvalidMessage();
            }
            if (!RegulationUtil.isEmpty(keyStr)) {
                htmlSb.append(" invalidMessage:\"").append(keyStr).append("\",");
            }

            if (!RegulationUtil.isEmpty(this.getMissingMessageKey())) {
                keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getMissingMessageKey());

                if (RegulationUtil.isEmpty(keyStr)) {
                    keyStr = RegulationUtil.isEmpty(this.getMissingMessage()) ? "" : this.getMissingMessage();
                }
            } else {
                keyStr = RegulationUtil.isEmpty(this.getMissingMessage()) ? "" : this.getMissingMessage();
            }

            if (!RegulationUtil.isEmpty(keyStr)) {
                htmlSb.append(" missingMessage:\"").append(this.getMissingMessage()).append("\",");
            }
            if (!RegulationUtil.isEmpty(this.getOnchange())) {
//      htmlSb.append("   onChange:function(){\n");
//      htmlSb.append(this.getOnChange()).append("(newValue, oldValue);},\n");
                htmlSb.append("   onChange:").append(this.getOnchange()).append(",\n");
            }
            if (!RegulationUtil.isEmpty(this.getOnclickIcon())) {
                htmlSb.append("   onClickIcon:").append(this.getOnclickIcon()).append(",\n");
            }
            if (!RegulationUtil.isEmpty(this.getOnclickButton())) {
                htmlSb.append("   onClickButton:").append(this.getOnclickButton()).append(",\n");
            }

            htmlSb.append(" type:\"").append(this.getType()).append("\"");
            htmlSb.append("})\n");
            //htmlSb.append("alert('create text');\n");
            //设置背景颜色
            if (this.isReadonly()) {
                htmlSb.append("$('input',$('#").append(tmpId).append("').next('span')).addClass('textbox-disabled-style');\n");
            }
            if (!RegulationUtil.isEmpty(this.getOnclick())) {
                htmlSb.append("$('input',$('#").append(tmpId).append("').next('span')).click(function(){\n");
                if (this.getOnclick().indexOf("(") > 0) {
                    htmlSb.append(this.getOnclick()).append(";})\n");
                } else {
                    htmlSb.append(this.getOnclick()).append("(this);})\n");
                }
            }
            if (!RegulationUtil.isEmpty(this.getOnblur())) {
                htmlSb.append("$('input',$('#").append(tmpId).append("').next('span')).blur(function(){\n");
                if (this.getOnblur().indexOf("(") > 0) {
                    htmlSb.append(this.getOnblur()).append(";})\n");
                } else {
                    htmlSb.append(this.getOnblur()).append("(this);})\n");
                }
            }
            if (!RegulationUtil.isEmpty(this.getOnenterKey())) {
                htmlSb.append("$('#").append(tmpId).append("').textbox('textbox').keydown(function(e){\n");
                htmlSb.append("if (e.keyCode == 13) {\n");
                if (this.getOnenterKey().indexOf("(") > 0) {
                    htmlSb.append(this.getOnenterKey()).append(";}\n");
                } else {
                    htmlSb.append(this.getOnenterKey()).append("(this);}\n");
                }
                htmlSb.append("})\n");
            }

            if (!RegulationUtil.isEmpty(this.getOnkeypress())) {
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

}
