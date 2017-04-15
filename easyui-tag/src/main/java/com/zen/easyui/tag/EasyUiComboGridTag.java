package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyUiComboGridTag.java
 * <p>
 * comments:
 *
 * @author hexuming
 * @version Version_2012
 * @creation date Oct 16, 2012
 */
public class EasyUiComboGridTag extends BodyTagSupport {
    private static final long serialVersionUID = 1634618855354058065L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String defaultAglin = "center";

    private String id;

    private String name;

    private String style;

    private String url;

    private String idField;

    private String textField;

    private String value; //如果combogrid存在父关联对象，则子对象一定要设置value值

    private String panelWidth = "150";//面板与默认宽度统一

    private String panelHeight;

    private String width = "150";//默认宽度统一150，

    private boolean autoValueCombo = true;//是否开启弹出edit页自动赋值

    private boolean editable = false;

    private boolean disabled = false;

    private boolean standardsize = false;

    private boolean required = false;
    /**
     * 过滤方式：local/remote
     */
    private String filterMode;

    private String onChange;
    /**
     * 默认选中第一行
     */
    private boolean selectFirst = true;

    private boolean autoShowPanel = true;

    private boolean readonly = false;

    private List<EasyUiComboGridColumnTag> columns = new ArrayList<EasyUiComboGridColumnTag>();

    @Override
    public int doStartTag() throws JspException {

        StringBuilder htmlSb = new StringBuilder();

        htmlSb.append("<select id=\"").append(this.getId()).append("\" name=\"").append(this.getName()).append("\" ");
        htmlSb.append(" class='sign-combobox'");//用于jquery定位，勿删
        if (isAutoValueCombo()) {
            htmlSb.append(" autoValueCombo='true'");
        }

        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append(" \"");
        }

        htmlSb.append("></select>");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("var isinitFlagCombogrid" + this.getId() + "= false;\n");
        if (!TriRegulation.isEmpty(this.getUrl())) {
//      htmlSb.append(" alert('起初：unloadCombogridNum='+unloadCombogridNum+'当前combogrid=").append(this.getId()).append("');");
            htmlSb.append("if (isEmpty(unloadCombogridIds) || unloadCombogridIds.indexOf('" + this.getId() + "')<0) {\n");
            htmlSb.append(" ++unloadCombogridNum ;\n");
//      htmlSb.append(" alert('unloadCombogridNum='+unloadCombogridNum+'当前combogrid=").append(this.getId()).append("');");
            htmlSb.append(" isinitFlagCombogrid" + this.getId() + "=true ;\n");
            htmlSb.append("}\n  ");
        }
        // htmlSb.append("$(function(){\n");
        htmlSb.append(" $('#").append(this.getId()).append("').combogrid({\n");
        htmlSb.append("   url: '").append(this.getUrl()).append("',\n");
        htmlSb.append("   idField: '").append(this.getIdField()).append("',\n");
        htmlSb.append("   textField: '").append(this.getTextField()).append("',\n");
        htmlSb.append("   autoShowPanel: ").append(this.autoShowPanel).append(",\n");
        //默认关闭小三角下拉框
        htmlSb.append("   enableHeaderClickMenu: ").append("false").append(",\n");
        htmlSb.append("   editable: ").append(this.isEditable());

        if (this.isReadonly()) {
            htmlSb.append(",\n");
            htmlSb.append("   readonly: ").append(this.isReadonly());
        }
        if (this.isDisabled()) {
            htmlSb.append(",\n");
            htmlSb.append("   disabled: ").append(this.isDisabled());
        }
        if (this.isRequired()) {
            htmlSb.append(",\n");
            htmlSb.append("   required: true");
        }
        if (!TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append(",\n");
            htmlSb.append("   value:'").append(this.getValue()).append("'");
        }

        if (!TriRegulation.isEmpty(this.getFilterMode())) {
            htmlSb.append(",\n");
            htmlSb.append("   mode:'").append(this.getFilterMode()).append("'");
        }
        // 标准样式
        if (this.isStandardsize()) {
            this.setPanelHeight(this.getPanelHeight());
            this.setPanelWidth(this.getPanelWidth());
        }
        if (!TriRegulation.isEmpty(this.getPanelWidth())) {
            htmlSb.append(",\n");
            htmlSb.append("   panelWidth:").append(this.getPanelWidth());
        }

        if (!TriRegulation.isEmpty(this.getPanelHeight())) {
            htmlSb.append(",\n");
            htmlSb.append("   panelHeight:").append(this.getPanelHeight());
        }
        if (!TriRegulation.isEmpty(this.getWidth())) {
            htmlSb.append(",\n");
            htmlSb.append("   width:").append(this.getWidth());
        }
        if (!TriRegulation.isEmpty(this.getOnChange())) {
            htmlSb.append(",\n");
            htmlSb.append("   onChange:").append(this.getOnChange());
        }

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {

        StringBuilder htmlSb = new StringBuilder();

        htmlSb.append(",\n");
        htmlSb.append("columns:[[ \n ");

        int colSize = this.getColumns().size();
        int showColSize = 0;

        for (int i = 0; i < colSize; i++) {
            if (!this.getColumns().get(i).isHidden()) {
                ++showColSize;
            }
        }
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
            if (!columnTag.isHidden() && TriRegulation.isEmpty(columnTag.getWidth())) {
                columnTag.setWidth(String.valueOf((Integer.valueOf(this.getPanelWidth()) / showColSize) - 1));
            }
            if (!columnTag.isHidden() && !TriRegulation.isEmpty(columnTag.getWidth())) {
                htmlSb.append("width:").append(columnTag.getWidth()).append(",");
            }
            if (!columnTag.isHidden() && !TriRegulation.isEmpty(columnTag.getAlign())) {
                htmlSb.append("align:'").append(columnTag.getAlign()).append("',");
            } else {
                htmlSb.append("align:'").append(this.defaultAglin).append("',");
            }

            htmlSb.append("hidden:").append(columnTag.isHidden()).append("}");

            if (i < colSize - 1) {
                htmlSb.append(",\n");
            } else {
                htmlSb.append("\n");
            }
        }
        htmlSb.append("]] \n ");

