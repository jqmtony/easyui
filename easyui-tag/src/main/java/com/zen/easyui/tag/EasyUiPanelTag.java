package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * EasyUiPanelTag.java
 * <p>
 * comments:
 *
 * @author hexuming
 * @creation date        Oct 31, 2012
 * @version Version_2012
 */
public class EasyUiPanelTag extends BodyTagSupport {
    private static final long serialVersionUID = 1980539023299830443L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String style; // 自定义样式

    private boolean fit = true; // 是否自动大小

    private String tools; // 工具栏按钮

    private String title; // 按钮文字

    private String titleKey; // 按钮对应的文字key

    private String iconCls; // 图标样式

    private boolean noheader = false; // 是否有头栏

    private boolean border = false; // 是否有边框

    private boolean closable = false; // 关闭按钮

    private boolean collapsible = false; // 可折叠按钮

    private boolean minimizable = false; // 最小化按钮

    private boolean maximizable = false; // 最大化按钮

    private boolean alldisable = true; // 所有按钮都不存在

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<div class=\"easyui-panel\" ");
        htmlSb.append(" id=\"").append(this.getId()).append("\" ");
        htmlSb.append(" fit=\"").append(this.isFit()).append("\" ");
        htmlSb.append(" noheader=\"").append(this.isNoheader()).append("\" ");
        htmlSb.append(" border=\"").append(this.isBorder()).append("\" ");

        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\" ");
        }
        if (!TriRegulation.isEmpty(this.getTools())) {
            htmlSb.append(" tooles=\"#").append(this.getTools()).append("\" ");
        }
        if (!TriRegulation.isEmpty(this.getTitle())) {
            htmlSb.append(" title=\"");
            htmlSb.append(TriRegulation.isEmpty(this.getTitle()) ? "" : this.getTitle());
            htmlSb.append("\"");
        } else if (!TriRegulation.isEmpty(this.getTitleKey())) {
            htmlSb.append(" title=\"");
            String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
            htmlSb.append(!TriRegulation.isEmpty(keyStr) ? keyStr : "");
            htmlSb.append("\"");
        }
        if (!TriRegulation.isEmpty(this.getIconCls())) {
            htmlSb.append(" iconCls=\"").append(this.getIconCls()).append("\" ");
        }
        if (this.isAlldisable()) {
            this.setClosable(false);
            this.setCollapsible(false);
            this.setMinimizable(false);
            this.setMaximizable(false);
        }
        htmlSb.append(" closable=\"").append(this.isClosable()).append("\" ");
        htmlSb.append(" collapsible=\"").append(this.isCollapsible()).append("\" ");
        htmlSb.append(" minimizable=\"").append(this.isMinimizable()).append("\" ");
        htmlSb.append(" maximizable=\"").append(this.isMaximizable()).append("\" ");
        htmlSb.append(">");

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {

        try {
            pageContext.getOut().write("</div>");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAlldisable() {
        return alldisable;
    }

    public void setAlldisable(boolean alldisable) {
        this.alldisable = alldisable;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    public boolean isMinimizable() {
        return minimizable;
    }

    public void setMinimizable(boolean minimizable) {
        this.minimizable = minimizable;
    }

    public boolean isMaximizable() {
        return maximizable;
    }

    public void setMaximizable(boolean maximizable) {
        this.maximizable = maximizable;
    }

    public boolean isNoheader() {
        return noheader;
    }

    public void setNoheader(boolean noheader) {
        this.noheader = noheader;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

}
