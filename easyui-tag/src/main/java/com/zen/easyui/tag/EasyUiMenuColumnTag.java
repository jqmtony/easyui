package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * @author hexuming
 */
public class EasyUiMenuColumnTag extends BodyTagSupport {
    private static final long serialVersionUID = 6593835476395070313L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String title; // 名称

    private String titleKey; // 对应的文字key

    private String onclick; // 点击事件

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<div ");
        htmlSb.append(" onclick=\"").append(this.getOnclick()).append("\" ");

        if (!TriRegulation.isEmpty(this.getId())) {
            htmlSb.append(" id=\"").append(this.getId()).append("\" ");
        }
        htmlSb.append(">");

        if (!TriRegulation.isEmpty(this.getTitle())) {
            htmlSb.append(this.getTitle());
        } else if (!TriRegulation.isEmpty(this.getTitleKey())) {
            String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
            htmlSb.append(!TriRegulation.isEmpty(keyStr) ? keyStr : "");
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

        try {
            pageContext.getOut().write("</div>");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

}
