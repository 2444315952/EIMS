package com.eims.service;

import com.eims.mybatis.entity.Customer;
import com.eims.vo.form.CustomerQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Customer)表服务接口
 *
 * @author makejava
 * @since 2021-06-08 13:46:48
 */
public interface CustomerService {

    /**
     * 通过ID查询单条数据
     *
     * @param customerId 主键
     * @return 实例对象
     */
    Customer queryById(Integer customerId);

    /**
     * 查询所有数据
     *
     * @param customerQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Customer> queryAll(CustomerQueryForm customerQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param customerQueryForm
     * @return 对象列表
     */
    PageInfo<Customer> queryBySearch(CustomerQueryForm customerQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param customerQueryForm
     * @return 对象列表
     */
    PageInfo<Customer> queryByScreen(CustomerQueryForm customerQueryForm);

    PageInfo<Customer> queryAllXyj(int currentPage, int pageSize, String customerName, int workPointId, String is_enabled );

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer insert(Customer customer);

    /**
     * 批量新增数据
     *
     * @param CustomerList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Customer> CustomerList);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer update(Customer customer);

    /**
     * 批量修改数据
     *
     * @param customerList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Customer> customerList);

    int updateStop(List<Integer> customerId);

    int UpdateOpen(List<Integer> customerId);

    /**
     * 通过主键删除数据
     *
     * @param customerId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer customerId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
