package com.zen.easyui.tag;

import com.zen.easyui.constant.GlobalConstant;
import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 针对Ext的TreeGrid的封装 (解决easyui性能慢)
 */
public class ExtTreeGridTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private String id; // TreeGrid标识

    private String title; // TreeGrid标题

    private String url; // json数据请求路径

    private String idField; // TreeGrid主键

    private String width; // TreeGrid面板宽度

    private String height; // TreeGrid面板高度

    private boolean fit = true; // 表格自适应

    private boolean fitColumns = true; // 列自适应


    private boolean useArrows = true;// 使用箭头折叠效果

    private boolean collapsible = false;// 面板默认折叠

    private boolean bufferedrenderer = true;// 是否开启超大数据缓存（界面显示数据超过1000条，建议开启）

    private boolean pagination = false; // 启用分页(暂不适用)

    private String toolbarId; // 工具栏

    private String onClickRow;

    private String onDbClickRow;

    private int pageSize = GlobalConstant.TREEGRID_PAGE_SIZE; // 每页显示几行

    private boolean dndRow = GlobalConstant.TREEGRID_DND_ROW; // 开启此表格的行拖动排序功能

    private boolean enableTextSelection = true;//单元格文字是否可选中

    private List<ExtTreeGridColumnTag> columns = null;

    @Override
    public int doStartTag() throws JspException {

        try {
            StringBuilder htmlSb = new StringBuilder();
            htmlSb.append("<script type=\"text/javascript\">\n");
            // 请求JS
            htmlSb.append(" Ext.require([ 'Ext.data.*', 'Ext.grid.*', 'Ext.tip.*', 'Ext.tree.*' ]);   \n");// 引用定义
            // 初始化
            htmlSb.append("Ext.onReady(function() {   \n"); // 参数定义


            htmlSb.append("Ext.tip.QuickTipManager.init();   \n");
            htmlSb.append("var ").append(this.getId()).append("Store = ").append("Ext.create('Ext.data.TreeStore', {   \n");
            htmlSb.append("model : '").append(this.getId()).append("TreeGridModel',   \n");
            htmlSb.append("proxy : {   \n");
            htmlSb.append("type : 'ajax',   \n");
            htmlSb.append("reader : 'json',   \n");
            htmlSb.append("timeout : 60000*5,   \n");// 请求时长
            htmlSb.append("url : '").append(this.getUrl()).append("'   \n");
            htmlSb.append("},   \n");
            htmlSb.append("lazyFill : false   \n");
            htmlSb.append("});   \n");
            htmlSb.append("var ").append(this.getId()).append("Panel=").append("Ext.create('Ext.tree.Panel', {   \n");
            htmlSb.append("id:'").append(this.getId()).append("',   \n");
            htmlSb.append("viewConfig:{ \n");
            htmlSb.append("enableTextSelection:").append(isEnableTextSelection()).append(" \n");
            htmlSb.append("},   \n");
            if (!TriRegulation.isEmpty(this.getWidth())) {
                htmlSb.append("width: ").append(this.getWidth()).append(",  \n");
            }
            if (!TriRegulation.isEmpty(this.getHeight())) {
                htmlSb.append("height: ").append(this.getHeight()).append(",  \n");
            } else {
                htmlSb.append("height : getLayoutHeight(),  \n");
            }

            if (this.isFitColumns()) {
                htmlSb.append("forceFit:true, \n");
            }
            htmlSb.append(" region:'center', \n");
            htmlSb.append(" layout:'fit', \n");

            if (this.isFit()) {
                htmlSb.append("autoHeight:true, \n");
            }

            //htmlSb.append("width: Ext.get(\"content\").getWidth(),  \n");
            //htmlSb.append("height: 700,  \n");

            if (!TriRegulation.isEmpty(this.getToolbarId())) {
                htmlSb.append("tbar :  ").append(this.getToolbarId()).append(",  \n");
            }

            if (this.isPagination()) {
                htmlSb.append("bbar :  new Ext.PagingToolbar({pageSize: ").append(pageSize)

                        .append(",").append(this.getId()).append("Store = ").append(": store,displayInfo: true,displayMsg: \"当前 {0} - {1} 行, 一共 {2} 行\",emptyMsg: \"没有数据\"}),  \n");
            }
            if (this.isDndRow()) {
                htmlSb.append("enableColumnMove :  ").append(this.isDndRow()).append(",  \n");
            }
            htmlSb.append("bodyStyle:'height:100%;width:100%',   \n");
            if (!TriRegulation.isEmpty(this.getTitle())) {
                htmlSb.append("title : '").append(this.getTitle()).append("',   \n");// 面板标题
            }
            htmlSb.append("renderTo : '").append(this.getId()).append("DIV',   \n");// TreeGrid标识id
            htmlSb.append("reserveScrollbar : true,   \n");
            htmlSb.append("collapsible : ").append(this.isCollapsible()).append(",   \n");// 面板是否可折叠
            htmlSb.append("loadMask : true,   \n");
            htmlSb.append("useArrows : ").append(this.isUseArrows()).append(",   \n");// 折叠图标使用箭头
            htmlSb.append("rootVisible : false,   \n");//
            htmlSb.append("border  : false,   \n");//
            htmlSb.append("bodyBorder  : false,   \n");//
            htmlSb.append("columnLines  : true,   \n"); //列线
            htmlSb.append("rowLines  : true,   \n");//行线
            //htmlSb.append("enableDD: true,  \n");//是否可拖动
            htmlSb.append("containerScroll: true,   \n");
            htmlSb.append("autoScroll: true,   \n");
            htmlSb.append("anchor:'100% 100%',   \n");


            htmlSb.append("forceFit : false,   \n");//水平滚动是否关闭

            htmlSb.append("frame  : false,   \n");
            htmlSb.append("store : ").append(this.getId()).append("Store,\n ");
            htmlSb.append("animate : false,   \n");
            if (this.isBufferedrenderer()) {
                htmlSb.append("plugins: [{ ptype : 'bufferedrenderer' }],   \n");
            }

            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

  /*
   * @Override public int doEndTag() throws JspException {
   * 
   * try {
   * 
   * } catch (IOException e) { log.error(e.getMessage(), e); }
   * 
   * return EVAL_PAGE; }
   */

    @Override
    public int doEndTag() throws JspException {

        try {
            StringBuilder htmlSb = new StringBuilder();
            // 构造行对象清单
            htmlSb.append("columns:[\n");

            if (!TriRegulation.isEmpty(this.getColumns())) {

                for (Iterator columnIter = this.getColumns().iterator(); columnIter
                        .hasNext(); ) {
                    ExtTreeGridColumnTag column = (ExtTreeGridColumnTag) columnIter
                            .next();

                    htmlSb.append(this.getColumnHtml(column));
                    if (columnIter.hasNext()) {
                        htmlSb.append(",\n");
                    }
                } //columns 结尾
            }
            htmlSb.append("]\n"); // columns 结尾
            htmlSb.append(" });\n"); // create end

            //======================= 事件追加 begin ===========================================
            if (!TriRegulation.isEmpty(this.getOnClickRow())) {
                // 单击行事件
                htmlSb.append(" ").append(this.getId()).append("Panel.addListener('itemclick', function(view,record,item,index,e){  ");
                htmlSb.append(this.getOnClickRow()).append("(record.raw,index);");
                htmlSb.append("});");
            }

            if (!TriRegulation.isEmpty(this.getOnDbClickRow())) {
                // 双击击行事件
                htmlSb.append(" ").append(this.getId()).append("Panel.addListener('itemdblclick', function(view,record,item,index,e){  ");
                htmlSb.append(this.getOnDbClickRow()).append("(record.raw,index);");
                htmlSb.append("});");
            }

            //======================= 事件追加 end ===========================================
            htmlSb.append("})   \n"); // onReady end
            //======================= onReady end ===========================================

            //======================= define begin ===========================================

            htmlSb.append("Ext.define('").append(this.getId()).append("TreeGridModel', {   \n");// 数据模型定义
            htmlSb.append("extend : 'Ext.data.TreeModel',   \n");
            htmlSb.append("idProperty : '").append(this.getIdField())
                    .append("',   \n");// id字段定义
            // htmlSb.append("fields : fields   \n");
            htmlSb.append("fields:[\n");

            if (!TriRegulation.isEmpty(this.getColumns())) {
                // htmlSb.append("[\n");
                for (Iterator columnIter = this.getColumns().iterator(); columnIter
                        .hasNext(); ) {
                    ExtTreeGridColumnTag column = (ExtTreeGridColumnTag) columnIter
                            .next();
                    htmlSb.append(this.getFieldHtml(column));
                    if (columnIter.hasNext()) {
                        htmlSb.append(",\n");
                    }
                }// for end

            }
            htmlSb.append("]\n"); // fields 结尾
            htmlSb.append("})   \n"); // define end
            //======================= define end ===========================================

            this.columns = null;
            // htmlSb.append("});   \n");

            htmlSb.append("</script> \n");
            htmlSb.append("<div id='").append(this.getId())
                    .append("DIV' ></div> \n");

            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    private String getFieldHtml(ExtTreeGridColumnTag column) {
        StringBuilder htmlSb = new StringBuilder();

        htmlSb.append("{   \n");
        htmlSb.append("name : '").append(column.getField()).append("'   \n");
        htmlSb.append("}   \n");

        return htmlSb.toString();
    }

    private String getColumnHtml(ExtTreeGridColumnTag column) {
        StringBuilder htmlSb = new StringBuilder();
        // 数据字段定义
    /*
     * htmlSb.append("fields.push({   \n");
     * htmlSb.append("name : '").append(column.getField()).append("'   \n");
     * htmlSb.append("});   \n");
     * 
     * htmlSb.append("   \n");
     */
        // 显示列定义
        htmlSb.append("{   \n");
        if (column.isLocked()) {
            htmlSb.append("locked  : true,   \n");
        }
        if (column.isTreecolumn()) {// 是否为树折叠字段类型
            htmlSb.append("xtype : 'treecolumn',   \n");
        } else if (!TriRegulation.isEmpty(column.getType())) {
            htmlSb.append("xtype : '").append(column.getType()).append("',   \n");
        }
        if (!TriRegulation.isEmpty(column.getSimplyFormatter())) {// 格式化
            htmlSb.append("format : '").append(column.getSimplyFormatter()).append("',   \n");
        }

        if (!TriRegulation.isEmpty(column.getFormatter())) {// 改变渲染到单元格的值和样式（类似原easyui的farmatter）
            htmlSb.append("renderer : ").append(column.getFormatter()).append(",   \n");
        }

        if (!TriRegulation.isEmpty(column.getWidth())) {
            htmlSb.append("width : ").append(column.getWidth()).append(",   \n");
        }
        if (!TriRegulation.isEmpty(column.getAlign())) {
            htmlSb.append("align : '").append(column.getAlign()).append("',   \n");
        }
        if (!TriRegulation.isEmpty(column.getTdCls())) {
            htmlSb.append("tdCls : '").append(column.getTdCls()).append("',   \n");
        }

        if (!TriRegulation.isEmpty(column.getTitle())) {
            htmlSb.append("text : '").append(column.getTitle()).append("',   \n");
        } else if (!TriRegulation.isEmpty(column.getTitleKey())) {
            String keyStr = MessageUtil.getMessage(pageContext.getRequest(), column.getTitleKey());
            htmlSb.append("text : '").append(!TriRegulation.isEmpty(keyStr) ? keyStr : "").append("',   \n");
        }
        if (column.isHidden()) {// 是否为树折叠字段类型
            htmlSb.append("hidden : ").append(column.isHidden()).append(",   \n");
        }
        // htmlSb.append("flex : 1,   \n");
        if (column.isSortable()) {// 是否可排序
            htmlSb.append("sortable : true,   \n");
        } else {
            htmlSb.append("sortable : false,   \n");
        }


        htmlSb.append("dataIndex : '").append(column.getField()).append("'   \n");
        htmlSb.append("}   \n");

        return htmlSb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    public String getOnClickRow() {
        return onClickRow;
    }

    public void setOnClickRow(String onClickRow) {
        this.onClickRow = onClickRow;
    }

    public String getOnDbClickRow() {
        return onDbClickRow;
    }

    public void setOnDbClickRow(String onDbClickRow) {
        this.onDbClickRow = onDbClickRow;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public boolean isUseArrows() {
        return useArrows;
    }

    public void setUseArrows(boolean useArrows) {
        this.useArrows = useArrows;
    }

    public boolean isBufferedrenderer() {
        return bufferedrenderer;
    }

    public void setBufferedrenderer(boolean bufferedrenderer) {
        this.bufferedrenderer = bufferedrenderer;
    }

    public String getToolbarId() {
        return toolbarId;
    }

    public void setToolbarId(String toolbarId) {
        this.toolbarId = toolbarId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isDndRow() {
        return dndRow;
    }

    public void setDndRow(boolean dndRow) {
        this.dndRow = dndRow;
    }

    public List<ExtTreeGridColumnTag> getColumns() {
        return columns;
    }

    public void setColumns(List<ExtTreeGridColumnTag> columns) {
        this.columns = columns;
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


    public boolean isEnableTextSelection() {
        return enableTextSelection;
    }

    public void setEnableTextSelection(boolean enableTextSelection) {
        this.enableTextSelection = enableTextSelection;
    }

    /**
     * 向columnMap对象中插入一个新行数据
     *
     * @param column
     */
    public synchronized void addColumn(ExtTreeGridColumnTag column) {
        if (TriRegulation.isEmpty(this.getColumns())) {
            columns = new ArrayList<ExtTreeGridColumnTag>();
        }
        this.columns.add(column);
    }

}
