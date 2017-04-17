package com.zen.easyui.common.util;

import com.zen.easyui.common.vo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具树
 *
 * @author 000538 E-mail: jinjing@fcbox.com
 * @version 1.0.0
 * @ClassName TreeNodeNodeUtil.java
 * @Date 2017年3月17日 上午9:40:32
 */
public class TreeNodeUtil {

    /**
     * 获取父节点
     *
     * @param treeNodeDataList 树节点集合
     * @return List<TreeNode>
     */
    public static <T> List<TreeNode<T>> getRootNodeList(List<TreeNode<T>> treeNodeDataList) {
        List<TreeNode<T>> rootTreeNodeDataList = new ArrayList<TreeNode<T>>();
        for (TreeNode<T> treeNode : treeNodeDataList) {
            String pid = treeNode.getPid();
            if (isRootNode(pid, treeNodeDataList)) {
                // 获取父节点下的子节点
                treeNode.setChildren(getChildrenNodeList(treeNode.getId(), treeNodeDataList));
                treeNode.setState("open");
                rootTreeNodeDataList.add(treeNode);
            }
        }
        return rootTreeNodeDataList;
    }

    /**
     * 是否为根节点
     *
     * @param pid              父id
     * @param treeNodeDataList 树节点集合
     * @return
     */
    private static <T> boolean isRootNode(String pid, List<TreeNode<T>> treeNodeDataList) {
        if (pid == null || "".equals(pid) || "null".equals(pid)) {
            return true;
        }
        for (TreeNode<T> treeNode : treeNodeDataList) {
            if (pid.equals(treeNode.getId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据父id获取资节点
     *
     * @param pid              父id
     * @param treeNodeDataList 树节点集合
     * @return List<TreeNode>
     */
    private static <T> List<TreeNode<T>> getChildrenNodeList(String pid, List<TreeNode<T>> treeNodeDataList) {
        List<TreeNode<T>> sonTreeNodeDataList = new ArrayList<TreeNode<T>>();
        for (TreeNode<T> treeNode : treeNodeDataList) {
            if (treeNode.getPid() == null)
                continue;
            // 这是一个子节点
            if (treeNode.getPid().equals(pid)) {
                // 递归获取子节点下的子节点
                treeNode.setChildren(getChildrenNodeList(treeNode.getId(), treeNodeDataList));
                sonTreeNodeDataList.add(treeNode);
            }
        }
        return sonTreeNodeDataList;
    }
}
