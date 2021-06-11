package com.eims.controller;

import com.eims.mybatis.entity.Customer;
import com.eims.vo.form.CustomerQueryForm;
import com.eims.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Customer)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:24:35
 */
@RestController
public class CustomerController {
    /**
     * 服务对象
     */
    @Resource
    private CustomerService customerService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/customer/one")
    public Customer selectOne(Integer id) {
        return this.customerService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param customerQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/customer")
    public PageInfo<Customer> queryAll(CustomerQueryForm customerQueryForm) {
        customerQueryForm.setCompanyId(1);
        return this.customerService.queryAll(customerQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param customerQueryForm
     * @return 对象列表
     */
    @GetMapping("/customer/search")
    public PageInfo<Customer> queryBySearch(CustomerQueryForm customerQueryForm) {
        customerQueryForm.setCompanyId(1);
        return this.customerService.queryBySearch(customerQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param customerQueryForm
     * @return 对象列表
     */
    @GetMapping("/customer/screen")
    public PageInfo<Customer> queryByScreen(CustomerQueryForm customerQueryForm) {
        customerQueryForm.setCompanyId(1);
        return this.customerService.queryByScreen(customerQueryForm);
    }

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @PostMapping("/customer")
    public Customer insert(@RequestBody Customer customer) {
        customer.setCompanyId(1);
        return this.customerService.insert(customer);
    }

    /**
     * 批量新增数据
     *
     * @param CustomerList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/customer/batch")
    public boolean insertBatch(@RequestBody List<Customer> CustomerList) {
        return this.customerService.insertBatch(CustomerList);
    }

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @PutMapping("/customer")
    public Customer update(@RequestBody Customer customer) {
        return this.customerService.update(customer);
    }

    /**
     * 批量修改数据
     *
     * @param customerList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/customer/batch")
    public boolean updateBatch(@RequestBody List<Customer> customerList) {
        return this.customerService.updateBatch(customerList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/customer")
    public boolean deleteById(Integer id) {
        return this.customerService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/customer/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.customerService.deleteBatch(ids);
    }
}
