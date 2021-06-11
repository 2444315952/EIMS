package com.eims.service;

import com.eims.mybatis.entity.Employee;
import com.eims.vo.form.EmployeeQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Employee)表服务接口
 *
 * @author makejava
 * @since 2021-06-04 18:19:53
 */
public interface EmployeeService {

    /**
     * 通过ID查询单条数据
     *
     * @param employeeId 主键
     * @return 实例对象
     */
    Employee queryById(Integer employeeId);

    /**
     * 查询所有数据
     *
     * @param employeeQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Employee> queryAll(EmployeeQueryForm employeeQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param employeeQueryForm
     * @return 对象列表
     */
    PageInfo<Employee> queryBySearch(EmployeeQueryForm employeeQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param employeeQueryForm
     * @return 对象列表
     */
    PageInfo<Employee> queryByScreen(EmployeeQueryForm employeeQueryForm);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee insert(Employee employee);

    /**
     * 批量新增数据
     *
     * @param EmployeeList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Employee> EmployeeList);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee update(Employee employee);

    /**
     * 批量修改数据
     *
     * @param employeeList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Employee> employeeList);

    /**
     * 通过主键删除数据
     *
     * @param employeeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer employeeId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
