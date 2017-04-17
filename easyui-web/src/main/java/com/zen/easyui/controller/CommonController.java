package com.zen.easyui.controller;

import com.zen.easyui.common.constant.GlobalConstant;
import com.zen.easyui.util.TriRegulation;
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
 * @creation date        2013-10-15
 * @version Version_2012
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

    @RequestMapping(value = "/toListPage")
    public String toListPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!TriRegulation.isEmpty(request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return (String) request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else if (!TriRegulation.isEmpty(request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/toEditPage")
    public String toEditPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!TriRegulation.isEmpty(request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return (String) request.getAttribute(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else if (!TriRegulation.isEmpty(request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME))) {
            return request.getParameter(GlobalConstant.TO_PAGE_PARAM_NAME);
        } else {
            return null;
        }
    }
}
