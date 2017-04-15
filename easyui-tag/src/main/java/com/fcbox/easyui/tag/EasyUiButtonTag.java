package com.fcbox.easyui.tag;

import com.fcbox.easyui.util.TriRegulation;
import com.fcbox.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;


public class EasyUiButtonTag extends BodyTagSupport {

    private static final long serialVersionUID = 4040423476954803162L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String iconClass; // 图标样式

    private String onClick; // 点击事件

    private String title; // 按钮文字

    private boolean plain = false; // 是否扁平效果

    private String titleKey; // 按钮对应的文字key

    private String titleText; // 按钮浮动文字描述

    private boolean disabled = false; // 是否启用

    private String authCode; // 权限编号

    private boolean onceEnterKey = false; // 一次操作（保存、修改）

    private boolean defaultEnterKey = false; // 默认回车键（针对一个页面出现多个回车键按钮时）

    private boolean stopAutoSearch = false; // 查询按钮页面初始化时，默认自动查询一次

    @Override
    public int doStartTag() throws JspException {
        // 屏蔽按钮权限控制
//    if (!TriRegulation.isEmpty(this.getAuthCode())) {
//      String[] codeArr = this.getAuthCode().split(",");
//      boolean isAuth = false;
//      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
//      if (!TriObjectHelper.equals(SessionContainer.getUserInfo(request).getLoginName(), GlobalConstant.ADMIN_USER_ID)) {// 管理员拥有所以权限
//        for (int i = 0; i < codeArr.length; i++) {
//          if (AuthenticateUtil.isHaveRightForFunDetail(request, codeArr[i])) {
//            isAuth = true;
//            break;
//          }
//        }
//        if (!isAuth) {
//          return SKIP_BODY;
//        }
//      }
//    }

        try {
            boolean menuButFlag = false;
            Tag parentTag = getParent();
            if (!TriRegulation.isEmpty(parentTag)) {
                if (parentTag instanceof EasyUiMenuButtonTag) {
                    menuButFlag = true;
                }
            }


            StringBuffer htmlSb = new StringBuffer();
            if (!menuButFlag) {
                htmlSb.append("<a href=\"#\"  class=\"easyui-linkbutton\" ");
            } else {
                htmlSb.append("<div ");
            }

            htmlSb.append(" plain=\"").append(this.isPlain()).append("\"");
            String tmpTitle = "";
            if (!TriRegulation.isEmpty(this.getTitle())) {
                tmpTitle = this.getTitle();
            } else if (!TriRegulation.isEmpty(this.getTitleKey())) {
                tmpTitle = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
            }

            if (!TriRegulation.isEmpty(this.getTitleText())) {
                htmlSb.append(" title=\"").append(this.getTitleText()).append("\"");
            } else {
                htmlSb.append(" title=\"").append(tmpTitle).append("\"");

            }
            if (!this.isStopAutoSearch()) {
                htmlSb.append(" autoSearch=\"true\"");
            }
            if (!"".equals(this.getIconClass())) {
                htmlSb.append(" iconCls=\"").append(this.getIconClass()).append("\"");
            }
            if (!"".equals(this.getOnClick())) {
//        if (this.defaultEnterKey) {
//          String showToolbar = "if (!isEmpty(" + GlobalConstant.PAGE_VAR_CURRENT_TOOLBAR_ID + ")) {showToolbarForm(" + GlobalConstant.PAGE_VAR_CURRENT_TOOLBAR_ID + ")};";
//          this.setOnClick(this.getOnClick() + showToolbar);
//        }
                htmlSb.append(" onclick=\"").append(this.getOnClick()).append("\"");
            }
            if (!TriRegulation.isEmpty(this.getId())) {
                htmlSb.append(" id=\"").append(this.getId()).append("\"");
            }
            if (this.isDisabled()) {
                htmlSb.append(" disabled=\"").append(this.isDisabled()).append("\"");
                htmlSb.append(" style=\"display:none;\"");//禁用同时隐藏
            }
            if (this.defaultEnterKey) {
                htmlSb.append("  defaultEnterKey=\"").append(this.defaultEnterKey).append("\"");
            }
            htmlSb.append(">");
            htmlSb.append(tmpTitle);

            if (!menuButFlag) {
                htmlSb.append("</a>\n ");
            } else {
                htmlSb.append("</div>\n ");
            }

            if (!TriRegulation.isEmpty(this.getId()) && !TriRegulation.isEmpty(this.getOnClick()) && (this.isOnceEnterKey() || this.isDefaultEnterKey())) {
                htmlSb.append("<script type=\"text/javascript\">\n");
                htmlSb.append("$(function(){ \n");
                // $(document)是获取整个网页的，$(window)是获取当前窗体的
                if (this.isOnceEnterKey()) {
                    htmlSb.append("   currentEnterKeyIds.push(\"").append(this.getId()).append("\");  \n");
                }
                if (this.defaultEnterKey) {
                    htmlSb.append("   defaultEnterKeyId=\"").append(this.getId()).append("\";  \n");
                }
//        if (!TriObjectHelper.equals(this.getOnClick(), "") && this.defaultEnterKey) {
//          htmlSb.append("   function ").append(this.getId()).append("Onclick(){\n");
//          htmlSb.append(this.getOnClick());
//          htmlSb.append("     if (!isEmpty(").append(GlobalConstant.PAGE_VAR_CURRENT_TOOLBAR_ID).append(")) {\n");
//          htmlSb.append("         showToolbarForm(").append(GlobalConstant.PAGE_VAR_CURRENT_TOOLBAR_ID).append(");\n");
//          htmlSb.append("     }\n");
//        }

                htmlSb.append(" });\n");
                htmlSb.append("</script>\n");
            }


            pageContext.getOut().write(htmlSb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    public boolean isStopAutoSearch() {
        return stopAutoSearch;
    }

    public void setStopAutoSearch(boolean stopAutoSearch) {
        this.stopAutoSearch = stopAutoSearch;
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

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public boolean isPlain() {
        return plain;
    }

    public void setPlain(boolean plain) {
        this.plain = plain;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * 通过按钮名称获取对应的icon
     * 可能比较耗资源，暂不实现
     *
     * @param titleName
     * @return
     */
    private String getIconByTitleName(String titleName) {

        return "";
    }

    public boolean isOnceEnterKey() {
        return onceEnterKey;
    }

    public void setOnceEnterKey(boolean onceEnterKey) {
        this.onceEnterKey = onceEnterKey;
    }

    public boolean isDefaultEnterKey() {
        return defaultEnterKey;
    }

    public void setDefaultEnterKey(boolean defaultEnterKey) {
        this.defaultEnterKey = defaultEnterKey;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }


}
