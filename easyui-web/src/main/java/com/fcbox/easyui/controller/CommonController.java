package com.fcbox.easyui.controller;

import com.fcbox.easyui.util.TriRegulation;
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
        if (!TriRegulation.isEmpty(request.getAttribute("page"))) {
            return (String) request.getAttribute("page");
        } else if (!TriRegulation.isEmpty(request.getParameter("page"))) {
            return request.getParameter("page");
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/toEditPage")
    public String toEditPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!TriRegulation.isEmpty(request.getAttribute("page"))) {
            return (String) request.getAttribute("page");
        } else if (!TriRegulation.isEmpty(request.getParameter("page"))) {
            return request.getParameter("page");
        } else {
            return null;
        }
    }
}
