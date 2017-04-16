package com.zen.easyui.tag;

import com.zen.easyui.util.TriRegulation;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ExtTreeGridColumnTag extends TagSupport implements Cloneable {
    private static final long serialVersionUID = -4308552552551957488L;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    private boolean treecolumn = false;//是否为树折叠字段

    private String field; // 字段值

    private String title; // 标题

    private String titleKey;

    private String width; // 宽度

    private String simplyFormatter;// 简易格式化函数 如'Y-m-d H:i:s'

    private String formatter; // 格式化函数

    private String type;//字段类型 datecolumn

    private String align;//设置字段水平对齐 center

    private String tdCls;//设置字段垂直对齐 tdValign

    private boolean sortable = false; // 是否可以排序

    private boolean hidden = false; // 是否隐藏

    private boolean locked = false; // 是否冻结


    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuilder htmlSb = new StringBuilder();


            Tag parentTag = getParent();
            //存在parentTag為ExtTreeGridTag 時 存在BUG
            while (!TriRegulation.isEmpty(parentTag) && !(parentTag instanceof ExtTreeGridTag)) {
                parentTag = parentTag.getParent();
            }

            if (!TriRegulation.isEmpty(parentTag)) {
                if (parentTag instanceof ExtTreeGridTag) {
                    ExtTreeGridTag gridTag = (ExtTreeGridTag) parentTag;
                    gridTag.addColumn((ExtTreeGridColumnTag) this.clone());
                }
            }


            //数据字段定义
/*    htmlSb.append("fields.push({   \n");
    htmlSb.append("name : '").append(this.getField()).append("'   \n");
    htmlSb.append("});   \n");    
    
    htmlSb.append("   \n");*/    
    /*
    //显示列定义
    htmlSb.append("columns.push({   \n");
    if(this.isTreecolumn()){//是否为树折叠字段类型
      htmlSb.append("xtype : 'treecolumn',   \n");
    }else if(!TriRegulation.isEmpty(this.getType())){
      htmlSb.append("xtype : '").append(this.getType()).append("',   \n");
    }
    if(!TriRegulation.isEmpty(this.getSimplyFormatter())){//格式化
      htmlSb.append("format : '").append(this.getSimplyFormatter()).append("',   \n");
    }
    
    if(!TriRegulation.isEmpty(this.getFormatter())){//改变渲染到单元格的值和样式（类似原easyui的farmatter）
      htmlSb.append("renderer : ").append(this.getFormatter()).append(",   \n");
    }
    
    
    if(!TriRegulation.isEmpty(this.getWidth())){
      htmlSb.append("width : ").append(this.getWidth()).append(",   \n");
    }
    if(!TriRegulation.isEmpty(this.getTitle())){
      htmlSb.append("text : '").append(this.getTitle()).append("',   \n");
    }else if(!TriRegulation.isEmpty(this.getTitleKey())){          
      String keyStr = MessageUtil.getMessage(pageContext.getRequest(), this.getTitleKey());              
      htmlSb.append("text : '").append(!TriRegulation.isEmpty(keyStr) ? keyStr : "").append("',   \n");
    }
    if(this.isHidden()){//是否为树折叠字段类型
      htmlSb.append("hidden : ").append(this.isHidden()).append(",   \n");
    }
    //htmlSb.append("flex : 1,   \n");
    if(this.isSortable()){//是否可排序
      htmlSb.append("sortable : true,   \n");
    }else{
      htmlSb.append("sortable : false,   \n");
    }
    htmlSb.append("dataIndex : '").append(this.getField()).append("'   \n");
    htmlSb.append("});   \n");*/


            pageContext.getOut().write(htmlSb.toString());
        } catch (IOException | CloneNotSupportedException e) {
            log.error(e.getMessage(), e);
        }

        return EVAL_BODY_INCLUDE;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }


    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }


    public boolean isTreecolumn() {
        return treecolumn;
    }

    public void setTreecolumn(boolean treecolumn) {
        this.treecolumn = treecolumn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getSimplyFormatter() {
        return simplyFormatter;
    }

    public void setSimplyFormatter(String simplyFormatter) {
        this.simplyFormatter = simplyFormatter;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getTdCls() {
        return tdCls;
    }

    public void setTdCls(String tdCls) {
        this.tdCls = tdCls;
    }


}
