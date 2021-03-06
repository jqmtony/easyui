package com.zen.easyui.service.impl;

import com.zen.easyui.common.util.IdentityUtil;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dao.SysRoleDao;
import com.zen.easyui.dto.SysRoleDto;
import com.zen.easyui.service.ISysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName SysRoleServiceImpl.java
 * @Date 2017/4/17 15:53
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleDao sysRoleDao;

    /**
     * 新增角色
     *
     * @param roleDto 角色信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addRole(SysRoleDto roleDto) {
        roleDto.setId(IdentityUtil.dbUuid32());
        roleDto.setCreateTm(new Date());
        sysRoleDao.insertSysRoleDto(roleDto);
    }

    /**
     * 删除角色
     *
     * @param roleDto 角色信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteRoleByPk(SysRoleDto roleDto) {
        sysRoleDao.deleteSysRoleByPk(roleDto);
    }

    /**
     * 修改角色
     *
     * @param roleDto 角色信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateRoleByPk(SysRoleDto roleDto) {
        sysRoleDao.updateSysRoleByPk(roleDto);
    }

    /**
     * 获取角色
     *
     * @param roleDto 角色信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SysRoleDto getRoleByPk(SysRoleDto roleDto) {
        return sysRoleDao.getSysRoleByPk(roleDto);
    }

    /**
     * 分页获取角色
     *
     * @param roleDto   角色信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageLister<SysRoleDto> listRoleByPage(SysRoleDto roleDto) {
        return new PageLister<SysRoleDto>(sysRoleDao.listSysRoleByDto(roleDto));
    }
}
