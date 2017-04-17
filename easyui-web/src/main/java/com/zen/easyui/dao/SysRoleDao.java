package com.zen.easyui.dao;

import com.zen.easyui.dto.SysRoleDto;
import java.util.List;

/**
 * 表sys_role基础CRUD
 * 
 *
 */
public interface SysRoleDao {

	/**
	 * 插入一条表 sys_role记录
	 * @param SysRoleDto sysRole
	 * @return void
	 */
	void insertSysRoleDto(SysRoleDto sysRole);

	/**
	 * 批量插入 sys_role记录
	 * @param SysRoleDto sysRole
	 * @return void
	 */	
	void batchInsertSysRoleDto(List<SysRoleDto> list);

	/**
	 * 使用表 sys_role主键更新记录
	 * @param  SysRoleDto sysRole
	 * @return 影响的记录数
	 */
	int updateSysRoleByPk(SysRoleDto sysRole);
	
	/**
	 * 使用表 sys_role主键查询一条记录
	 * @param SysRoleDto sysRole
	 * @return SysRoleDto
	 */
	SysRoleDto getSysRoleByPk(SysRoleDto sysRole);
	
	/**
	 * 使用表 sys_role主键删除一条记录
	 * @param SysRoleDto sysRole
	 * @return SysRoleDto sysRole
	 */	
	int deleteSysRoleByPk(SysRoleDto sysRole);
	
	/**
	 * 使用表 sys_role主键查询列表
	 * @param SysRoleDto sysRole
	 * @return SysRoleDto
	 */
	List<SysRoleDto> listSysRoleByDto(SysRoleDto sysRole);
	
}
