package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.TriStringUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * EasyUiDateBoxTag.java
 * <p>
 * comments:
 *
 * @author hexuming
 * @version Version_2012
 * @company IM Tri_stone Information Techonology Co.,Ltd
 * @creation date        Oct 16, 2012
 */
public class EasyUiDateBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = -4306262114383104919L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String name;

    private String style;

    private boolean required = false; //是否必输

    private String value;

    private int width = 150;//时间文本框默认宽度

    private int panelWidth = 180;

    private int panelHeight;//下拉日历面板高度。auto

    private String onSelect;  //选中触发事件

    private String formatter;

    private String currentText; //显示当天按钮(Today)

    private String closeText; //显示关闭按钮。(close)

    private String okText; //显示OK按钮。(OK)

    private boolean today = false;  //默认选择当天

    private boolean disabled = false;  //是否可用

    private boolean readonly = false;  //是否只读

    private boolean time = false;//是否选择时间

    private String timeSeparator; //定义在小时、分钟和秒之间的时间分割字符。(:)

    private boolean showSeconds = true;  //定义是否显示秒钟信息

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        String tmpId = TriRegulation.isEmpty(this.getId()) ? this.getName() + TriStringUtil.random(2) : this.getId();

        htmlSb.append("<input name=\"").append(this.getName()).append("\"\n");
        htmlSb.append(" id=\"").append(tmpId).append("\" type=\"text\"\n");
        htmlSb.append(">\n");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("$(function(){\n");
        htmlSb.append("$('#").append(tmpId);
        if (isTime()) {
            htmlSb.append("').datetimebox({ \n");
            if (!TriRegulation.isEmpty(this.getTimeSeparator())) {
                htmlSb.append(" timeSeparator:\"").append(this.getTimeSeparator()).append("\", ");
            }
            if (this.isShowSeconds()) {
                htmlSb.append(" showSeconds:\"").append(this.isShowSeconds()).append("\", ");
            }
        } else {
            htmlSb.append("').datebox({ \n");
        }
        //htmlSb.append(" name:\"").append(this.getName()).append("\",");

        if (this.isRequired()) {
            htmlSb.append(" required:").append(this.isRequired()).append(",");
        }
        if (this.isDisabled()) {
            htmlSb.append(" disabled:").append(this.isDisabled()).append(",");
        }
        if (this.isReadonly()) {
            htmlSb.append(" readonly:").append(this.isReadonly()).append(",");
        }
        if (!TriRegulation.isEmpty(this.getWidth())) {
            htmlSb.append(" width:\"").append(this.getWidth()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getPanelHeight())) {
            htmlSb.append(" panelHeight:\"").append(this.getPanelHeight()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getPanelWidth())) {
            htmlSb.append(" panelWidth:\"").append(this.getPanelWidth()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getCurrentText())) {
            htmlSb.append(" currentText:\"").append(this.getCurrentText()).append("\",");
        }
        if (!TriRegulation.isEmpty(this.getCloseText())) {
            htmlSb.append(" closeText:\"").append(this.getCloseText()).append("\",");
        }

        if (!TriRegulation.isEmpty(this.getOkText())) {
            htmlSb.append(" okText:\"").append(this.getOkText()).append("\",");
        }

        if (!TriRegulation.isEmpty(this.getOnSelect())) {
            if (this.getOnSelect().indexOf("(") > 0) {
                htmlSb.append(" onSelect:\"").append(this.getOnSelect()).append("\",");
            } else {
                htmlSb.append(" onSelect:\"").append(this.getOnSelect()).append("(this)\", ");
            }
        }

        if (!TriRegulation.isEmpty(this.getValue())) {
            htmlSb.append(" value:\"").append(this.getValue()).append("\" ");
        } else if (this.isToday() && this.isTime()) {
            htmlSb.append(" value:\"").append(this.getSystemDateTime()).append("\" ");
        } else if (this.isToday() && !this.isTime()) {
            htmlSb.append(" value:\"").append(this.getSystemDate()).append("\" ");
        }
        //格式化有问题
//    if (!TriRegulation.isEmpty(this.getFormatter())) {
//      htmlSb.append(" formatter:function(date){\n");
//      htmlSb.append(" alert(date);\n");
//        htmlSb.append(this.getFormatter()).append("(data);");
//        htmlSb.append("}\n");
//      }
        htmlSb.append("});\n");

        htmlSb.append("});\n");
        htmlSb.append("</script>\n");


        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {

        try {
            pageContext.getOut().write("</input>");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    /*
    * 获取当前日期的yyyy-MM-dd HH:mm:ss表示格式
    *
    * 返回String類型
    */
    public String getSystemDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取系统日期
     * @param date
     * @param pattern
     * @return
     */
    public String getSystemDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
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

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public String getOnSelect() {
        return onSelect;
    }

    public void setOnSelect(String onSelect) {
        this.onSelect = onSelect;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public String getCloseText() {
        return closeText;
    }

    public void setCloseText(String closeText) {
        this.closeText = closeText;
    }

    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    public String getTimeSeparator() {
        return timeSeparator;
    }

    public void setTimeSeparator(String timeSeparator) {
        this.timeSeparator = timeSeparator;
    }

    public boolean isShowSeconds() {
        return showSeconds;
    }

    public void setShowSeconds(boolean showSeconds) {
        this.showSeconds = showSeconds;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }


}
