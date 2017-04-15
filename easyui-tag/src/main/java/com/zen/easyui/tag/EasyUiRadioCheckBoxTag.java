package com.zen.easyui.tag;

import com.zen.easyui.util.TriObjectHelper;
import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.TriStringUtil;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 * EasyUiComboGridTag.java
 * <p>
 * comments:
 *
 * @author hexuming
 * @version Version_2012
 * @creation date Oct 16, 2012
 */
public class EasyUiRadioCheckBoxTag extends BodyTagSupport {
    private static final long serialVersionUID = 1634618855354058065L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

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
        if (!TriRegulation.isEmpty(list)) {
            if (TriRegulation.isEmpty(this.getText())) {
                this.setText("label");
            }
            if (TriRegulation.isEmpty(this.getValue())) {
                this.setValue("value");
            }
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                Object obj = (Object) iterator.next();
                String textTemp = (String) TriObjectHelper.getFieldValue4Object(obj, this.getText());
                String valueTemp = (String) TriObjectHelper.getFieldValue4Object(obj, this.getValue());
                String id = this.getName() + TriStringUtil.random(2);
                htmlSb.append("<input  type=\"").append(this.getType()).append("\"");
                htmlSb.append(" name=\"").append(this.getName()).append("\"");
                htmlSb.append(" id=\"").append(id).append("\"");
                htmlSb.append(" value=\"").append(valueTemp).append("\"");
                if (!TriRegulation.isEmpty(this.getOnClick())) {
                    htmlSb.append(" onclick=\"").append(this.getOnClick()).append("('").append(valueTemp).append("')\"");
                }
                if (!TriRegulation.isEmpty(this.getOnChange())) {
                    htmlSb.append(" onchange=\"").append(this.getOnChange()).append("('").append(valueTemp).append("')\"");
                }
                if (!TriRegulation.isEmpty(this.getDisabled()) && this.getDisabled().equals("true")) {
                    htmlSb.append(" disabled=\"disabled\"");
                }
                if (!TriRegulation.isEmpty(this.getReadonly()) && this.getReadonly().equals("true")) {
                    htmlSb.append(" readonly=\"readonly\"");
                }
                htmlSb.append("/>");

                htmlSb.append("<label for=\"").append(id).append("\">").append(textTemp).append("</label>\n");
            }
        } else {
            for (int i = 0; i < this.getText().split(",").length; i++) {
                htmlSb.append("<input  type=\"").append(this.getType()).append("\"");
                htmlSb.append(" name=\"").append(this.getName()).append("\"");
                if (!TriRegulation.isEmpty(this.getId())) {
                    htmlSb.append(" id=\"").append(this.getId().split(",")[i]).append("\"");
                }
                if (!TriRegulation.isEmpty(this.getValue())) {
                    htmlSb.append(" value=\"").append(this.getValue().split(",")[i]).append("\"");
                }
                if (!TriRegulation.isEmpty(this.getChecked()) && this.getChecked().equals(this.getId().split(",")[i])) {
                    htmlSb.append(" checked=\"checked\"");
                }
                if (!TriRegulation.isEmpty(this.getOnClick())) {
                    htmlSb.append(" onclick=\"").append(this.getOnClick()).append("('").append(this.getId().split(",")[i]).append("','")
                            .append(!TriRegulation.isEmpty(this.getValue()) ? this.getValue().split(",")[i] : "").append("')\"");
                }
                if (!TriRegulation.isEmpty(this.getOnChange())) {
                    htmlSb.append(" onchange=\"").append(this.getOnChange()).append("('").append(this.getId().split(",")[i]).append("','")
                            .append(!TriRegulation.isEmpty(this.getValue()) ? this.getValue().split(",")[i] : "").append("')\"");
                }
                if (!TriRegulation.isEmpty(this.getDisabled()) && this.getDisabled().equals("true")) {
                    htmlSb.append(" disabled=\"disabled\"");
                }
                if (!TriRegulation.isEmpty(this.getReadonly()) && this.getReadonly().equals("true")) {
                    htmlSb.append(" readonly=\"readonly\"");
                }
                htmlSb.append("/>");

                htmlSb.append("<label for=\"").append(this.getId().split(",")[i]).append("\">");
                if (titleKey) {
                    htmlSb.append(MessageUtil.getMessage((HttpServletRequest) pageContext.getRequest(), this.getText().split(",")[i]));
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public boolean isTitleKey() {
        return titleKey;
    }

    public void setTitleKey(boolean titleKey) {
        this.titleKey = titleKey;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}
