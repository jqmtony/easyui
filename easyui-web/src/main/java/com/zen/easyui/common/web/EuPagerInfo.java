package com.zen.easyui.common.web;

import com.github.pagehelper.PageHelper;
import com.zen.easyui.util.RegulationUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 *
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName EuPagerInfo.java
 * @Date 2017年3月17日 上午9:40:32
 */
@Data
public class EuPagerInfo implements Serializable {

    private static final long serialVersionUID = -6294244855454374072L;

    private int page; // 当前页

    private int rows; // 每页显示多少行

    private String sort; // 排序列

    private String order; // 升序或者降序

    public EuPagerInfo() {
        super();
    }

    public EuPagerInfo(int page, int rows) {
        super();
        this.page = page;
        this.rows = rows;
    }

    /**
     * 排序
     */
    public String getOrderBy() {
        if (StringUtils.isBlank(sort)) {
            return null;
        }
        if (StringUtils.isBlank(order)) {
            return this.getDbFieldNameByCamelCase(sort);
        }
        return this.getDbFieldNameByCamelCase(sort) + " " + order;
    }

    /**
     * 设置分页及排序
     * 注意：查询语句中不要自己写排序
     */
    public void startPage() {
        if (this.getPage() > 0 && this.getRows() > 0) {
            //分页以及排序设置，注意：如果设置了排序，查询语句中不要自己写排序
            PageHelper.startPage(this.getPage(), this.getRows(), this.getOrderBy());
        } else {
            PageHelper.orderBy(this.getOrderBy());
        }
    }

    /**
     * 把形如"fieldName"的Java属性转成形如"FIELD_NAME"的数据库字段名格式.
     *
     * @param fieldName
     * @return
     */
    private String getDbFieldNameByCamelCase(String fieldName) {
        if (RegulationUtil.isEmpty(fieldName)) {
            return "";
        } else {
            List<String> list = new ArrayList<String>();
            StringBuffer buf = new StringBuffer();
            for (char c : fieldName.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    list.add(buf.toString().toUpperCase());
                    buf = new StringBuffer();
                }
                buf.append(c);
            }
            list.add(buf.toString().toUpperCase());

            return StringUtils.join(list, '_');
        }

    }

}
