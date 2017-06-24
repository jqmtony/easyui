package com.zen.easyui.tag;

import com.zen.easyui.util.MessageUtil;
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
public class EasyUiPanelTag extends BodyTagSupport {

    private static final long serialVersionUID = 1980539023299830443L;

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

        if (!RegulationUtil.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\" ");
        }
        if (!RegulationUtil.isEmpty(this.getTools())) {
            htmlSb.append(" tooles=\"#").append(this.getTools()).append("\" ");
        }
        if (!RegulationUtil.isEmpty(this.getTitle())) {
            htmlSb.append(" title=\"");
            htmlSb.append(RegulationUtil.isEmpty(this.getTitle()) ? "" : this.getTitle());
            htmlSb.append("\"");
        } else if (!RegulationUtil.isEmpty(this.getTitleKey())) {
            htmlSb.append(" title=\"");
            String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
            htmlSb.append(!RegulationUtil.isEmpty(keyStr) ? keyStr : "");
            htmlSb.append("\"");
        }
        if (!RegulationUtil.isEmpty(this.getIconCls())) {
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

}
