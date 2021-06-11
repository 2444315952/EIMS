package com.eims.service;

import com.eims.mybatis.entity.Payment;
import com.eims.vo.form.PaymentQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Payment)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:25:33
 */
public interface PaymentService {

    /**
     * 通过ID查询单条数据
     *
     * @param payId 主键
     * @return 实例对象
     */
    Payment queryById(Integer payId);

    /**
     * 查询所有数据
     *
     * @param paymentQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Payment> queryAll(PaymentQueryForm paymentQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param paymentQueryForm
     * @return 对象列表
     */
    PageInfo<Payment> queryBySearch(PaymentQueryForm paymentQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param paymentQueryForm
     * @return 对象列表
     */
    PageInfo<Payment> queryByScreen(PaymentQueryForm paymentQueryForm);

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    Payment insert(Payment payment);

    /**
     * 批量新增数据
     *
     * @param PaymentList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Payment> PaymentList);

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    Payment update(Payment payment);

    /**
     * 批量修改数据
     *
     * @param paymentList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Payment> paymentList);

    /**
     * 通过主键删除数据
     *
     * @param payId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer payId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
