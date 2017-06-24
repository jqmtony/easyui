package com.zen.easyui.controller;

import com.alibaba.fastjson.JSON;
import com.zen.easyui.common.constant.GlobalConstant;
import com.zen.easyui.common.enums.EditFlagEnum;
import com.zen.easyui.common.vo.ExtTreeNode;
import com.zen.easyui.common.vo.TreeNode;
import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.common.web.ResultDto;
import com.zen.easyui.dto.SysResourceDto;
import com.zen.easyui.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/resource")
public class SysResourceController {

    @Autowired
    private ISysResourceService sysResourceService;

    /**
     * 跳转到资源列表页面
     *
     * @return
     */
    @RequestMapping(value = "/toListPage")
    ModelAndView toListPage() {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.LIST_PAGE);
        modelAndView.addObject(GlobalConstant.TO_PAGE_PARAM_NAME, "jsp/sys/listResource");
        return modelAndView;
    }

    /**
     * 分页资源获取信息
     *
     * @param resourceDto
     * @param pageInfo
     * @return
     */
    @RequestMapping(value = "/listByPage")
    PageLister<SysResourceDto> listByPage(SysResourceDto resourceDto, EuPagerInfo pageInfo) {
        return sysResourceService.listResourceByPage(resourceDto, pageInfo);
    }

    /**
     * 跳转到EXT资源列表页面
     *
     * @return
     */
    @RequestMapping(value = "/toExtListPage")
    ModelAndView toExtListPage() {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.LIST_PAGE);
        modelAndView.addObject(GlobalConstant.TO_PAGE_PARAM_NAME, "jsp/sys/listExtResource");
        return modelAndView;
    }

    /**
     * 获取Ext资源树信息
     *
     * @param resourceDto
     * @return
     */
    @RequestMapping(value = "/listExtTreeNode")
    List<ExtTreeNode> listExtTreeNode(SysResourceDto resourceDto) {
        return sysResourceService.listExtResourceTree(resourceDto);
    }

    /**
     * 获取下拉资源树信息
     *
     * @param resourceDto
     * @return
     */
    @RequestMapping(value = "/listTreeNode")
    List<TreeNode<SysResourceDto>> listTreeNode(SysResourceDto resourceDto) {
        return sysResourceService.listResourceTree(resourceDto);
    }

    /**
     * 跳转到资源编辑页面
     *
     * @param resourceDto
     * @param editFlag
     * @return
     */
    @RequestMapping(value = "/toEditPage")
    ModelAndView toEditPage(SysResourceDto resourceDto, String editFlag) {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.EDIT_PAGE);
        if (EditFlagEnum.UPDATE.name().equalsIgnoreCase(editFlag)) {//修改
            modelAndView.addObject(GlobalConstant.EDIT_PAGE_VALUES_PARAM_NAME, JSON.toJSONString(sysResourceService.getResourceByPk(resourceDto)));
        }
        modelAndView.addObject(GlobalConstant.EDIT_PAGE_FLAG_PARAM_NAME, editFlag);
        modelAndView.addObject(GlobalConstant.TO_PAGE_PARAM_NAME, "jsp/sys/editResource");
        return modelAndView;
    }

    /**
     * 编辑资源信息
     *
     * @param resourceDto
     * @param flag
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    ResultDto<Void> edit(SysResourceDto resourceDto, String editFlag) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            if (EditFlagEnum.UPDATE.name().equalsIgnoreCase(editFlag)) {//修改
                sysResourceService.updateResourceByPk(resourceDto);
            } else {//新增
                sysResourceService.addResource(resourceDto);
            }
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }

    /**
     * 删除资源信息
     *
     * @param resourceDto
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResultDto<Void> delete(SysResourceDto resourceDto) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            sysResourceService.deleteResourceByPk(resourceDto);
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }
}
