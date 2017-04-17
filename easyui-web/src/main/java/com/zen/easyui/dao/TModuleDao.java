package com.zen.easyui.dao;

import com.zen.easyui.vo.TModuleDto;

import java.util.List;

/**
 * 表t_module基础CRUD
 */
public interface TModuleDao {

    /**
     * 插入一条表 t_module记录
     *
     * @param TModuleDto tModule
     * @return void
     */
    void insertTModuleDto(TModuleDto tModule);

    /**
     * 批量插入 t_module记录
     *
     * @param TModuleDto tModule
     * @return void
     */
    void batchInsertTModuleDto(List<TModuleDto> list);

    /**
     * 使用表 t_module主键更新记录
     *
     * @param TModuleDto tModule
     * @return 影响的记录数
     */
    int updateTModuleByPk(TModuleDto tModule);

    /**
     * 使用表 t_module主键查询一条记录
     *
     * @param TModuleDto tModule
     * @return TModuleDto
     */
    TModuleDto selectTModuleByPk(TModuleDto tModule);

    /**
     * 使用表 t_module主键删除一条记录
     *
     * @param TModuleDto tModule
     * @return TModuleDto tModule
     */
    int deleteTModuleByPk(TModuleDto tModule);

    /**
     * 使用表 t_module主键查询列表
     *
     * @param TModuleDto tModule
     * @return TModuleDto
     */
    List<TModuleDto> selectTModuleByDto(TModuleDto tModule);

}
