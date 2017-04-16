package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.TriStringUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class EasyUiNumberBoxTag extends BodyTagSupport {


    private static final long serialVersionUID = 1L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

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
        String tmpId = TriRegulation.isEmpty(this.getId()) ? this.getName() + TriStringUtil.random(2) : this.getId();

        htmlSb.append("<input name=\"").append(this.getName()).append("\"\n");
        htmlSb.append(" id=\"").append(tmpId).append("\" type=\"text\"\n");
        htmlSb.append(">\n");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("$(function(){\n");
        htmlSb.append("$('#").append(tmpId).append("').numberbox({ \n");

        if (this.isDisabled()) {
            htmlSb.append(" disabled:").append(this.isDisabled()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append(" value:\"").append(this.getValue()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getMin())) {
            htmlSb.append(" min:").append(this.getMin()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getMax())) {
            htmlSb.append(" max:").append(this.getMax()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getPrecision())) {
            htmlSb.append(" precision:").append(this.getPrecision()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getDecimalSeparator())) {
            htmlSb.append(" decimalSeparator:\"").append(this.getDecimalSeparator()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getGroupSeparator())) {
            htmlSb.append(" groupSeparator:\"").append(this.getGroupSeparator()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getPrefix())) {
            htmlSb.append(" prefix:\"").append(this.getPrefix()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getSuffix())) {
            htmlSb.append(" suffix:\"").append(this.getSuffix()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getFormatter())) {
            htmlSb.append("  formatter:").append(this.getFilter()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getParser())) {
            htmlSb.append("  parser:").append(this.getParser()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getOnChange())) {
            htmlSb.append("  onChange:").append(this.getOnChange()).append(" /n");
        }
        if (!TriRegulation.isEmpty(this.getValidType())) {
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(Integer decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public String getGroupSeparator() {
        return groupSeparator;
    }

    public void setGroupSeparator(String groupSeparator) {
        this.groupSeparator = groupSeparator;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getParser() {
        return parser;
    }

    public void setParser(String parser) {
        this.parser = parser;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}
