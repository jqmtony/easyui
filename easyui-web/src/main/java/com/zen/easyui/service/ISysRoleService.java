package com.zen.easyui.service;

import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dto.SysRoleDto;

/**
 * 角色管理
 *
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName ISysRoleService.java
 * @Date 2017/4/17 15:53
 */
public interface ISysRoleService {

    /**
     * 新增角色
     *
     * @param roleDto 角色信息实体
     */
    void addRole(SysRoleDto roleDto);

    /**
     * 删除角色
     *
     * @param roleDto 角色信息实体
     */
    void deleteRoleByPk(SysRoleDto roleDto);

    /**
     * 修改角色
     *
     * @param roleDto 角色信息实体
     */
    void updateRoleByPk(SysRoleDto roleDto);

    /**
     * 获取角色
     *
     * @param roleDto 角色信息实体
     */
    SysRoleDto getRoleByPk(SysRoleDto roleDto);

    /**
     * 分页获取角色
     *
     * @param roleDto   角色信息实体
     */
    PageLister<SysRoleDto> listRoleByPage(SysRoleDto roleDto);
}
