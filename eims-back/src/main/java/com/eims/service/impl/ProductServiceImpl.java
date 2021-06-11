package com.eims.service.impl;

import com.eims.mybatis.entity.Product;
import com.eims.vo.form.ProductQueryForm;
import com.eims.mybatis.dao.ProductDao;
import com.eims.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:25:52
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Integer productId) {
        return this.productDao.queryById(productId);
    }

    /**
     * 查询所有数据
     *
     * @param productQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Product> queryAll(ProductQueryForm productQueryForm) {
        Page<Product> page = PageHelper.startPage(productQueryForm.getPageNum(), productQueryForm.getPageSize());
        List<Product> productList = this.productDao.queryAll(productQueryForm);
        return new PageInfo<>(productList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param productQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Product> queryBySearch(ProductQueryForm productQueryForm) {
        Page<Product> page = PageHelper.startPage(productQueryForm.getPageNum(), productQueryForm.getPageSize());
        List<Product> productList = this.productDao.queryOrByPojo(productQueryForm);
        return new PageInfo<>(productList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param productQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Product> queryByScreen(ProductQueryForm productQueryForm) {
        Page<Product> page = PageHelper.startPage(productQueryForm.getPageNum(), productQueryForm.getPageSize());
        List<Product> productList = this.productDao.queryAndByPojo(productQueryForm);
        return new PageInfo<>(productList);
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        this.productDao.insert(product);
        return this.queryById(product.getProductId());
    }

    /**
     * 批量新增数据
     *
     * @param ProductList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Product> ProductList) {
        return this.productDao.insertBatch(ProductList) == -1;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productDao.update(product);
        return this.queryById(product.getProductId());
    }

    /**
     * 批量修改数据
     *
     * @param productList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Product> productList) {
        return this.productDao.updateBatch(productList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer productId) {
        return this.productDao.deleteById(productId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.productDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
