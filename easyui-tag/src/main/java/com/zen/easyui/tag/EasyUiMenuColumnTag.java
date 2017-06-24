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
public class EasyUiMenuColumnTag extends BodyTagSupport {
    private static final long serialVersionUID = 6593835476395070313L;

    private String id;

    private String title; // 名称

    private String titleKey; // 对应的文字key

    private String onclick; // 点击事件

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<div ");
        htmlSb.append(" onclick=\"").append(this.getOnclick()).append("\" ");

        if (!RegulationUtil.isEmpty(this.getId())) {
            htmlSb.append(" id=\"").append(this.getId()).append("\" ");
        }
        htmlSb.append(">");

        if (!RegulationUtil.isEmpty(this.getTitle())) {
            htmlSb.append(this.getTitle());
        } else if (!RegulationUtil.isEmpty(this.getTitleKey())) {
            String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
            htmlSb.append(!RegulationUtil.isEmpty(keyStr) ? keyStr : "");
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
}
