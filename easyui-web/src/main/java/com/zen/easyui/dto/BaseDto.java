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

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 当前页
     */
    private Integer pageNumber;

    /**
     * 每页显示多少行
     */
    private Integer rows;

    /**
     * 每页显示多少行
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String sort;

    /**
     * 升序或者降序(asc,desc)
     */
    private String order;

}
