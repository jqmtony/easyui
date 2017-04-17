package com.zen.easyui.common.web;

import com.zen.easyui.util.TriRegulation;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hexuming
 * 
 */
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	/**
     * 排序
     */
	public String getOrderBy(){
		if(StringUtils.isBlank(sort)){
			return null;
		}
		if(StringUtils.isBlank(order)){
			return this.getDbFieldNameByCamelCase(sort);
		}
		return this.getDbFieldNameByCamelCase(sort)+" "+order;
	}
	/**
	 * 设置分页及排序
	 * 注意：查询语句中不要自己写排序
	 */
	public void startPage(){
		if(this.getPage() > 0 && this.getRows() > 0){
			//分页以及排序设置，注意：如果设置了排序，查询语句中不要自己写排序
			PageHelper.startPage(this.getPage(),this.getRows(),this.getOrderBy());
		}else{
			PageHelper.orderBy(this.getOrderBy());
		}
	}

	/**
	 * 把形如"fieldName"的Java属性转成形如"FIELD_NAME"的数据库字段名格式.
	 *
	 * @param fieldName
	 * @return
	 */
	public String getDbFieldNameByCamelCase(String fieldName) {
		if (TriRegulation.isEmpty(fieldName)) {
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
