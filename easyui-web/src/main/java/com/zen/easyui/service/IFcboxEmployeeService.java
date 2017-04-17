package com.zen.easyui.service;

import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.dto.FcboxEmployeeDto;

import java.util.List;

/**
 * Created by 000538 on 2017/4/14.
 */
public interface IFcboxEmployeeService {

    /**
     * 新增员工信息
     * @param employeeDto
     */
    void add(FcboxEmployeeDto employeeDto);

    /**
     * 修改员工信息
     * @param employeeDto
     */
    void update(FcboxEmployeeDto employeeDto);

    /**
     * 删除员工信息
     * @param employeeDto
     */
    void delete(FcboxEmployeeDto employeeDto);

    /**
     * 获取员工信息
     * @param employeeDto
     * @return
     */
    FcboxEmployeeDto get(FcboxEmployeeDto employeeDto);

    /**
     * 获取员工信息集合
     * @param employeeDto
     * @return
     */
    List<FcboxEmployeeDto> list(FcboxEmployeeDto employeeDto);

    /**
     * 分页获取员工信息
     * @param employeeDto
     * @return
     */
    PageLister<FcboxEmployeeDto> listByPage(FcboxEmployeeDto employeeDto, EuPagerInfo pageInfo);
}
