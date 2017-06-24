package com.zen.easyui.tag;

import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.ObjectHelper;
import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiComboboxTag extends BodyTagSupport {

    private static final long serialVersionUID = -799903023945799288L;

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
        if ((!RegulationUtil.isEmpty(this.getValue()) || !RegulationUtil.isEmpty(this.getDefaultOption()))) {
            htmlSb.append("function ").append(this.getId()).append("SelectValue(){\n");
            if (!RegulationUtil.isEmpty(this.getValue())) {
                htmlSb.append("var tagValue = '").append(this.getValue()).append("';\n");
            } else if (!RegulationUtil.isEmpty(this.getDefaultOption())) {
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

        if (!RegulationUtil.isEmpty(this.getUrl())) {
            htmlSb.append(" url:\"").append(this.getUrl()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getData())) {
            htmlSb.append(" data:").append(this.getData()).append(",");
        }

        if (!RegulationUtil.isEmpty(this.getValueField())) {
            htmlSb.append(" valueField:\"").append(this.getValueField()).append("\",");
        }

        if (!RegulationUtil.isEmpty(this.getTextField())) {
            htmlSb.append(" textField:\"").append(this.getTextField()).append("\",");
        }

        if (!RegulationUtil.isEmpty(this.getGroupField())) {
            htmlSb.append(" groupField:\"").append(this.getGroupField()).append("\",");
        }

        if (!RegulationUtil.isEmpty(this.getStyle())) {
            htmlSb.append(" style:\"").append(this.getStyle()).append("\",");
        }

        if (this.isMultiple()) {
            htmlSb.append(" multiple:").append(this.isMultiple()).append(",");
        }
        if (!RegulationUtil.isEmpty(this.getWidth())) {
            htmlSb.append(" width:").append(this.getWidth()).append(",");
        }
        if (this.isMultiline()) {
            htmlSb.append(" multiline:").append(this.isMultiline()).append(",");
        }
        // 1:list数据不为空，构造data
        if (RegulationUtil.isEmpty(this.getUrl()) && !RegulationUtil.isEmpty(this.getList())) {
            htmlSb.append(" data:[\n");
            Object obj = null;
            Object value = null;
            for (Iterator iterator = this.getList().iterator(); iterator.hasNext(); ) {
                obj = iterator.next();//obj类型 list/map/bean
                value = ObjectHelper.getFieldValue4Object(obj, this.getValueField());
                htmlSb.append("{label:\"");
                htmlSb.append(ObjectHelper.getFieldValue4Object(obj, this.getTextField()));
                // 是否带内容描述字段
                htmlSb.append(!RegulationUtil.isEmpty(this.getDescField()) ? "\",desc:\"" + ObjectHelper.getFieldValue4Object(obj, this.getDescField()) : "");
                // 是否带分组字段
                htmlSb.append(!RegulationUtil.isEmpty(this.getGroupField()) ? "\",group:\"" + ObjectHelper.getFieldValue4Object(obj, this.getGroupField()) : "");
                htmlSb.append("\",value:\"");
                htmlSb.append(RegulationUtil.isEmpty(value) ? "" : value).append(iterator.hasNext() ? "\"}," : "\"}");
            }
            htmlSb.append(" ],\n");
        }
        // 2:options数据不为空，构造data
        if (RegulationUtil.isEmpty(this.getUrl()) && !RegulationUtil.isEmpty(this.getOptions())) {
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
                    if (RegulationUtil.isEmpty(this.getDescField())) {
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

        if (!RegulationUtil.isEmpty(this.getOnLoadSuccess())) {
            htmlSb.append(this.getOnLoadSuccess()).append(";");
        }
        // 3:成功回调函数或赋值不为空
        if (!RegulationUtil.isEmpty(this.getOnLoadSuccess()) || (!RegulationUtil.isEmpty(this.getValue()) || !RegulationUtil.isEmpty(this.getDefaultOption()))) {
            htmlSb.append(this.getId()).append("SelectValue();\n");
        }
        htmlSb.append("},\n");

        // 自定义组合模式：选项中内容带描述字段
        if (!RegulationUtil.isEmpty(this.getDescField())) {

            htmlSb.append(" formatter:function(row){\n");
            htmlSb.append("var formatterStr = '<span>' + row.").append(this.getTextField()).append(" + '</span>").append(this.getOptionsDescLinkStr()).append("'");
            htmlSb.append("+ '<span style=\"color:#888\">' + row.").append(this.getDescField()).append(" + '</span>';");
            htmlSb.append(" return formatterStr;},\n");
        } else if (!RegulationUtil.isEmpty(this.getFormatter())) {
            htmlSb.append(" formatter:").append(this.getFormatter()).append(",\n");
//      htmlSb.append(" formatter:function(row){\n");
//      htmlSb.append(this.getFormatter()).append("(row);},\n");
        }
        if (!RegulationUtil.isEmpty(this.getOnLoadError())) {
            htmlSb.append(" onLoadError:").append(this.getOnLoadError()).append(",\n");
        }
        if (!RegulationUtil.isEmpty(this.getOnSelect())) {
            htmlSb.append(" onSelect:").append(this.getOnSelect()).append(",\n");
        }
        if (!RegulationUtil.isEmpty(this.getOnUnselect())) {
            htmlSb.append(" onUnselect:").append(this.getOnUnselect()).append(",\n");
        }
        if (!RegulationUtil.isEmpty(this.getOnChange())) {
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

}
