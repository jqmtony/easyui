package com.zen.easyui.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class TModuleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块id
     */
    private Long moduleId;
    /**
     * 父模块id
     */
    private String parentCode;
    /**
     * 名称
     */
    private String moduleName;
    /**
     * 代码
     */
    private String moduleCode;
    /**
     * 描述
     */
    private String moduleDesc;
    /**
     * 图标
     */
    private String moduleIcon;
    /**
     * 类型 0 菜单目录  1功能
     */
    private Long moduleType;
    /**
     * 所属子系统 1.web 2.gui
     */
    private Long appType;
    /**
     * 请求链接
     */
    private String actionUrl;
    /**
     * 排序序号
     */
    private Long sort;
    /**
     * 帮助链接
     */
    private String helpUrl;
    /**
     * 0手动创建，1代码自动生成
     */
    private Long autoCreate;

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getModuleId() {
        return this.moduleId;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public String getModuleDesc() {
        return this.moduleDesc;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    public String getModuleIcon() {
        return this.moduleIcon;
    }

    public void setModuleType(Long moduleType) {
        this.moduleType = moduleType;
    }

    public Long getModuleType() {
        return this.moduleType;
    }

    public void setAppType(Long appType) {
        this.appType = appType;
    }

    public Long getAppType() {
        return this.appType;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getActionUrl() {
        return this.actionUrl;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return this.sort;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public String getHelpUrl() {
        return this.helpUrl;
    }

    public void setAutoCreate(Long autoCreate) {
        this.autoCreate = autoCreate;
    }

    public Long getAutoCreate() {
        return this.autoCreate;
    }


    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object object) {
        if (!(object instanceof TModuleDto)) {
            return false;
        }
        TModuleDto rhs = (TModuleDto) object;
        return EqualsBuilder.reflectionEquals(this, rhs);
    }
}