package com.zen.easyui.dto;

import org.apache.commons.lang.builder.*;

import java.io.Serializable;


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

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getSysRoleId() {
        return this.sysRoleId;
    }

    public void setSysResourceId(String sysResourceId) {
        this.sysResourceId = sysResourceId;
    }

    public String getSysResourceId() {
        return this.sysResourceId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object object) {
        if (!(object instanceof SysRoleResourceDto)) {
            return false;
        }
        SysRoleResourceDto rhs = (SysRoleResourceDto) object;
        return EqualsBuilder.reflectionEquals(this, rhs);
    }
}