package com.zen.easyui.dao;

import com.zen.easyui.dto.SysUserDto;
import java.util.List;

/**
 * 表sys_user基础CRUD
 * 
 *
 */
public interface SysUserDao {

	/**
	 * 插入一条表 sys_user记录
	 * @param SysUserDto sysUser
	 * @return void
	 */
	void insertSysUserDto(SysUserDto sysUser);

	/**
	 * 批量插入 sys_user记录
	 * @param SysUserDto sysUser
	 * @return void
	 */	
	void batchInsertSysUserDto(List<SysUserDto> list);

	/**
	 * 使用表 sys_user主键更新记录
	 * @param  SysUserDto sysUser
	 * @return 影响的记录数
	 */
	int updateSysUserByPk(SysUserDto sysUser);
	
	/**
	 * 使用表 sys_user主键查询一条记录
	 * @param SysUserDto sysUser
	 * @return SysUserDto
	 */
	SysUserDto getSysUserByPk(SysUserDto sysUser);
	
	/**
	 * 使用表 sys_user主键删除一条记录
	 * @param SysUserDto sysUser
	 * @return SysUserDto sysUser
	 */	
	int deleteSysUserByPk(SysUserDto sysUser);
	
	/**
	 * 使用表 sys_user主键查询列表
	 * @param SysUserDto sysUser
	 * @return SysUserDto
	 */
	List<SysUserDto> listSysUserByDto(SysUserDto sysUser);
	
}
