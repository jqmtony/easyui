package com.zen.easyui.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiComboGridColumnTag extends TagSupport implements Cloneable {

    private static final long serialVersionUID = 7931137982528038882L;

    private String field; // 字段值

    private String title; // 标题

    private String titleKey; // 对应的文字key

    private String width = "140"; // 宽度，防止出现横向滚动

    private boolean sortable = false; // 是否可以排序

    private String formatter; // 格式化函数

    private String align = "center"; // 对其方式

    private boolean hidden = false; // 是否隐藏

    @Override
    public int doEndTag() throws JspException {

        try {
            EasyUiComboGridTag comboGridTag = (EasyUiComboGridTag) getParent();
            comboGridTag.addColumn((EasyUiComboGridColumnTag) this.clone());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return SKIP_BODY;
    }

}
