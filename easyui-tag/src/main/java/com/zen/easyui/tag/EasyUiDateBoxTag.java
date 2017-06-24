package com.zen.easyui.tag;

import com.zen.easyui.util.RandomUtil;
import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiDateBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = -4306262114383104919L;

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
        String tmpId = RegulationUtil.isEmpty(this.getId()) ? this.getName() + RandomUtil.random(2) : this.getId();

        htmlSb.append("<input name=\"").append(this.getName()).append("\"\n");
        htmlSb.append(" id=\"").append(tmpId).append("\" type=\"text\"\n");
        htmlSb.append(">\n");

        htmlSb.append("<script type=\"text/javascript\">\n");
        htmlSb.append("$(function(){\n");
        htmlSb.append("$('#").append(tmpId);
        if (isTime()) {
            htmlSb.append("').datetimebox({ \n");
            if (!RegulationUtil.isEmpty(this.getTimeSeparator())) {
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
        if (!RegulationUtil.isEmpty(this.getWidth())) {
            htmlSb.append(" width:\"").append(this.getWidth()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getPanelHeight())) {
            htmlSb.append(" panelHeight:\"").append(this.getPanelHeight()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getPanelWidth())) {
            htmlSb.append(" panelWidth:\"").append(this.getPanelWidth()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getCurrentText())) {
            htmlSb.append(" currentText:\"").append(this.getCurrentText()).append("\",");
        }
        if (!RegulationUtil.isEmpty(this.getCloseText())) {
            htmlSb.append(" closeText:\"").append(this.getCloseText()).append("\",");
        }

        if (!RegulationUtil.isEmpty(this.getOkText())) {
            htmlSb.append(" okText:\"").append(this.getOkText()).append("\",");
        }

        if (!RegulationUtil.isEmpty(this.getOnSelect())) {
            if (this.getOnSelect().indexOf("(") > 0) {
                htmlSb.append(" onSelect:\"").append(this.getOnSelect()).append("\",");
            } else {
                htmlSb.append(" onSelect:\"").append(this.getOnSelect()).append("(this)\", ");
            }
        }

        if (!RegulationUtil.isEmpty(this.getValue())) {
            htmlSb.append(" value:\"").append(this.getValue()).append("\" ");
        } else if (this.isToday() && this.isTime()) {
            htmlSb.append(" value:\"").append(this.getSystemDateTime()).append("\" ");
        } else if (this.isToday() && !this.isTime()) {
            htmlSb.append(" value:\"").append(this.getSystemDate()).append("\" ");
        }
        //格式化有问题
//    if (!RegulationUtil.isEmpty(this.getFormatter())) {
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

    /**
     * 获取当前日期的yyyy-MM-dd HH:mm:ss表示格式
     * 返回String類型
     */
    public String getSystemDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取系统日期
     *
     * @return
     */
    public String getSystemDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
