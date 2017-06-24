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

/**
 * ValidateBox（验证框）
 *
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName EasyUiValidateBoxTag.java
 * @Date 2017/4/24 15:53
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiValidateBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = -4795604324752399473L;

    /**
     * 编号
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 默认值
     */
    private String value;

    /**
     * 类型
     */
    private String type = "text";

    /**
     * 样式、宽度、高度
     */
    private String style;

    /**
     * 定义为必填字段
     */
    private boolean required = false;

    /**
     * 为true时关闭验证功能。（该属性自1.3.4版开始可用）
     */
    private boolean novalidate = false;

    /**
     * 为true时用户可以在文本域中输入内容。（该属性自1.4.5版开始可用）
     */

    private boolean editable = true;

    /**
     * 为true时禁用验证框（在表单提交时不会被提交）。（该属性自1.4.5版开始可用）
     */
    private boolean disabled = false;

    /**
     * 为true时将验证框设为只读（在表单提交时会被提交）。（该属性自1.4.5版开始可用）
     */
    private boolean readonly = false;

    /**
     * 为true时在创建该组件时就进行验证。（该属性自1.4.5版开始可用）
     */
    private boolean validateOnCreate = true;

    /**
     * 为true时在该组件失去焦点的时候进行验证。（该属性自1.4.5版开始可用）
     */
    private boolean validateOnBlur = false;

    /**
     * 特殊验证类型
     */
    private String validType;

    /**
     * 当文本框未填写时出现的提示信息
     */
    private String missingMessage;

    private String missingMessageKey;

    /**
     * 当文本框的内容被验证为无效时出现的提示。
     */
    private String invalidMessage;

    private String invalidMessageKey;

    /**
     * 焦点事件
     */
    private String onblur;

    /**
     * 点击事件
     */
    private String onclick;

    /**
     * 改变事件
     */
    private String onchange;

    /**
     * 鼠标移出事件
     */
    private String onmouseout;

    /**
     * 在验证一个字段之前触发。（该事件自1.4版开始可用）
     */
    private String onBeforeValidate;

    /**
     * 在验证一个字段的时候触发。（该事件自1.4版开始可用）
     */
    private String onValidate;

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<input class=\"easyui-validatebox").append("\"");

        htmlSb.append(" name=\"").append(this.getName()).append("\"");

        if (!RegulationUtil.isEmpty(this.getId())) {
            htmlSb.append(" id=\"").append(this.getId()).append("\"");
        }

        if (!RegulationUtil.isEmpty(this.getType())) {
            htmlSb.append(" type=\"").append(this.getType()).append("\"");
        }

        if (!RegulationUtil.isEmpty(this.getValue())) {
            htmlSb.append(" value=\"").append(this.getValue()).append("\"");
        }

        if (this.isReadonly()) {
            this.setStyle(!RegulationUtil.isEmpty(this.getStyle()) ? this.getStyle() + EasyuiTagGlobalConstant.WEB_DISABLE_STYLE : EasyuiTagGlobalConstant.WEB_DISABLE_STYLE);
        }
        if (!RegulationUtil.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        }

        if (!RegulationUtil.isEmpty(this.getOnblur())) {
            htmlSb.append("  onblur=\"").append(this.onblur).append("\"");
        }
        if (!RegulationUtil.isEmpty(this.getOnclick())) {
            htmlSb.append("  onclick=\"").append(this.onclick).append("\"");
        }
        if (!RegulationUtil.isEmpty(this.getOnchange())) {
            htmlSb.append("  onChange=\"").append(this.getOnchange()).append("\"");
        }
        if (!RegulationUtil.isEmpty(this.getOnmouseout())) {
            htmlSb.append("  onmouseout=\"").append(this.getOnmouseout()).append("\"");
        }


        htmlSb.append(" data-options=\"");

        htmlSb.append(" required : ").append(this.isRequired()).append(",\n");
        if (!RegulationUtil.isEmpty(this.getValidType())) {
            htmlSb.append(" validType : ").append(this.getValidType()).append(",\n");
        }
        htmlSb.append(" novalidate : ").append(this.isNovalidate()).append(",\n");
        htmlSb.append(" editable : ").append(this.isEditable()).append(",\n");
        htmlSb.append(" disabled : ").append(this.isDisabled()).append(",\n");
        htmlSb.append(" readonly : ").append(this.isReadonly()).append(",\n");
        htmlSb.append(" validateOnCreate : ").append(this.isValidateOnCreate()).append(",\n");
        htmlSb.append(" validateOnBlur : ").append(this.isValidateOnBlur());

        htmlSb.append("\"");

        if (!RegulationUtil.isEmpty(this.getOnBeforeValidate())) {
            htmlSb.append("  onBeforeValidate=\"").append(this.getOnBeforeValidate()).append("\"");
        }
        if (!RegulationUtil.isEmpty(this.getOnValidate())) {
            htmlSb.append("  onValidate=\"").append(this.getOnValidate()).append("\"");
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
            htmlSb.append(" invalidMessage=\"").append(keyStr).append("\"");
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
            htmlSb.append(" missingMessage=\"").append(this.getMissingMessage()).append("\"");
        }

        htmlSb.append(" />");

        if (!RegulationUtil.isEmpty(this.getId()) && !RegulationUtil.isEmpty(this.getValue())) {
            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("$(function(){\n");
            htmlSb.append("if (isEmpty(getValue2TagId(\"").append(this.getId()).append("\",'input'))) {\n");
            htmlSb.append("setValue2TagId(\"").append(this.getId()).append("\",'input','").append(this.getValue()).append("');\n");
            htmlSb.append("}\n");
            htmlSb.append("   });\n");
            htmlSb.append("</script>\n");
        }

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

}
