package com.zen.easyui.tag;

import com.zen.easyui.constant.EasyuiTagGlobalConstant;
import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.*;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiDatagridTableTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

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

    private boolean rownumbers = EasyuiTagGlobalConstant.DATAGRID_ROWNUMBERS; // 显示行号 Y

    private boolean onlySingleSelect = EasyuiTagGlobalConstant.DATAGRID_ROW_ONLY_SINGLE_SELECT; // 开启只能单选

    private boolean singleSelect = EasyuiTagGlobalConstant.DATAGRID_ROW_SINGLE_SELECT; // 开启单行选择,false=多行

    private boolean checkOnSelect = true; //

    private boolean selectOnCheck = true; //

    private boolean headerContextMenu = EasyuiTagGlobalConstant.DATAGRID_HEADER_CONTEXT_MENU;// 开启表头字段的菜单功能

    private boolean enableHeaderClickMenu = EasyuiTagGlobalConstant.DATAGRID_ENABLE_HEADER_CLICK_MENU;// 开启表头列名称右侧那个箭头形状的鼠标左键点击菜单

    private boolean enableHeaderContextMenu = EasyuiTagGlobalConstant.DATAGRID_ENABLE_R_HEADER_CONTEXT_MENU;// 开启表头列名称右键点击菜单

    private boolean enableRowContextMenu = EasyuiTagGlobalConstant.DATAGRID_ENABLE_ROW_CONTEXT_MENU;// 开启行右键点击菜单

    private boolean moveMenu = EasyuiTagGlobalConstant.DATAGRID_MOVE_MENU; // 开始行右键菜单的移动列功能

    private boolean dndRow = EasyuiTagGlobalConstant.DATAGRID_DND_ROW; // 开启此表格的行拖动排序功能 Y

    private boolean selectOnRowContextMenu = EasyuiTagGlobalConstant.DATAGRID_SELECT_ON_ROW_CONTEXT_MENU; // 此属性开启当右键点击行时自动选择该行的功能

    private boolean rowTooltip = EasyuiTagGlobalConstant.DATAGRID_ROW_TOOLTIP;// 开启行内容浮动提示

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

    private int pageSize = EasyuiTagGlobalConstant.DATAGRID_PAGE_SIZE; // 每页显示几行

    private String pageSizeList = EasyuiTagGlobalConstant.DATAGRID_PAGE_SIZE_LIST; // 供用户选择的行数

    private String onBeforeEdit;

    private String onAfterEdit;

    private String autoMergeCells; // 自动合并列字段定义 （暂时不用）

    private String mergeCellsFields; // 需要合并的字段，用逗号隔开

    private String mergeCellsKeys; // 合并行的依据字段，用逗号隔开 （与mergeCellsFields配合一起使用）

    private boolean remoteSort = EasyuiTagGlobalConstant.DATAGRID_REMOTE_SORT; // 定义是否从服务器对数据进行排序。
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
            if (!RegulationUtil.isEmpty(this.getStyle())) {
                htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
            }
            htmlSb.append(">\n</table>");

            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append(" var ").append(this.getId()).append("URL = '").append(this.getUrl()).append("';\n");
            htmlSb.append(" var ").append(this.getId()).append("CascadeDatagrid = '").append(this.getCascadeDatagrid()).append("';\n");
            htmlSb.append(" var ").append(this.getId()).append("CascadeDatagridParams = '").append(this.getCascadeDatagridParams()).append("';\n");
            // 如果开启行双击编辑弹出框字段
            if (this.isOpenSearchWindowField() && !RegulationUtil.isEmpty(this.getSearchWindowFields())) {
                htmlSb.append(" var ").append(this.getId()).append("RowIndex = 0;\n");
                // htmlSb.append(" var currentClickRowIndex = 0;\n");
            }
            htmlSb.append("$(function(){\n");
            // 纳入需要统计的datagrid
            if (!RegulationUtil.isEmpty(this.getUrl())) {
                htmlSb.append("if ((isEmpty(unloadDatagridIds) && ").append(this.isStopReload()).append("==false) || (").append(this.isAutoReload()).append("==true)){\n");
                htmlSb.append("   unloadDatagridIds +='" + this.getId() + ",';\n");
                htmlSb.append("}\n");
            }
            // 渲染脚本
            htmlSb.append(" var t = $('#").append(this.getId()).append("').datagrid({\n");
            htmlSb.append("   border: false,\n");
            htmlSb.append("   pagination: ").append(this.isPagination()).append(",\n");
            if (!RegulationUtil.isEmpty(this.getTitleKey()) && !RegulationUtil.isEmpty(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey()))) {
                htmlSb.append("   title: ").append(MessageUtil.getMessage(this.pageContext.getRequest(), this.getTitleKey())).append(",\n");
            } else if (!RegulationUtil.isEmpty(this.getTitle())) {
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
            if (!RegulationUtil.isEmpty(this.getColumnFilter())) {
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

            if (!RegulationUtil.isEmpty(this.getFrozenFields())) {
                htmlSb.append(",\n");
                htmlSb.append("   frozenColumns:[[").append("\n");
                for (int i = 0; i < this.getFrozenFields().split(",").length; i++) {
                    if (RegulationUtil.isEmpty(this.getFrozenFields().split(",")[i])) {
                        continue;
                    }
                    htmlSb.append("   { ");

                    if (!RegulationUtil.isEmpty(this.getFrozenFields()) && !RegulationUtil.isEmpty(this.getFrozenFields().split(",")[i])) {
                        htmlSb.append("field:'").append(this.getFrozenFields().split(",")[i]).append("'\n");
                    }
                    if (!RegulationUtil.isEmpty(this.getFrozenTitles()) && !RegulationUtil.isEmpty(this.getFrozenTitles().split(",")[i])) {
                        htmlSb.append(",title:'").append(this.getFrozenTitles().split(",")[i]).append("'\n");
                    } else if (!RegulationUtil.isEmpty(this.getFrozenTitleKeys())) {
                        htmlSb.append(",title:'").append(MessageUtil.getMessage(this.pageContext.getRequest(), this.getFrozenTitleKeys().split(",")[i])).append("'\n");
                    }
                    if (!RegulationUtil.isEmpty(this.getFrozenWidths()) && !RegulationUtil.isEmpty(this.getFrozenWidths().split(",")[i])) {
                        htmlSb.append(",width:'").append(this.getFrozenWidths().split(",")[i]).append("'\n");
                    }
                    if (!RegulationUtil.isEmpty(this.getFrozenEditors()) && !RegulationUtil.isEmpty(this.getFrozenFields().split(",")[i])) {
                        htmlSb.append(",\n");
                        htmlSb.append(",editor:'").append(this.getFrozenEditors().split(",")[i]).append("'");
                    }
                    if (!RegulationUtil.isEmpty(this.getFrozenColumnTypes()) && this.getFrozenColumnTypes().split(",").length - 1 == i) {
                        // 单选框类型
                        if ("checkbox".equals(this.getFrozenColumnTypes().split(",")[i])) {
                            htmlSb.append(",\n");
                            htmlSb.append("checkbox:'true' \n");
                        }
                        // 其他类型
                    }
                    if (!RegulationUtil.isEmpty(this.getFrozenFormatters()) && !RegulationUtil.isEmpty(this.getFrozenFormatters().split(",")[i])) {
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
            if (!RegulationUtil.isEmpty(this.getRowStyler())) {
                htmlSb.append(",\n");
                htmlSb.append("   rowStyler:").append(this.getRowStyler());
            }
            if (!RegulationUtil.isEmpty(this.getGroupField())) {
                htmlSb.append(",\n");
                htmlSb.append(" groupField:").append(this.getGroupField());
            }
            if (!RegulationUtil.isEmpty(this.getGroupFormatter())) {
                htmlSb.append(",\n");
                htmlSb.append("groupFormatter:").append(this.getGroupFormatter());
            }

            if (!RegulationUtil.isEmpty(this.getData())) {
                htmlSb.append(",\n");
                htmlSb.append("   data:").append(this.getData());
            }
            if (!RegulationUtil.isEmpty(this.getIdField())) {
                htmlSb.append(",\n");
                htmlSb.append("   idField:'").append(this.getIdField()).append("'");
            }
            // 单击开启编辑
            if (!RegulationUtil.isEmpty(this.getOnClickRow()) || this.isClickEdit()) {
                htmlSb.append(",\n");
                htmlSb.append("   onClickRow: function (rowIndex,row) {\n");
                if (this.isClickEdit()) {
                    htmlSb.append("      $('#").append(this.getId()).append("').datagrid('beginEdit',rowIndex); ");
                }
                if (!RegulationUtil.isEmpty(this.getOnClickRow())) {
                    htmlSb.append(this.getOnClickRow()).append("(rowIndex,row);");
                }
                htmlSb.append("}");
            }
            // 双击开启编辑
            if (!RegulationUtil.isEmpty(this.getOnDbClickRow()) || this.isDbClickEdit()) {
                htmlSb.append(",\n");
                htmlSb.append("   onDblClickRow : function(rowIndex,row) {\n");
                if (this.isDbClickEdit()) {
                    htmlSb.append("      $('#").append(this.getId()).append("').datagrid('beginEdit',rowIndex); ");
                }
                if (!RegulationUtil.isEmpty(this.getOnDbClickRow())) {
                    htmlSb.append(this.getOnDbClickRow()).append("(rowIndex,row);");
                }
                htmlSb.append("   }");
            }
            // 行编辑前操作 ,或开启编辑searchInput
            if (!RegulationUtil.isEmpty(this.getOnBeforeEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeEdit:").append(this.getOnBeforeEdit());
            }
            if (!RegulationUtil.isEmpty(this.getOnAfterEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onAfterEdit:").append(this.getOnAfterEdit());
            }
            if (!RegulationUtil.isEmpty(this.getOnBeginEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeginEdit:").append(this.getOnBeginEdit());
            }

            if (!RegulationUtil.isEmpty(this.getOnEndEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onEndEdit:").append(this.getOnAfterEdit());
            }
            // 行更新前操作 或者需要开启行searchInput点击后，回写行字段editStatus状态
            if (!RegulationUtil.isEmpty(this.getOnBeforeUpdateRow()) || (this.isOpenSearchWindowField() && !RegulationUtil.isEmpty(this.getSearchWindowFields()))) {// 该事件在扩展datagrid
                // js中
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeUpdateRow:function(rowIndex,row){ \n");
                // 开启编辑searchInput
                if (this.isOpenSearchWindowField() && !RegulationUtil.isEmpty(this.getSearchWindowFields())) {
                    // 该事件在扩展datagrid js中
                    if (!RegulationUtil.isEmpty(this.getIdField())) {
                        htmlSb.append("if(isEmpty(row.").append(this.getIdField()).append(")){");
                        htmlSb.append("   row.editStatus='update'; \n");
                        htmlSb.append("}");
                    } else {
                        htmlSb.append("   row.editStatus='update'; \n");
                    }

                }
                if (!RegulationUtil.isEmpty(this.getOnBeforeUpdateRow())) {
                    htmlSb.append(this.getOnBeforeUpdateRow()).append("(rowIndex,row);");
                }
                htmlSb.append("} ");
            }

            if (!RegulationUtil.isEmpty(this.getDoEvent())) {// "1:dd();2:d2();"

                String[] str = this.getDoEvent().split(";");
                for (int i = 0; i < str.length; i++) {
                    String[] actionType = str[i].split(":");
                    if (!RegulationUtil.isEmpty(actionType[0]) && actionType[0].equals("onclick")) {// 1:单机事件
                        htmlSb.append(",\n");
                        htmlSb.append("onClickRow: function (index,row){\n");
                        for (int j = 0; j < actionType[1].split(",").length; j++) {
                            htmlSb.append(actionType[1].split(",")[j]).append("(index, row);\n");
                        }
                        htmlSb.append("} ");
                    }
                    if (!RegulationUtil.isEmpty(actionType[0]) && actionType[0].equals("ondbclick")) {// 2：双机事件
                        htmlSb.append(",\n");
                        htmlSb.append("onDblClickRow: function (index,row){\n");
                        for (int j = 0; j < actionType[1].split(",").length; j++) {
                            htmlSb.append(actionType[1].split(",")[j]).append("(index, row);\n");
                        }
                        htmlSb.append("} ");
                    }
                }
            }
            if (!RegulationUtil.isEmpty(this.getOnSelect())) {
                htmlSb.append(",\n");
                htmlSb.append("   onSelect:").append(this.getOnSelect());
            }
            if (!RegulationUtil.isEmpty(this.getOnClickCell())) {
                htmlSb.append(",\n");
                htmlSb.append("   onClickCell:").append(this.getOnClickCell());
            }

            if (!RegulationUtil.isEmpty(this.getOnUnselect())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUnselect:").append(this.getOnUnselect());
            }

            if (!RegulationUtil.isEmpty(this.getOnSelectAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onSelectAll:").append(this.getOnSelectAll());
            }

            if (!RegulationUtil.isEmpty(this.getOnUnselectAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUnselectAll:").append(this.getOnUnselectAll());
            }

            if (!RegulationUtil.isEmpty(this.getOnCheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheck:").append(this.getOnCheck());
            }

            if (!RegulationUtil.isEmpty(this.getOnUncheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheck:").append(this.getOnUncheck());
            }

            if (!RegulationUtil.isEmpty(this.getOnCheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheckAll:").append(this.getOnCheckAll());
            }

            if (!RegulationUtil.isEmpty(this.getOnUncheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheckAll:").append(this.getOnUncheckAll());
            }

            if (!RegulationUtil.isEmpty(this.getQueryParams())) {
                htmlSb.append(",\n");
                htmlSb.append("   queryParams:{").append(this.getQueryParams()).append("}");
            }
            htmlSb.append(",\n");
            htmlSb.append("   onLoadSuccess: ").append("function(data){\n");
            // htmlSb.append("alert('onLoadSuccess over');");
            // htmlSb.append("  $.fn.datagrid.extensions.onLoadSuccess.apply(this, arguments); \n");// 这句要加上。以支持相应扩展功能 （1.4.3已无扩展功能，需另找其他类似扩展）

            if (!RegulationUtil.isEmpty(this.getOnLoadSuccess()) || (!RegulationUtil.isEmpty(this.getMergeCellsFields()) && !RegulationUtil.isEmpty(this.getMergeCellsKeys()))) {
                if (!RegulationUtil.isEmpty(this.getOnLoadSuccess())) {
                    htmlSb.append(this.getOnLoadSuccess()).append("(data);\n");
                }
                if (!RegulationUtil.isEmpty(this.getMergeCellsFields()) && !RegulationUtil.isEmpty(this.getMergeCellsKeys())) {
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

            if (!RegulationUtil.isEmpty(this.getColumnMap())) {
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
                if (!RegulationUtil.isEmpty(this.getColumns())) {
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
        if (!RegulationUtil.isEmpty(column.getTitle()) || !RegulationUtil.isEmpty(column.getTitleKey())) {
            htmlSb.append("title:\"");
            if (!RegulationUtil.isEmpty(column.getTitle())) {
                htmlSb.append(column.getTitle());
            } else if (!RegulationUtil.isEmpty(column.getTitleKey())) {
                String keyStr = MessageUtil.getMessage(pageContext.getRequest(), column.getTitleKey());
                htmlSb.append(!RegulationUtil.isEmpty(keyStr) ? keyStr : "");
            }
            htmlSb.append("\",");
        }
        if (!RegulationUtil.isEmpty(column.getField())) {
            htmlSb.append("field:\"").append(column.getField()).append("\",");
        }
        htmlSb.append("sortable:").append(column.isSortable()).append("");


        if (!RegulationUtil.isEmpty(this.getColumnMap()) && !RegulationUtil.isEmpty(column.getWidth())) {//多表头
            if (!RegulationUtil.isEmpty(column.isHidden()) && !column.isHidden()) {
                htmlSb.append(",width:").append(column.getWidth());
            }
        } else {  //单表头
            if (!RegulationUtil.isEmpty(column.getWidth())) {
                if (!RegulationUtil.isEmpty(column.isHidden()) && !column.isHidden()) {
                    htmlSb.append(",width:").append(column.getWidth());
                }
            }
        }

        if (!RegulationUtil.isEmpty(column.getRowspan()) && column.getRowspan() > 0) {
            htmlSb.append(",rowspan:").append(column.getRowspan()).append("");
        }
        if (!RegulationUtil.isEmpty(column.getColspan()) && column.getColspan() > 0) {
            htmlSb.append(",colspan:").append(column.getColspan()).append("");
        }
        if (!RegulationUtil.isEmpty(column.getFormatter())) {
            htmlSb.append(",formatter:").append(column.getFormatter()).append("");
        }
        if (!RegulationUtil.isEmpty(column.getStyler())) {
            htmlSb.append(",styler:\"").append(column.getStyler()).append("\"");
        }
        if (!RegulationUtil.isEmpty(column.getAlign())) {
            htmlSb.append(",align:\"").append(column.getAlign()).append("\"");
        }
        if (column.isCheckbox()) {
            htmlSb.append(",checkbox:\"").append(column.isCheckbox()).append("\"");
            htmlSb.append(",checked:\"checked\"");
        }
        if (!RegulationUtil.isEmpty(column.isHidden()) && column.isHidden()) {
            htmlSb.append(",hidden:").append(column.isHidden());
        }
        if (!RegulationUtil.isEmpty(column.getEditor())) {
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


    /**
     * 向columnMap对象中插入一个新行数据
     *
     * @param column
     */
    public synchronized void addColumn(EasyUiTableColumnTag column) {
        if (RegulationUtil.isEmpty(this.getColumnMap())) {
            if (RegulationUtil.isEmpty(this.columns)) {
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
        if (RegulationUtil.isEmpty(this.getColumnMap())) {
            this.columnMap = new TreeMap<String, List<EasyUiTableColumnTag>>();
            this.columnMap.put("1", new ArrayList<EasyUiTableColumnTag>());
        } else {
            this.columnMap.put(Integer.valueOf(this.columnMap.lastKey()) + 1 + "", new ArrayList<EasyUiTableColumnTag>());
        }
    }

}
