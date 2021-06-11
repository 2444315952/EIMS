package com.eims.service;

import com.eims.mybatis.entity.Company;
import com.eims.vo.form.CompanyQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Company)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:24:33
 */
public interface CompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    Company queryById(Integer companyId);

    /**
     * 查询所有数据
     *
     * @param companyQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Company> queryAll(CompanyQueryForm companyQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param companyQueryForm
     * @return 对象列表
     */
    PageInfo<Company> queryBySearch(CompanyQueryForm companyQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param companyQueryForm
     * @return 对象列表
     */
    PageInfo<Company> queryByScreen(CompanyQueryForm companyQueryForm);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Company insert(Company company);

    /**
     * 批量新增数据
     *
     * @param CompanyList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Company> CompanyList);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Company update(Company company);

    /**
     * 批量修改数据
     *
     * @param companyList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Company> companyList);

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer companyId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
