package com.eims.service;

import com.eims.mybatis.entity.Supplier;
import com.eims.vo.form.SupplierQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Supplier)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:55
 */
public interface SupplierService {

    /**
     * 通过ID查询单条数据
     *
     * @param supplierId 主键
     * @return 实例对象
     */
    Supplier queryById(Integer supplierId);

    /**
     * 查询所有数据
     *
     * @param supplierQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Supplier> queryAll(SupplierQueryForm supplierQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param supplierQueryForm
     * @return 对象列表
     */
    PageInfo<Supplier> queryBySearch(SupplierQueryForm supplierQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param supplierQueryForm
     * @return 对象列表
     */
    PageInfo<Supplier> queryByScreen(SupplierQueryForm supplierQueryForm);

    /**
     * 新增数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    Supplier insert(Supplier supplier);

    /**
     * 批量新增数据
     *
     * @param SupplierList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Supplier> SupplierList);

    /**
     * 修改数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    Supplier update(Supplier supplier);

    /**
     * 批量修改数据
     *
     * @param supplierList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Supplier> supplierList);

    /**
     * 通过主键删除数据
     *
     * @param supplierId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer supplierId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
