package com.eims.service;

import com.eims.mybatis.entity.Product;
import com.eims.vo.form.ProductQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2021-06-04 18:19:54
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    Product queryById(Integer productId);

    /**
     * 查询所有数据
     *
     * @param productQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Product> queryAll(ProductQueryForm productQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param productQueryForm
     * @return 对象列表
     */
    PageInfo<Product> queryBySearch(ProductQueryForm productQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param productQueryForm
     * @return 对象列表
     */
    PageInfo<Product> queryByScreen(ProductQueryForm productQueryForm);

    PageInfo<Product> queryByAllLike(int currentPage, int pageSize,String comm_type_name,String is_enabled, String product_name) ;

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insert(Product product);

    /**
     * 批量新增数据
     *
     * @param ProductList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Product> ProductList);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product update(Product product);

    /**
     * 批量修改数据
     *
     * @param productList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Product> productList);

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer productId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
