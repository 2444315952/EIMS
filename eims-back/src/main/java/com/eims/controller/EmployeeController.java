package com.eims.controller;

import com.eims.mybatis.entity.Employee;
import com.eims.vo.form.EmployeeQueryForm;
import com.eims.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2021-06-04 18:19:53
 */
@RestController
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/employee/one")
    public Employee selectOne(Integer id) {
        return this.employeeService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param employeeQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/employee")
    public PageInfo<Employee> queryAll(EmployeeQueryForm employeeQueryForm) {
        employeeQueryForm.setWorkPointId(1);
        return this.employeeService.queryAll(employeeQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param employeeQueryForm
     * @return 对象列表
     */
    @GetMapping("/employee/search")
    public PageInfo<Employee> queryBySearch(EmployeeQueryForm employeeQueryForm) {
        employeeQueryForm.setWorkPointId(1);
        return this.employeeService.queryBySearch(employeeQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param employeeQueryForm
     * @return 对象列表
     */
    @GetMapping("/employee/screen")
    public PageInfo<Employee> queryByScreen(EmployeeQueryForm employeeQueryForm) {
        employeeQueryForm.setWorkPointId(1);
        return this.employeeService.queryByScreen(employeeQueryForm);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @PostMapping("/employee")
    public Employee insert(@RequestBody Employee employee) {
        employee.setWorkPointId(1);
        employee.setWorkPointName("示例工作点");
        return this.employeeService.insert(employee);
    }

    /**
     * 批量新增数据
     *
     * @param EmployeeList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/employee/batch")
    public boolean insertBatch(@RequestBody List<Employee> EmployeeList) {
        return this.employeeService.insertBatch(EmployeeList);
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @PutMapping("/employee")
    public Employee update(@RequestBody Employee employee) {
        return this.employeeService.update(employee);
    }

    /**
     * 批量修改数据
     *
     * @param employeeList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/employee/batch")
    public boolean updateBatch(@RequestBody List<Employee> employeeList) {
        return this.employeeService.updateBatch(employeeList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/employee")
    public boolean deleteById(Integer id) {
        return this.employeeService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/employee/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.employeeService.deleteBatch(ids);
    }
}
