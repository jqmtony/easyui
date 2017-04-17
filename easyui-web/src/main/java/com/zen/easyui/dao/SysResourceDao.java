package com.zen.easyui.dao;

import com.zen.easyui.dto.SysResourceDto;
import java.util.List;

/**
 * 表sys_resource基础CRUD
 * 
 *
 */
public interface SysResourceDao {

	/**
	 * 插入一条表 sys_resource记录
	 * @param SysResourceDto sysResource
	 * @return void
	 */
	void insertSysResourceDto(SysResourceDto sysResource);

	/**
	 * 批量插入 sys_resource记录
	 * @param SysResourceDto sysResource
	 * @return void
	 */	
	void batchInsertSysResourceDto(List<SysResourceDto> list);

	/**
	 * 使用表 sys_resource主键更新记录
	 * @param  SysResourceDto sysResource
	 * @return 影响的记录数
	 */
	int updateSysResourceByPk(SysResourceDto sysResource);
	
	/**
	 * 使用表 sys_resource主键查询一条记录
	 * @param SysResourceDto sysResource
	 * @return SysResourceDto
	 */
	SysResourceDto getSysResourceByPk(SysResourceDto sysResource);
	
	/**
	 * 使用表 sys_resource主键删除一条记录
	 * @param SysResourceDto sysResource
	 * @return SysResourceDto sysResource
	 */	
	int deleteSysResourceByPk(SysResourceDto sysResource);
	
	/**
	 * 使用表 sys_resource主键查询列表
	 * @param SysResourceDto sysResource
	 * @return SysResourceDto
	 */
	List<SysResourceDto> listSysResourceByDto(SysResourceDto sysResource);
	
}
