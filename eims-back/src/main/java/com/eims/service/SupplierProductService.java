package com.eims.service;

import com.eims.mybatis.entity.SupplierProduct;
import com.eims.vo.form.SupplierProductQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (SupplierProduct)表服务接口
 *
 * @author makejava
 * @since 2021-06-30 19:40:39
 */
public interface SupplierProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param supplierProductId 主键
     * @return 实例对象
     */
    SupplierProduct queryById(Integer supplierProductId);

    /**
     * 查询所有数据
     *
     * @param supplierProductQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<SupplierProduct> queryAll(SupplierProductQueryForm supplierProductQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param supplierProductQueryForm
     * @return 对象列表
     */
    PageInfo<SupplierProduct> queryBySearch(SupplierProductQueryForm supplierProductQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param supplierProductQueryForm
     * @return 对象列表
     */
    PageInfo<SupplierProduct> queryByScreen(SupplierProductQueryForm supplierProductQueryForm);

    /**
     * 新增数据
     *
     * @param supplierProduct 实例对象
     * @return 实例对象
     */
    SupplierProduct insert(SupplierProduct supplierProduct);

    /**
     * 批量新增数据
     *
     * @param SupplierProductList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<SupplierProduct> SupplierProductList);

    /**
     * 修改数据
     *
     * @param supplierProduct 实例对象
     * @return 实例对象
     */
    SupplierProduct update(SupplierProduct supplierProduct);

    /**
     * 批量修改数据
     *
     * @param supplierProductList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<SupplierProduct> supplierProductList);

    /**
     * 通过主键删除数据
     *
     * @param supplierProductId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer supplierProductId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
