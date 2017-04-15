package com.zen.easyui.common;

import java.io.Serializable;
import java.util.List;

/**
 * 数节点对象
 * @ClassName Tree.java
 * @author 000538 E-mail: jinjing@zen.com
 * @Date 2017年3月17日 上午9:40:05
 * @version 1.0.0
 */
public class TreeNode<T> implements Serializable{

    private static final long serialVersionUID = 4665871086212970189L;

    /**节点ID，对加载远程数据很重要*/
    private String id;

    /**父节点ID*/
    private String pid;

    /**显示节点文本**/
    private String text;

    /**节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点*/
    private String state;

    /**表示该节点是否被选中*/
    private boolean checked;

    /**被添加到节点的自定义属性*/
    private T attributes;

    /**一个节点数组声明了若干节点*/
    private List<TreeNode<T>> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public T getAttributes() {
        return attributes;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }
}
