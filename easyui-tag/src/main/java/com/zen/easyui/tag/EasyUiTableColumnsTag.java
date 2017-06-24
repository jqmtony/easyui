package com.zen.easyui.tag;

import com.zen.easyui.util.RegulationUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

@Slf4j
public class EasyUiTableColumnsTag extends BodyTagSupport {

    private static final long serialVersionUID = -4308552552551957488L;

    @Override
    public int doStartTag() throws JspException {

        try {
            Tag parentTag = getParent();
            //存在parentTag為EasyUiTreegridTableTag 時 存在BUG
            while (!RegulationUtil.isEmpty(parentTag) && !(parentTag instanceof EasyUiDatagridTableTag)) {
                parentTag = parentTag.getParent();
            }

            if (!RegulationUtil.isEmpty(parentTag)) {
                EasyUiDatagridTableTag datagridTag = (EasyUiDatagridTableTag) parentTag;

                datagridTag.addColumnMap();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

}
