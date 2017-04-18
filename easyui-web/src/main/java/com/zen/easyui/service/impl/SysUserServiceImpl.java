package com.zen.easyui.service.impl;

import com.zen.easyui.common.util.DesUtil;
import com.zen.easyui.common.util.IdentityUtil;
import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dao.SysUserDao;
import com.zen.easyui.dto.SysUserDto;
import com.zen.easyui.service.ISysUserService;
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
 * @ClassName SysUserServiceImpl.java
 * @Date 2017/4/17 15:53
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 新增用户
     *
     * @param userDto 用户信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(SysUserDto userDto) {
        userDto.setId(IdentityUtil.dbUuid32());
        userDto.setPassword(DesUtil.strEncrypt(userDto.getPassword()));
        userDto.setCreateTm(new Date());
        sysUserDao.insertSysUserDto(userDto);
    }

    /**
     * 删除用户
     *
     * @param userDto 用户信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUserByPk(SysUserDto userDto) {
        sysUserDao.deleteSysUserByPk(userDto);
    }

    /**
     * 修改用户
     *
     * @param userDto 用户信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUserByPk(SysUserDto userDto) {
        sysUserDao.updateSysUserByPk(userDto);
    }

    /**
     * 获取用户
     *
     * @param userDto 用户信息实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SysUserDto getUserByPk(SysUserDto userDto) {
        return sysUserDao.getSysUserByPk(userDto);
    }

    /**
     * 分页获取用户
     *
     * @param userDto   用户信息实体
     * @param pagerInfo 分页参数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageLister<SysUserDto> listUserByPage(SysUserDto userDto, EuPagerInfo pagerInfo) {
        pagerInfo.startPage();
        return new PageLister<SysUserDto>(sysUserDao.listSysUserByDto(userDto));
    }
}
