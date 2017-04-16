package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class EasyUiTextareaTag extends BodyTagSupport {
    private static final long serialVersionUID = -4795604324752399473L;

    private static final String GENERAL = "general";
    private static final String BASIC = "basic";
    private static final String STANDARD = "standard";

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // 编号

    private String name; // 名称

    private String value; // 默认值

    private int rows;

    private int cols;

    private String onclick;

    private String onblur;

    private String onkeyup;

    private String title;

    private boolean required;

    private boolean readonly;

    private String recordFlag;//带文件上传组件，文件标记，暂无用

    private String style;// 样式

    private String textareaType = GENERAL;//文本域类型(默认或不填为普通类型)，general/basic


    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();

        htmlSb.append("<textarea class=\"ckeditor\" ");
        htmlSb.append(" id=\"").append(this.getId()).append("\"");
        htmlSb.append(" name=\"").append(this.getName()).append("\"");
        if (!TriRegulation.isEmpty(this.getStyle())) {
            htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getRows())) {
            htmlSb.append(" rows=\"").append(this.getRows()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getCols())) {
            htmlSb.append(" cols=\"").append(this.getCols()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getOnclick())) {
            htmlSb.append(" onclick=\"").append(this.getOnclick()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getOnkeyup())) {
            htmlSb.append(" onkeyup=\"").append(this.getOnkeyup()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getTitle())) {
            htmlSb.append(" title=\"").append(this.getTitle()).append("\"");
        }
        if (!TriRegulation.isEmpty(this.getOnblur())) {
            htmlSb.append(" onblur=\"").append(this.getOnblur()).append("\"");
        }
        htmlSb.append(">");

        if (!TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append(this.getValue());
        }
        htmlSb.append("</textarea>");

        if (BASIC.equals(textareaType) || STANDARD.equals(textareaType)) {
            String path = pageContext.getServletContext().getContextPath();
            if (BASIC.equals(textareaType)) {
                htmlSb.append("<script type=\"text/javascript\" src=\"").append(path).append("/js/ckeditor/ckeditor.js\"></script>\n");
            } else if (STANDARD.equals(textareaType)) {
                htmlSb.append("<script type=\"text/javascript\" src=\"").append(path).append("/js/ckeditor/ckeditor_std.js\"></script>\n");
            }
            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("$(function(){\n");
            htmlSb.append("CKEDITOR.replace('").append(this.getId()).append("'");
            if (!TriRegulation.isEmpty(recordFlag)) {
                htmlSb.append(",{filebrowserUploadUrl : '").append(path).append("/uploader?Type=File&recordFlag=");
                htmlSb.append(this.getRecordFlag()).append("',\n");
                htmlSb.append("  filebrowserImageUploadUrl : '").append(path).append("/uploader?Type=Image&recordFlag=");
                htmlSb.append(this.getRecordFlag()).append("',\n");
                htmlSb.append("  filebrowserFlashUploadUrl : '").append(path).append("/uploader?Type=Flash&recordFlag=");
                htmlSb.append(this.getRecordFlag()).append("'\n");
                htmlSb.append("}");
            }
            htmlSb.append(");\n");
            htmlSb.append(" });\n");
        }
        htmlSb.append("</script>\n");

        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOnkeyup() {
        return onkeyup;
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getOnblur() {
        return onblur;
    }

    public void setOnblur(String onblur) {
        this.onblur = onblur;
    }

    public String getRecordFlag() {
        return recordFlag;
    }

    public void setRecordFlag(String recordFlag) {
        this.recordFlag = recordFlag;
    }

    public String getTextareaType() {
        return textareaType;
    }

    public void setTextareaType(String textareaType) {
        this.textareaType = textareaType;
    }


}
