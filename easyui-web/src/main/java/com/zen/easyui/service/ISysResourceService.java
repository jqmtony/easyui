package com.zen.easyui.service;

import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dto.SysResourceDto;

/**
 * 资源管理
 *
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName ISysResourceService.java
 * @Date 2017/4/17 15:53
 */
public interface ISysResourceService {

    /**
     * 新增资源
     *
     * @param resourceDto 资源信息实体
     */
    void addResource(SysResourceDto resourceDto);

    /**
     * 删除资源
     *
     * @param resourceDto 资源信息实体
     */
    void deleteResourceByPk(SysResourceDto resourceDto);

    /**
     * 修改资源
     *
     * @param resourceDto 资源信息实体
     */
    void updateResourceByPk(SysResourceDto resourceDto);

    /**
     * 获取资源
     *
     * @param resourceDto 资源信息实体
     */
    SysResourceDto getResourceByPk(SysResourceDto resourceDto);

    /**
     * 分页获取资源
     *
     * @param resourceDto   资源信息实体
     * @param pagerInfo 分页参数
     */
    PageLister<SysResourceDto> listResourceByPage(SysResourceDto resourceDto, EuPagerInfo pagerInfo);
}
