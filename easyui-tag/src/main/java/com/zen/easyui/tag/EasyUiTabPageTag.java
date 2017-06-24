package com.zen.easyui.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiTabPageTag extends TagSupport implements Cloneable {

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

}
