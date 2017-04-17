package com.zen.easyui.controller;

import com.alibaba.fastjson.JSON;
import com.zen.easyui.common.enums.EditEnum;
import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.common.web.ResultDto;
import com.zen.easyui.service.IFcboxEmployeeService;
import com.zen.easyui.vo.FcboxEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 000538 on 2017/4/14.
 */
@RestController
@RequestMapping("/employee")
public class FcboxEmployeeController {

    @Autowired
    private IFcboxEmployeeService fcboxEmployeeService;

    /**
     * 跳转到员工列表页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toListPage")
    ModelAndView toListPage() {
        ModelAndView modelAndView = new ModelAndView("jsp/base/list");
        modelAndView.addObject("page", "jsp/sys/listEmployee");
        return modelAndView;
    }

    /**
     * 分页员工获取信息
     *
     * @param employeeDto
     * @param pageInfo
     * @return
     */
    @RequestMapping(value = "/listByPage")
    PageLister<FcboxEmployeeDto> listByPage(FcboxEmployeeDto employeeDto, EuPagerInfo pageInfo) {
        return fcboxEmployeeService.listByPage(employeeDto, pageInfo);
    }

    /**
     * 跳转到员工编辑页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toEditPage")
    ModelAndView toEditPage(FcboxEmployeeDto employeeDto, String flag) {
        ModelAndView modelAndView = new ModelAndView("jsp/base/edit");
        if (EditEnum.UPDATE.name().equalsIgnoreCase(flag)) {//修改
            modelAndView.addObject("formJson", JSON.toJSONString(fcboxEmployeeService.get(employeeDto)));
        }
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("page", "jsp/sys/editEmployee");
        return modelAndView;
    }

    /**
     * 编辑员工信息
     *
     * @param employeeDto
     * @param flag
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    ResultDto<Void> edit(FcboxEmployeeDto employeeDto, String flag) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            if (EditEnum.UPDATE.name().equalsIgnoreCase(flag)) {//修改
                fcboxEmployeeService.update(employeeDto);
            } else {//新增
                fcboxEmployeeService.add(employeeDto);
            }
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }

    /**
     * 删除员工信息
     *
     * @param employeeDto
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResultDto<Void> delete(FcboxEmployeeDto employeeDto) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            fcboxEmployeeService.delete(employeeDto);
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }
}
