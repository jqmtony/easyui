package com.fcbox.easyui.tag;

import com.fcbox.easyui.tag.vo.LabelValueBean;
import com.fcbox.easyui.util.TriObjectHelper;
import com.fcbox.easyui.util.TriRegulation;
import com.fcbox.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class EasyUiComboboxTag extends BodyTagSupport {

    private static final long serialVersionUID = -799903023945799288L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String name;

    private String value;

    private boolean required = false;

    private String url;

    private String valueField = "value";//默认value  ，供options等使用

    private String textField = "label";//默认label  ，供options等使用

    private String descField; //描述字段  ，供options等使用

    private String groupField; // 分组字段 ，供options等使用

    private String style;

    private String width = "150";//默认宽度统一

    private boolean multiple; // 是否多选

    private boolean multiline; // 是否多行显示textarea

    private String formatter; // 格式化（中文、英文图标）

    private boolean editable = false; //默认为不可写，只读

    private boolean disabled = false; // 不可用

    private List list;

    private boolean optionsTitleKey = false; // options title默认为非多语言

    private String options; // 逗号隔开  label:value,label:value,.....

    private String defaultOption;// 默认选中行的值 value

    private String optionsDescLinkStr = "<br/>"; // 描述信息是否回车

    private String data; // data: [{label: 'java',value: 'Java'},{label:
    // 'perl',value: 'Perl'}]"

    private boolean autoShowPanel = true;  //菜单编辑页面格式化图标数据用

    private String onSelect; // 选中事件

    private String onUnselect; // 取消选中事件

    private String onBeforeLoad; // 在请求加载数据之前触发，返回false取消该加载动作

    private String onLoadSuccess; // 成功加载事件

    private String onLoadError; // 加载失败事件

    private String onChange; // 选项改变事件

    private boolean readonly = false;

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        boolean comboboxExist = false;
        boolean comboboxProperty = false;

        htmlSb.append("<input name=\"").append(this.getName()).append("\" id=\"").append(this.getId()).append("\">");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("$(function(){\n");
        if ((!TriRegulation.isEmpty(this.getValue()) || !TriRegulation.isEmpty(this.getDefaultOption()))) {
            htmlSb.append("function ").append(this.getId()).append("SelectValue(){\n");
            if (!TriRegulation.isEmpty(this.getValue())) {
                htmlSb.append("var tagValue = '").append(this.getValue()).append("';\n");
            } else if (!TriRegulation.isEmpty(this.getDefaultOption())) {
                htmlSb.append("var tagValue = '").append(this.getDefaultOption()).append("';\n");
            }
            htmlSb.append("setValue2TagId('").append(this.getId()).append("','select',tagValue);\n");
            htmlSb.append("}\n");
        }
        htmlSb.append(" $('#").append(this.getId()).append("').combobox({\n");
        htmlSb.append(" name:\"").append(this.getName()).append("\",");
        if (this.isRequired()) {
            htmlSb.append(" required:").append(this.isRequired()).append(",\n");
        }
        htmlSb.append(" editable:").append(this.isEditable()).append(",\n");

        if (this.isReadonly()) {
            htmlSb.append(" readonly: ").append(this.isReadonly()).append(",\n");
        }
        if (this.isDisabled()) {
            htmlSb.append(" disabled:").append(this.isDisabled()).append(",\n");
        }
//    if (!this.isAutoShowPanel()) {
//      htmlSb.append(" autoShowPanel:").append(this.isAutoShowPanel()).append(",");
//    }

        if (!TriRegulation.isEmpty(this.getUrl())) {
            htmlSb.append(" url:\"").append(this.getUrl()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getData())) {
            htmlSb.append(" data:").append(this.getData()).append(",");
        }

        if (!TriRegulation.isEmpty(this.getValueField())) {
            htmlSb.append(" valueField:\"").append(this.getValueField()).append("\",");
        }

        if (!TriRegulation.isEmpty(this.getTextField())) {
            htmlSb.append(" textField:\"").append(this.getTextField()).append("\",");
        }

        if (!TriRegulation.isEmpty(this.getGroupField())) {
            htmlSb.append(" groupField:\"").append(this.getGroupField()).append("\",");
        }

        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style:\"").append(this.getStyle()).append("\",");
        }

        if (this.isMultiple()) {
            htmlSb.append(" multiple:").append(this.isMultiple()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getWidth())) {
            htmlSb.append(" width:").append(this.getWidth()).append(",");
        }
        if (this.isMultiline()) {
            htmlSb.append(" multiline:").append(this.isMultiline()).append(",");
        }
        // 1:list数据不为空，构造data
        if (TriRegulation.isEmpty(this.getUrl()) && !TriRegulation.isEmpty(this.getList())) {
            htmlSb.append(" data:[\n");
            Object obj = null;
            Object value = null;
            for (Iterator iterator = this.getList().iterator(); iterator.hasNext(); ) {
                obj = iterator.next();//obj类型 list/map/bean
                value = TriObjectHelper.getFieldValue4Object(obj, this.getValueField());
                htmlSb.append("{label:\"");
                htmlSb.append(TriObjectHelper.getFieldValue4Object(obj, this.getTextField()));
                // 是否带内容描述字段
                htmlSb.append(!TriRegulation.isEmpty(this.getDescField()) ? "\",desc:\"" + TriObjectHelper.getFieldValue4Object(obj, this.getDescField()) : "");
                // 是否带分组字段
                htmlSb.append(!TriRegulation.isEmpty(this.getGroupField()) ? "\",group:\"" + TriObjectHelper.getFieldValue4Object(obj, this.getGroupField()) : "");
                htmlSb.append("\",value:\"");
                htmlSb.append(TriRegulation.isEmpty(value) ? "" : value).append(iterator.hasNext() ? "\"}," : "\"}");
            }
            htmlSb.append(" ],\n");
        }
        // 2:options数据不为空，构造data
        if (TriRegulation.isEmpty(this.getUrl()) && !TriRegulation.isEmpty(this.getOptions())) {
            //默认设置valueField和textField
            String[] optionsArr = this.getOptions().split(",");
            htmlSb.append(" data:[\n");

            for (int i = 0; i < optionsArr.length; i++) {
                htmlSb.append("{label:\"");
                if (this.isOptionsTitleKey()) {
                    htmlSb.append(MessageUtil.getMessage((HttpServletRequest) pageContext.getRequest(), optionsArr[i].split(":")[0]));
                } else {
                    htmlSb.append(optionsArr[i].split(":")[0]);
                }
                // 是否带内容描述字段
                if (optionsArr[i].split(":").length == 3) {
                    if (TriRegulation.isEmpty(this.getDescField())) {
                        this.setDescField("desc");
                    }
                    htmlSb.append("\",desc:\"" + optionsArr[i].split(":")[2]);
                }
                htmlSb.append("\",value:\"");
                htmlSb.append(optionsArr[i].split(":").length > 1 ? optionsArr[i].split(":")[1] : "").append(i == optionsArr.length - 1 ? "\"}" : "\"},");
            }

            htmlSb.append(" ],\n");

        }

        // 默认选择第一条数据，由于跟FORM LOAD冲突，暂时屏蔽(目前解决方式：新增selectFirst属性)
        htmlSb.append("onLoadSuccess: function () {\n");

        if (this.isReadonly()) {//为只读设置灰色样式，在加载完成后执行
            htmlSb.append("var sty =  $(\"input[name='").append(this.getName()).append("']\").prev(); ");
            htmlSb.append(" sty.addClass('textbox-disabled-style');");
        }

        if (!TriRegulation.isEmpty(this.getOnLoadSuccess())) {
            htmlSb.append(this.getOnLoadSuccess()).append(";");
        }
        // 3:成功回调函数或赋值不为空
        if (!TriRegulation.isEmpty(this.getOnLoadSuccess()) || (!TriRegulation.isEmpty(this.getValue()) || !TriRegulation.isEmpty(this.getDefaultOption()))) {
            htmlSb.append(this.getId()).append("SelectValue();\n");
        }
        htmlSb.append("},\n");

        // 自定义组合模式：选项中内容带描述字段
        if (!TriRegulation.isEmpty(this.getDescField())) {

            htmlSb.append(" formatter:function(row){\n");
            htmlSb.append("var formatterStr = '<span>' + row.").append(this.getTextField()).append(" + '</span>").append(this.getOptionsDescLinkStr()).append("'");
            htmlSb.append("+ '<span style=\"color:#888\">' + row.").append(this.getDescField()).append(" + '</span>';");
            htmlSb.append(" return formatterStr;},\n");
        } else if (!TriRegulation.isEmpty(this.getFormatter())) {
            htmlSb.append(" formatter:").append(this.getFormatter()).append(",\n");
//      htmlSb.append(" formatter:function(row){\n");
//      htmlSb.append(this.getFormatter()).append("(row);},\n");
        }
        if (!TriRegulation.isEmpty(this.getOnLoadError())) {
            htmlSb.append(" onLoadError:").append(this.getOnLoadError()).append(",\n");
        }
        if (!TriRegulation.isEmpty(this.getOnSelect())) {
            htmlSb.append(" onSelect:").append(this.getOnSelect()).append(",\n");
        }
        if (!TriRegulation.isEmpty(this.getOnUnselect())) {
            htmlSb.append(" onUnselect:").append(this.getOnUnselect()).append(",\n");
        }
        if (!TriRegulation.isEmpty(this.getOnChange())) {
            htmlSb.append(" onChange:").append(this.getOnChange()).append(",\n");
        }

        htmlSb.append(" id:\"").append(this.getId()).append("\"");
        htmlSb.append(" });\n");
        htmlSb.append(" });\n");


        htmlSb.append("</script>\n");
        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public String getDefaultOption() {
        return defaultOption;
    }

    public String getOptionsDescLinkStr() {
        return optionsDescLinkStr;
    }

    public void setOptionsDescLinkStr(String optionsDescLinkStr) {
        this.optionsDescLinkStr = optionsDescLinkStr;
    }

    public void setDefaultOption(String defaultOption) {
        this.defaultOption = defaultOption;
    }

    public boolean isOptionsTitleKey() {
        return optionsTitleKey;
    }

    public void setOptionsTitleKey(boolean optionsTitleKey) {
        this.optionsTitleKey = optionsTitleKey;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getList() {
        return list;
    }

    public void setList(List<LabelValueBean> list) {
        this.list = list;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOnSelect() {
        return onSelect;
    }

    public void setOnSelect(String onSelect) {
        this.onSelect = onSelect;
    }

    public String getOnUnselect() {
        return onUnselect;
    }

    public void setOnUnselect(String onUnselect) {
        this.onUnselect = onUnselect;
    }

    public String getOnBeforeLoad() {
        return onBeforeLoad;
    }

    public void setOnBeforeLoad(String onBeforeLoad) {
        this.onBeforeLoad = onBeforeLoad;
    }

    public String getOnLoadSuccess() {
        return onLoadSuccess;
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
        this.onLoadSuccess = onLoadSuccess;
    }

    public String getOnLoadError() {
        return onLoadError;
    }

    public void setOnLoadError(String onLoadError) {
        this.onLoadError = onLoadError;
    }

    public boolean isMultiline() {
        return multiline;
    }

    public void setMultiline(boolean multiline) {
        this.multiline = multiline;
    }

    public String getDescField() {
        return descField;
    }

    public void setDescField(String descField) {
        this.descField = descField;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }

    public boolean isAutoShowPanel() {
        return autoShowPanel;
    }

    public void setAutoShowPanel(boolean autoShowPanel) {
        this.autoShowPanel = autoShowPanel;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }


}
