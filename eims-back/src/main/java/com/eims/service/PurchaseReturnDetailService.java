package com.eims.service;

import com.eims.mybatis.entity.PurchaseReturnDetail;
import com.eims.vo.form.PurchaseReturnDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (PurchaseReturnDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:25:57
 */
public interface PurchaseReturnDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param purchReturnDetailId 主键
     * @return 实例对象
     */
    PurchaseReturnDetail queryById(Integer purchReturnDetailId);

    /**
     * 查询所有数据
     *
     * @param purchaseReturnDetailQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<PurchaseReturnDetail> queryAll(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseReturnDetailQueryForm
     * @return 对象列表
     */
    PageInfo<PurchaseReturnDetail> queryBySearch(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseReturnDetailQueryForm
     * @return 对象列表
     */
    PageInfo<PurchaseReturnDetail> queryByScreen(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm);

    /**
     * 新增数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 实例对象
     */
    PurchaseReturnDetail insert(PurchaseReturnDetail purchaseReturnDetail);

    /**
     * 批量新增数据
     *
     * @param PurchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<PurchaseReturnDetail> PurchaseReturnDetailList);

    /**
     * 修改数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 实例对象
     */
    PurchaseReturnDetail update(PurchaseReturnDetail purchaseReturnDetail);

    /**
     * 批量修改数据
     *
     * @param purchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<PurchaseReturnDetail> purchaseReturnDetailList);

    /**
     * 通过主键删除数据
     *
     * @param purchReturnDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer purchReturnDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
