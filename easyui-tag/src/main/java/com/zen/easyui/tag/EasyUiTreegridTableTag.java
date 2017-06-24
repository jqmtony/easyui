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
public class EasyUiTreegridTableTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

    private String id; // TreeGrid标识

    private String title; // TreeGrid标题

    private String url; // 数据路径

    private String idField; // TreeGrid主键

    private String treeField;// TreeGrid展开字段

    private boolean dataPlain; // 该属性用以启用当前 easyui-treegrid 控件对平滑数据格式的支持

    private String parentField;// 该属性表示支持平滑数据格式时指向父级节点idField的属性名，默认为 "pid"

    private String frozenFields; // 锁定列字段

    private String frozenTitleKeys;

    private String frozenEditors;

    private String frozenColumnTypes;

    private String frozenWidths;

    private String frozenFormatters;

    private String frozenTitles;

    private String frozenTitle; // 锁定列名称

    private String frozenTitleKey; // 多语言锁定列名称

    private String frozenEditor; // 锁定列编辑类型

    private int frozenWidth; // 锁定列宽度

    private String frozenFormatter; // 锁定列样式

    private String iconCls; // 图标样式

    private boolean nowrap = true; // 如果为true，则在同一行中显示数据

    private boolean pagination = true; // 启用分页

    private boolean collapsible = EasyuiTagGlobalConstant.TREEGRID_COLLAPSIBLE; // 是否折叠所有节点  Y

    private boolean checkOnSelect = true;

    private boolean selectOnCheck = true;

    private boolean headerContextMenu = EasyuiTagGlobalConstant.TREEGRID_HEADER_CONTEXT_MENU;// 开启表头字段的菜单功能

    private boolean enableHeaderClickMenu = EasyuiTagGlobalConstant.TREEGRID_ENABLE_HEADER_CLICK_MENU;// 开启表头列名称右侧那个箭头形状的鼠标左键点击菜单    Y

    private boolean enableHeaderContextMenu = EasyuiTagGlobalConstant.TREEGRID_ENABLE_R_HEADER_CONTEXT_MENU;// 开启表头列名称右键点击菜单   Y

    private boolean enableRowContextMenu = EasyuiTagGlobalConstant.TREEGRID_ENABLE_ROW_CONTEXT_MENU;// 开启行右键点击菜单    Y

    private boolean moveMenu = EasyuiTagGlobalConstant.TREEGRID_MOVE_MENU; // 开始行右键菜单的移动列功能   Y

    private boolean dndRow = EasyuiTagGlobalConstant.TREEGRID_DND_ROW; // 开启此表格的行拖动排序功能    Y

    private boolean selectOnRowContextMenu = EasyuiTagGlobalConstant.TREEGRID_SELECT_ON_ROW_CONTEXT_MENU; // 此属性开启当右键点击行时自动选择该行的功能   Y

    private boolean rowTooltip = EasyuiTagGlobalConstant.TREEGRID_ROW_TOOLTIP;// 开启行内容浮动提示   Y

    private boolean autoEditing = false; // 该属性启用双击行时自定开启该行的编辑状态

    private boolean singleEditing = false; // 该属性启用treegrid的只允许单行编辑效果

    private String columnFilter = EasyuiTagGlobalConstant.TREEGRID_COLUMN_FILTER;// 定义表头过滤器    Y

    private boolean toggleOnClick = EasyuiTagGlobalConstant.TREEGRID_TOGGLE_ON_CLICK;// 单击节点自动展开/折叠   Y

    private boolean onlyNodeExpand = EasyuiTagGlobalConstant.TREEGRID_ONLY_NODE_EXPAND;// 自动关闭其他节点，只展开当前节点    Y

    private boolean fit = true; // 表格自适应

    private boolean fitColumns = true; // 列自适应

    private boolean rownumbers = EasyuiTagGlobalConstant.TREEGRID_ROW_TOOLTIP; // 显示行号    Y

    private boolean singleSelect = true; // 是否只能单选一行

    private String toolbarId; // 工具栏

    private String style; // 自定义样式

    private int pageSize = EasyuiTagGlobalConstant.TREEGRID_PAGE_SIZE; // 每页显示几行

    private String pageSizeList = EasyuiTagGlobalConstant.TREEGRID_PAGE_SIZE_LIST; // 供用户选择的行数   Y

    // 函数
    private String onLoadSuccess; // 数据加载完成的事件

    private String onLoadError; // 数据加载完成出错的事件

    private String onBeforeLoad;// 加载数据之前触发

    private String onClick; // 单击事件

    private String onDbClick; // 双击事件

    private String onBeforeEdit; // 编辑前事件

    private String onAfterEdit; // 编辑后事件

    private String rowStylerFun; // 行展示方式函数

    private String onContextMenu; // 右键菜单

    private String onBeforeExpand; // 展开前事件

    private String onClickRow;

    private String onDbClickRow;

    private String onCheck;

    private String onUnCheck;

    private String onCheckAll;


    private String onUncheckAll;


    private boolean autoReload = false;

    private boolean stopReload = false;  // 第一次打开页面时，阻止自动刷新（针对一个页面两个treegrid需要同时刷新情况)

    private List<EasyUiTableColumnTag> columns = null;
    //多表头情况
    private TreeMap<String, List<EasyUiTableColumnTag>> columnMap = null;

    private String doEvent;

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

    public String getFrozenColumnTypes() {
        return frozenColumnTypes;
    }

    public void setFrozenColumnTypes(String frozenColumnTypes) {
        this.frozenColumnTypes = frozenColumnTypes;
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

    public String getOnBeforeExpand() {
        return onBeforeExpand;
    }

    public void setOnBeforeExpand(String onBeforeExpand) {
        this.onBeforeExpand = onBeforeExpand;
    }

    public String getFrozenTitles() {
        return frozenTitles;
    }

    public void setFrozenTitles(String frozenTitles) {
        this.frozenTitles = frozenTitles;
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuilder htmlSb = new StringBuilder();

            htmlSb.append("<table id=\"").append(this.getId()).append("\"");
            htmlSb.append(" class=\"sign-treegrid\"");//用于jquery，class定位，勿删
            if (!RegulationUtil.isEmpty(this.getStyle())) {
                htmlSb.append(" style=\"").append(this.getStyle()).append("\"");
            }
            htmlSb.append(">\n</table>");

            htmlSb.append("<script type=\"text/javascript\">\n");
            htmlSb.append("if((isEmpty(unloadTreegridIds) && ").append(this.isStopReload()).append("==false) || (").append(this.isAutoReload()).append("==true)){\n");
            htmlSb.append("  unloadTreegridIds +='" + this.getId() + ",';  \n");
            htmlSb.append("}\n ");

            htmlSb.append(" var ").append(this.getId()).append("URL = '").append(this.getUrl()).append("';\n");
            htmlSb.append("$(function(){\n");
            htmlSb.append(" var t = $('#").append(this.getId()).append("').treegrid({\n");
            htmlSb.append("   nowrap: ").append(this.isNowrap()).append(",\n");
            htmlSb.append("   idField: '").append(this.getIdField()).append("',\n");
            htmlSb.append("   treeField: '").append(this.getTreeField()).append("',\n");
            htmlSb.append("   dataPlain: ").append(this.isDataPlain()).append(",\n");
            htmlSb.append("   parentField: '").append(this.getParentField()).append("',\n");

            htmlSb.append("   collapsible: ").append(this.isCollapsible()).append(",\n");
            htmlSb.append("   pagination: ").append(this.isPagination()).append(",\n");
            htmlSb.append("   pageSize: ").append(this.getPageSize()).append(",\n");
            htmlSb.append("   pageList: ").append(this.getPageSizeList()).append(" ,\n");
            htmlSb.append("   fit: ").append(this.isFit()).append(",\n");
            htmlSb.append("   rownumbers: ").append(this.isRownumbers()).append(",\n");
            htmlSb.append("   fitColumns: ").append(this.isFitColumns()).append(",\n");
            htmlSb.append("   singleSelect: ").append(this.isSingleSelect()).append(",\n");
            htmlSb.append("   selectOnCheck: ").append(this.isSelectOnCheck()).append(",\n");
            htmlSb.append("   checkOnSelect: ").append(this.isCheckOnSelect()).append(",\n");
            htmlSb.append("   loadMsg: '正在加载数据，请稍候...',\n");

            htmlSb.append("   enableHeaderClickMenu:").append(this.isEnableHeaderClickMenu()).append(",\n");
            htmlSb.append("   enableHeaderContextMenu: ").append(this.isEnableHeaderContextMenu()).append(",\n");
            htmlSb.append("   enableRowContextMenu: ").append(this.isEnableRowContextMenu()).append(",\n");
            htmlSb.append("   moveMenu:").append(this.isMoveMenu()).append(",\n");
            htmlSb.append("   dndRow:").append(this.isDndRow()).append(",\n");
            htmlSb.append("   selectOnRowContextMenu: ").append(this.isSelectOnRowContextMenu()).append(",\n");
            htmlSb.append("   rowTooltip: ").append(this.isRowTooltip()).append(",\n");
            // htmlSb.append("   smooth: true").append(",\n");
            if (this.isHeaderContextMenu()) {
                // 表头自定义扩展菜单
                htmlSb.append("   headerContextMenu: [{ text: '表格设置', iconCls: 'icon-hamburg-address', children: [ ");
                //htmlSb.append(" { text: '冻结该列',iconCls:'icon-hamburg-check', disabled: function (e, field) { return t.treegrid('getColumnFields', true).contains(field); },handler: function (e, field) { t.treegrid('freezeColumn', field); }},{text: '取消冻结该列', iconCls:'icon-hamburg-check', disabled: function (e, field) { return t.treegrid('getColumnFields', false).contains(field); },handler: function (e, field) { t.treegrid('unfreezeColumn', field); }},");
                htmlSb.append(" { text: '开启行拖动',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.treegrid('options')['dndRow']; },handler: function () { t.treegrid('enableRowDnd');} },  { text: '关闭行拖动',  iconCls: 'icon-hamburg-check', disabled: function (e, field) { return !t.treegrid('options')['dndRow']; },handler: function () { t.treegrid('disableRowDnd'); } },");
                htmlSb.append(" { text: '开启行浮动提示',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.treegrid('options')['rowTooltip']; },handler: function () {t.treegrid('options')['rowTooltip']=true; t.treegrid('reload'); } },  { text: '关闭行浮动提示',  iconCls: 'icon-hamburg-check', disabled: function (e, field) { return !t.treegrid('options')['rowTooltip']; },handler: function () { t.treegrid('options')['rowTooltip']=false;t.treegrid('reload');  } },");
                htmlSb.append(" { text: '开启斑马线效果',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.treegrid('options')['striped']; },handler: function () {t.treegrid('options')['striped']=true; t.treegrid('reload'); } },  { text: '关闭斑马线效果',  iconCls: 'icon-hamburg-check', disabled: function (e, field) { return !t.treegrid('options')['striped']; },handler: function () { t.treegrid('options')['striped']=false;t.treegrid('reload');  } }  ");//注意结尾无逗号
                //treegrid第一列无checkbox，暂不能开启多行，单行选择切换功能,
                //htmlSb.append(" { text: '开启单行选择',iconCls:'icon-hamburg-check',disabled: function (e, field) { return t.treegrid('options')['singleSelect']; },handler: function () { var fie = t.treegrid('getColumnFields')[0]; var col = t.treegrid('getColumnOption', fie) ; if(col.checkbox){ t.treegrid('hideColumn', fie); t.treegrid('options')['singleSelect']=true; t.treegrid('reload');} } }, ");
                //htmlSb.append(" { text: '开启多行选择',iconCls:'icon-hamburg-check',disabled: function (e, field) { return !t.treegrid('options')['singleSelect']; },handler: function (e, field) {var fie = t.treegrid('getColumnFields')[0]; var col = t.treegrid('getColumnOption', fie) ; if(col.checkbox){ t.treegrid('showColumn', fie); t.treegrid('options')['singleSelect']=false;t.treegrid('reload');  } } }  ");
                htmlSb.append(" ] } ]").append(",\n");
            }

            if (!RegulationUtil.isEmpty(this.getColumnFilter())) {
                htmlSb.append("   columnFilter: ").append(this.getColumnFilter()).append(",\n");
            }
            if (this.isAutoEditing()) {
                htmlSb.append("   autoEditing: ").append(this.isAutoEditing()).append(",\n");
                htmlSb.append("   singleEditing: ").append(this.isSingleEditing()).append(",\n");
            }
            htmlSb.append("   toggleOnClick: ").append(this.isToggleOnClick()).append(",\n");
            htmlSb.append("   onlyNodeExpand: ").append(this.isOnlyNodeExpand()).append(",\n");
            //默认第一次不加载url
//      htmlSb.append("   url: '").append(this.getUrl()).append("'");
            htmlSb.append("   url: ''");
            if (!RegulationUtil.isEmpty(this.getTitle())) {
                htmlSb.append(",\n");
                htmlSb.append("   title: '").append(this.getTitle()).append("'");
            }
            if (!RegulationUtil.isEmpty(this.getIconCls())) {
                htmlSb.append(",\n");
                htmlSb.append("   iconCls: '").append(this.getIconCls()).append("'");
            }
            // 工具栏按钮
            if (!RegulationUtil.isEmpty(this.getToolbarId())) {
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

                        if ("checkbox".equals(this.getFrozenColumnTypes().split(",")[i])) {
                            htmlSb.append(",\n");
                            htmlSb.append("checkbox:'true' \n");
                        }

                    }
                    if (!RegulationUtil.isEmpty(this.getFrozenFormatters()) && !RegulationUtil.isEmpty(this.getFrozenFormatters().split(",")[i])) {
                        htmlSb.append(",\n");
                        htmlSb.append("   formatter:function(value){\n");
                        htmlSb.append("   return '<span style=\"").append(this.getFrozenFormatters().split(",")[i]).append("\">' + value + '</span>'").append(";\n");
                        htmlSb.append("     }\n");
                    }
                    htmlSb.append(i < this.getFrozenFields().split(",").length ? "   }," : "}").append("\n");

                }
                //   htmlSb.append("   }\n");
                htmlSb.append("]] ");
            } else {
                htmlSb.append(",\n");
                htmlSb.append("  frozenColumns: [[{ field: 'ckb', checkbox: true,hidden:true }]]");//开启隐藏的冻结列（用于列冻结/取消冻结动态配置）
            }
            if (!RegulationUtil.isEmpty(this.getRowStylerFun())) {
                htmlSb.append(",\n");
                htmlSb.append("   rowStyler:").append(this.getRowStylerFun());
            }
            if (!RegulationUtil.isEmpty(this.getOnContextMenu())) {
                htmlSb.append(",\n");
                htmlSb.append("   onContextMenu:").append(this.getOnContextMenu());
            }

            if (!RegulationUtil.isEmpty(this.getOnClick())) {
                htmlSb.append(",\n");
                htmlSb.append("   onClick:").append(this.getOnClick());
            }

            if (!RegulationUtil.isEmpty(this.getOnCheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheck:").append(this.getOnCheck());
            }

            if (!RegulationUtil.isEmpty(this.getOnUnCheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheck:").append(this.getOnUnCheck());
            }

            if (!RegulationUtil.isEmpty(this.getOnUncheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheckAll:").append(this.getOnUncheckAll());
            }

            if (!RegulationUtil.isEmpty(this.getOnCheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheckAll:").append(this.getOnCheckAll());
            }

            if (!RegulationUtil.isEmpty(this.getOnDbClickRow())) {
                htmlSb.append(",\n");
                htmlSb.append("   onDblClickRow:").append(this.getOnDbClickRow());
            }

            if (!RegulationUtil.isEmpty(this.getOnClickRow())) {
                htmlSb.append(",\n");
                htmlSb.append("   onClickRow:").append(this.getOnClickRow());
            }
            if (!RegulationUtil.isEmpty(this.getOnDbClickRow())) {
                htmlSb.append(",\n");
                htmlSb.append("   onDbClickRow:").append(this.getOnDbClickRow());
            }

            if (!RegulationUtil.isEmpty(this.getDoEvent())) {//"1:dd();2:d2();"

                String[] str = this.getDoEvent().split(";");
                for (int i = 0; i < str.length; i++) {
                    String[] actionType = str[i].split(":");
                    if (!RegulationUtil.isEmpty(actionType[0]) && actionType[0].equals("onclick")) {//1:单机事件
                        htmlSb.append(",\n");
                        htmlSb.append("   onClick:").append(actionType[1]);
                    }
                    if (!RegulationUtil.isEmpty(actionType[0]) && actionType[0].equals("ondbclick")) {//2：双机事件
                        htmlSb.append(",\n");
                        htmlSb.append("   onDbClick:").append(actionType[1]);
                    }
                }
            }


            if (!RegulationUtil.isEmpty(this.getOnBeforeEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeEdit:").append(this.getOnBeforeEdit());
            }

            if (!RegulationUtil.isEmpty(this.getOnAfterEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onAfterEdit:").append(this.getOnAfterEdit());
            }
            if (!RegulationUtil.isEmpty(this.getOnBeforeExpand())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeExpand:").append(this.getOnBeforeExpand());
            }
            //回调函数必须执行
            htmlSb.append(",onLoadSuccess: ").append("function(row, data){\n");
            if (this.isCollapsible()) {
                htmlSb.append("$(this).treegrid('collapseAll'); ");
            } else {
                htmlSb.append("$(this).treegrid('expandAll'); ");
            }

            htmlSb.append(" if (!isEmpty(relationObjParamMap) && !isEmpty(relationObjParamMap.get('").append(this.getId()).append("NodeId'))) {\n");
            htmlSb.append("     $(this).treegrid(\"select\",relationObjParamMap.get('").append(this.getId()).append("NodeId'));\n");
            htmlSb.append(" }\n");
            if (!RegulationUtil.isEmpty(this.getOnLoadSuccess())) {
                for (int i = 0; i < this.getOnLoadSuccess().split(",").length; i++) {
                    htmlSb.append(this.getOnLoadSuccess().split(",")[i]).append("(row);\n");
                }
            }

            htmlSb.append("unloadTreegridIds = unloadTreegridIds.replace('" + this.getId() + ",','');\n");
            //不知道为什么要加 ，暂时屏蔽
//      htmlSb.append("$(this).treegrid('options').url ='" + this.getUrl() + "';\n");


            htmlSb.append(" }\n");

            if (!RegulationUtil.isEmpty(this.getOnLoadError())) {
                htmlSb.append(",\n");
                htmlSb.append("   onLoadError:").append(this.getOnLoadError()).append("(row);\n");
            }

//      htmlSb.append("onLoadSuccess:function(row, data) {\n");
//      htmlSb.append(" if (!isEmpty(relationObjParamMap) && !isEmpty(relationObjParamMap.get('").append(this.getId()).append("NodeId'))) {\n");
//      htmlSb.append("     $(this).treegrid(\"select\",relationObjParamMap.get('").append(this.getId()).append("NodeId'));\n");
//      htmlSb.append(" }\n");
//      if (!RegulationUtil.isEmpty(this.getOnLoadSuccess())) {
//        for (int i = 0; i < this.getOnLoadSuccess().split(",").length; i++) {
//          htmlSb.append(this.getOnLoadSuccess().split(",")[i]).append("(row);\n");
//        }
//      }
//
//      htmlSb.append("unloadTreegridIds = unloadTreegridIds.replace('" + this.getId() + ",','');\n");
//      htmlSb.append("$(this).treegrid('options').url ='" + this.getUrl() + "';\n");
//      htmlSb.append("}\n");
//      if (!RegulationUtil.isEmpty(this.getOnLoadSuccess())) {
//        htmlSb.append(",\n");
//        htmlSb.append("   onLoadSuccess:").append(this.getOnLoadSuccess());
//      }
            if (!RegulationUtil.isEmpty(this.getOnBeforeLoad())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeLoad:").append(this.getOnBeforeLoad());
            }

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
            htmlSb.append(" });\n");  //function end
            htmlSb.append("</script>\n");


            //初始化变量：理论上来说，不应该存在！！！！！！！！！！
            this.columns = null;
            this.columnMap = null;
            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
//    try {
//      pageContext.getOut().write("</tr></thead>\n</table>");
//    } catch (IOException e) {
//      log.error(e.getMessage(), e);
//    }

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
