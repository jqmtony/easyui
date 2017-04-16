package com.zen.easyui.tag;

import com.zen.easyui.constant.GlobalConstant;
import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.*;

public class EasyUiTreegridTableTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // TreeGrid标识

    private String title; // TreeGrid标题

    private String url; // 数据路径

    private String idField; // TreeGrid主键

    private String treeField;// TreeGrid展开字段

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

    private boolean collapsible = GlobalConstant.TREEGRID_COLLAPSIBLE; // 是否折叠所有节点  Y

    private boolean checkOnSelect = true;

    private boolean selectOnCheck = true;

    private boolean headerContextMenu = GlobalConstant.TREEGRID_HEADER_CONTEXT_MENU;// 开启表头字段的菜单功能

    private boolean enableHeaderClickMenu = GlobalConstant.TREEGRID_ENABLE_HEADER_CLICK_MENU;// 开启表头列名称右侧那个箭头形状的鼠标左键点击菜单    Y

    private boolean enableHeaderContextMenu = GlobalConstant.TREEGRID_ENABLE_R_HEADER_CONTEXT_MENU;// 开启表头列名称右键点击菜单   Y

    private boolean enableRowContextMenu = GlobalConstant.TREEGRID_ENABLE_ROW_CONTEXT_MENU;// 开启行右键点击菜单    Y

    private boolean moveMenu = GlobalConstant.TREEGRID_MOVE_MENU; // 开始行右键菜单的移动列功能   Y

    private boolean dndRow = GlobalConstant.TREEGRID_DND_ROW; // 开启此表格的行拖动排序功能    Y

    private boolean selectOnRowContextMenu = GlobalConstant.TREEGRID_SELECT_ON_ROW_CONTEXT_MENU; // 此属性开启当右键点击行时自动选择该行的功能   Y

    private boolean rowTooltip = GlobalConstant.TREEGRID_ROW_TOOLTIP;// 开启行内容浮动提示   Y

    private boolean autoEditing = false; // 该属性启用双击行时自定开启该行的编辑状态

    private boolean singleEditing = false; // 该属性启用treegrid的只允许单行编辑效果

    private String columnFilter = GlobalConstant.TREEGRID_COLUMN_FILTER;// 定义表头过滤器    Y

    private boolean toggleOnClick = GlobalConstant.TREEGRID_TOGGLE_ON_CLICK;// 单击节点自动展开/折叠   Y

    private boolean onlyNodeExpand = GlobalConstant.TREEGRID_ONLY_NODE_EXPAND;// 自动关闭其他节点，只展开当前节点    Y

    private boolean fit = true; // 表格自适应

    private boolean fitColumns = true; // 列自适应

    private boolean rownumbers = GlobalConstant.TREEGRID_ROW_TOOLTIP; // 显示行号    Y

    private boolean singleSelect = true; // 是否只能单选一行

    private String toolbarId; // 工具栏

    private String style; // 自定义样式

    private int pageSize = GlobalConstant.TREEGRID_PAGE_SIZE; // 每页显示几行

    private String pageSizeList = GlobalConstant.TREEGRID_PAGE_SIZE_LIST; // 供用户选择的行数   Y

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
            if (!TriRegulation.isEmpty(this.getStyle())) {
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

            if (!TriRegulation.isEmpty(this.getColumnFilter())) {
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
            if (!TriRegulation.isEmpty(this.getTitle())) {
                htmlSb.append(",\n");
                htmlSb.append("   title: '").append(this.getTitle()).append("'");
            }
            if (!TriRegulation.isEmpty(this.getIconCls())) {
                htmlSb.append(",\n");
                htmlSb.append("   iconCls: '").append(this.getIconCls()).append("'");
            }
            // 工具栏按钮
            if (!TriRegulation.isEmpty(this.getToolbarId())) {
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

                        if ("checkbox".equals(this.getFrozenColumnTypes().split(",")[i])) {
                            htmlSb.append(",\n");
                            htmlSb.append("checkbox:'true' \n");
                        }

                    }
                    if (!TriRegulation.isEmpty(this.getFrozenFormatters()) && !TriRegulation.isEmpty(this.getFrozenFormatters().split(",")[i])) {
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
            if (!TriRegulation.isEmpty(this.getRowStylerFun())) {
                htmlSb.append(",\n");
                htmlSb.append("   rowStyler:").append(this.getRowStylerFun());
            }
            if (!TriRegulation.isEmpty(this.getOnContextMenu())) {
                htmlSb.append(",\n");
                htmlSb.append("   onContextMenu:").append(this.getOnContextMenu());
            }

            if (!TriRegulation.isEmpty(this.getOnClick())) {
                htmlSb.append(",\n");
                htmlSb.append("   onClick:").append(this.getOnClick());
            }

            if (!TriRegulation.isEmpty(this.getOnCheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheck:").append(this.getOnCheck());
            }

            if (!TriRegulation.isEmpty(this.getOnUnCheck())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheck:").append(this.getOnUnCheck());
            }

            if (!TriRegulation.isEmpty(this.getOnUncheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onUncheckAll:").append(this.getOnUncheckAll());
            }

            if (!TriRegulation.isEmpty(this.getOnCheckAll())) {
                htmlSb.append(",\n");
                htmlSb.append("   onCheckAll:").append(this.getOnCheckAll());
            }

            if (!TriRegulation.isEmpty(this.getOnDbClickRow())) {
                htmlSb.append(",\n");
                htmlSb.append("   onDblClickRow:").append(this.getOnDbClickRow());
            }

            if (!TriRegulation.isEmpty(this.getOnClickRow())) {
                htmlSb.append(",\n");
                htmlSb.append("   onClickRow:").append(this.getOnClickRow());
            }
            if (!TriRegulation.isEmpty(this.getOnDbClickRow())) {
                htmlSb.append(",\n");
                htmlSb.append("   onDbClickRow:").append(this.getOnDbClickRow());
            }

            if (!TriRegulation.isEmpty(this.getDoEvent())) {//"1:dd();2:d2();"

                String[] str = this.getDoEvent().split(";");
                for (int i = 0; i < str.length; i++) {
                    String[] actionType = str[i].split(":");
                    if (!TriRegulation.isEmpty(actionType[0]) && actionType[0].equals("onclick")) {//1:单机事件
                        htmlSb.append(",\n");
                        htmlSb.append("   onClick:").append(actionType[1]);
                    }
                    if (!TriRegulation.isEmpty(actionType[0]) && actionType[0].equals("ondbclick")) {//2：双机事件
                        htmlSb.append(",\n");
                        htmlSb.append("   onDbClick:").append(actionType[1]);
                    }
                }
            }


            if (!TriRegulation.isEmpty(this.getOnBeforeEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onBeforeEdit:").append(this.getOnBeforeEdit());
            }

            if (!TriRegulation.isEmpty(this.getOnAfterEdit())) {
                htmlSb.append(",\n");
                htmlSb.append("   onAfterEdit:").append(this.getOnAfterEdit());
            }
            if (!TriRegulation.isEmpty(this.getOnBeforeExpand())) {
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
            if (!TriRegulation.isEmpty(this.getOnLoadSuccess())) {
                for (int i = 0; i < this.getOnLoadSuccess().split(",").length; i++) {
                    htmlSb.append(this.getOnLoadSuccess().split(",")[i]).append("(row);\n");
                }
            }

            htmlSb.append("unloadTreegridIds = unloadTreegridIds.replace('" + this.getId() + ",','');\n");
            //不知道为什么要加 ，暂时屏蔽
//      htmlSb.append("$(this).treegrid('options').url ='" + this.getUrl() + "';\n");


            htmlSb.append(" }\n");

            if (!TriRegulation.isEmpty(this.getOnLoadError())) {
                htmlSb.append(",\n");
                htmlSb.append("   onLoadError:").append(this.getOnLoadError()).append("(row);\n");
            }

//      htmlSb.append("onLoadSuccess:function(row, data) {\n");
//      htmlSb.append(" if (!isEmpty(relationObjParamMap) && !isEmpty(relationObjParamMap.get('").append(this.getId()).append("NodeId'))) {\n");
//      htmlSb.append("     $(this).treegrid(\"select\",relationObjParamMap.get('").append(this.getId()).append("NodeId'));\n");
//      htmlSb.append(" }\n");
//      if (!TriRegulation.isEmpty(this.getOnLoadSuccess())) {
//        for (int i = 0; i < this.getOnLoadSuccess().split(",").length; i++) {
//          htmlSb.append(this.getOnLoadSuccess().split(",")[i]).append("(row);\n");
//        }
//      }
//
//      htmlSb.append("unloadTreegridIds = unloadTreegridIds.replace('" + this.getId() + ",','');\n");
//      htmlSb.append("$(this).treegrid('options').url ='" + this.getUrl() + "';\n");
//      htmlSb.append("}\n");
//      if (!TriRegulation.isEmpty(this.getOnLoadSuccess())) {
//        htmlSb.append(",\n");
//        htmlSb.append("   onLoadSuccess:").append(this.getOnLoadSuccess());
//      }
            if (!TriRegulation.isEmpty(this.getOnBeforeLoad())) {
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

    public String getOnLoadError() {
        return onLoadError;
    }

    public void setOnLoadError(String onLoadError) {
        this.onLoadError = onLoadError;
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

    public String getOnClickRow() {
        return onClickRow;
    }

    public void setOnClickRow(String onClickRow) {
        this.onClickRow = onClickRow;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getFrozenTitleKey() {
        return frozenTitleKey;
    }

    public void setFrozenTitleKey(String frozenTitleKey) {
        this.frozenTitleKey = frozenTitleKey;
    }

    public String getOnDbClick() {
        return onDbClick;
    }

    public void setOnDbClick(String onDbClick) {
        this.onDbClick = onDbClick;
    }

    public String getOnContextMenu() {
        return onContextMenu;
    }

    public void setOnContextMenu(String onContextMenu) {
        this.onContextMenu = onContextMenu;
    }

    public String getRowStylerFun() {
        return rowStylerFun;
    }

    public void setRowStylerFun(String rowStylerFun) {
        this.rowStylerFun = rowStylerFun;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getFrozenTitle() {
        return frozenTitle;
    }

    public void setFrozenTitle(String frozenTitle) {
        this.frozenTitle = frozenTitle;
    }

    public boolean isNowrap() {
        return nowrap;
    }

    public void setNowrap(boolean nowrap) {
        this.nowrap = nowrap;
    }

    public int getFrozenWidth() {
        return frozenWidth;
    }

    public void setFrozenWidth(int frozenWidth) {
        this.frozenWidth = frozenWidth;
    }


    public String getFrozenFields() {
        return frozenFields;
    }

    public void setFrozenFields(String frozenFields) {
        this.frozenFields = frozenFields;
    }

    public String getFrozenFormatter() {
        return frozenFormatter;
    }

    public void setFrozenFormatter(String frozenFormatter) {
        this.frozenFormatter = frozenFormatter;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTreeField() {
        return treeField;
    }

    public void setTreeField(String treeField) {
        this.treeField = treeField;
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    public String getId() {
        return id;
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

    public String getFrozenEditor() {
        return frozenEditor;
    }

    public void setFrozenEditor(String frozenEditor) {
        this.frozenEditor = frozenEditor;
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

    public String getOnLoadSuccess() {
        return onLoadSuccess;
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
        this.onLoadSuccess = onLoadSuccess;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getOnCheck() {
        return onCheck;
    }

    public void setOnCheck(String onCheck) {
        this.onCheck = onCheck;
    }

    public String getOnBeforeLoad() {
        return onBeforeLoad;
    }

    public void setOnBeforeLoad(String onBeforeLoad) {
        this.onBeforeLoad = onBeforeLoad;
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

    public boolean isToggleOnClick() {
        return toggleOnClick;
    }

    public void setToggleOnClick(boolean toggleOnClick) {
        this.toggleOnClick = toggleOnClick;
    }

    public boolean isOnlyNodeExpand() {
        return onlyNodeExpand;
    }

    public void setOnlyNodeExpand(boolean onlyNodeExpand) {
        this.onlyNodeExpand = onlyNodeExpand;
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

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isStopReload() {
        return stopReload;
    }

    public void setStopReload(boolean stopReload) {
        this.stopReload = stopReload;
    }

    public List<EasyUiTableColumnTag> getColumns() {
        return columns;
    }

    public void setColumns(List<EasyUiTableColumnTag> columns) {
        this.columns = columns;
    }

    public TreeMap<String, List<EasyUiTableColumnTag>> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(TreeMap<String, List<EasyUiTableColumnTag>> columnMap) {
        this.columnMap = columnMap;
    }

    public String getOnUnCheck() {
        return onUnCheck;
    }

    public void setOnUnCheck(String onUnCheck) {
        this.onUnCheck = onUnCheck;
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
}
