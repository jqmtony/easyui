package com.zen.easyui.tag;

import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class ExtButtonTag extends BodyTagSupport {

    private static final long serialVersionUID = 4040423476954803162L;

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
        /*if (!RegulationUtil.isEmpty(this.getAuthCode())) {
            String[] codeArr = this.getAuthCode().split(",");
            boolean isAuth = false;
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            if (!ObjectHelper.equals(SessionContainer.getUserInfo(request).getLoginName(), GlobalConstant.ADMIN_USER_ID)) {// 管理员拥有所以权限
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

            if (!RegulationUtil.isEmpty(this.getOnClick())) {
                htmlSb.append("<script> \n");
                //数据字段定义
                htmlSb.append(toolbar.getId()).append(".push({   \n");
                htmlSb.append("xtype: 'button',");
                if (!RegulationUtil.isEmpty(this.getTitle())) {
                    htmlSb.append("text : '").append(this.getTitle()).append("',   \n");
                } else if (!RegulationUtil.isEmpty(this.getTitleKey())) {
                    String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());
                    htmlSb.append("text : '").append(!RegulationUtil.isEmpty(keyStr) ? keyStr : "").append("',   \n");
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

}
