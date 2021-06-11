package com.eims.service.impl;

import com.eims.mybatis.entity.Employee;
import com.eims.vo.form.EmployeeQueryForm;
import com.eims.mybatis.dao.EmployeeDao;
import com.eims.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Employee)表服务实现类
 *
 * @author makejava
 * @since 2021-06-04 18:19:53
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param employeeId 主键
     * @return 实例对象
     */
    @Override
    public Employee queryById(Integer employeeId) {
        return this.employeeDao.queryById(employeeId);
    }

    /**
     * 查询所有数据
     *
     * @param employeeQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Employee> queryAll(EmployeeQueryForm employeeQueryForm) {
        Page<Employee> page = PageHelper.startPage(employeeQueryForm.getPageNum(), employeeQueryForm.getPageSize());
        List<Employee> employeeList = this.employeeDao.queryAll(employeeQueryForm);
        return new PageInfo<>(employeeList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param employeeQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Employee> queryBySearch(EmployeeQueryForm employeeQueryForm) {
        Page<Employee> page = PageHelper.startPage(employeeQueryForm.getPageNum(), employeeQueryForm.getPageSize());
        List<Employee> employeeList = this.employeeDao.queryOrByPojo(employeeQueryForm);
        return new PageInfo<>(employeeList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param employeeQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Employee> queryByScreen(EmployeeQueryForm employeeQueryForm) {
        Page<Employee> page = PageHelper.startPage(employeeQueryForm.getPageNum(), employeeQueryForm.getPageSize());
        List<Employee> employeeList = this.employeeDao.queryAndByPojo(employeeQueryForm);
        return new PageInfo<>(employeeList);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee) {
        this.employeeDao.insert(employee);
        return this.queryById(employee.getEmployeeId());
    }

    /**
     * 批量新增数据
     *
     * @param EmployeeList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Employee> EmployeeList) {
        return this.employeeDao.insertBatch(EmployeeList) == -1;
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee update(Employee employee) {
        this.employeeDao.update(employee);
        return this.queryById(employee.getEmployeeId());
    }

    /**
     * 批量修改数据
     *
     * @param employeeList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Employee> employeeList) {
        return this.employeeDao.updateBatch(employeeList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param employeeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer employeeId) {
        return this.employeeDao.deleteById(employeeId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.employeeDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
