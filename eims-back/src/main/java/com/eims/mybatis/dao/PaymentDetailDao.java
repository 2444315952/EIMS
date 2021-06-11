package com.eims.mybatis.dao;

import com.eims.mybatis.entity.PaymentDetail;
import com.eims.vo.form.PaymentDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PaymentDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:25:34
 */
@Mapper
public interface PaymentDetailDao {

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
    List<PaymentDetail> queryAll(PaymentDetailQueryForm paymentDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param paymentDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<PaymentDetail> queryOrByPojo(PaymentDetailQueryForm paymentDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param paymentDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<PaymentDetail> queryAndByPojo(PaymentDetailQueryForm paymentDetailQueryForm);

    /**
     * 新增数据
     *
     * @param paymentDetail 实例对象
     * @return 影响行数
     */
    int insert(PaymentDetail paymentDetail);

    /**
     * 批量新增数据
     *
     * @param paymentDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<PaymentDetail> paymentDetailList);

    /**
     * 修改数据
     *
     * @param paymentDetail 实例对象
     * @return 影响行数
     */
    int update(PaymentDetail paymentDetail);

    /**
     * 批量修改数据
     *
     * @param paymentDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<PaymentDetail> paymentDetailList);

    /**
     * 通过主键删除数据
     *
     * @param payDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer payDetailId);

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
     * @param paymentDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<PaymentDetail> paymentDetailList);
}

