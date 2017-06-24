package com.zen.easyui.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Ext tree类基础类
 */
@Data
public class ExtTreeNode implements Serializable {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     *
     */
    private String name;

    /**
     * 子节点
     */
    public List<ExtTreeNode> children;

    /**
     * 是否为叶子节点
     */
    public boolean leaf = true;

    /**
     * 节点是否展开
     */
    public boolean expanded = false;

}