        // 默认选择第一条数据，由于跟FORM LOAD冲突，暂时屏蔽(目前解决方式：新增selectFirst属性)
        if (TriRegulation.isEmpty(this.getValue())) {
//    if (this.isSelectFirst() && TriRegulation.isEmpty(this.getValue())) { //是否需要考虑this.isSelectFirst()这个条件
            htmlSb.append(",onLoadSuccess: function () {\n");

            if (this.isReadonly()) {//为只读设置灰色样式，在加载完成后执行
                htmlSb.append("var sty =  $(\"input[name='").append(this.getName()).append("']\").prev(); ");
                htmlSb.append(" sty.addClass('textbox-disabled-style');");
            }

//      htmlSb.append(" alert('onLoadSuccess="+this.getId()+" unloadCombogridNum='+unloadCombogridNum);");
            htmlSb.append(" if (isinitFlagCombogrid" + this.getId() + " && (isEmpty(unloadCombogridIds) || unloadCombogridIds.indexOf('" + this.getId() + "')<0)) {\n");
            htmlSb.append(" --unloadCombogridNum;\n");
            htmlSb.append("unloadCombogridIds +='," + this.getId() + "';\n");
            htmlSb.append("isinitFlagCombogrid" + this.getId() + "=false;\n");
            htmlSb.append("}\n");
//        如果combogrid存在父关联对象，则子对象一定要设置value值
            if (!TriRegulation.isEmpty(this.getValue())) {
                htmlSb.append("var tagValue = '").append(this.getValue()).append("';\n");
            } else if (this.isSelectFirst()) {//如果不选第一条记录，则不自动赋值
                htmlSb.append("var tagValue=getValue2TagId('").append(this.getId()).append("','combogrid');");
            }
//      htmlSb.append(" alert('combogird="+this.getId()+" tagValue='+tagValue);");
            htmlSb.append("var tagValue = ").append(!TriRegulation.isEmpty(this.getValue()) ? "'" + this.getValue() + "'" : this.isSelectFirst() ? "getValue2TagId('" + this.getId() + "','combogrid')" : "''").append(";");
            htmlSb.append("if (isEmpty(tagValue) && ").append(this.isSelectFirst()).append(") {\n");
            htmlSb.append("var rows=$('#").append(this.getId()).append("').combogrid('grid').datagrid('getRows');\n");
            htmlSb.append("if (rows.length>0) {\n");
            htmlSb.append("$('#").append(this.getId()).append("').combogrid('grid').datagrid('selectRow',0);\n");
            if (!TriRegulation.isEmpty(this.getOnChange())) {
                htmlSb.append(this.getOnChange()).append("(rows[0]." + this.getIdField().trim() + ");");
            }
            htmlSb.append("} \n");
            htmlSb.append("} else {\n");
            htmlSb.append("setValue2TagId('").append(this.getId()).append("','combogrid',tagValue);\n");
            //编辑状态下，可能出现“省份”、“地市”  级联操作。   省份确定后，必须执行onChange事件使地市渲染值以后，才把formJson值赋给edit页面。但是loadFormJson时，会自动执行onChange事件。
//      if (!TriRegulation.isEmpty(this.getOnChange())) {
//        htmlSb.append(this.getOnChange()).append("(tagValue);");
//      }
            htmlSb.append("}\n");
            htmlSb.append("}\n");
        }


        htmlSb.append(" }); \n");

//    htmlSb.append("$('#").append(this.getId()).append("').combobox({onLoadSuccess: function(param){");
//    htmlSb.append("alert(2); ");    
//    if (this.isReadonly()) {//为只读设置灰色样式，在加载完成后执行
//      htmlSb.append("var sty =  $(\"input[name='").append(this.getName()).append("']\").prev(); ");     
//      htmlSb.append(" sty.addClass('textbox-disabled-style');");    
//    }
//    htmlSb.append("}});");

        htmlSb.append("</script>\n");

        try {
            pageContext.getOut().write(htmlSb.toString());
            this.getColumns().clear();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    public String getFilterMode() {
        return filterMode;
    }

    public void setFilterMode(String filterMode) {
        this.filterMode = filterMode;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
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

    public boolean isStandardsize() {
        return standardsize;
    }

    public void setStandardsize(boolean standardsize) {
        this.standardsize = standardsize;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public List<EasyUiComboGridColumnTag> getColumns() {
        return columns;
    }

    public void setColumns(List<EasyUiComboGridColumnTag> columns) {
        this.columns = columns;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isSelectFirst() {
        return selectFirst;
    }

    public void setSelectFirst(boolean selectFirst) {
        this.selectFirst = selectFirst;
    }

    public boolean isAutoShowPanel() {
        return autoShowPanel;
    }

    public void setAutoShowPanel(boolean autoShowPanel) {
        this.autoShowPanel = autoShowPanel;
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

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }


}
