package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


public class EasyUiTreeTag extends BodyTagSupport {

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String url;

    private String formatter;

    private boolean collapseAll = true; // 菜单收拢

    private boolean animate = false; // 定义当节点展开或折叠时是否显示动画效果

    private boolean checkbox = false;

    private boolean onlyLeafCheck = false;

    private boolean lines = false;

    private boolean dnd = false;

    private boolean multiCheck; // 是否多选

    private boolean cascadeCheck; // 普通级联（不包括未加载的子节点）

    private boolean deepCascadeCheck; // 深度级联（包括未加载的子节点）

    private String onChange;

    private String onClick; // 可以传多个方法名，以盗号隔开

    private String onSuccess;

    private String doEvent;

    private String onExpand; //展开事件

    private String onBeforeExpand;

    private boolean isselectRoot = true; // 是否默认选择根节点

    @Override
    public int doStartTag() throws JspException {

        StringBuilder htmlSb = new StringBuilder();

        htmlSb.append("<dir icon=\"icon-reload\" selected=\"false\" style=\"padding: 10px;\"> \n");
        htmlSb.append("<ul id=\"").append(this.getId()).append("\"></ul> \n");
        htmlSb.append("</dir> \n");

        htmlSb.append("<script type=\"text/javascript\">\n");
        // htmlSb.append("var ").append(this.getId()).append("NodeId = \"\"; \n");
        htmlSb.append("$(function(){\n");
        htmlSb.append(" $('#").append(this.getId()).append("').tree({\n");
        htmlSb.append("   url: '").append(this.getUrl()).append("',\n");

        if (!TriRegulation.isEmpty(this.getOnClick())) {
            htmlSb.append("onClick: function (node){\n");
            for (int i = 0; i < this.getOnClick().split(",").length; i++) {
                htmlSb.append(this.getOnClick().split(",")[i]).append("(node);\n");
            }
            htmlSb.append("}, ");
        }

        if (!TriRegulation.isEmpty(this.getOnExpand())) {
            htmlSb.append("  onExpand: ").append(this.getOnExpand());
            htmlSb.append(",\n");
        }

        if (!TriRegulation.isEmpty(this.getOnBeforeExpand())) {
            htmlSb.append("  onBeforeExpand: ").append(this.getOnBeforeExpand());
            htmlSb.append(",\n");
        }


        // 初始化页面时，选择第一个节点相应ONCLICK事件
        String doEventCode = "";
        if (!TriRegulation.isEmpty(this.getDoEvent())) {// "1:dd();2:d2();"
            String[] str = this.getDoEvent().split(";");
            for (int i = 0; i < str.length; i++) {
                String[] actionType = str[i].split(":");
                if (!TriRegulation.isEmpty(actionType[0]) && actionType[0].equals("onclick")) {// 1:单机事件
                    htmlSb.append("onClick: function (node){\n");
                    // htmlSb.append("alert('").append(actionType[1].split(",")).append("');");
                    for (int j = 0; j < actionType[1].split(",").length; j++) {
                        htmlSb.append(actionType[1].split(",")[j]).append("(node);\n");

                        // 初始化页面时，选择第一个节点相应ONCLICK事件
                        doEventCode += actionType[1].split(",")[j] + "(node);";
                    }
                    htmlSb.append("}, ");
                } else if (!TriRegulation.isEmpty(actionType[0]) && actionType[0].equals("ondbclick")) {// 2：双机事件
                    htmlSb.append("onDbClick: function (node){\n");
                    for (int j = 0; j < actionType[1].split(",").length; j++) {
                        htmlSb.append(actionType[1].split(",")[j]).append("(node);\n");
                    }
                    htmlSb.append("}, ");
                }
            }
        }
        htmlSb.append("onLoadSuccess:function(node, data) {\n ");
        htmlSb.append(" if (isEmpty(relationObjParamMap) || isEmpty(relationObjParamMap.get('").append(this.getId()).append("NodeId'))) {\n");
        htmlSb.append("     node = $(this).tree(\"getRoot\");\n");
        htmlSb.append(" } else {\n");
        htmlSb.append("     node = $(this).tree('find',relationObjParamMap.get('").append(this.getId()).append("NodeId'));\n");
        htmlSb.append("}\n");
        // 收拢
        if (!TriRegulation.isEmpty(this.isCollapseAll()) && this.isCollapseAll()) {
            htmlSb.append("$(this).tree('collapseAll');\n");
        }

        if (!TriRegulation.isEmpty(this.isselectRoot) && this.isselectRoot) {
            htmlSb.append("$(this).tree('select', node.target);\n");
            htmlSb.append("$(this).tree('expandTo', node.target);\n");

            if (!TriRegulation.isEmpty(this.getOnClick())) {
                for (int i = 0; i < this.getOnClick().split(",").length; i++) {
                    htmlSb.append(this.getOnClick().split(",")[i]).append("(node);\n");
                }
            }
        }

        if (!TriRegulation.isEmpty(doEventCode)) {
            htmlSb.append(doEventCode);
        }
        if (!TriRegulation.isEmpty(this.getOnSuccess())) {
            htmlSb.append(this.getOnSuccess()).append("(node);\n");
        }

        htmlSb.append("}\n");

        htmlSb.append("     });\n");
        htmlSb.append(" });\n");
        htmlSb.append("</script>\n");

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIsselectRoot() {
        return isselectRoot;
    }

    public void setIsselectRoot(boolean isselectRoot) {
        this.isselectRoot = isselectRoot;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getOnSuccess() {
        return onSuccess;
    }

    public void setOnSuccess(String onSuccess) {
        this.onSuccess = onSuccess;
    }

    public boolean isCollapseAll() {
        return collapseAll;
    }

    public void setCollapseAll(boolean collapseAll) {
        this.collapseAll = collapseAll;
    }

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isOnlyLeafCheck() {
        return onlyLeafCheck;
    }

    public void setOnlyLeafCheck(boolean onlyLeafCheck) {
        this.onlyLeafCheck = onlyLeafCheck;
    }

    public boolean isLines() {
        return lines;
    }

    public void setLines(boolean lines) {
        this.lines = lines;
    }

    public boolean isDnd() {
        return dnd;
    }

    public void setDnd(boolean dnd) {
        this.dnd = dnd;
    }

    public boolean isMultiCheck() {
        return multiCheck;
    }

    public void setMultiCheck(boolean multiCheck) {
        this.multiCheck = multiCheck;
    }

    public boolean isCascadeCheck() {
        return cascadeCheck;
    }

    public void setCascadeCheck(boolean cascadeCheck) {
        this.cascadeCheck = cascadeCheck;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public boolean isDeepCascadeCheck() {
        return deepCascadeCheck;
    }

    public void setDeepCascadeCheck(boolean deepCascadeCheck) {
        this.deepCascadeCheck = deepCascadeCheck;
    }

    public String getDoEvent() {
        return doEvent;
    }

    public void setDoEvent(String doEvent) {
        this.doEvent = doEvent;
    }

    public String getOnExpand() {
        return onExpand;
    }

    public void setOnExpand(String onExpand) {
        this.onExpand = onExpand;
    }

    public String getOnBeforeExpand() {
        return onBeforeExpand;
    }

    public void setOnBeforeExpand(String onBeforeExpand) {
        this.onBeforeExpand = onBeforeExpand;
    }

}
