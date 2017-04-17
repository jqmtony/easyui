package com.zen.easyui.dao;

import com.zen.easyui.dto.SysUserRoleDto;
import java.util.List;

/**
 * 表sys_user_role基础CRUD
 * 
 *
 */
public interface SysUserRoleDao {

	/**
	 * 插入一条表 sys_user_role记录
	 * @param SysUserRoleDto sysUserRole
	 * @return void
	 */
	void insertSysUserRoleDto(SysUserRoleDto sysUserRole);

	/**
	 * 批量插入 sys_user_role记录
	 * @param SysUserRoleDto sysUserRole
	 * @return void
	 */	
	void batchInsertSysUserRoleDto(List<SysUserRoleDto> list);

	/**
	 * 使用表 sys_user_role主键更新记录
	 * @param  SysUserRoleDto sysUserRole
	 * @return 影响的记录数
	 */
	int updateSysUserRoleByPk(SysUserRoleDto sysUserRole);
	
	/**
	 * 使用表 sys_user_role主键查询一条记录
	 * @param SysUserRoleDto sysUserRole
	 * @return SysUserRoleDto
	 */
	SysUserRoleDto getSysUserRoleByPk(SysUserRoleDto sysUserRole);
	
	/**
	 * 使用表 sys_user_role主键删除一条记录
	 * @param SysUserRoleDto sysUserRole
	 * @return SysUserRoleDto sysUserRole
	 */	
	int deleteSysUserRoleByPk(SysUserRoleDto sysUserRole);
	
	/**
	 * 使用表 sys_user_role主键查询列表
	 * @param SysUserRoleDto sysUserRole
	 * @return SysUserRoleDto
	 */
	List<SysUserRoleDto> listSysUserRoleByDto(SysUserRoleDto sysUserRole);
	
}
