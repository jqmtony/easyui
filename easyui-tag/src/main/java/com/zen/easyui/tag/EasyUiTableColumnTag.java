package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class EasyUiTableColumnTag extends TagSupport implements Cloneable {
    private static final long serialVersionUID = -4308552552551957488L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String field; // 字段值

    private String title; // 标题

    private String titleKey; // 对应的文字key

    private String width; // 宽度

    private String formatter; // 格式化函数

    private String align; // 对其方式

    private String editor; // 编辑方式

    private boolean hidden; // 是否隐藏

    private boolean checkbox = false; // 是否显示Checkbox

    private boolean sortable = true; // 是否可以排序

    private String styler; // 列的样式 可自行定义方法

    private int rowspan; // 跨几行(多表头）

    private int colspan; // 跨几列(多表头）

    @Override
    public int doEndTag() throws JspException {
        try {
            Tag parentTag = getParent();
            //存在parentTag為EasyUiTreegridTableTag 時 存在BUG
            while (!TriRegulation.isEmpty(parentTag) && !(parentTag instanceof EasyUiDatagridTableTag) && !(parentTag instanceof EasyUiTreegridTableTag)) {
                parentTag = parentTag.getParent();
                /** 解决以下写法BUG:CZ
                 eu:column parentTag 为：c:forEach
                 <c:forEach var="bean"   items="${requestScope.columnList }" >
                 <eu:column  field="${bean.columnName }"  title="${bean.columnTitle }"  width="${bean.width }"
                 align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
                 </c:forEach>
                 **/
            }

            if (!TriRegulation.isEmpty(parentTag)) {
                if (parentTag instanceof EasyUiDatagridTableTag) {
                    EasyUiDatagridTableTag datagridTag = (EasyUiDatagridTableTag) parentTag;
                    datagridTag.addColumn((EasyUiTableColumnTag) this.clone());
                } else if (parentTag instanceof EasyUiTreegridTableTag) {
                    EasyUiTreegridTableTag treegridTag = (EasyUiTreegridTableTag) parentTag;
                    treegridTag.addColumn((EasyUiTableColumnTag) this.clone());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);

        }

        return SKIP_BODY;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public String getStyler() {
        return styler;
    }

    public void setStyler(String styler) {
        this.styler = styler;
    }

}
