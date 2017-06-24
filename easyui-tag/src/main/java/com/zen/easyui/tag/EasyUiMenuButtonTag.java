package com.zen.easyui.tag;

import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.RandomUtil;
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
public class EasyUiMenuButtonTag extends BodyTagSupport {

    private static final long serialVersionUID = 4040423476954803162L;

    private String id; // 编号

    private String iconClass = "icon-more"; // 图标样式icon-standard-rainbow

    private String title = "More"; // 按钮文字

    private String titleKey;

    private String style;

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuffer htmlSb = new StringBuffer();
            if (RegulationUtil.isEmpty(this.getId())) {
                this.setId(RandomUtil.numRandom(2));
            }
            htmlSb.append("<a href=\"#\"  class=\"easyui-menubutton\" ");

            if (!"".equals(this.getIconClass())) {
                htmlSb.append(" iconCls=\"").append(this.getIconClass()).append("\"");
            }

            if (!RegulationUtil.isEmpty(this.getId())) {
                htmlSb.append(" id=\"").append(this.getId()).append("\"");
                htmlSb.append(" menu=\"#mm_").append(this.getId()).append("\"");
            }

            htmlSb.append(">\n");

            if (!RegulationUtil.isEmpty(this.getTitle())) {
                htmlSb.append(this.getTitle());
            } else if (!RegulationUtil.isEmpty(this.getTitleKey())) {
                String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
                htmlSb.append(!RegulationUtil.isEmpty(keyStr) ? keyStr : "");
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

}
