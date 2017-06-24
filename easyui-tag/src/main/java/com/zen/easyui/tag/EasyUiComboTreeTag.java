package com.zen.easyui.tag;

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
public class EasyUiComboTreeTag extends BodyTagSupport {

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
        if (!RegulationUtil.isEmpty(this.getId())) {
            htmlSb.append(" id=\"").append(this.getId()).append("\"");
        }
        if (!RegulationUtil.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        }
        htmlSb.append("></select>");


        htmlSb.append("<script type=\"text/javascript\">\n");
        if (!RegulationUtil.isEmpty(this.getUrl())) {
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
        if (!RegulationUtil.isEmpty(this.getValueField())) {
            htmlSb.append(" valueField:'").append(this.getValueField()).append("',\n");
        }
        if (!RegulationUtil.isEmpty(this.getTextField())) {
            htmlSb.append(" textField:'").append(this.getTextField()).append("',\n");
        }

        if (!RegulationUtil.isEmpty(this.getWidth())) {
            htmlSb.append(" width:'").append(this.getWidth()).append("',\n");
        }

        if (this.isRequired()) {
            htmlSb.append("   required:").append(this.isRequired()).append(",\n");
        }
        if (!RegulationUtil.isEmpty(this.getOnChange())) {
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
        if (!RegulationUtil.isEmpty(this.getValue())) {
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
//    if (!RegulationUtil.isEmpty(this.getOnChange())) {
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
        if (!RegulationUtil.isEmpty(this.getOnSelect()) || this.isOnlyLeafSelect()) {
            htmlSb.append(",\n");
            if (!RegulationUtil.isEmpty(this.getOnSelect())) {
                htmlSb.append(" onSelect:").append(this.getOnSelect()).append(" \n");
            }
            if (this.isOnlyLeafSelect()) {
                htmlSb.append(!RegulationUtil.isEmpty(this.getOnSelect()) ? ";" : " onSelect:\n");
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

}
