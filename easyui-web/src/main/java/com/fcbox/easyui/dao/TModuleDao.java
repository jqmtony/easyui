package com.fcbox.easyui.dao;

import com.fcbox.easyui.vo.TModuleDto;

import java.util.List;
/**
 * 表t_module基础CRUD
 * 
 *
 */
public interface TModuleDao {

	/**
	 * 插入一条表 t_module记录
	 * @param TModuleDto tModule
	 * @return void
	 */
	public void insertTModuleDto(TModuleDto tModule);

	/**
	 * 批量插入 t_module记录
	 * @param TModuleDto tModule
	 * @return void
	 */	
	public void batchInsertTModuleDto(List<TModuleDto> list);

	/**
	 * 使用表 t_module主键更新记录
	 * @param  TModuleDto tModule
	 * @return 影响的记录数
	 */
	public int updateTModuleByPk(TModuleDto tModule);
	
	/**
	 * 使用表 t_module主键查询一条记录
	 * @param TModuleDto tModule
	 * @return TModuleDto
	 */
	public TModuleDto selectTModuleByPk(TModuleDto tModule);
	
	/**
	 * 使用表 t_module主键删除一条记录
	 * @param TModuleDto tModule
	 * @return TModuleDto tModule
	 */	
	public int deleteTModuleByPk(TModuleDto tModule);
	
	/**
	 * 使用表 t_module主键查询列表
	 * @param TModuleDto tModule
	 * @return TModuleDto
	 */
	public List<TModuleDto> selectTModuleByDto(TModuleDto tModule);
	
}
