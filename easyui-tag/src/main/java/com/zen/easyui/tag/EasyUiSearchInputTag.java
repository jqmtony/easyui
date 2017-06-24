package com.zen.easyui.tag;

import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;


@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiSearchInputTag extends BodyTagSupport {

    private static final String _HEIGHT = "550";

    private static final String _WIDTH = "850";

    private static final long serialVersionUID = -799903023945799288L;

    private String id;

    private String idTagName; // id对应的input name名称 bean属性

    private String textTagName; // text对应的input name名称

    private String idField; // 从弹出框中,选择的与idTagName对应的隐藏字段

    private String textField; // 从弹出框中,选择的与textTagName对应的显示文本字段

    private int maSqlNum = 1; // 主表选择执行的SQL语句序号

    private int miSqlNum = 1; // 从表选择执行的SQL语句序号（如果只有一个table那么默认是从表）

    private String maSqlParams = ""; // 主表sql语句中的参数 统一规则：field1=1&field2=2

    private String miSqlParams = ""; // 从表sql语句中的参数
    // 统一规则（如果只有一个table那么默认是从表）：field1=1&field2=2

    private String value;

    // 是否是自定义开发页面
    private boolean isCustomPage = false;

    // 自定义开发页面url
    private String customUrl;

    // 回调方法
    private String callback;

    private boolean readonly = false;

    // 是否必输
    private boolean required = false;

    // 是否开启验证
    private boolean validate = true;

    // 验证方式
    private String validType = "";

    // 输入验证url请求地址
    private static final String inputValidateUrl = "'jsonDo.do?method=toJsonDo&businessName=SEARCH_CHECKQUERYVALUE";

    // private static final String inputValidateUrl =
    // "'windowSearch.do?method=checkQueryValue";

    // 弹出查询类型
    private String searchFlag;

    // 选择类型：0、单选；1、单父节点多选；2、多父节点多选
    private String selectType = "0";

    // 弹出框标题
    private String title = "查询";

    // 宽度
    private String width;

    // 高度
    private String height;

    // 对应弹框查询中搜索框字段id
    private String queryInputObj;

    // 对应form表单中的字段文本
    private String queryValue;

    // 对应固定常量值
    private String queryConstantValue;

    // 对应简易功能绑定字段的id
    private String fromFieldId;

    @Override
    public int doStartTag() throws JspException {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("<script type=\"text/javascript\">\n");
        // cz:变更为LastValue+id 避免ID中首字母出现数字导致JS异常
        htmlSb.append("var ").append("LastValue").append(this.getId()).append("='';\n");
        //idSearchBox：隐藏域   idtext:文本域
        if (RegulationUtil.isEmpty(this.getCustomUrl())) {

            // 为id默认分配一个回调函数.
            htmlSb.append("function callback4").append(this.getId()).append("(data){\n");
            // htmlSb.append("alert('1='+data);");
            htmlSb.append("var map ;\n");
            htmlSb.append("if (!isEmpty(data)) {\n");
            // htmlSb.append("alert('2');");
            htmlSb.append("var row;\n");
            htmlSb.append("if (isString(data)) {\n");
            // htmlSb.append("alert('3');");
            htmlSb.append("row = evalObj(data);\n");
            htmlSb.append("map = new HashMap();\n");
            htmlSb.append("map.put(\"rowObj\",row);\n");
            htmlSb.append("} else {\n");
            // htmlSb.append("alert('4');");
            htmlSb.append("map = data;\n");
            htmlSb.append("row = map.get(\"rowObj\");\n");
            // htmlSb.append("alert('row='+row);");
            // htmlSb.append("mainObj = map.get(\"mainObj\");\n");
            // htmlSb.append("rowIndex = getRowIndex4Params(map.get(\"paramsObj\"));\n");
            htmlSb.append("}\n");

            // 判断是否为json字符串，如果不是,则转换。
            htmlSb.append("if(!isEmpty(row)){\n");
            // htmlSb.append("alert('!isEmpty(row)');");
            // htmlSb.append("row = json2Str(row);\n");
            htmlSb.append("LastValue").append(this.getId()).append("= row.").append(this.getTextField()).append(";\n");
            // 判断selectType，可能存在多行记录。
            htmlSb.append(" if ('").append(this.getSelectType()).append("'=='0') {\n");
            // htmlSb.append("alert('row.").append(this.getTextField()).append("='+row.").append(this.getTextField()).append(");");
            htmlSb.append("   setValue2Input(\"").append(this.getId()).append("text\", row.").append(this.getIdField()).append(");\n");
            htmlSb.append("   setValue2Input(\"").append(this.getId()).append("SearchBox\", row.").append(this.getTextField()).append(");\n");
            htmlSb.append(" } else {\n");
            htmlSb.append("     var tempIdFieldArr = ['").append(this.getIdField()).append("'] ;\n");
            htmlSb.append("     var tempTextFieldArr = ['").append(this.getTextField()).append("'] ;\n");
            htmlSb.append("     setValue2Input(\"").append(this.getId()).append("text\", getDatagridAllRowsStr(\"selectDataGrid\", tempIdFieldArr));\n");
            htmlSb.append("     setValue2Input(\"").append(this.getId()).append("SearchBox\", getDatagridAllRowsStr(\"selectDataGrid\", tempTextFieldArr));\n");
            htmlSb.append(" }\n");

            htmlSb.append("} else {\n");
            // htmlSb.append("alert('isEmpty(row)');");
            htmlSb.append("euShow('【' + ").append("getValue2TagId(\"").append(this.getId()).append("SearchBox\", \"input\")").append("+'】不存在该记录相关信息!');\n");
            htmlSb.append("setValue2Input(\"").append(this.getId()).append("text\", '');\n");
            htmlSb.append("setValue2Input(\"").append(this.getId()).append("SearchBox\", '');\n");
            htmlSb.append("}\n");
            htmlSb.append("} else {\n");
            // htmlSb.append("alert('isEmpty(row)');");
            htmlSb.append("euShow('【' + ").append("getValue2TagId(\"").append(this.getId()).append("SearchBox\", \"input\")").append("+'】不存在该记录相关信息!');\n");
            htmlSb.append("setValue2Input(\"").append(this.getId()).append("text\", '');\n");
            htmlSb.append("setValue2Input(\"").append(this.getId()).append("SearchBox\", '');\n");
            htmlSb.append("}\n");
            if (!RegulationUtil.isEmpty(this.getCallback())) {
                htmlSb.append(this.getCallback()).append("(map);\n");
            }
            htmlSb.append(" }\n");
            htmlSb.append("function checkQueryValue").append(this.getId()).append("(){\n");
            // htmlSb.append("alert('checkQueryValue');");
            htmlSb.append(" var ").append("TempValue").append(this.getId()).append("= $(\"#").append(this.getId()).append("SearchBox\").val().trim();\n");
            // htmlSb.append("alert('"+this.getId()).append("LastValue='+"+this.getId()).append("LastValue+',").append(this.getId()).append("TempValue='+"+this.getId()).append("TempValue);\n");
            htmlSb.append(" if (!isEmpty(").append("TempValue").append(this.getId()).append(") && (").append("LastValue").append(this.getId()).append(" != ").append("TempValue").append(this.getId()).append(")) {\n");
            htmlSb.append("LastValue").append(this.getId()).append("= ").append("TempValue").append(this.getId()).append(";\n");
            htmlSb.append("     ajaxPost(").append(this.inputValidateUrl).append("&searchFlag=").append(this.getSearchFlag()).append("&recordFlag=").append(this.getSearchFlag()).append("&selectType=")
                    .append(this.getSelectType());
            htmlSb.append("&").append(RegulationUtil.isEmpty(this.getQueryInputObj()) ? this.getTextField() : this.getQueryInputObj()).append("=' +").append("TempValue").append(this.getId()).append(" + ")
                    .append("'&queryValue=' +").append("TempValue").append(this.getId()).append("  ,callback4").append(this.getId()).append("); \n");
            htmlSb.append("   } else if (isEmpty(").append("TempValue").append(this.getId()).append(")){\n");
            // 清空隐藏字段的值
            htmlSb.append("           setValue2Input(\"").append(this.getId()).append("SearchBox\", '');\n");
            htmlSb.append("           setValue2Input(\"").append(this.getId()).append("text\", '');\n");
            htmlSb.append("          \n");
            htmlSb.append("          }\n");
            htmlSb.append(" }\n");
        }
        htmlSb.append("</script>\n");
        htmlSb.append("<span class=\"searchbox textbox\" style=\"width: 148px;\">\n");
        htmlSb.append("<input type=\"text\" class=\"searchbox-text easyui-validatebox \" id=\"").append(this.getId()).append("SearchBox\" name=\"").append(this.getTextTagName()).append("\" ");
        if (!RegulationUtil.isEmpty(this.getValue())) {
            htmlSb.append(" value='").append(this.getValue()).append("' ");
        }
        if (this.isReadonly()) {
            htmlSb.append(" readonly='").append(this.isReadonly()).append("' ");
        }
        if (!RegulationUtil.isEmpty(this.isRequired())) {
            htmlSb.append(" data-options=\"required:").append(this.isRequired());
        }
        if (!RegulationUtil.isEmpty(this.getValidType())) {
            htmlSb.append(",validType:'").append(this.getValidType()).append("'\"\n");
        } else {
            htmlSb.append("\"\n");
        }

        // 开启验证或可编辑
        if (!this.isReadonly() && (!RegulationUtil.isEmpty(this.isValidate()) && this.isValidate())) {
            htmlSb.append(" onblur=\"checkQueryValue").append(this.getId()).append("()\" ");
        }
        htmlSb.append("style=\"width: 120px;line-height: 20px;\" />\n");
        htmlSb.append("<span id=\"").append(this.getId()).append("SearchButtonSpan\">\n");

        // 自定义页面
        if (isCustomPage && !RegulationUtil.isEmpty(customUrl)) {

            htmlSb.append("<span id=\"").append(this.getId()).append("SearchButton\" class=\"searchbox-button\" style=\"height: 20px;\" onclick=\"javascript:openSearchWindowSelf2DgColumn('")
                    .append(this.getCustomUrl()).append("','").append(this.getTitle()).append("','").append(this.getWidth()).append("','").append(this.getHeight()).append("','');\">\n");
            htmlSb.append("</span>\n");

        } else {// 配置型页面
            htmlSb.append("<span id=\"").append(this.getId()).append("SearchButton\" class=\"searchbox-button\" style=\"height: 20px;\" onclick=\"javascript:openSearchBusinessWindow('")
                    .append(this.getId()).append("','").append(this.getSearchFlag()).append("','").append(this.getIdField()).append("','").append(this.getIdTagName()).append("','")
                    .append(this.getTextTagName()).append("','").append(this.getSelectType()).append("','").append(this.getMaSqlNum()).append("','").append(this.getMiSqlNum()).append("','")
                    .append(this.getMaSqlParams()).append("','").append(this.getMiSqlParams()).append("','");
            htmlSb.append("callback4").append(this.getId()).append("','").append(this.isValidate()).append("','").append(this.getTitle()).append("','").append(this.getWidth()).append("','")
                    .append(this.getHeight()).append("','").append(this.getQueryInputObj()).append("','").append(this.getQueryValue()).append("','").append(this.getQueryConstantValue()).append("','")
                    .append(this.getFromFieldId()).append("','").append(this.isCustomPage()).append("');\">\n");
            htmlSb.append("</span>\n");
        }

        htmlSb.append("</span>\n");
        htmlSb.append("</span>\n");
        // 隐藏主键名称字段值
        htmlSb.append("<input type=\"hidden\" id=\"").append(this.getId()).append("text\" name=\"").append(this.getIdTagName()).append("\" />\n");
        try {
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
