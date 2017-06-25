package com.zen.easyui.service;

import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dto.SysUserDto;

/**
 * 用户管理
 *
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName ISysUserService.java
 * @Date 2017/4/17 15:53
 */
public interface ISysUserService {

    /**
     * 新增用户
     *
     * @param userDto 用户信息实体
     */
    void addUser(SysUserDto userDto);

    /**
     * 删除用户
     *
     * @param userDto 用户信息实体
     */
    void deleteUserByPk(SysUserDto userDto);

    /**
     * 修改用户
     *
     * @param userDto 用户信息实体
     */
    void updateUserByPk(SysUserDto userDto);

    /**
     * 获取用户
     *
     * @param userDto 用户信息实体
     */
    SysUserDto getUserByPk(SysUserDto userDto);

    /**
     * 分页获取用户
     *
     * @param userDto   用户信息实体
     */
    PageLister<SysUserDto> listUserByPage(SysUserDto userDto);
}
