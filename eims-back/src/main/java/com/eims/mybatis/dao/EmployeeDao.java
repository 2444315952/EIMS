package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Employee;
import com.eims.vo.form.EmployeeQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Employee)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-04 18:19:53
 */
@Mapper
public interface EmployeeDao {

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
    List<Employee> queryAll(EmployeeQueryForm employeeQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param employeeQueryForm 实例对象
     * @return 对象列表
     */
    List<Employee> queryOrByPojo(EmployeeQueryForm employeeQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param employeeQueryForm 实例对象
     * @return 对象列表
     */
    List<Employee> queryAndByPojo(EmployeeQueryForm employeeQueryForm);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int insert(Employee employee);

    /**
     * 批量新增数据
     *
     * @param employeeList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Employee> employeeList);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int update(Employee employee);

    /**
     * 批量修改数据
     *
     * @param employeeList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Employee> employeeList);

    /**
     * 通过主键删除数据
     *
     * @param employeeId 主键
     * @return 影响行数
     */
    int deleteById(Integer employeeId);

    /**
     * 通过主键列表删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    int deleteBatch(List<Integer> ids);

    /**
     * 批量对象列表删除数据
     *
     * @param employeeList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Employee> employeeList);
}

