package com.zen.easyui.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 数节点对象
 *
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName Tree.java
 * @Date 2017年3月17日 上午9:40:05
 */
@Data
public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = 4665871086212970189L;

    /**
     * 节点ID，对加载远程数据很重要
     */
    private String id;

    /**
     * 父节点ID
     */
    private String pid;

    /**
     * 显示节点文本
     **/
    private String text;

    /**
     * 节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点
     */
    private String state;

    /**
     * 表示该节点是否被选中
     */
    private boolean checked;

    /**
     * 被添加到节点的自定义属性
     */
    private T attributes;

    /**
     * 一个节点数组声明了若干节点
     */
    private List<TreeNode<T>> children;

}
