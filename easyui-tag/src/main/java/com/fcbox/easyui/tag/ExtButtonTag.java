package com.fcbox.easyui.tag;

import com.fcbox.easyui.util.TriRegulation;
import com.fcbox.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ExtButtonTag extends BodyTagSupport {

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

    @Override
    public int doStartTag() throws JspException {
        // 屏蔽按钮权限控制
        /*if (!TriRegulation.isEmpty(this.getAuthCode())) {
            String[] codeArr = this.getAuthCode().split(",");
            boolean isAuth = false;
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            if (!TriObjectHelper.equals(SessionContainer.getUserInfo(request).getLoginName(), GlobalConstant.ADMIN_USER_ID)) {// 管理员拥有所以权限
                for (int i = 0; i < codeArr.length; i++) {
                    if (AuthenticateUtil.isHaveRightForFunDetail(request, codeArr[i])) {
                        isAuth = true;
                        break;
                    }
                }
                if (!isAuth) {
                    return SKIP_BODY;
                }
            }
        }*/

        try {
            StringBuffer htmlSb = new StringBuffer();

            ExtToolbarTag toolbar = (ExtToolbarTag) getParent();

            if (!TriRegulation.isEmpty(this.getOnClick())) {
                htmlSb.append("<script> \n");
                //数据字段定义
                htmlSb.append(toolbar.getId()).append(".push({   \n");
                htmlSb.append("xtype: 'button',");
                if (!TriRegulation.isEmpty(this.getTitle())) {
                    htmlSb.append("text : '").append(this.getTitle()).append("',   \n");
                } else if (!TriRegulation.isEmpty(this.getTitleKey())) {
                    String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
                    htmlSb.append("text : '").append(!TriRegulation.isEmpty(keyStr) ? keyStr : "").append("',   \n");
                }
                htmlSb.append("iconCls : '").append(this.getIconClass()).append("',   \n");
                htmlSb.append("handler :  function() {").append(this.getOnClick()).append("}   \n");
                htmlSb.append("});   \n");

                htmlSb.append("</script> \n");


            }
            pageContext.getOut().write(htmlSb.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
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

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

}
