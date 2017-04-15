package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * EasyUiTabPanelTag.java
 * <p>
 * comments:
 *
 * @author hexuming
 * @creation date        Oct 31, 2012
 * @version Version_2012
 */
public class EasyUiTabsTag extends BodyTagSupport {
    private static final long serialVersionUID = 1980539023299830443L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String style; // 自定义样式

    private String onSelect; // 选择函数

    private boolean fit = true; // 是否自动大小

    private boolean border = true; // 边框

    private List<EasyUiTabPageTag> tabPages = new ArrayList<EasyUiTabPageTag>();

    @Override
    public int doStartTag() throws JspException {

        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<div "); //class=\"easyui-tabs\"  无需 此处 声明  此处 声明 导致 与JS声明 重复 形成 2个 TAB cz.
        htmlSb.append(" id=\"").append(this.getId()).append("\" ");
        htmlSb.append("></div>");

        htmlSb.append("<script type=\"text/javascript\">\n");
        // htmlSb.append("$(function(){\n");
        htmlSb.append(" $('#").append(this.getId()).append("').tabs({\n");
        htmlSb.append(" fit: ").append(this.isFit()).append(", \n");
        htmlSb.append(" border: ").append(this.isBorder());
        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(",\n");
            htmlSb.append(" style:").append(this.getStyle());
        }
        if (!TriRegulation.isEmpty(this.getOnSelect())) {
            htmlSb.append(",\n");
            htmlSb.append(" onSelect:").append(this.getOnSelect());
        }
        htmlSb.append(" });\n");

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
        List<EasyUiTabPageTag> listTab = this.getTabPages();
        //如果tabs没有设置默认选中，自动选中第一个tab
        boolean existSelected = false;
        for (Iterator iterator = listTab.iterator(); iterator.hasNext(); ) {
            EasyUiTabPageTag tempTabPage = (EasyUiTabPageTag) iterator.next();
            if (tempTabPage.isSelected()) {
                existSelected = true;
                break;
            }
        }
        for (int i = 0; i < listTab.size(); i++) {
            EasyUiTabPageTag tempTabPage = listTab.get(i);
            if (i == 0 && !existSelected) {
                tempTabPage.setSelected(Boolean.TRUE);
            }
            htmlSb.append(this.addTagPage(tempTabPage));
        }
        htmlSb.append("</script>\n");

        try {
            pageContext.getOut().write(htmlSb.toString());
            this.getTabPages().clear();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    /**
     * 添加tab页
     *
     * @param tabPage
     * @return
     */
    public String addTagPage(EasyUiTabPageTag tabPage) {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append(" $('#").append(this.getId()).append("').tabs('add',{\n");
        if (!TriRegulation.isEmpty(tabPage.getId())) {
            htmlSb.append("id:'").append(tabPage.getId()).append("',\n");
        }
        htmlSb.append("title:'");
        if (!TriRegulation.isEmpty(tabPage.getTitle())) {
            htmlSb.append(tabPage.getTitle());
        } else {
            String keyStr = MessageUtil.getMessage(pageContext.getRequest(), tabPage.getTitleKey());

            if (!TriRegulation.isEmpty(keyStr)) {
                htmlSb.append(keyStr);
            } else {
                htmlSb.append(tabPage.getTitle());
            }
        }
        htmlSb.append("',\n");
        if (!TriRegulation.isEmpty(tabPage.getHref())) {
            htmlSb.append("href:'").append(tabPage.getHref()).append("',\n");
        }
        htmlSb.append("selected:").append(tabPage.isSelected()).append(",\n");
        if (tabPage.isClosable()) {
            htmlSb.append("closable:true,\n");
        }
        htmlSb.append("cache:").append(tabPage.isCache()).append("\n");
        htmlSb.append("  });\n");

        return htmlSb.toString();
    }

    public synchronized void addTabPages(EasyUiTabPageTag tabPage) {
        this.getTabPages().add(tabPage);
    }

    public String getOnSelect() {
        return onSelect;
    }

    public void setOnSelect(String onSelect) {
        this.onSelect = onSelect;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public List<EasyUiTabPageTag> getTabPages() {
        return tabPages;
    }

    public void setTabPages(List<EasyUiTabPageTag> tabPages) {
        this.tabPages = tabPages;
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

}
