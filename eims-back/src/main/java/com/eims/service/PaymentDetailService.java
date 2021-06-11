package com.eims.service;

import com.eims.mybatis.entity.PaymentDetail;
import com.eims.vo.form.PaymentDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (PaymentDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:25:34
 */
public interface PaymentDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param payDetailId 主键
     * @return 实例对象
     */
    PaymentDetail queryById(Integer payDetailId);

    /**
     * 查询所有数据
     *
     * @param paymentDetailQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<PaymentDetail> queryAll(PaymentDetailQueryForm paymentDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param paymentDetailQueryForm
     * @return 对象列表
     */
    PageInfo<PaymentDetail> queryBySearch(PaymentDetailQueryForm paymentDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param paymentDetailQueryForm
     * @return 对象列表
     */
    PageInfo<PaymentDetail> queryByScreen(PaymentDetailQueryForm paymentDetailQueryForm);

    /**
     * 新增数据
     *
     * @param paymentDetail 实例对象
     * @return 实例对象
     */
    PaymentDetail insert(PaymentDetail paymentDetail);

    /**
     * 批量新增数据
     *
     * @param PaymentDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<PaymentDetail> PaymentDetailList);

    /**
     * 修改数据
     *
     * @param paymentDetail 实例对象
     * @return 实例对象
     */
    PaymentDetail update(PaymentDetail paymentDetail);

    /**
     * 批量修改数据
     *
     * @param paymentDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<PaymentDetail> paymentDetailList);

    /**
     * 通过主键删除数据
     *
     * @param payDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer payDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
