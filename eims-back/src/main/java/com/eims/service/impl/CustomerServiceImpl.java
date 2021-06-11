package com.eims.service.impl;

import com.eims.mybatis.entity.Customer;
import com.eims.vo.form.CustomerQueryForm;
import com.eims.mybatis.dao.CustomerDao;
import com.eims.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Customer)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:24:34
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param customerId 主键
     * @return 实例对象
     */
    @Override
    public Customer queryById(Integer customerId) {
        return this.customerDao.queryById(customerId);
    }

    /**
     * 查询所有数据
     *
     * @param customerQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Customer> queryAll(CustomerQueryForm customerQueryForm) {
        Page<Customer> page = PageHelper.startPage(customerQueryForm.getPageNum(), customerQueryForm.getPageSize());
        List<Customer> customerList = this.customerDao.queryAll(customerQueryForm);
        return new PageInfo<>(customerList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param customerQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Customer> queryBySearch(CustomerQueryForm customerQueryForm) {
        Page<Customer> page = PageHelper.startPage(customerQueryForm.getPageNum(), customerQueryForm.getPageSize());
        List<Customer> customerList = this.customerDao.queryOrByPojo(customerQueryForm);
        return new PageInfo<>(customerList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param customerQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Customer> queryByScreen(CustomerQueryForm customerQueryForm) {
        Page<Customer> page = PageHelper.startPage(customerQueryForm.getPageNum(), customerQueryForm.getPageSize());
        List<Customer> customerList = this.customerDao.queryAndByPojo(customerQueryForm);
        return new PageInfo<>(customerList);
    }

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer insert(Customer customer) {
        this.customerDao.insert(customer);
        return this.queryById(customer.getCustomerId());
    }

    /**
     * 批量新增数据
     *
     * @param CustomerList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Customer> CustomerList) {
        return this.customerDao.insertBatch(CustomerList) == -1;
    }

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer update(Customer customer) {
        this.customerDao.update(customer);
        return this.queryById(customer.getCustomerId());
    }

    /**
     * 批量修改数据
     *
     * @param customerList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Customer> customerList) {
        return this.customerDao.updateBatch(customerList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param customerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer customerId) {
        return this.customerDao.deleteById(customerId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.customerDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
