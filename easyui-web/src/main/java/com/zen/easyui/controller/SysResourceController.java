package com.zen.easyui.controller;

import com.alibaba.fastjson.JSON;
import com.zen.easyui.common.constant.GlobalConstant;
import com.zen.easyui.common.enums.EditEnum;
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


@RestController
@RequestMapping("/resource")
public class SysResourceController {

    @Autowired
    private ISysResourceService sysResourceService;

    /**
     * 跳转到资源列表页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toListPage")
    ModelAndView toListPage() {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.LIST_PAGE);
        modelAndView.addObject("page", "jsp/sys/listResource");
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
     * 跳转到资源编辑页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toEditPage")
    ModelAndView toEditPage(SysResourceDto resourceDto, String flag) {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.EDIT_PAGE);
        if (EditEnum.UPDATE.name().equalsIgnoreCase(flag)) {//修改
            modelAndView.addObject("formJson", JSON.toJSONString(sysResourceService.getResourceByPk(resourceDto)));
        }
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("page", "jsp/sys/editResource");
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
    ResultDto<Void> edit(SysResourceDto resourceDto, String flag) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            if (EditEnum.UPDATE.name().equalsIgnoreCase(flag)) {//修改
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