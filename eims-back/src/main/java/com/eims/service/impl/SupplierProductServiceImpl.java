package com.eims.service.impl;

import com.eims.mybatis.entity.SupplierProduct;
import com.eims.vo.form.SupplierProductQueryForm;
import com.eims.mybatis.dao.SupplierProductDao;
import com.eims.service.SupplierProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (SupplierProduct)表服务实现类
 *
 * @author makejava
 * @since 2021-06-30 19:40:39
 */
@Service("supplierProductService")
public class SupplierProductServiceImpl implements SupplierProductService {
    @Resource
    private SupplierProductDao supplierProductDao;

    /**
     * 通过ID查询单条数据
     *
     * @param supplierProductId 主键
     * @return 实例对象
     */
    @Override
    public SupplierProduct queryById(Integer supplierProductId) {
        return this.supplierProductDao.queryById(supplierProductId);
    }

    /**
     * 查询所有数据
     *
     * @param supplierProductQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SupplierProduct> queryAll(SupplierProductQueryForm supplierProductQueryForm) {
        Page<SupplierProduct> page = PageHelper.startPage(supplierProductQueryForm.getPageNum(), supplierProductQueryForm.getPageSize());
        List<SupplierProduct> supplierProductList = this.supplierProductDao.queryAll(supplierProductQueryForm);
        return new PageInfo<>(supplierProductList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param supplierProductQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SupplierProduct> queryBySearch(SupplierProductQueryForm supplierProductQueryForm) {
        Page<SupplierProduct> page = PageHelper.startPage(supplierProductQueryForm.getPageNum(), supplierProductQueryForm.getPageSize());
        List<SupplierProduct> supplierProductList = this.supplierProductDao.queryOrByPojo(supplierProductQueryForm);
        return new PageInfo<>(supplierProductList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param supplierProductQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SupplierProduct> queryByScreen(SupplierProductQueryForm supplierProductQueryForm) {
        Page<SupplierProduct> page = PageHelper.startPage(supplierProductQueryForm.getPageNum(), supplierProductQueryForm.getPageSize());
        List<SupplierProduct> supplierProductList = this.supplierProductDao.queryAndByPojo(supplierProductQueryForm);
        return new PageInfo<>(supplierProductList);
    }

    /**
     * 新增数据
     *
     * @param supplierProduct 实例对象
     * @return 实例对象
     */
    @Override
    public SupplierProduct insert(SupplierProduct supplierProduct) {
        this.supplierProductDao.insert(supplierProduct);
        return this.queryById(supplierProduct.getSupplierProductId());
    }

    /**
     * 批量新增数据
     *
     * @param SupplierProductList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SupplierProduct> SupplierProductList) {
        return this.supplierProductDao.insertBatch(SupplierProductList) == -1;
    }

    /**
     * 修改数据
     *
     * @param supplierProduct 实例对象
     * @return 实例对象
     */
    @Override
    public SupplierProduct update(SupplierProduct supplierProduct) {
        this.supplierProductDao.update(supplierProduct);
        return this.queryById(supplierProduct.getSupplierProductId());
    }

    /**
     * 批量修改数据
     *
     * @param supplierProductList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SupplierProduct> supplierProductList) {
        return this.supplierProductDao.updateBatch(supplierProductList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param supplierProductId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer supplierProductId) {
        return this.supplierProductDao.deleteById(supplierProductId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.supplierProductDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
