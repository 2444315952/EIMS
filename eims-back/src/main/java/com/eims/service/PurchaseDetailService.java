package com.eims.service;

import com.eims.mybatis.entity.PurchaseDetail;
import com.eims.vo.form.PurchaseDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (PurchaseDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:25:54
 */
public interface PurchaseDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param purchDetailId 主键
     * @return 实例对象
     */
    PurchaseDetail queryById(Integer purchDetailId);

    /**
     * 查询所有数据
     *
     * @param purchaseDetailQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<PurchaseDetail> queryAll(PurchaseDetailQueryForm purchaseDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseDetailQueryForm
     * @return 对象列表
     */
    PageInfo<PurchaseDetail> queryBySearch(PurchaseDetailQueryForm purchaseDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseDetailQueryForm
     * @return 对象列表
     */
    PageInfo<PurchaseDetail> queryByScreen(PurchaseDetailQueryForm purchaseDetailQueryForm);

    /**
     * 新增数据
     *
     * @param purchaseDetail 实例对象
     * @return 实例对象
     */
    PurchaseDetail insert(PurchaseDetail purchaseDetail);

    /**
     * 批量新增数据
     *
     * @param PurchaseDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<PurchaseDetail> PurchaseDetailList);

    /**
     * 修改数据
     *
     * @param purchaseDetail 实例对象
     * @return 实例对象
     */
    PurchaseDetail update(PurchaseDetail purchaseDetail);

    /**
     * 批量修改数据
     *
     * @param purchaseDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<PurchaseDetail> purchaseDetailList);

    /**
     * 通过主键删除数据
     *
     * @param purchDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer purchDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
