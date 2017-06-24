package com.zen.easyui.dto;

import lombok.Data;

import java.util.Date;

@Data
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

}
