package com.zen.easyui.tag;

import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class EasyUiTableColumnTag extends TagSupport implements Cloneable {

    private static final long serialVersionUID = -4308552552551957488L;

    private String field; // 字段值

    private String title; // 标题

    private String titleKey; // 对应的文字key

    private String width; // 宽度

    private String formatter; // 格式化函数

    private String align; // 对其方式

    private String editor; // 编辑方式

    private boolean hidden; // 是否隐藏

    private boolean checkbox = false; // 是否显示Checkbox

    private boolean sortable = false; // 是否可以排序

    private String styler; // 列的样式 可自行定义方法

    private int rowspan; // 跨几行(多表头）

    private int colspan; // 跨几列(多表头）

    @Override
    public int doEndTag() throws JspException {
        try {
            Tag parentTag = getParent();
            //存在parentTag為EasyUiTreegridTableTag 時 存在BUG
            while (!RegulationUtil.isEmpty(parentTag) && !(parentTag instanceof EasyUiDatagridTableTag) && !(parentTag instanceof EasyUiTreegridTableTag)) {
                parentTag = parentTag.getParent();
                /** 解决以下写法BUG:CZ
                 eu:column parentTag 为：c:forEach
                 <c:forEach var="bean"   items="${requestScope.columnList }" >
                 <eu:column  field="${bean.columnName }"  title="${bean.columnTitle }"  width="${bean.width }"
                 align="${bean.align }" hidden="${bean.ishidden == '1'}"  formatter="${bean.formatter}" />
                 </c:forEach>
                 **/
            }

            if (!RegulationUtil.isEmpty(parentTag)) {
                if (parentTag instanceof EasyUiDatagridTableTag) {
                    EasyUiDatagridTableTag datagridTag = (EasyUiDatagridTableTag) parentTag;
                    datagridTag.addColumn((EasyUiTableColumnTag) this.clone());
                } else if (parentTag instanceof EasyUiTreegridTableTag) {
                    EasyUiTreegridTableTag treegridTag = (EasyUiTreegridTableTag) parentTag;
                    treegridTag.addColumn((EasyUiTableColumnTag) this.clone());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);

        }

        return SKIP_BODY;
    }

}
