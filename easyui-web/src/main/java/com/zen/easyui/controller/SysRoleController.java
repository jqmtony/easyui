package com.zen.easyui.controller;

import com.alibaba.fastjson.JSON;
import com.zen.easyui.common.constant.GlobalConstant;
import com.zen.easyui.common.enums.EditFlagEnum;
import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.common.web.ResultDto;
import com.zen.easyui.dto.SysRoleDto;
import com.zen.easyui.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 跳转到角色列表页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toListPage")
    ModelAndView toListPage() {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.LIST_PAGE);
        modelAndView.addObject(GlobalConstant.TO_PAGE_PARAM_NAME, "jsp/sys/listRole");
        return modelAndView;
    }

    /**
     * 分页角色获取信息
     *
     * @param roleDto
     * @param pageInfo
     * @return
     */
    @RequestMapping(value = "/listByPage")
    PageLister<SysRoleDto> listByPage(SysRoleDto roleDto, EuPagerInfo pageInfo) {
        return sysRoleService.listRoleByPage(roleDto, pageInfo);
    }

    /**
     * 跳转到角色编辑页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toEditPage")
    ModelAndView toEditPage(SysRoleDto roleDto, String editFlag) {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.EDIT_PAGE);
        if (EditFlagEnum.UPDATE.name().equalsIgnoreCase(editFlag)) {//修改
            modelAndView.addObject(GlobalConstant.EDIT_PAGE_VALUES_PARAM_NAME, JSON.toJSONString(sysRoleService.getRoleByPk(roleDto)));
        }
        modelAndView.addObject(GlobalConstant.EDIT_PAGE_FLAG_PARAM_NAME, editFlag);
        modelAndView.addObject(GlobalConstant.TO_PAGE_PARAM_NAME, "jsp/sys/editRole");
        return modelAndView;
    }

    /**
     * 编辑角色信息
     *
     * @param roleDto
     * @param editFlag
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    ResultDto<Void> edit(SysRoleDto roleDto, String editFlag) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            if (EditFlagEnum.UPDATE.name().equalsIgnoreCase(editFlag)) {//修改
                sysRoleService.updateRoleByPk(roleDto);
            } else {//新增
                sysRoleService.addRole(roleDto);
            }
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }

    /**
     * 删除角色信息
     *
     * @param roleDto
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResultDto<Void> delete(SysRoleDto roleDto) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            sysRoleService.deleteRoleByPk(roleDto);
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }
}
