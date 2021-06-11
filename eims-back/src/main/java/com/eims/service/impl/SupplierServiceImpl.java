package com.eims.service.impl;

import com.eims.mybatis.entity.Supplier;
import com.eims.vo.form.SupplierQueryForm;
import com.eims.mybatis.dao.SupplierDao;
import com.eims.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Supplier)表服务实现类
 *
 * @author makejava
 * @since 2021-06-04 18:20:01
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierDao supplierDao;

    /**
     * 通过ID查询单条数据
     *
     * @param supplierId 主键
     * @return 实例对象
     */
    @Override
    public Supplier queryById(Integer supplierId) {
        return this.supplierDao.queryById(supplierId);
    }

    /**
     * 查询所有数据
     *
     * @param supplierQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Supplier> queryAll(SupplierQueryForm supplierQueryForm) {
        Page<Supplier> page = PageHelper.startPage(supplierQueryForm.getPageNum(), supplierQueryForm.getPageSize());
        List<Supplier> supplierList = this.supplierDao.queryAll(supplierQueryForm);
        return new PageInfo<>(supplierList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param supplierQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Supplier> queryBySearch(SupplierQueryForm supplierQueryForm) {
        Page<Supplier> page = PageHelper.startPage(supplierQueryForm.getPageNum(), supplierQueryForm.getPageSize());
        List<Supplier> supplierList = this.supplierDao.queryOrByPojo(supplierQueryForm);
        return new PageInfo<>(supplierList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param supplierQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Supplier> queryByScreen(SupplierQueryForm supplierQueryForm) {
        Page<Supplier> page = PageHelper.startPage(supplierQueryForm.getPageNum(), supplierQueryForm.getPageSize());
        List<Supplier> supplierList = this.supplierDao.queryAndByPojo(supplierQueryForm);
        return new PageInfo<>(supplierList);
    }

    /**
     * 新增数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    @Override
    public Supplier insert(Supplier supplier) {
        this.supplierDao.insert(supplier);
        return this.queryById(supplier.getSupplierId());
    }

    /**
     * 批量新增数据
     *
     * @param SupplierList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Supplier> SupplierList) {
        return this.supplierDao.insertBatch(SupplierList) == -1;
    }

    /**
     * 修改数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    @Override
    public Supplier update(Supplier supplier) {
        this.supplierDao.update(supplier);
        return this.queryById(supplier.getSupplierId());
    }

    /**
     * 批量修改数据
     *
     * @param supplierList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Supplier> supplierList) {
        return this.supplierDao.updateBatch(supplierList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param supplierId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer supplierId) {
        return this.supplierDao.deleteById(supplierId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.supplierDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
