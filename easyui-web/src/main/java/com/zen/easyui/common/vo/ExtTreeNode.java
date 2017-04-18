package com.zen.easyui.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Ext tree类基础类
 *
 */
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

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExtTreeNode other = (ExtTreeNode) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
