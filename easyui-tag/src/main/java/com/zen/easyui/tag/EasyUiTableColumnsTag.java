package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;


/**
 * @author hexuming
 */
public class EasyUiTableColumnsTag extends BodyTagSupport {
    private static final long serialVersionUID = -4308552552551957488L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public int doStartTag() throws JspException {

        try {
            Tag parentTag = getParent();
            //存在parentTag為EasyUiTreegridTableTag 時 存在BUG
            while (!TriRegulation.isEmpty(parentTag) && !(parentTag instanceof EasyUiDatagridTableTag)) {
                parentTag = parentTag.getParent();
            }

            if (!TriRegulation.isEmpty(parentTag)) {
                EasyUiDatagridTableTag datagridTag = (EasyUiDatagridTableTag) parentTag;

                datagridTag.addColumnMap();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

}
