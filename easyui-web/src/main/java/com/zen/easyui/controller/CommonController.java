package com.zen.easyui.controller;

import com.zen.easyui.common.constant.GlobalConstant;
import com.zen.easyui.util.RegulationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * commonController.java
 * <p>
 * comments:	通用controller类
 *
 * @author Administrator
 * @version Version_2012
 * @creation date        2013-10-15
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

    /**
     * 通用请求跳转页面
     *
     * @param request 请求request
     * @return String
     */
    @RequestMapping(value = {"/toListPage", "/toEditPage"})
    public String toListPage(HttpServletRequest request) {
        if (!RegulationUtil.isEmpty(request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return (String) request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else if (!RegulationUtil.isEmpty(request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else {
            return null;
        }
    }

    /*消灭重复代码
    @RequestMapping(value = "/toEditPage")
    public String toEditPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!RegulationUtil.isEmpty(request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return (String) request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else if (!RegulationUtil.isEmpty(request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else {
            return null;
        }
    }*/
}
