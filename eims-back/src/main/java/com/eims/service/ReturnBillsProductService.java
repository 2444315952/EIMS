package com.eims.service;

import com.eims.mybatis.entity.ReturnBillsProduct;
import com.eims.vo.form.ReturnBillsProductQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (ReturnBillsProduct)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:46
 */
public interface ReturnBillsProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param retBpId 主键
     * @return 实例对象
     */
    ReturnBillsProduct queryById(Integer retBpId);

    /**
     * 查询所有数据
     *
     * @param returnBillsProductQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<ReturnBillsProduct> queryAll(ReturnBillsProductQueryForm returnBillsProductQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param returnBillsProductQueryForm
     * @return 对象列表
     */
    PageInfo<ReturnBillsProduct> queryBySearch(ReturnBillsProductQueryForm returnBillsProductQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param returnBillsProductQueryForm
     * @return 对象列表
     */
    PageInfo<ReturnBillsProduct> queryByScreen(ReturnBillsProductQueryForm returnBillsProductQueryForm);

    /**
     * 新增数据
     *
     * @param returnBillsProduct 实例对象
     * @return 实例对象
     */
    ReturnBillsProduct insert(ReturnBillsProduct returnBillsProduct);

    /**
     * 批量新增数据
     *
     * @param ReturnBillsProductList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<ReturnBillsProduct> ReturnBillsProductList);

    /**
     * 修改数据
     *
     * @param returnBillsProduct 实例对象
     * @return 实例对象
     */
    ReturnBillsProduct update(ReturnBillsProduct returnBillsProduct);

    /**
     * 批量修改数据
     *
     * @param returnBillsProductList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<ReturnBillsProduct> returnBillsProductList);

    /**
     * 通过主键删除数据
     *
     * @param retBpId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer retBpId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
