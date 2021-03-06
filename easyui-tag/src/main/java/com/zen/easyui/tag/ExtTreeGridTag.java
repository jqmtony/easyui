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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class ExtTreeGridTag extends BodyTagSupport {

    private static final long serialVersionUID = 91284553969493878L;

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

    private int pageSize = EasyuiTagGlobalConstant.TREEGRID_PAGE_SIZE; // 每页显示几行

    private boolean dndRow = EasyuiTagGlobalConstant.TREEGRID_DND_ROW; // 开启此表格的行拖动排序功能

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
            if (!RegulationUtil.isEmpty(this.getWidth())) {
                htmlSb.append("width: ").append(this.getWidth()).append(",  \n");
            }
            if (!RegulationUtil.isEmpty(this.getHeight())) {
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

            if (!RegulationUtil.isEmpty(this.getToolbarId())) {
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
            if (!RegulationUtil.isEmpty(this.getTitle())) {
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

            if (!RegulationUtil.isEmpty(this.getColumns())) {

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
            if (!RegulationUtil.isEmpty(this.getOnClickRow())) {
                // 单击行事件
                htmlSb.append(" ").append(this.getId()).append("Panel.addListener('itemclick', function(view,record,item,index,e){  ");
                htmlSb.append(this.getOnClickRow()).append("(record.raw,index);");
                htmlSb.append("});");
            }

            if (!RegulationUtil.isEmpty(this.getOnDbClickRow())) {
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

            if (!RegulationUtil.isEmpty(this.getColumns())) {
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
        } else if (!RegulationUtil.isEmpty(column.getType())) {
            htmlSb.append("xtype : '").append(column.getType()).append("',   \n");
        }
        if (!RegulationUtil.isEmpty(column.getSimplyFormatter())) {// 格式化
            htmlSb.append("format : '").append(column.getSimplyFormatter()).append("',   \n");
        }

        if (!RegulationUtil.isEmpty(column.getFormatter())) {// 改变渲染到单元格的值和样式（类似原easyui的farmatter）
            htmlSb.append("renderer : ").append(column.getFormatter()).append(",   \n");
        }

        if (!RegulationUtil.isEmpty(column.getWidth())) {
            htmlSb.append("width : ").append(column.getWidth()).append(",   \n");
        }
        if (!RegulationUtil.isEmpty(column.getAlign())) {
            htmlSb.append("align : '").append(column.getAlign()).append("',   \n");
        }
        if (!RegulationUtil.isEmpty(column.getTdCls())) {
            htmlSb.append("tdCls : '").append(column.getTdCls()).append("',   \n");
        }

        if (!RegulationUtil.isEmpty(column.getTitle())) {
            htmlSb.append("text : '").append(column.getTitle()).append("',   \n");
        } else if (!RegulationUtil.isEmpty(column.getTitleKey())) {
            String keyStr = MessageUtil.getMessage(pageContext.getRequest(), column.getTitleKey());
            htmlSb.append("text : '").append(!RegulationUtil.isEmpty(keyStr) ? keyStr : "").append("',   \n");
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

    /**
     * 向columnMap对象中插入一个新行数据
     *
     * @param column
     */
    public synchronized void addColumn(ExtTreeGridColumnTag column) {
        if (RegulationUtil.isEmpty(this.getColumns())) {
            columns = new ArrayList<ExtTreeGridColumnTag>();
        }
        this.columns.add(column);
    }

}
