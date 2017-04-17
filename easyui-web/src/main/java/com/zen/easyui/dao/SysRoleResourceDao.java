package com.zen.easyui.dao;

import com.zen.easyui.dto.SysRoleResourceDto;
import java.util.List;

/**
 * 表sys_role_resource基础CRUD
 * 
 *
 */
public interface SysRoleResourceDao {

	/**
	 * 插入一条表 sys_role_resource记录
	 * @param SysRoleResourceDto sysRoleResource
	 * @return void
	 */
	void insertSysRoleResourceDto(SysRoleResourceDto sysRoleResource);

	/**
	 * 批量插入 sys_role_resource记录
	 * @param SysRoleResourceDto sysRoleResource
	 * @return void
	 */	
	void batchInsertSysRoleResourceDto(List<SysRoleResourceDto> list);

	/**
	 * 使用表 sys_role_resource主键更新记录
	 * @param  SysRoleResourceDto sysRoleResource
	 * @return 影响的记录数
	 */
	int updateSysRoleResourceByPk(SysRoleResourceDto sysRoleResource);
	
	/**
	 * 使用表 sys_role_resource主键查询一条记录
	 * @param SysRoleResourceDto sysRoleResource
	 * @return SysRoleResourceDto
	 */
	SysRoleResourceDto getSysRoleResourceByPk(SysRoleResourceDto sysRoleResource);
	
	/**
	 * 使用表 sys_role_resource主键删除一条记录
	 * @param SysRoleResourceDto sysRoleResource
	 * @return SysRoleResourceDto sysRoleResource
	 */	
	int deleteSysRoleResourceByPk(SysRoleResourceDto sysRoleResource);
	
	/**
	 * 使用表 sys_role_resource主键查询列表
	 * @param SysRoleResourceDto sysRoleResource
	 * @return SysRoleResourceDto
	 */
	List<SysRoleResourceDto> listSysRoleResourceByDto(SysRoleResourceDto sysRoleResource);
	
}
