package com.zen.easyui.common.util;

import com.zen.easyui.common.vo.ExtTreeNode;
import com.zen.easyui.util.TriRegulation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ExtUtils {

    /**
     * ext构建节点树结构 (查询结果要求先经过排序，提高效率)
     *
     * @param <ExtTreeNode>
     * @param nodes
     * @param parentId
     * @return
     */
    public static List<ExtTreeNode> getTreeList(List<ExtTreeNode> nodes, String rootParentId, Boolean expanded) {
        // 根节点集合
        List<ExtTreeNode> rootList = new LinkedList<ExtTreeNode>();
        // 获取所有的根节点
        for (Iterator iterator = nodes.iterator(); iterator.hasNext(); ) {
            // 获取自身bean
            ExtTreeNode rootTask = (ExtTreeNode) iterator.next();
            // 获取自身父节点
            String _parentId = rootTask.getParentId();
            //判断是否满足根节点条件 1.满足根节点要求 2.当父节点ID不存在在于集合之中时视为根节点
            if ((rootParentId == null && _parentId == null) || _parentId.equals(rootParentId) || (!isExistIdValueInListNode(_parentId, rootList) && !isExistIdValueInListNode(_parentId, nodes))) {
                rootList.add(rootTask);
                // 移除集合
                iterator.remove();
            }
        }
        // 最终结果集
        List<ExtTreeNode> resultList = new LinkedList<ExtTreeNode>();
        // 循环根节点获取所有的子节点
        for (Iterator iterator = rootList.iterator(); iterator.hasNext(); ) {
            ExtTreeNode rootTask = (ExtTreeNode) iterator.next();
            // 获取主键
            String _Id = rootTask.getId();
            // 获取子数据
            List<ExtTreeNode> children = getTreeChildList(nodes, _Id, expanded);
            // 组织子数据
            if (children != null && children.size() > 0) {
                // 组织层级数据
                rootTask.setChildren(children);
                rootTask.setLeaf(false);
                rootTask.setExpanded(expanded);
            }
            // 将整个完整的根到叶数据加入最终集合中
            resultList.add(rootTask);
        }
        return resultList;
    }


    /**
     * 查找list<obj>中，是否存在fieldName属性，并且属性值为fieldValue
     *
     * @param fieldName
     * @param fieldValue
     * @param list
     * @return
     */
    private static boolean isExistIdValueInListNode(String id, List<ExtTreeNode> list) {
        if (id == null || "".equals(id) || "null".equalsIgnoreCase(id)) {
            return false;
        }
        if (list != null && list.size() > 0) {
            for (ExtTreeNode treeNode : list) {
                if (id.equals(treeNode.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ext构建节点树结构 (查询结果要求先经过排序，提高效率)
     *
     * @param <ExtTreeNode>
     * @param nodes
     * @param parentId
     * @return
     */
    private static List<ExtTreeNode> getTreeChildList(List<ExtTreeNode> nodes, String parentId, Boolean expanded) {
        // 当前节点下所有子集
        List<ExtTreeNode> childrenList = new LinkedList<ExtTreeNode>();

        if (nodes != null && nodes.size() > 0) {
            // 父节点ID
            parentId = TriRegulation.isEmpty(parentId) ? "" : parentId;
            // 循环
            Iterator<ExtTreeNode> ite = nodes.iterator();
            while (ite.hasNext()) {
                // 获取当前父节点的子数据
                ExtTreeNode task = (ExtTreeNode) ite.next();
                // task为parentId的儿子
                String _parentId = task.getParentId();
                // 判断是否为子节点
                if (parentId.equals(_parentId)) {
                    // 追加到当前数据子集合用户下一层递归
                    childrenList.add(task);
                    // 移除总集合
                    ite.remove();
                }
            }

            if (childrenList != null && childrenList.size() > 0) {
                // 遍历子集递归查询子集的子集
                for (Iterator iterator = childrenList.iterator(); iterator.hasNext(); ) {
                    // 获取子集对象
                    ExtTreeNode task = (ExtTreeNode) iterator.next();
                    // 获取本身Id
                    String _Id = task.getId();
                    // 递归儿子
                    List<ExtTreeNode> children = getTreeChildList(nodes, _Id, expanded);
                    if (children != null && children.size() > 0) {
                        // 设置通用属性
                        task.setChildren(children);
                        task.setLeaf(false);
                        task.setExpanded(expanded);
                    }
                }
            }
        }
        // 返回当前子节点数据
        return childrenList;
    }
}
