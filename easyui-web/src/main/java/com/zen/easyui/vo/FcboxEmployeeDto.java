package com.zen.easyui.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;


public class FcboxEmployeeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工号
     */
    private String empCode;
    /**
     * 姓名
     */
    private String empName;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 组织
     */
    private String organization;
    /**
     * 岗位ID
     */
    private String dutyId;
    /**
     * 岗位名称
     */
    private String dutyName;
    /**
     * 人员类别
     */
    private String empType;
    /**
     * 手机号码
     */
    private String empMobile;
    /**
     * 员工属性
     */
    private String empAttribute;
    /**
     * 是否总部
     */
    private Long isHeadquarters;
    /**
     * 是否领导
     */
    private Long isLeader;
    /**
     * 是否离职
     */
    private Long empStus;
    /**
     * 是否内部职工 1.是 0.否
     */
    private Long innerFlg;
    /**
     * 创建时间
     */
    private Date creattime;

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpCode() {
        return this.empCode;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyId() {
        return this.dutyId;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyName() {
        return this.dutyName;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmpType() {
        return this.empType;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpMobile() {
        return this.empMobile;
    }

    public void setEmpAttribute(String empAttribute) {
        this.empAttribute = empAttribute;
    }

    public String getEmpAttribute() {
        return this.empAttribute;
    }

    public void setIsHeadquarters(Long isHeadquarters) {
        this.isHeadquarters = isHeadquarters;
    }

    public Long getIsHeadquarters() {
        return this.isHeadquarters;
    }

    public void setIsLeader(Long isLeader) {
        this.isLeader = isLeader;
    }

    public Long getIsLeader() {
        return this.isLeader;
    }

    public void setEmpStus(Long empStus) {
        this.empStus = empStus;
    }

    public Long getEmpStus() {
        return this.empStus;
    }

    public void setInnerFlg(Long innerFlg) {
        this.innerFlg = innerFlg;
    }

    public Long getInnerFlg() {
        return this.innerFlg;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Date getCreattime() {
        return this.creattime;
    }


    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object object) {
        if (!(object instanceof FcboxEmployeeDto)) {
            return false;
        }
        FcboxEmployeeDto rhs = (FcboxEmployeeDto) object;
        return EqualsBuilder.reflectionEquals(this, rhs);
    }
}