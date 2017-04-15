package com.zen.easyui.tag;

import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * EasyUiTabTag.java
 * <p>
 * comments:
 *
 * @author hexuming
 * @version Version_2012
 * @creation date        2013-1-28
 */
public class EasyUiTabPageTag extends TagSupport implements Cloneable {

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // id

    private String title; // 标题

    private String titleKey; // 对应的文字key

    private boolean closable = false; // 是否可以关闭

    private String iconClass; // 图标样式

    private String style; // 自定义样式

    private String toolsId; // 标签头工具栏div编号

    private boolean selected = false; // 是否默认选中

    private boolean cache = true; // 是否缓存

    private String href; // 页面路径

    private String onSelect;

    @Override
    public int doEndTag() throws JspException {

        try {
            EasyUiTabsTag tabsTag = (EasyUiTabsTag) getParent();
            tabsTag.addTabPages((EasyUiTabPageTag) this.clone());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }

        return SKIP_BODY;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getOnSelect() {
        return onSelect;
    }

    public void setOnSelect(String onSelect) {
        this.onSelect = onSelect;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    public String getIconClass() {
        return iconClass;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getToolsId() {
        return toolsId;
    }

    public void setToolsId(String toolsId) {
        this.toolsId = toolsId;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
