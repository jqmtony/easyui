package com.fcbox.easyui.tag;

import com.fcbox.easyui.constant.GlobalConstant;
import com.fcbox.easyui.util.TriRegulation;
import com.fcbox.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.*;


/**
 * @author hexuming
 *         针对EasyUi的Datagrid的封装
 */
public class EasyUiDatagridTableTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // datagrid标识

    private String url; // 数据路径

    private String data; // 数据

    private String title;// table名称

    private String titleKey; // 对应的文字key

    private boolean pagination = true; // 启用分页

    private boolean nowrap = true; // 设置为true，当数据长度超出列宽时将会自动截取。

    private boolean fit = true; // 表格自适应

    private boolean fitColumns = true; // 列自适应
    // (如果datagrid字段不多，不需要横向滚动条时，fitColumns不需要设置，默认为true;否则设置为false，datagrid就会出现滚动条)

    private String frozenFields; // 锁定列字段

    private String frozenTitles; // 锁定列名称

    private String frozenTitleKeys; // 多语言锁定列名称

    private String frozenEditors; // 锁定列编辑类型

    private String frozenColumnTypes; // 锁定列类型(隐藏/checkBox)

    private String frozenWidths; // 锁定列宽度

    private String frozenFormatters; // 锁定列样式

    private boolean rownumbers = GlobalConstant.DATAGRID_ROWNUMBERS; // 显示行号 Y

    private boolean onlySingleSelect = GlobalConstant.DATAGRID_ROW_ONLY_SINGLE_SELECT; // 开启只能单选

    private boolean singleSelect = GlobalConstant.DATAGRID_ROW_SINGLE_SELECT; // 开启单行选择,false=多行

    private boolean checkOnSelect = true; //

    private boolean selectOnCheck = true; //

    private boolean headerContextMenu = GlobalConstant.DATAGRID_HEADER_CONTEXT_MENU;// 开启表头字段的菜单功能

    private boolean enableHeaderClickMenu = GlobalConstant.DATAGRID_ENABLE_HEADER_CLICK_MENU;// 开启表头列名称右侧那个箭头形状的鼠标左键点击菜单

    private boolean enableHeaderContextMenu = GlobalConstant.DATAGRID_ENABLE_R_HEADER_CONTEXT_MENU;// 开启表头列名称右键点击菜单

    private boolean enableRowContextMenu = GlobalConstant.DATAGRID_ENABLE_ROW_CONTEXT_MENU;// 开启行右键点击菜单

    private boolean moveMenu = GlobalConstant.DATAGRID_MOVE_MENU; // 开始行右键菜单的移动列功能

    private boolean dndRow = GlobalConstant.DATAGRID_DND_ROW; // 开启此表格的行拖动排序功能 Y

    private boolean selectOnRowContextMenu = GlobalConstant.DATAGRID_SELECT_ON_ROW_CONTEXT_MENU; // 此属性开启当右键点击行时自动选择该行的功能

    private boolean rowTooltip = GlobalConstant.DATAGRID_ROW_TOOLTIP;// 开启行内容浮动提示

    private boolean autoEditing = false; // 该属性启用双击行时自定开启该行的编辑状态

    private boolean singleEditing = false; // 该属性启用datagrid的只允许单行编辑效果，该属性默认为 true。

    private boolean extEditing = false; // 该属性启用行编辑状态的 ExtEditing 风格效果

    private String columnFilter;// 定义表头过滤器

    private String toolbarId; // 工具栏的ID

    private String idField; // 关键字列

    private String queryParams; // 参数

    private String onDbClickRow; // 双击行的事件

    private String onClickRow; // 单击行的事件

    private String onLoadSuccess; // 数据加载完成事件

    private String onClickCell;//

    private String onSelect; // Fires when user select a row

    private String onUnselect; // Fires when user unselect a row

    private String onSelectAll; // Fires when user select all rows

    private String onUnselectAll; // Fires when user unselect all rows

    private String onCheck;

    private String onUncheck;

    private String onCheckAll;

    private String onUncheckAll;

    private int pageSize = GlobalConstant.DATAGRID_PAGE_SIZE; // 每页显示几行

    private String pageSizeList = GlobalConstant.DATAGRID_PAGE_SIZE_LIST; // 供用户选择的行数

    private String onBeforeEdit;

    private String onAfterEdit;

    private String autoMergeCells; // 自动合并列字段定义 （暂时不用）

    private String mergeCellsFields; // 需要合并的字段，用逗号隔开

    private String mergeCellsKeys; // 合并行的依据字段，用逗号隔开 （与mergeCellsFields配合一起使用）

    private boolean remoteSort = GlobalConstant.DATAGRID_REMOTE_SORT; // 定义是否从服务器对数据进行排序。
    // Y

    private boolean multiSort = false;//

    private boolean autoReload = false; // 是否需要自动刷新（针对一个页面两个datagrid需要同时刷新情况)

    private boolean stopReload = false; // 第一次打开页面时，阻止自动刷新（针对一个页面两个datagrid需要同时刷新情况)

    private boolean dbClickEdit = false; // 是否双击开启编辑

    private boolean clickEdit = false; // 是否单击开启编辑

    private boolean openSearchWindowField = false; // 是否开启编辑弹出框字段

    private String searchWindowFields = ""; // 开启编辑弹出框字段列表 materialId,materialName

    private String searchWindowFieldFun = ""; // 双击开启编辑弹出框字段方法名称

    private String onBeforeUpdateRow; // 更新行之前的事件

    private String onEndEdit;//在一行进入编辑模式的时候触发。

    private String onBeginEdit;//在用户完成编辑一行的时候触发
    // 属性
    private String doEvent;//

    private String style; // 列样式 可自行设置方法 N

    private String rowStyler; // 行样式 可自行设置方法 N

    private boolean showFooter = false; // 显示footer

    private String groupField; // 分组的字段

    private String groupFormatter; // 分组格式化

    private String cascadeDatagrid = ""; // 上一级级联datagrid

    private String cascadeDatagridParams = ""; // 级联后，当前URL过滤的参数

    private List<EasyUiTableColumnTag> columns = null;
    //多表头情况
    private TreeMap<String, List<EasyUiTableColumnTag>> columnMap = null;

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuilder htmlSb = new StringBuilder();

            htmlSb.append("<table class='sign-datagrid'");//class='sign-datagrid'不要删！！！，影响查询栏隐藏实现
            htmlSb.append(" id=\"").append(this.getId()).append("\"");// 用于jquery，class定位，勿删  class=\"sign-datagrid\"
            htmlSb.append(" nowrap=\"").append(this.isNowrap()).append("\"");
            if (!TriRegulation.isEmpty(this.getStyle())) {
                htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
            }
            htmlSb.append(">\n</table>");

            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append(" var ").append(this.getId()).append("URL = '").append(this.getUrl()).append("';\n");
            htmlSb.append(" var ").append(this.getId()).append("CascadeDatagrid = '").append(this.getCascadeDatagrid()).append("';\n");
            htmlSb.append(" var ").append(this.getId()).append("CascadeDatagridParams = '").append(this.getCascadeDatagridParams()).append("';\n");
            // 如果开启行双击编辑弹出框字段
            if (this.isOpenSearchWindowField() && !TriRegulation.isEmpty(this.getSearchWindowFields())) {
                htmlSb.append(" var ").append(this.getId()).append("RowIndex = 0;\n");
                // htmlSb.append(" var currentClickRowIndex = 0;\n");
            }
            htmlSb.append("$(function(){\n");
            // 纳入需要统计的datagrid
            if (!TriRegulation.isEmpty(this.getUrl())) {
                htmlSb.append("if ((isEmpty(unloadDatagridIds) && ").append(this.isStopReload()).append("==false) || (").append(this.isAutoReload()).append("==true)){\n");
                htmlSb.append("   unloadDatagridIds +='" + this.getId() + ",';\n");
                htmlSb.append("}\n");
            }
            // 渲染脚本
            htmlSb.append(" var t = $('#").append(this.getId()).append("').datagrid({\n");
            htmlSb.append("   border: false,\n");
            htmlSb.append("   pagination: ").append(this.isPagination()).append(",\n");
            if (!TriRegulation.isEmpty(this.getTitleKey()) && !TriRegulation.isEmpty(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey()))) {
                htmlSb.append("   title: ").append(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey())).append(",\n");
            } else if (!TriRegulation.isEmpty(this.getTitle())) {
                htmlSb.append("   title: ").append(this.getTitle()).append(",\n");
            }

            // showFooter
            if (this.isShowFooter()) {
                htmlSb.append("showFooter: true, ");
            }
            htmlSb.append("   fit: ").append(this.isFit()).append(",\n");
            htmlSb.append("   rownumbers: ").append(this.isRownumbers()).append(",\n");
            htmlSb.append("   fitColumns: ").append(this.isFitColumns()).append(",\n");
            htmlSb.append("   singleSelect: ").append(this.isSingleSelect()).append(",\n");
            htmlSb.append("   checkOnSelect: ").append(this.isCheckOnSelect()).append(",\n");
            htmlSb.append("   selectOnCheck: ").append(this.isSelectOnCheck()).append(",\n");
            htmlSb.append("   pageSize: ").append(this.getPageSize()).append(",\n");
            htmlSb.append("   pageList: ").append(this.getPageSizeList()).append(",\n");

            //更新1.4.3后，以下菜单扩展属性无效
            htmlSb.append("   dndRow:").append(this.isDndRow()).append(",\n");
            htmlSb.append("   enableHeaderClickMenu:").append(this.isEnableHeaderClickMenu()).append(",\n");// 小三角错位,先禁用
            htmlSb.append("   enableHeaderContextMenu: ").append(this.isEnableHeaderContextMenu()).append(",\n");
            htmlSb.append("   enableRowContextMenu: ").append(this.isEnableRowContextMenu()).append(",\n");
            htmlSb.append("   rowTooltip: ").append(this.isRowTooltip()).append(",\n");
            if (this.isHeaderContextMenu()) {
                // 表头自定义扩展菜单
                htmlSb.append("   headerContextMenu: [{ text: '表格设置', iconCls: 'icon-hamburg-address', children: [ ");
                htmlSb
                        .append(" { text: '冻结该列',iconCls:'icon-hamburg-check', disabled: function (e, field) { return t.datagrid('getColumnFields', true).contains(field); },handler: function (e, field) { t.datagrid('freezeColumn', field); }},{text: '取消冻结该列', iconCls:'icon-hamburg-check', disabled: function (e, field) { return t.datagrid('getColumnFields', false).contains(field); },handler: function (e, field) { t.datagrid('unfreezeColumn', field); }},");
                htmlSb
                        .append(" { text: '开启行拖动',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.datagrid('options')['dndRow']; },handler: function () { t.datagrid('enableRowDnd');} },  { text: '关闭行拖动',  iconCls: 'icon-hamburg-check', disabled: function (e, field) { return !t.datagrid('options')['dndRow']; },handler: function () { t.datagrid('disableRowDnd'); } },");
                htmlSb
                        .append(" { text: '开启行浮动提示',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.datagrid('options')['rowTooltip']; },handler: function () {t.datagrid('options')['rowTooltip']=true; t.datagrid('reload'); } },  { text: '关闭行浮动提示',  iconCls: 'icon-hamburg-check', disabled: function (e, field) { return !t.datagrid('options')['rowTooltip']; },handler: function () { t.datagrid('options')['rowTooltip']=false;t.datagrid('reload');  } },");
                htmlSb
                        .append(" { text: '开启斑马线效果',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.datagrid('options')['striped']; },handler: function () {t.datagrid('options')['striped']=true; t.datagrid('reload'); } },  { text: '关闭斑马线效果',  iconCls: 'icon-hamburg-check', disabled: function (e, field) { return !t.datagrid('options')['striped']; },handler: function () { t.datagrid('options')['striped']=false;t.datagrid('reload');  } },  ");
                htmlSb
                        .append(" { text: '开启单行选择',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.datagrid('options')['singleSelect']; },handler: function () { var fie = t.datagrid('getColumnFields')[0]; var col = t.datagrid('getColumnOption', fie) ; if(col.checkbox){ t.datagrid('hideColumn', fie); t.datagrid('options')['singleSelect']=true; t.datagrid('reload');} } }, ");
                htmlSb
                        .append(" { text: '开启多行选择',iconCls: 'icon-hamburg-check',disabled: function (e, field) { return ")
                        .append(this.isOnlySingleSelect())
                        .append(
                                " },handler: function (e, field) {var fie = t.datagrid('getColumnFields')[0]; var col = t.datagrid('getColumnOption', fie) ; if(col.checkbox){ t.datagrid('showColumn', fie); t.datagrid('options')['singleSelect']=false;t.datagrid('reload');  } } }  ");
                htmlSb.append(" ] } ]").append(",\n");
            }

            htmlSb.append("   moveMenu:").append(this.isMoveMenu()).append(",\n");

            htmlSb.append("   selectOnRowContextMenu: ").append(this.isSelectOnRowContextMenu()).append(",\n");
            htmlSb.append("   remoteSort: ").append(this.isRemoteSort()).append(",\n");
            htmlSb.append("   multiSort: ").append(this.isMultiSort()).append(",\n");
            if (!TriRegulation.isEmpty(this.getColumnFilter())) {
                htmlSb.append("   columnFilter: ").append(this.getColumnFilter()).append(",\n");
            }
            if (this.isAutoEditing()) {
                htmlSb.append("   autoEditing: ").append(this.isAutoEditing()).append(",\n");
                htmlSb.append("    rowContextMenu: [{ text: '编辑', iconCls: 'icon-edit', handler: function (e, index) { $('#" + this.getId() + "').datagrid('beginEdit', index); } }] ").append(",\n");
            }
            htmlSb.append("   singleEditing: ").append(this.isSingleEditing()).append(",\n");
            htmlSb.append("   extEditing: ").append(this.isExtEditing()).append(",\n");
            htmlSb.append("   url: '' ");

            if (!"".equals(this.getToolbarId())) {
                htmlSb.append(",\n");
                htmlSb.append("   toolbar:'#").append(this.getToolbarId()).append("'");
            }

            if (!TriRegulation.isEmpty(this.getFrozenFields())) {
                htmlSb.append(",\n");
                htmlSb.append("   frozenColumns:[[").append("\n");
                for (int i = 0; i < this.getFrozenFields().split(",").length; i++) {
                    if (TriRegulation.isEmpty(this.getFrozenFields().split(",")[i])) {
                        continue;
                    }
                    htmlSb.append("   { ");

                    if (!TriRegulation.isEmpty(this.getFrozenFields()) && !TriRegulation.isEmpty(this.getFrozenFields().split(",")[i])) {
                        htmlSb.append("field:'").append(this.getFrozenFields().split(",")[i]).append("'\n");
                    }
                    if (!TriRegulation.isEmpty(this.getFrozenTitles()) && !TriRegulation.isEmpty(this.getFrozenTitles().split(",")[i])) {
                        htmlSb.append(",title:'").append(this.getFrozenTitles().split(",")[i]).append("'\n");
                    } else if (!TriRegulation.isEmpty(this.getFrozenTitleKeys())) {
                        htmlSb.append(",title:'").append(MessageUtil.getMessage(this.pageContext.getRequest(), this.getFrozenTitleKeys().split(",")[i])).append("'\n");
                    }
                    if (!TriRegulation.isEmpty(this.getFrozenWidths()) && !TriRegulation.isEmpty(this.getFrozenWidths().split(",")[i])) {
                        htmlSb.append(",width:'").append(this.getFrozenWidths().split(",")[i]).append("'\n");
                    }
                    if (!TriRegulation.isEmpty(this.getFrozenEditors()) && !TriRegulation.isEmpty(this.getFrozenFields().split(",")[i])) {
                        htmlSb.append(",\n");
                        htmlSb.append(",editor:'").append(this.getFrozenEditors().split(",")[i]).append("'");
                    }
                    if (!TriRegulation.isEmpty(this.getFrozenColumnTypes()) && this.getFrozenColumnTypes().split(",").length - 1 == i) {
                        // 单选框类型
                        if ("checkbox".equals(this.getFrozenColumnTypes().split(",")[i])) {
                            htmlSb.append(",\n");
                            htmlSb.append("checkbox:'true' \n");
                        }
                        // 其他类型
                    }
                    if (!TriRegulation.isEmpty(this.getFrozenFormatters()) && !TriRegulation.isEmpty(this.getFrozenFormatters().split(",")[i])) {
                        htmlSb.append(",\n");
                        htmlSb.append("   formatter:function(value){\n");
                        htmlSb.append("   return '<span style=\"").append(this.getFrozenFormatters().split(",")[i]).append("\">' + value + '</span>'").append(";\n");
                        htmlSb.append("     }\n");
                    }
                    htmlSb.append("   },\n");
                }
                htmlSb.append("]] ");
            } else {
                htmlSb.append(",\n");
                htmlSb.append("  frozenColumns: [[{ field: 'ckb', checkbox: true,hidden:true }]]");// 开启隐藏的冻结列（用于列冻结/取消冻结动态配置）
            }
            if (!TriRegulation.isEmpty(this.getRowStyler())) {
                htmlSb.append(",\n");
                htmlSb.append("   rowStyler:").append(this.getRowStyler());
            }
            if (!TriRegulation.isEmpty(this.getGroupField())) {
                htmlSb.append(",\n");
                htmlSb.append(" groupField:").append(this.getGroupField());
            }
            if (!TriRegulation.isEmpty(this.getGroupFormatter())) {
                htmlSb.append(",\n");
                htmlSb.append("groupFormatter:").append(this.getGroupFormatter());
            }

            if (!TriRegulation.isEmpty(this.getData())) {
                htmlSb.append(",\n");
                htmlSb.append("   data:").append(this.getData());
            }
            if (!TriRegulation.isEmpty(this.getIdField())) {
                htmlSb.append(",\n");
                htmlSb.append("   idField:'").append(this.getIdField()).append("'");
            }
            // 单击开启编辑
            if (!TriRegulation.isEmpty(this.getOnClickRow()) || this.isClickEdit()) {
                htmlSb.append(",\n");
                htmlSb.append("   onClickRow: function (rowIndex,row) {\n");
                if (this.isClickEdit()) {
                    htmlSb.append("      $('#").append(this.getId()).append("').datagrid('beginEdit',rowIndex); ");
                }
                if (!TriRegulation.isEmpty(this.getOnClickRow())) {
                    htmlSb.append(this.getOnClickRow()).append("(rowIndex,row);");
                }
                htmlSb.append("}");
            }
            // 双击开启编辑
            if (!TriRegulation.isEmpty(this.getOnDbClickRow()) || this.isDbClickEdit()) {
                htmlSb.append(",\n");
                htmlSb.append("   onDblClickRow : function(rowIndex,row) {\n");
                if (this.isDbClickEdit()) {
                    htmlSb.append("      $('#").append(this.getId()).append("').datagrid('beginEdit',rowIndex); ");
                }
                if (!TriRegulation.isEmpty(this.getOnDbClickRow())) {
                    htmlSb.append(this.getOnDbClickRow()).append("(rowIndex,row);");
                }
                htmlSb.append("   }");
            }
            // 行编辑前操作 ,或开启编辑searchInput
            if (!TriRegulation.isEmpty(this.getOnBeforeEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeEdit:").append(this.getOnBeforeEdit());
            }
            if (!TriRegulation.isEmpty(this.getOnAfterEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onAfterEdit:").append(this.getOnAfterEdit());
            }
            if (!TriRegulation.isEmpty(this.getOnBeginEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeginEdit:").append(this.getOnBeginEdit());
            }

            if (!TriRegulation.isEmpty(this.getOnEndEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onEndEdit:").append(this.getOnAfterEdit());
            }
            // 行更新前操作 或者需要开启行searchInput点击后，回写行字段editStatus状态
            if (!TriRegulation.isEmpty(this.getOnBeforeUpdateRow()) || (this.isOpenSearchWindowField() && !TriRegulation.isEmpty(this.getSearchWindowFields()))) {// 该事件在扩展datagrid
                // js中
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeUpdateRow:function(rowIndex,row){ \n");
                // 开启编辑searchInput
                if (this.isOpenSearchWindowField() && !TriRegulation.isEmpty(this.getSearchWindowFields())) {
                    // 该事件在扩展datagrid js中
                    if (!TriRegulation.isEmpty(this.getIdField())) {
                        htmlSb.append("if(isEmpty(row.").append(this.getIdField()).append(")){");
                        htmlSb.append("   row.editStatus='update'; \n");
                        htmlSb.append("}");
                    } else {
                        htmlSb.append("   row.editStatus='update'; \n");
                    }

                }
                if (!TriRegulation.isEmpty(this.getOnBeforeUpdateRow())) {
                    htmlSb.append(this.getOnBeforeUpdateRow()).append("(rowIndex,row);");
                }
                htmlSb.append("} ");
            }

            if (!TriRegulation.isEmpty(this.getDoEvent())) {// "1:dd();2:d2();"

                String[] str = this.getDoEvent().split(";");
                for (int i = 0; i < str.length; i++) {
                    String[] actionType = str[i].split(":");
                    if (!TriRegulation.isEmpty(actionType[0]) && actionType[0].equals("onclick")) {// 1:单机事件
                        htmlSb.append(",\n");
                        htmlSb.append("onClickRow: function (index,row){\n");
                        for (int j = 0; j < actionType[1].split(",").length; j++) {
                            htmlSb.append(actionType[1].split(",")[j]).append("(index, row);\n");
                        }
                        htmlSb.append("} ");
                    }
                    if (!TriRegulation.isEmpty(actionType[0]) && actionType[0].equals("ondbclick")) {// 2：双机事件
                        htmlSb.append(",\n");
                        htmlSb.append("onDblClickRow: function (index,row){\n");
                        for (int j = 0; j < actionType[1].split(",").length; j++) {
                            htmlSb.append(actionType[1].split(",")[j]).append("(index, row);\n");
                        }
                        htmlSb.append("} ");
                    }
                }
            }
            if (!TriRegulation.isEmpty(this.getOnSelect())) {
                htmlSb.append(",\n");
                htmlSb.append("   onSelect:").append(this.getOnSelect());
            }
            if (!TriRegulation.isEmpty(this.getOnClickCell())) {
                htmlSb.append(",\n");
                htmlSb.append("   onClickCell:").append(this.getOnClickCell());
            }

            if (!TriRegulation.isEmpty(this.getOnUnselect())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUnselect:").append(this.getOnUnselect());
            }

            if (!TriRegulation.isEmpty(this.getOnSelectAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onSelectAll:").append(this.getOnSelectAll());
            }

            if (!TriRegulation.isEmpty(this.getOnUnselectAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUnselectAll:").append(this.getOnUnselectAll());
            }

            if (!TriRegulation.isEmpty(this.getOnCheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheck:").append(this.getOnCheck());
            }

            if (!TriRegulation.isEmpty(this.getOnUncheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheck:").append(this.getOnUncheck());
            }

            if (!TriRegulation.isEmpty(this.getOnCheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheckAll:").append(this.getOnCheckAll());
            }

            if (!TriRegulation.isEmpty(this.getOnUncheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheckAll:").append(this.getOnUncheckAll());
            }

            if (!TriRegulation.isEmpty(this.getQueryParams())) {
                htmlSb.append(",\n");
                htmlSb.append("   queryParams:{").append(this.getQueryParams()).append("}");
            }
            htmlSb.append(",\n");
            htmlSb.append("   onLoadSuccess: ").append("function(data){\n");
            // htmlSb.append("alert('onLoadSuccess over');");
            // htmlSb.append("  $.fn.datagrid.extensions.onLoadSuccess.apply(this, arguments); \n");// 这句要加上。以支持相应扩展功能 （1.4.3已无扩展功能，需另找其他类似扩展）

            if (!TriRegulation.isEmpty(this.getOnLoadSuccess()) || (!TriRegulation.isEmpty(this.getMergeCellsFields()) && !TriRegulation.isEmpty(this.getMergeCellsKeys()))) {
                if (!TriRegulation.isEmpty(this.getOnLoadSuccess())) {
                    htmlSb.append(this.getOnLoadSuccess()).append("(data);\n");
                }
                if (!TriRegulation.isEmpty(this.getMergeCellsFields()) && !TriRegulation.isEmpty(this.getMergeCellsKeys())) {
                    htmlSb.append(" var margerCellsFields = \"").append(this.getMergeCellsFields()).append("\";\n");
                    htmlSb.append(" var margerCellsKeys = \"").append(this.getMergeCellsKeys()).append("\";\n");
                    htmlSb.append("  mergeColCells2Dg(\"").append(this.getId()).append("\",replaceSpace(margerCellsKeys).split(\",\"),replaceSpace(margerCellsFields).split(\",\"));\n");
                }
            }
            //如果datagrid正常加载，那么去掉变量中当前这个datagrid Id，避免重复刷新
            htmlSb.append("unloadDatagridIds = unloadDatagridIds.replace('" + this.getId() + ",','');\n");
            htmlSb.append(" }\n");


            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {

        try {
            StringBuilder htmlSb = new StringBuilder();
            //构造行对象清单
            htmlSb.append(",\n");
            htmlSb.append("columns:[\n");

            if (!TriRegulation.isEmpty(this.getColumnMap())) {
                Set<String> keySet = this.getColumnMap().keySet();
                Iterator<String> iter = keySet.iterator();
                while (iter.hasNext()) {
                    htmlSb.append("[\n");
                    List<EasyUiTableColumnTag> columnList = this.getColumnMap().get(iter.next());
                    for (Iterator iterator = columnList.iterator(); iterator.hasNext(); ) {
                        EasyUiTableColumnTag column = (EasyUiTableColumnTag) iterator.next();
                        htmlSb.append(this.getColumnHtml(column));
                        if (iterator.hasNext()) {
                            htmlSb.append(",\n");
                        }
                    }//for end
                    htmlSb.append("]\n");
                    if (iter.hasNext()) {
                        //htmlSb.append(",[\n");
                        htmlSb.append(",\n");
                    }

                }//while
            } else {
                if (!TriRegulation.isEmpty(this.getColumns())) {
                    htmlSb.append("[\n");
                    for (Iterator columnIter = this.getColumns().iterator(); columnIter.hasNext(); ) {
                        EasyUiTableColumnTag column = (EasyUiTableColumnTag) columnIter.next();
                        htmlSb.append(this.getColumnHtml(column));
                        if (columnIter.hasNext()) {
                            htmlSb.append(",\n");
                        }
                    }//for end
                    htmlSb.append("]\n");     //columns 结尾
                }
            }

            htmlSb.append("]\n");     //columns 结尾
            htmlSb.append(" });\n");  //datagrid end

            // 设置datagrid网格的样式
            htmlSb.append("$('#").append(this.getId()).append("').datagrid('getPanel').removeClass('lines-both lines-no lines-right lines-bottom').addClass('lines-no');");
            htmlSb.append(" });\n");  //function end
            htmlSb.append("</script>\n");


            //初始化变量：理论上来说，不应该存在！！！！！！！！！！
            this.columns = null;
            this.columnMap = null;
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    /**
     * 根据行对象获取构造行对象的html
     *
     * @param column
     * @return
     */
    private String getColumnHtml(EasyUiTableColumnTag column) {
        StringBuilder htmlSb = new StringBuilder();
        htmlSb.append("{");
        if (!TriRegulation.isEmpty(column.getTitle()) || !TriRegulation.isEmpty(column.getTitleKey())) {
            htmlSb.append("title:\"");
            if (!TriRegulation.isEmpty(column.getTitle())) {
                htmlSb.append(column.getTitle());
            } else if (!TriRegulation.isEmpty(column.getTitleKey())) {
                String keyStr = MessageUtil.getMessage(pageContext.getRequest(), column.getTitleKey());
                htmlSb.append(!TriRegulation.isEmpty(keyStr) ? keyStr : "");
            }
            htmlSb.append("\",");
        }
        if (!TriRegulation.isEmpty(column.getField())) {
            htmlSb.append("field:\"").append(column.getField()).append("\",");
        }
        htmlSb.append("sortable:").append(column.isSortable()).append("");


        if (!TriRegulation.isEmpty(this.getColumnMap()) && !TriRegulation.isEmpty(column.getWidth())) {//多表头
            if (!TriRegulation.isEmpty(column.isHidden()) && !column.isHidden()) {
                htmlSb.append(",width:").append(column.getWidth());
            }
        } else {  //单表头
            if (!TriRegulation.isEmpty(column.getWidth())) {
                if (!TriRegulation.isEmpty(column.isHidden()) && !column.isHidden()) {
                    htmlSb.append(",width:").append(column.getWidth());
                }
            }
        }

        if (!TriRegulation.isEmpty(column.getRowspan()) && column.getRowspan() > 0) {
            htmlSb.append(",rowspan:").append(column.getRowspan()).append("");
        }
        if (!TriRegulation.isEmpty(column.getColspan()) && column.getColspan() > 0) {
            htmlSb.append(",colspan:").append(column.getColspan()).append("");
        }
        if (!TriRegulation.isEmpty(column.getFormatter())) {
            htmlSb.append(",formatter:").append(column.getFormatter()).append("");
        }
        if (!TriRegulation.isEmpty(column.getStyler())) {
            htmlSb.append(",styler:\"").append(column.getStyler()).append("\"");
        }
        if (!TriRegulation.isEmpty(column.getAlign())) {
            htmlSb.append(",align:\"").append(column.getAlign()).append("\"");
        }
        if (column.isCheckbox()) {
            htmlSb.append(",checkbox:\"").append(column.isCheckbox()).append("\"");
            htmlSb.append(",checked:\"checked\"");
        }
        if (!TriRegulation.isEmpty(column.isHidden()) && column.isHidden()) {
            htmlSb.append(",hidden:").append(column.isHidden());
        }
        if (!TriRegulation.isEmpty(column.getEditor())) {
            /**
             CZI:
             错误写法:
             editor:"{type:'text'}"
             正确写法:
             editor:{type:'text'}
             editor 值为对象,而非String
             */
            htmlSb.append(",editor:").append(column.getEditor()).append("");
        }

        htmlSb.append("}");

        return htmlSb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutoMergeCells() {
        return autoMergeCells;
    }

    public void setAutoMergeCells(String autoMergeCells) {
        this.autoMergeCells = autoMergeCells;
    }

    public String getId() {
        return id;
    }

    public String getOnClickCell() {
        return onClickCell;
    }

    public void setOnClickCell(String onClickCell) {
        this.onClickCell = onClickCell;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public boolean isFitColumns() {
        return fitColumns;
    }

    public void setFitColumns(boolean fitColumns) {
        this.fitColumns = fitColumns;
    }

    public boolean isRownumbers() {
        return rownumbers;
    }

    public void setRownumbers(boolean rownumbers) {
        this.rownumbers = rownumbers;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageSizeList() {
        return pageSizeList;
    }

    public void setPageSizeList(String pageSizeList) {
        this.pageSizeList = pageSizeList;
    }

    public String getToolbarId() {
        return toolbarId;
    }

    public void setToolbarId(String toolbarId) {
        this.toolbarId = toolbarId;
    }

    public boolean isSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public String getOnClickRow() {
        return onClickRow;
    }

    public void setOnClickRow(String onClickRow) {
        this.onClickRow = onClickRow;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getOnLoadSuccess() {
        return onLoadSuccess;
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
        this.onLoadSuccess = onLoadSuccess;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public String getOnSelect() {
        return onSelect;
    }

    public void setOnSelect(String onSelect) {
        this.onSelect = onSelect;
    }

    public String getOnUnselect() {
        return onUnselect;
    }

    public void setOnUnselect(String onUnselect) {
        this.onUnselect = onUnselect;
    }

    public String getOnSelectAll() {
        return onSelectAll;
    }

    public void setOnSelectAll(String onSelectAll) {
        this.onSelectAll = onSelectAll;
    }

    public String getOnUnselectAll() {
        return onUnselectAll;
    }

    public void setOnUnselectAll(String onUnselectAll) {
        this.onUnselectAll = onUnselectAll;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public String getOnCheck() {
        return onCheck;
    }

    public String getOnDbClickRow() {
        return onDbClickRow;
    }

    public void setOnDbClickRow(String onDbClickRow) {
        this.onDbClickRow = onDbClickRow;
    }

    public String getOnBeforeEdit() {
        return onBeforeEdit;
    }

    public void setOnBeforeEdit(String onBeforeEdit) {
        this.onBeforeEdit = onBeforeEdit;
    }

    public String getOnAfterEdit() {
        return onAfterEdit;
    }

    public void setOnAfterEdit(String onAfterEdit) {
        this.onAfterEdit = onAfterEdit;
    }

    public void setOnCheck(String onCheck) {
        this.onCheck = onCheck;
    }

    public String getOnUncheck() {
        return onUncheck;
    }

    public void setOnUncheck(String onUncheck) {
        this.onUncheck = onUncheck;
    }

    public String getOnCheckAll() {
        return onCheckAll;
    }

    public void setOnCheckAll(String onCheckAll) {
        this.onCheckAll = onCheckAll;
    }

    public String getOnUncheckAll() {
        return onUncheckAll;
    }

    public void setOnUncheckAll(String onUncheckAll) {
        this.onUncheckAll = onUncheckAll;
    }

    public boolean isCheckOnSelect() {
        return checkOnSelect;
    }

    public void setCheckOnSelect(boolean checkOnSelect) {
        this.checkOnSelect = checkOnSelect;
    }

    public boolean isSelectOnCheck() {
        return selectOnCheck;
    }

    public void setSelectOnCheck(boolean selectOnCheck) {
        this.selectOnCheck = selectOnCheck;
    }

    public boolean isNowrap() {
        return nowrap;
    }

    public void setNowrap(boolean nowrap) {
        this.nowrap = nowrap;
    }

    public String getRowStyler() {
        return rowStyler;
    }

    public void setRowStyler(String rowStyler) {
        this.rowStyler = rowStyler;
    }

    public String getMergeCellsFields() {
        return mergeCellsFields;
    }

    public void setMergeCellsFields(String mergeCellsFields) {
        this.mergeCellsFields = mergeCellsFields;
    }

    public String getMergeCellsKeys() {
        return mergeCellsKeys;
    }

    public void setMergeCellsKeys(String mergeCellsKeys) {
        this.mergeCellsKeys = mergeCellsKeys;
    }

    public String getFrozenFields() {
        return frozenFields;
    }

    public void setFrozenFields(String frozenFields) {
        this.frozenFields = frozenFields;
    }

    public String getFrozenTitles() {
        return frozenTitles;
    }

    public void setFrozenTitles(String frozenTitles) {
        this.frozenTitles = frozenTitles;
    }

    public String getFrozenTitleKeys() {
        return frozenTitleKeys;
    }

    public void setFrozenTitleKeys(String frozenTitleKeys) {
        this.frozenTitleKeys = frozenTitleKeys;
    }

    public String getFrozenEditors() {
        return frozenEditors;
    }

    public void setFrozenEditors(String frozenEditors) {
        this.frozenEditors = frozenEditors;
    }

    public String getFrozenWidths() {
        return frozenWidths;
    }

    public void setFrozenWidths(String frozenWidths) {
        this.frozenWidths = frozenWidths;
    }

    public String getFrozenFormatters() {
        return frozenFormatters;
    }

    public void setFrozenFormatters(String frozenFormatters) {
        this.frozenFormatters = frozenFormatters;
    }

    public String getFrozenColumnTypes() {
        return frozenColumnTypes;
    }

    public void setFrozenColumnTypes(String frozenColumnTypes) {
        this.frozenColumnTypes = frozenColumnTypes;
    }

    public boolean isEnableHeaderClickMenu() {
        return enableHeaderClickMenu;
    }

    public void setEnableHeaderClickMenu(boolean enableHeaderClickMenu) {
        this.enableHeaderClickMenu = enableHeaderClickMenu;
    }

    public boolean isEnableHeaderContextMenu() {
        return enableHeaderContextMenu;
    }

    public void setEnableHeaderContextMenu(boolean enableHeaderContextMenu) {
        this.enableHeaderContextMenu = enableHeaderContextMenu;
    }

    public boolean isEnableRowContextMenu() {
        return enableRowContextMenu;
    }

    public void setEnableRowContextMenu(boolean enableRowContextMenu) {
        this.enableRowContextMenu = enableRowContextMenu;
    }

    public boolean isMoveMenu() {
        return moveMenu;
    }

    public void setMoveMenu(boolean moveMenu) {
        this.moveMenu = moveMenu;
    }

    public boolean isDndRow() {
        return dndRow;
    }

    public void setDndRow(boolean dndRow) {
        this.dndRow = dndRow;
    }

    public boolean isSelectOnRowContextMenu() {
        return selectOnRowContextMenu;
    }

    public void setSelectOnRowContextMenu(boolean selectOnRowContextMenu) {
        this.selectOnRowContextMenu = selectOnRowContextMenu;
    }

    public boolean isRowTooltip() {
        return rowTooltip;
    }

    public void setRowTooltip(boolean rowTooltip) {
        this.rowTooltip = rowTooltip;
    }

    public boolean isAutoEditing() {
        return autoEditing;
    }

    public void setAutoEditing(boolean autoEditing) {
        this.autoEditing = autoEditing;
    }

    public boolean isSingleEditing() {
        return singleEditing;
    }

    public void setSingleEditing(boolean singleEditing) {
        this.singleEditing = singleEditing;
    }

    public String getColumnFilter() {
        return columnFilter;
    }

    public void setColumnFilter(String columnFilter) {
        this.columnFilter = columnFilter;
    }

    public boolean isExtEditing() {
        return extEditing;
    }

    public void setExtEditing(boolean extEditing) {
        this.extEditing = extEditing;
    }

    public boolean isRemoteSort() {
        return remoteSort;
    }

    public void setRemoteSort(boolean remoteSort) {
        this.remoteSort = remoteSort;
    }

    public boolean isMultiSort() {
        return multiSort;
    }

    public void setMultiSort(boolean multiSort) {
        this.multiSort = multiSort;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isHeaderContextMenu() {
        return headerContextMenu;
    }

    public void setHeaderContextMenu(boolean headerContextMenu) {
        this.headerContextMenu = headerContextMenu;
    }

    public String getDoEvent() {
        return doEvent;
    }

    public void setDoEvent(String doEvent) {
        this.doEvent = doEvent;
    }

    public boolean isStopReload() {
        return stopReload;
    }

    public void setStopReload(boolean stopReload) {
        this.stopReload = stopReload;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isOnlySingleSelect() {
        return onlySingleSelect;
    }

    public void setOnlySingleSelect(boolean onlySingleSelect) {
        this.onlySingleSelect = onlySingleSelect;
    }

    public boolean isOpenSearchWindowField() {
        return openSearchWindowField;
    }

    public void setOpenSearchWindowField(boolean openSearchWindowField) {
        this.openSearchWindowField = openSearchWindowField;
    }

    public String getSearchWindowFields() {
        return searchWindowFields;
    }

    public void setSearchWindowFields(String searchWindowFields) {
        this.searchWindowFields = searchWindowFields;
    }

    public String getSearchWindowFieldFun() {
        return searchWindowFieldFun;
    }

    public void setSearchWindowFieldFun(String searchWindowFieldFun) {
        this.searchWindowFieldFun = searchWindowFieldFun;
    }

    public String getOnBeforeUpdateRow() {
        return onBeforeUpdateRow;
    }

    public void setOnBeforeUpdateRow(String onBeforeUpdateRow) {
        this.onBeforeUpdateRow = onBeforeUpdateRow;
    }

    public boolean isDbClickEdit() {
        return dbClickEdit;
    }

    public void setDbClickEdit(boolean dbClickEdit) {
        this.dbClickEdit = dbClickEdit;
    }

    public boolean isClickEdit() {
        return clickEdit;
    }

    public boolean isShowFooter() {
        return showFooter;
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    public void setClickEdit(boolean clickEdit) {
        this.clickEdit = clickEdit;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }

    public String getGroupFormatter() {
        return groupFormatter;
    }

    public void setGroupFormatter(String groupFormatter) {
        this.groupFormatter = groupFormatter;
    }

    public String getCascadeDatagrid() {
        return cascadeDatagrid;
    }

    public void setCascadeDatagrid(String cascadeDatagrid) {
        this.cascadeDatagrid = cascadeDatagrid;
    }

    public String getCascadeDatagridParams() {
        return cascadeDatagridParams;
    }

    public void setCascadeDatagridParams(String cascadeDatagridParams) {
        this.cascadeDatagridParams = cascadeDatagridParams;
    }

    public List<EasyUiTableColumnTag> getColumns() {
        return columns;
    }

    public void setColumns(List<EasyUiTableColumnTag> columns) {
        this.columns = columns;
    }

    public Map<String, List<EasyUiTableColumnTag>> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(TreeMap<String, List<EasyUiTableColumnTag>> columnMap) {
        this.columnMap = columnMap;
    }

    /**
     * 向columnMap对象中插入一个新行数据
     *
     * @param column
     */
    public synchronized void addColumn(EasyUiTableColumnTag column) {
        if (TriRegulation.isEmpty(this.getColumnMap())) {
            if (TriRegulation.isEmpty(this.columns)) {
                this.columns = new ArrayList<EasyUiTableColumnTag>();
            }
            this.columns.add(column);
        } else {
            this.columnMap.get(this.columnMap.lastKey()).add(column);
        }
    }

    /**
     * 向columnMap对象中插入一个新对象，序号自增1
     */
    public synchronized void addColumnMap() {
        if (TriRegulation.isEmpty(this.getColumnMap())) {
            this.columnMap = new TreeMap<String, List<EasyUiTableColumnTag>>();
            this.columnMap.put("1", new ArrayList<EasyUiTableColumnTag>());
        } else {
            this.columnMap.put(Integer.valueOf(this.columnMap.lastKey()) + 1 + "", new ArrayList<EasyUiTableColumnTag>());
        }
    }

    public String getOnEndEdit() {
        return onEndEdit;
    }

    public void setOnEndEdit(String onEndEdit) {
        this.onEndEdit = onEndEdit;
    }

    public String getOnBeginEdit() {
        return onBeginEdit;
    }

    public void setOnBeginEdit(String onBeginEdit) {
        this.onBeginEdit = onBeginEdit;
    }


}
