package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


public class EasyUiComboTreeTag extends BodyTagSupport {

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String name;

    private String value;

    private String url;

    private String valueField;

    private String textField;

    private String style;

    private String width = "150"; //统一默认宽度

    private boolean collapseAll = true; // 菜单收拢

    private boolean required = false;

    private boolean editable = false;

    private boolean disable = false;

    private boolean enable = true;

    private boolean multiCheck; // 是否多选

    private boolean cascadeCheck;

    private boolean onlyLeafCheck = false;  //定义是否只在末级节点之前显示复选框

    private boolean onlyLeafSelect = false;  //只能选择叶子节点

    private String onChange;

    private String onSelect;

    @Override
    public int doStartTag() throws JspException {

        StringBuilder htmlSb = new StringBuilder();


        htmlSb.append("<select type=\"text\" ");
        htmlSb.append(" name=\"").append(this.getName()).append("\"");
        if (!TriRegulation.isEmpty(this.getId())) {
            htmlSb.append(" id=\"").append(this.getId()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        }
        htmlSb.append("></select>");


        htmlSb.append("<script type=\"text/javascript\">\n");
        if (!TriRegulation.isEmpty(this.getUrl())) {
//      htmlSb.append("alert('unloadCombotreeIds=====' + unloadCombotreeIds);");
            htmlSb.append("if (isEmpty(unloadCombotreeIds) || unloadCombotreeIds.indexOf('" + this.getId() + "')<0) {\n");
            htmlSb.append(" ++unloadCombotreeNum ;\n");
//      htmlSb.append(" alert('unloadCombotreeNum='+unloadCombotreeNum+',当前combotree=").append(this.getId()).append("');");
            htmlSb.append(" isinitFlagCombotree" + this.getId() + "=true ;\n");
            htmlSb.append("}\n  ");
        }
        // cz:变更为function OnlyLeafSelect+id(){ } 目的:避免出现数字或者关键字的ID,导致JS Unexpected token ILLEGAL异常
        htmlSb.append("function ").append("OnlyLeafSelect").append(this.getId()).append("(){\n");
        htmlSb.append("var t = $('#").append(this.getId()).append("').combotree('tree');\n");
        htmlSb.append("var n = t.tree('getSelected');\n");
        htmlSb.append("var flag=t.tree('isLeaf',n.target);\n");
        htmlSb.append("if(!flag){\n");
        htmlSb.append("   euShow(\"请选择菜单叶子节点\");\n");

        htmlSb.append("$('#").append(this.getId()).append("').combotree('clear');\n");
        htmlSb.append("}\n");
        htmlSb.append("return flag;\n");
        htmlSb.append("}\n");
        htmlSb.append(" $('#").append(this.getId()).append("').combotree({\n");

        htmlSb.append("   onlyLeafCheck: '").append(this.isOnlyLeafCheck()).append("',\n");
        htmlSb.append("   url: '").append(this.getUrl()).append("',\n");
        if (!TriRegulation.isEmpty(this.getValueField())) {
            htmlSb.append(" valueField:'").append(this.getValueField()).append("',\n");
        }
        if (!TriRegulation.isEmpty(this.getTextField())) {
            htmlSb.append(" textField:'").append(this.getTextField()).append("',\n");
        }

        if (!TriRegulation.isEmpty(this.getWidth())) {
            htmlSb.append(" width:'").append(this.getWidth()).append("',\n");
        }

        if (this.isRequired()) {
            htmlSb.append("   required:").append(this.isRequired()).append(",\n");
        }
        if (!TriRegulation.isEmpty(this.getOnChange())) {
            htmlSb.append("   onChange:function(){\n");
            htmlSb.append("     var tempChangeValue = getValue2TagId('").append(this.getId()).append("','combotree');");
            htmlSb.append("     if (!isEmpty(tempChangeValue)) {\n");
            htmlSb.append(this.getOnChange()).append("(tempChangeValue);}\n");
            htmlSb.append("     },\n");

        }
        htmlSb.append(" cascadeCheck:").append(this.isCascadeCheck()).append(",\n");
        htmlSb.append(" multiple:").append(this.isMultiCheck()).append(",\n");

        htmlSb.append("     onLoadSuccess:function(node, data) {\n");

//  htmlSb.append(" alert('onLoadSuccess="+this.getId()+" unloadCombotreeNum='+unloadCombotreeNum);");
        htmlSb.append(" if (isinitFlagCombotree" + this.getId() + " && (isEmpty(unloadCombotreeIds) || unloadCombotreeIds.indexOf('" + this.getId() + "')<0)) {\n");
        htmlSb.append(" --unloadCombotreeNum;\n");
        htmlSb.append("unloadCombotreeIds +='," + this.getId() + "';\n");
        htmlSb.append("isinitFlagCombotree" + this.getId() + "=false;\n");
        htmlSb.append("}\n");

        //设置弹出页面，combotree默认选择第一条
        if (!TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append(" var tagValue = '").append(this.getValue()).append("'; \n");
        } else {
            htmlSb.append(" var tagValue = getValue2TagId('").append(this.getId()).append("','combotree');");
        }
        //是否收缩树
        if (this.isCollapseAll()) {
            htmlSb.append("  $(this).tree('collapseAll');\n");
        }

        //暂时屏蔽默认选择树的根节点操作。
        htmlSb.append("if (isEmpty(tagValue").append(")) {\n");

//    htmlSb.append("   var roots=$('#").append(this.getId()).append("').combotree('tree').tree('getRoots');\n");
//    htmlSb.append("   if (roots.length>0) {\n");
//    htmlSb.append("       tagValue = roots[0].id; \n");
//    htmlSb.append("    } \n");
//    htmlSb.append("     setValue2TagId('").append(this.getId()).append("','combotree',tagValue);\n");
//    //默认定位到树选中节点
//    htmlSb.append(" var node= $(this).tree('find',tagValue);\n  ");
//    htmlSb.append(" alert(node);\n  ");
//    htmlSb.append(" $('#").append(this.getId()).append("').combotree('tree').tree('find',node.id);\n");
//    htmlSb.append(" $('#").append(this.getId()).append("').combotree('tree').tree('expandTo',node);\n");
//    htmlSb.append(" $('#").append(this.getId()).append("').combotree('tree').tree('check',node);\n");
//    if (!TriRegulation.isEmpty(this.getOnChange())) {
//      htmlSb.append(this.getOnChange()).append("(tagValue);");
//    }
        //暂时屏蔽默认选择树的根节点操作。

        htmlSb.append("} else {\n");
        //默认传一次空值原因是：连续传两次一样的值时，第二次不响应onChange事件。
        htmlSb.append("     setValue2TagId('").append(this.getId()).append("','combotree','');\n");
        htmlSb.append("     setValue2TagId('").append(this.getId()).append("','combotree',tagValue").append(");\n");
        //展开到指定节点
        htmlSb.append(" var node=  $('#").append(this.getId()).append("').combotree('tree').tree('find',tagValue);\n");
        htmlSb.append(" $('#").append(this.getId()).append("').combotree('tree').tree('expandTo',node.target);\n");

        htmlSb.append("}\n ");

        htmlSb.append("     }");
        if (!TriRegulation.isEmpty(this.getOnSelect()) || this.isOnlyLeafSelect()) {
            htmlSb.append(",\n");
            if (!TriRegulation.isEmpty(this.getOnSelect())) {
                htmlSb.append(" onSelect:").append(this.getOnSelect()).append(" \n");
            }
            if (this.isOnlyLeafSelect()) {
                htmlSb.append(!TriRegulation.isEmpty(this.getOnSelect()) ? ";" : " onSelect:\n");
                htmlSb.append("OnlyLeafSelect").append(this.getId()).append(" \n");
            }
        }

        htmlSb.append("     });\n");
//    htmlSb.append(" });\n");
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

    public boolean isCollapseAll() {
        return collapseAll;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setCollapseAll(boolean collapseAll) {
        this.collapseAll = collapseAll;
    }

    public String getName() {
        return name;
    }

    public boolean isCascadeCheck() {
        return cascadeCheck;
    }

    public void setCascadeCheck(boolean cascadeCheck) {
        this.cascadeCheck = cascadeCheck;
    }

    public boolean isMultiCheck() {
        return multiCheck;
    }

    public void setMultiCheck(boolean multiCheck) {
        this.multiCheck = multiCheck;
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

    public boolean isOnlyLeafCheck() {
        return onlyLeafCheck;
    }

    public void setOnlyLeafCheck(boolean onlyLeafCheck) {
        this.onlyLeafCheck = onlyLeafCheck;
    }

    public boolean isOnlyLeafSelect() {
        return onlyLeafSelect;
    }

    public void setOnlyLeafSelect(boolean onlyLeafSelect) {
        this.onlyLeafSelect = onlyLeafSelect;
    }

    public String getOnSelect() {
        return onSelect;
    }

    public void setOnSelect(String onSelect) {
        this.onSelect = onSelect;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}
