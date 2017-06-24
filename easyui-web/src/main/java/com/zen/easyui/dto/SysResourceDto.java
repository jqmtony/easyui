package com.zen.easyui.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysResourceDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 图标
     */
    private String icon;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型（0:菜单；1:按钮）
     */
    private String type;
    /**
     * 排序
     */
    private Long seq;
    /**
     * 链接
     */
    private String url;
    /**
     * 标识位
     */
    private String flag;
    /**
     * 父ID
     */
    private String pid;
    /**
     * 备注
     */
    private String remark;

}