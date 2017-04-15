package com.zen.easyui.dao;

import com.zen.easyui.vo.FcboxEmployeeDto;

import java.util.List;
/**
 * 表fcbox_employee基础CRUD
 * 
 *
 */
public interface FcboxEmployeeDao {

	/**
	 * 插入一条表 fcbox_employee记录
	 * @param FcboxEmployeeDto fcboxEmployee
	 * @return void
	 */
	public void insertFcboxEmployeeDto(FcboxEmployeeDto fcboxEmployee);

	/**
	 * 批量插入 fcbox_employee记录
	 * @param FcboxEmployeeDto fcboxEmployee
	 * @return void
	 */	
	public void batchInsertFcboxEmployeeDto(List<FcboxEmployeeDto> list);

	/**
	 * 使用表 fcbox_employee主键更新记录
	 * @param  FcboxEmployeeDto fcboxEmployee
	 * @return 影响的记录数
	 */
	public int updateFcboxEmployeeByPk(FcboxEmployeeDto fcboxEmployee);
	
	/**
	 * 使用表 fcbox_employee主键查询一条记录
	 * @param FcboxEmployeeDto fcboxEmployee
	 * @return FcboxEmployeeDto
	 */
	public FcboxEmployeeDto selectFcboxEmployeeByPk(FcboxEmployeeDto fcboxEmployee);
	
	/**
	 * 使用表 fcbox_employee主键删除一条记录
	 * @param FcboxEmployeeDto fcboxEmployee
	 * @return FcboxEmployeeDto fcboxEmployee
	 */	
	public int deleteFcboxEmployeeByPk(FcboxEmployeeDto fcboxEmployee);
	
	/**
	 * 使用表 fcbox_employee主键查询列表
	 * @param FcboxEmployeeDto fcboxEmployee
	 * @return FcboxEmployeeDto
	 */
	public List<FcboxEmployeeDto> selectFcboxEmployeeByDto(FcboxEmployeeDto fcboxEmployee);
	
}
