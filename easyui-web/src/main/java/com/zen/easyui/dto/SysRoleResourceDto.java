package com.zen.easyui.dto;

import lombok.Data;
import org.apache.commons.lang.builder.*;

import java.io.Serializable;

@Data
public class SysRoleResourceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sys_role表主键
     */
    private String sysRoleId;
    /**
     * sys_resource表主键
     */
    private String sysResourceId;

}