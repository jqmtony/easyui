package com.zen.easyui.dto;

import java.util.Date;

public class BaseDto {

    /**
     * 创建人
     */
    private String CreateUser;

    /**
     * 创建时间
     */
    private Date CreateTm;

    /**
     * 修改人
     */
    private String UpdateUser;

    /**
     * 修改时间
     */
    private Date UpdateTm;

    public String getCreateUser() {
        return CreateUser;
    }

    public void setCreateUser(String createUser) {
        CreateUser = createUser;
    }

    public Date getCreateTm() {
        return CreateTm;
    }

    public void setCreateTm(Date createTm) {
        CreateTm = createTm;
    }

    public String getUpdateUser() {
        return UpdateUser;
    }

    public void setUpdateUser(String updateUser) {
        UpdateUser = updateUser;
    }

    public Date getUpdateTm() {
        return UpdateTm;
    }

    public void setUpdateTm(Date updateTm) {
        UpdateTm = updateTm;
    }
}
