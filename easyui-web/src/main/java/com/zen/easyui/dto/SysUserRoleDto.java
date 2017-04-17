package com.zen.easyui.dto;

import org.apache.commons.lang.builder.*;

import java.io.Serializable;


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

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserId() {
        return this.sysUserId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getSysRoleId() {
        return this.sysRoleId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object object) {
        if (!(object instanceof SysUserRoleDto)) {
            return false;
        }
        SysUserRoleDto rhs = (SysUserRoleDto) object;
        return EqualsBuilder.reflectionEquals(this, rhs);
    }
}