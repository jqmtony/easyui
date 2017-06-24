package com.zen.easyui.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiMenuTreeTag extends BodyTagSupport {

    private String id; // 菜单编号

    private String url;

    private String icon; // 图标

    private boolean animate; // 动画效果

    private boolean dnd; // 拖拽方式

    private boolean collapseAll;  //是否折叠所有节点

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder htmlSb = new StringBuilder();

        htmlSb.append("<ul id=\"").append(this.getId()).append("\" class='easyui-tree'\n");
        htmlSb.append("animate='").append(this.isAnimate()).append("'\n");
        htmlSb.append("dnd='").append(this.isDnd()).append("'\n");
        htmlSb.append("></ul>\n");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("$(function(){\n");
        htmlSb.append(" $('#").append(this.getId()).append("').tree({\n");
        htmlSb.append("   url:\"").append(request.getContextPath()).append(this.getUrl()).append("\",\n");
        htmlSb.append("   onClick: function (node){\n");

        htmlSb.append("      var centerURL = \"").append(request.getContextPath()).append("\" + node.attributes.url + \"&menuid=\" + node.id;\n");//id改为menuid，貌似没什么用
        htmlSb.append("      var isNodeLeaf = $(this).tree(\"isLeaf\", node.target);\n");
        htmlSb.append("      if(isNodeLeaf) {\n");

        htmlSb.append("       addTab(node,centerURL").append(");\n");
        htmlSb.append("       } else {\n");
        htmlSb.append("         $(this).tree(\"toggle\", node.target);\n");
        htmlSb.append("       }\n");
        htmlSb.append("     },\n");
        htmlSb.append("     onLoadSuccess:function(node, data) {\n");
        if (this.collapseAll == Boolean.FALSE) {
            htmlSb.append("       $(this).tree(\"expandAll\");\n");
        } else {
            htmlSb.append("       $(this).tree(\"collapseAll\");\n");
        }
        htmlSb.append("     }\n");
        htmlSb.append("     });\n");
        htmlSb.append(" });\n");
        htmlSb.append("</script>\n");

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
            pageContext.getOut().write("");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

}
