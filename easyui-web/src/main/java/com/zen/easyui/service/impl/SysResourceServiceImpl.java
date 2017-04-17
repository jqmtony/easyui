package com.zen.easyui.service.impl;

import com.zen.easyui.common.util.IdentityUtil;
import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dao.SysResourceDao;
import com.zen.easyui.dto.SysResourceDto;
import com.zen.easyui.service.ISysResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public void deleteResourceByPk(SysResourceDto resourceDto) {
        sysResourceDao.deleteSysResourceByPk(resourceDto);
    }

    /**
     * 修改资源
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    public void updateResourceByPk(SysResourceDto resourceDto) {
        sysResourceDao.updateSysResourceByPk(resourceDto);
    }

    /**
     * 获取资源
     *
     * @param resourceDto 资源信息实体
     */
    @Override
    public SysResourceDto getResourceByPk(SysResourceDto resourceDto) {
        return sysResourceDao.getSysResourceByPk(resourceDto);
    }

    /**
     * 分页获取资源
     *
     * @param resourceDto   资源信息实体
     * @param pagerInfo 分页参数
     */
    @Override
    public PageLister<SysResourceDto> listResourceByPage(SysResourceDto resourceDto, EuPagerInfo pagerInfo) {
        pagerInfo.startPage();
        return new PageLister<SysResourceDto>(sysResourceDao.listSysResourceByDto(resourceDto));
    }
}
