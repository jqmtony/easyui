package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EasyUiDeptGridTag extends BodyTagSupport {

    private static final long serialVersionUID = 1634618855354058065L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private static final int defaultPanelWidth = 200;

    private String id;

    private String name;

    private String style;

    private String value;

    private String width = "150";//标准宽度

    private String panelWidth;

    private String panelHeight;

    private boolean required = false;

    private boolean disabled = false;

    private boolean autoValueCombo = true;//是否开启弹出edit页自动赋值

    private String onChange;

    private List<EasyUiComboGridColumnTag> columns = new ArrayList<EasyUiComboGridColumnTag>();

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        htmlSb.append("<select id=\"").append(this.getId()).append("\" name=\"").append(this.getName()).append("\" ");
        htmlSb.append(" class='sign-combobox'");//用于jquery定位，勿删
        htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        if (isAutoValueCombo()) {
            htmlSb.append(" autoValueCombo='true'");
        }
        htmlSb.append("></select>");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("$(function(){\n");
        htmlSb.append(" if (isEmpty(unloadCombogridIds) || unloadCombogridIds.indexOf('" + this.getId() + "')<0) {\n");
        htmlSb.append(" ++unloadCombogridNum ;");
        htmlSb.append(" isinitFlagCombogrid" + this.getId() + "=true ;\n");
        htmlSb.append("}\n");
        htmlSb.append(" $('#").append(this.getId()).append("').combogrid({\n");
        htmlSb.append("   url:'").append("login.do?method=getUserDeptListBean").append("',  \n");
        htmlSb.append("   idField: 'id', \n");
        htmlSb.append("   textField: 'deptName',  \n");
        htmlSb.append("   enableHeaderClickMenu:").append("false").append(",\n");//小三角显示错位,禁用
        htmlSb.append("   editable: false \n");

        if (this.isDisabled()) {
            htmlSb.append(",\n");
            htmlSb.append("   disabled: ").append(this.isDisabled());
        }
        // if (this.isReadonly()) {
        // htmlSb.append(" $('#").append(this.getId()).append("').focus(function(){  \n");
        // htmlSb.append("$(this).attr('defaultIndex',$(this).attr('selectedIndex'));  \n");
        // htmlSb.append(" }); \n");
        // htmlSb.append(" $('#").append(this.getId()).append("').change(function(){  \n");
        // htmlSb.append("$(this).attr('selectedIndex',$(this).attr('defaultIndex'));  \n");
        // htmlSb.append(" }); \n");
        // }

        if (this.isRequired()) {
            htmlSb.append(",\n");
            htmlSb.append("   required: true");
        }

        if (!TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append(",\n");
            htmlSb.append("   value:'").append(this.getValue()).append("' ");
        } else {
            htmlSb.append(",\n");
            //TODO
            //htmlSb.append("   value:'").append(SessionContainer.getUserInfo(request).getDeptId()).append("' ");
        }

        htmlSb.append(",panelWidth:").append(defaultPanelWidth);
        htmlSb.append(",width:").append(getWidth());
        if (!TriRegulation.isEmpty(this.getOnChange())) {
            htmlSb.append(",\n");
            htmlSb.append("   onChange:").append(this.getOnChange());
        }
        // if (!TriRegulation.isEmpty(this.getPanelWidth())) {
        // htmlSb.append(",\n");
        // htmlSb.append("    panelWidth:").append(this.getPanelWidth());
        // }
        // if (!TriRegulation.isEmpty(this.getPanelHeight())) {
        // htmlSb.append(",\n");
        // htmlSb.append("    panelHeight:").append(this.getPanelHeight());
        // }

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        // if (Regulation.isEmpty(this.getStyle())) {
        this.setStyle("width:150px;");
        // }
        EasyUiComboGridColumnTag tag = null;
        tag = new EasyUiComboGridColumnTag();
        tag.setField("id");
        tag.setTitle("编码");
        // tag.setTitleKey("baseinfo.dept.deptid");
        tag.setWidth("10");
        tag.setHidden(true);
        addColumn(tag);
        tag = new EasyUiComboGridColumnTag();
        tag.setField("deptNo");
        tag.setTitle("帐套号");
        // tag.setTitleKey("baseinfo.dept.deptno");
        tag.setWidth("80");
        addColumn(tag);
        tag = new EasyUiComboGridColumnTag();
        tag.setField("deptName");
        tag.setTitle("帐套名称");
        // tag.setTitleKey("baseinfo.dept.deptname");
        tag.setWidth("110");
        addColumn(tag);
        // tag = new EasyUiComboGridColumnTag();
        // tag.setField("deptDesc");
        // tag.setTitle("描述");
        // // tag.setTitleKey("baseinfo.dept.deptdesc");
        // tag.setWidth("100");
        // addColumn(tag);

        StringBuilder htmlSb = new StringBuilder();

        htmlSb.append(",\n");
        htmlSb.append("columns:[[ \n ");
        int colSize = this.getColumns().size();
        for (int i = 0; i < colSize; i++) {

            EasyUiComboGridColumnTag columnTag = this.getColumns().get(i);

            htmlSb.append("{field:'").append(columnTag.getField()).append("',title:'");

            if (!TriRegulation.isEmpty(columnTag.getTitleKey())) {
                String keyStr = MessageUtil.getMessage(pageContext.getRequest(), columnTag.getTitleKey());

                if (!TriRegulation.isEmpty(keyStr)) {
                    htmlSb.append(keyStr);
                } else {
                    htmlSb.append(columnTag.getTitle());
                }
            } else {
                htmlSb.append(columnTag.getTitle());
            }

            htmlSb.append("',");
            if (!TriRegulation.isEmpty(columnTag.getWidth())) {
                htmlSb.append("width:").append(columnTag.getWidth());
            }
            if (columnTag.isHidden()) {
                htmlSb.append(",");
                htmlSb.append("hidden:").append(columnTag.isHidden());
            }
            htmlSb.append("}");

            if (i < colSize - 1) {
                htmlSb.append(",\n");
            } else {
                htmlSb.append("\n");
            }
        }
        htmlSb.append("]]\n");
        htmlSb.append(" });");
        // 默认选择第一条数据，由于跟FORM LOAD冲突，暂时屏蔽(目前解决方式：新增selectFirst属性)


        htmlSb.append("$('#").append(this.getId()).append("').combogrid('grid').datagrid({\n");
        htmlSb.append("onLoadSuccess: function (data) {\n");
        htmlSb.append(" if (isinitFlagCombogrid" + this.getId() + " && (isEmpty(unloadCombogridIds) || unloadCombogridIds.indexOf('" + this.getId() + "')<0)) {\n");
        htmlSb.append(" --unloadCombogridNum;\n");
        htmlSb.append("unloadCombogridIds +='," + this.getId() + "';\n");
        htmlSb.append("isinitFlagCombogrid" + this.getId() + "=false;");
        htmlSb.append("}\n");
        htmlSb.append("var tagValue=getValue2TagId('").append(this.getId()).append("','combogrid');");
        if (!TriRegulation.isEmpty(this.getOnChange())) {
            htmlSb.append(this.getOnChange()).append("(tagValue);");
        }
        htmlSb.append("setValue2TagId('").append(this.getId()).append("','combogrid',tagValue);\n");

        htmlSb.append("}\n");
        htmlSb.append("});\n");


        htmlSb.append(" });");

        htmlSb.append("</script>\n");

        try {
            pageContext.getOut().write(htmlSb.toString());
            this.getColumns().clear();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    public synchronized void addColumn(EasyUiComboGridColumnTag column) {
        this.getColumns().add(column);
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

    public String getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(String panelWidth) {
        this.panelWidth = panelWidth;
    }

    public String getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(String panelHeight) {
        this.panelHeight = panelHeight;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public List<EasyUiComboGridColumnTag> getColumns() {
        return columns;
    }

    public void setColumns(List<EasyUiComboGridColumnTag> columns) {
        this.columns = columns;
    }

    public boolean isAutoValueCombo() {
        return autoValueCombo;
    }

    public void setAutoValueCombo(boolean autoValueCombo) {
        this.autoValueCombo = autoValueCombo;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }


}
