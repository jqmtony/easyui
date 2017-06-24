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
public class EasyUiNumberBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String width = "150"; //统一默认宽度

    private boolean disabled = false;  //是否禁用

    private String value; //默认值。

    private Integer min; //允许的最小值。

    private Integer max; //允许的最大值。

    private Integer precision; //在十进制分隔符之后显示的最大精度

    private Integer decimalSeparator;//使用哪一种十进制字符分隔数字的整数和小数部分。

    private String groupSeparator;  //使用哪一种字符分割整数组，以显示成千上万的数据。(比如：99,999,999.00中的','就是该分隔符设置。)

    private String prefix;//前缀字符。(比如：金额的$或者￥)

    private String suffix; //后缀字符。(比如：后置的欧元符号€)

    private String filter; //定义如何过滤按键，当返回true时则允许输入，反之禁止

    private String formatter; //用于格式化数值的函数。返回字符串值以显示到输入框中。

    private String parser;  //用于解析字符串的函数。返回数值。

    private String onChange; //当字段值更改的时候触发。

    private String validType;//验证

    private boolean required;//必填


    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        String tmpId = RegulationUtil.isEmpty(this.getId()) ? this.getName() + RandomUtil.random(2) : this.getId();

        htmlSb.append("<input name=\"").append(this.getName()).append("\"\n");
        htmlSb.append(" id=\"").append(tmpId).append("\" type=\"text\"\n");
        htmlSb.append(">\n");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("$(function(){\n");
        htmlSb.append("$('#").append(tmpId).append("').numberbox({ \n");

        if (this.isDisabled()) {
            htmlSb.append(" disabled:").append(this.isDisabled()).append(",");
        }
        if (!RegulationUtil.isEmpty(this.getValue())) {
            htmlSb.append(" value:\"").append(this.getValue()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getMin())) {
            htmlSb.append(" min:").append(this.getMin()).append(",");
        }
        if (!RegulationUtil.isEmpty(this.getMax())) {
            htmlSb.append(" max:").append(this.getMax()).append(",");
        }
        if (!RegulationUtil.isEmpty(this.getPrecision())) {
            htmlSb.append(" precision:").append(this.getPrecision()).append(",");
        }
        if (!RegulationUtil.isEmpty(this.getDecimalSeparator())) {
            htmlSb.append(" decimalSeparator:\"").append(this.getDecimalSeparator()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getGroupSeparator())) {
            htmlSb.append(" groupSeparator:\"").append(this.getGroupSeparator()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getPrefix())) {
            htmlSb.append(" prefix:\"").append(this.getPrefix()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getSuffix())) {
            htmlSb.append(" suffix:\"").append(this.getSuffix()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getFormatter())) {
            htmlSb.append("  formatter:").append(this.getFilter()).append(",");
        }
        if (!RegulationUtil.isEmpty(this.getParser())) {
            htmlSb.append("  parser:").append(this.getParser()).append(",");
        }
        if (!RegulationUtil.isEmpty(this.getOnChange())) {
            htmlSb.append("  onChange:").append(this.getOnChange()).append(" /n");
        }
        if (!RegulationUtil.isEmpty(this.getValidType())) {
            htmlSb.append(" validType:\"").append(this.getValidType()).append("\",");
        }
        if (this.isRequired()) {
            htmlSb.append(" required:").append(this.isRequired()).append(",");
        }

        htmlSb.append(" width:").append(this.getWidth()).append(" ");

        htmlSb.append("});\n");

        htmlSb.append("});\n");
        htmlSb.append("</script>\n");

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {

        try {
            pageContext.getOut().write("</input>");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }
}
