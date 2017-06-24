package com.zen.easyui.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sys_user表主键
     */
    private String sysUserId;
    /**
     * sys_role表主键
     */
    private String sysRoleId;

}