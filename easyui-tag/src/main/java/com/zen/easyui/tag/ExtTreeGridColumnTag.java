package com.zen.easyui.tag;

import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class ExtTreeGridColumnTag extends TagSupport implements Cloneable {

    private static final long serialVersionUID = -4308552552551957488L;

    private boolean treecolumn = false;//是否为树折叠字段

    private String field; // 字段值

    private String title; // 标题

    private String titleKey;

    private String width; // 宽度

    private String simplyFormatter;// 简易格式化函数 如'Y-m-d H:i:s'

    private String formatter; // 格式化函数

    private String type;//字段类型 datecolumn

    private String align;//设置字段水平对齐 center

    private String tdCls;//设置字段垂直对齐 tdValign

    private boolean sortable = false; // 是否可以排序

    private boolean hidden = false; // 是否隐藏

    private boolean locked = false; // 是否冻结


    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder htmlSb = new StringBuilder();


            Tag parentTag = getParent();
            //存在parentTag為ExtTreeGridTag 時 存在BUG
            while (!RegulationUtil.isEmpty(parentTag) && !(parentTag instanceof ExtTreeGridTag)) {
                parentTag = parentTag.getParent();
            }

            if (!RegulationUtil.isEmpty(parentTag)) {
                if (parentTag instanceof ExtTreeGridTag) {
                    ExtTreeGridTag gridTag = (ExtTreeGridTag) parentTag;
                    gridTag.addColumn((ExtTreeGridColumnTag) this.clone());
                }
            }

            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException | CloneNotSupportedException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

}
