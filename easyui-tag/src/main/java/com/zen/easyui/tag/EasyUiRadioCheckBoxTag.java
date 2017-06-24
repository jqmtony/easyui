package com.zen.easyui.tag;

import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.ObjectHelper;
import com.zen.easyui.util.RandomUtil;
import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiRadioCheckBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = 1634618855354058065L;

    private String id;

    private String name;

    private String type;

    private String value;

    private String text;

    private String onClick;

    private String onChange;

    private String checked;

    private String disabled;

    private String readonly;

    private List list;//数据列表  map/bean/String

    private boolean titleKey = false; //title默认为非多语言


    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        if (!RegulationUtil.isEmpty(list)) {
            if (RegulationUtil.isEmpty(this.getText())) {
                this.setText("label");
            }
            if (RegulationUtil.isEmpty(this.getValue())) {
                this.setValue("value");
            }
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                Object obj = (Object) iterator.next();
                String textTemp = (String) ObjectHelper.getFieldValue4Object(obj, this.getText());
                String valueTemp = (String) ObjectHelper.getFieldValue4Object(obj, this.getValue());
                String id = this.getName() + RandomUtil.random(2);
                htmlSb.append("<input  type=\"").append(this.getType()).append("\"");
                htmlSb.append(" name=\"").append(this.getName()).append("\"");
                htmlSb.append(" id=\"").append(id).append("\"");
                htmlSb.append(" value=\"").append(valueTemp).append("\"");
                if (!RegulationUtil.isEmpty(this.getOnClick())) {
                    htmlSb.append(" onclick=\"").append(this.getOnClick()).append("('").append(valueTemp).append("')\"");
                }
                if (!RegulationUtil.isEmpty(this.getOnChange())) {
                    htmlSb.append(" onchange=\"").append(this.getOnChange()).append("('").append(valueTemp).append("')\"");
                }
                if (!RegulationUtil.isEmpty(this.getDisabled()) && this.getDisabled().equals("true")) {
                    htmlSb.append(" disabled=\"disabled\"");
                }
                if (!RegulationUtil.isEmpty(this.getReadonly()) && this.getReadonly().equals("true")) {
                    htmlSb.append(" readonly=\"readonly\"");
                }
                htmlSb.append("/>");

                htmlSb.append("<label for=\"").append(id).append("\">").append(textTemp).append("</label>\n");
            }
        } else {
            for (int i = 0; i < this.getText().split(",").length; i++) {
                htmlSb.append("<input  type=\"").append(this.getType()).append("\"");
                htmlSb.append(" name=\"").append(this.getName()).append("\"");
                if (!RegulationUtil.isEmpty(this.getId())) {
                    htmlSb.append(" id=\"").append(this.getId().split(",")[i]).append("\"");
                }
                if (!RegulationUtil.isEmpty(this.getValue())) {
                    htmlSb.append(" value=\"").append(this.getValue().split(",")[i]).append("\"");
                }
                if (!RegulationUtil.isEmpty(this.getChecked()) && this.getChecked().equals(this.getId().split(",")[i])) {
                    htmlSb.append(" checked=\"checked\"");
                }
                if (!RegulationUtil.isEmpty(this.getOnClick())) {
                    htmlSb.append(" onclick=\"").append(this.getOnClick()).append("('").append(this.getId().split(",")[i]).append("','")
                            .append(!RegulationUtil.isEmpty(this.getValue()) ? this.getValue().split(",")[i] : "").append("')\"");
                }
                if (!RegulationUtil.isEmpty(this.getOnChange())) {
                    htmlSb.append(" onchange=\"").append(this.getOnChange()).append("('").append(this.getId().split(",")[i]).append("','")
                            .append(!RegulationUtil.isEmpty(this.getValue()) ? this.getValue().split(",")[i] : "").append("')\"");
                }
                if (!RegulationUtil.isEmpty(this.getDisabled()) && this.getDisabled().equals("true")) {
                    htmlSb.append(" disabled=\"disabled\"");
                }
                if (!RegulationUtil.isEmpty(this.getReadonly()) && this.getReadonly().equals("true")) {
                    htmlSb.append(" readonly=\"readonly\"");
                }
                htmlSb.append("/>");

                htmlSb.append("<label for=\"").append(this.getId().split(",")[i]).append("\">");
                if (titleKey) {
                    htmlSb.append(MessageUtil.getMessage(pageContext.getRequest(), this.getText().split(",")[i]));
                } else {
                    htmlSb.append(this.getText().split(",")[i]);
                }
                htmlSb.append("</label>\n");


            }
        }

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        // return SKIP_BODY;
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {

        return EVAL_PAGE;
    }

}
