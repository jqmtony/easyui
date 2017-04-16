package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.TriStringUtil;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class EasyUiMenuButtonTag extends BodyTagSupport {

    private static final long serialVersionUID = 4040423476954803162L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String iconClass = "icon-more"; // 图标样式icon-standard-rainbow

    private String title = "More"; // 按钮文字

    private String titleKey;

    private String style;

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuffer htmlSb = new StringBuffer();
            if (TriRegulation.isEmpty(this.getId())) {
                this.setId(TriStringUtil.numRandom(2));
            }
            htmlSb.append("<a href=\"#\"  class=\"easyui-menubutton\" ");

            if (!"".equals(this.getIconClass())) {
                htmlSb.append(" iconCls=\"").append(this.getIconClass()).append("\"");
            }

            if (!TriRegulation.isEmpty(this.getId())) {
                htmlSb.append(" id=\"").append(this.getId()).append("\"");
                htmlSb.append(" menu=\"#mm_").append(this.getId()).append("\"");
            }

            htmlSb.append(">\n");

            if (!TriRegulation.isEmpty(this.getTitle())) {
                htmlSb.append(this.getTitle());
            } else if (!TriRegulation.isEmpty(this.getTitleKey())) {
                String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
                htmlSb.append(!TriRegulation.isEmpty(keyStr) ? keyStr : "");
            }
            htmlSb.append("\n</a>\n");

            htmlSb.append("<div id=\"mm_").append(this.getId()).append("\" style=\"").append(getStyle()).append(" \">\n ");

            pageContext.getOut().write(htmlSb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException {

        try {
            pageContext.getOut().write("</div>\n");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
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

    public void setStyle(String style) {
        this.style = style;
    }


}
