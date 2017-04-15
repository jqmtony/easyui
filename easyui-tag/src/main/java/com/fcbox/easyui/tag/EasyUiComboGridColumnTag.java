package com.fcbox.easyui.tag;

import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * EasyUiComboGridColumnTag.java
 * <p>
 * comments:
 *
 * @author hexuming
 * @creation date        Oct 16, 2012
 * @version Version_2012
 */
public class EasyUiComboGridColumnTag extends TagSupport implements Cloneable {

    private static final long serialVersionUID = 7931137982528038882L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String field; // 字段值

    private String title; // 标题

    private String titleKey; // 对应的文字key

    private String width = "140"; // 宽度，防止出现横向滚动

    private boolean sortable = false; // 是否可以排序

    private String formatter; // 格式化函数

    private String align = "center"; // 对其方式

    private boolean hidden = false; // 是否隐藏

    @Override
    public int doEndTag() throws JspException {

        try {
            EasyUiComboGridTag comboGridTag = (EasyUiComboGridTag) getParent();
            comboGridTag.addColumn((EasyUiComboGridColumnTag) this.clone());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return SKIP_BODY;
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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

}
