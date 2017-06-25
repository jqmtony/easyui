package com.zen.easyui.service.impl;

import com.zen.easyui.common.util.ExtUtils;
import com.zen.easyui.common.util.IdentityUtil;
import com.zen.easyui.common.util.TreeNodeUtil;
import com.zen.easyui.common.vo.ExtTreeNode;
import com.zen.easyui.common.vo.TreeNode;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dao.SysResourceDao;
import com.zen.easyui.dto.SysResourceDto;
import com.zen.easyui.service.ISysResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName SysResourceServiceImpl.java
 * @Date 2017/4/17 15:53
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements ISysResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysResourceServiceImpl.class);

    @Autowired
    private SysResourceDao sysResourceDao;

    /**
     * 新增资源
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addResource(SysResourceDto resourceDto) {
        resourceDto.setId(IdentityUtil.dbUuid32());
        resourceDto.setCreateTm(new Date());
        sysResourceDao.insertSysResourceDto(resourceDto);
    }

    /**
     * 删除资源
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteResourceByPk(SysResourceDto resourceDto) {
        sysResourceDao.deleteSysResourceByPk(resourceDto);
    }

    /**
     * 修改资源
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateResourceByPk(SysResourceDto resourceDto) {
        sysResourceDao.updateSysResourceByPk(resourceDto);
    }

    /**
     * 获取资源
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SysResourceDto getResourceByPk(SysResourceDto resourceDto) {
        return sysResourceDao.getSysResourceByPk(resourceDto);
    }

    /**
     * 分页获取资源
     *
     * @param resourceDto 资源信息实体
     * @param pagerInfo   分页参数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageLister<SysResourceDto> listResourceByPage(SysResourceDto resourceDto) {
        return new PageLister<SysResourceDto>(sysResourceDao.listSysResourceByDto(resourceDto));
    }

    /**
     * 获取Ext资源树
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ExtTreeNode> listExtResourceTree(SysResourceDto resourceDto) {
        List<ExtTreeNode> extTreeNodes = new ArrayList<>();
        List<SysResourceDto> resources = sysResourceDao.listSysResourceByDto(resourceDto);
        for (SysResourceDto resource : resources) {
            ExtTreeNode extTreeNode = new ExtTreeNode();
            extTreeNode.setId(resource.getId());
            extTreeNode.setParentId(resource.getPid());
            extTreeNode.setName(resource.getName());
            extTreeNodes.add(extTreeNode);
        }
        return ExtUtils.getTreeList(extTreeNodes, null, true);
    }

    /**
     * 获取资源下拉树
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<TreeNode<SysResourceDto>> listResourceTree(SysResourceDto resourceDto) {
        List<TreeNode<SysResourceDto>> treeNodes = new ArrayList<>();
        List<SysResourceDto> resources = sysResourceDao.listSysResourceByDto(resourceDto);
        for (SysResourceDto resource : resources) {
            TreeNode<SysResourceDto> treeNode = new TreeNode<SysResourceDto>();
            treeNode.setId(resource.getId());
            treeNode.setPid(resource.getPid());
            treeNode.setText(resource.getName());
            treeNodes.add(treeNode);
        }
        return TreeNodeUtil.getRootNodeList(treeNodes);
    }
}
