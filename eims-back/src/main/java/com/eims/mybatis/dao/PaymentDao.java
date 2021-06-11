package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Payment;
import com.eims.vo.form.PaymentQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Payment)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:25:32
 */
@Mapper
public interface PaymentDao {

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
    List<Payment> queryAll(PaymentQueryForm paymentQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param paymentQueryForm 实例对象
     * @return 对象列表
     */
    List<Payment> queryOrByPojo(PaymentQueryForm paymentQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param paymentQueryForm 实例对象
     * @return 对象列表
     */
    List<Payment> queryAndByPojo(PaymentQueryForm paymentQueryForm);

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 影响行数
     */
    int insert(Payment payment);

    /**
     * 批量新增数据
     *
     * @param paymentList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Payment> paymentList);

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 影响行数
     */
    int update(Payment payment);

    /**
     * 批量修改数据
     *
     * @param paymentList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Payment> paymentList);

    /**
     * 通过主键删除数据
     *
     * @param payId 主键
     * @return 影响行数
     */
    int deleteById(Integer payId);

    /**
     * 通过主键列表删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    int deleteBatch(List<Integer> ids);

    /**
     * 批量对象列表删除数据
     *
     * @param paymentList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Payment> paymentList);
}

