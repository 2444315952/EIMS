package com.eims.service.impl;

import com.eims.mybatis.entity.PaymentDetail;
import com.eims.vo.form.PaymentDetailQueryForm;
import com.eims.mybatis.dao.PaymentDetailDao;
import com.eims.service.PaymentDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (PaymentDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:25:34
 */
@Service("paymentDetailService")
public class PaymentDetailServiceImpl implements PaymentDetailService {
    @Resource
    private PaymentDetailDao paymentDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param payDetailId 主键
     * @return 实例对象
     */
    @Override
    public PaymentDetail queryById(Integer payDetailId) {
        return this.paymentDetailDao.queryById(payDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param paymentDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<PaymentDetail> queryAll(PaymentDetailQueryForm paymentDetailQueryForm) {
        Page<PaymentDetail> page = PageHelper.startPage(paymentDetailQueryForm.getPageNum(), paymentDetailQueryForm.getPageSize());
        List<PaymentDetail> paymentDetailList = this.paymentDetailDao.queryAll(paymentDetailQueryForm);
        return new PageInfo<>(paymentDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param paymentDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PaymentDetail> queryBySearch(PaymentDetailQueryForm paymentDetailQueryForm) {
        Page<PaymentDetail> page = PageHelper.startPage(paymentDetailQueryForm.getPageNum(), paymentDetailQueryForm.getPageSize());
        List<PaymentDetail> paymentDetailList = this.paymentDetailDao.queryOrByPojo(paymentDetailQueryForm);
        return new PageInfo<>(paymentDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param paymentDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PaymentDetail> queryByScreen(PaymentDetailQueryForm paymentDetailQueryForm) {
        Page<PaymentDetail> page = PageHelper.startPage(paymentDetailQueryForm.getPageNum(), paymentDetailQueryForm.getPageSize());
        List<PaymentDetail> paymentDetailList = this.paymentDetailDao.queryAndByPojo(paymentDetailQueryForm);
        return new PageInfo<>(paymentDetailList);
    }

    /**
     * 新增数据
     *
     * @param paymentDetail 实例对象
     * @return 实例对象
     */
    @Override
    public PaymentDetail insert(PaymentDetail paymentDetail) {
        this.paymentDetailDao.insert(paymentDetail);
        return this.queryById(paymentDetail.getPayDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param PaymentDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<PaymentDetail> PaymentDetailList) {
        return this.paymentDetailDao.insertBatch(PaymentDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param paymentDetail 实例对象
     * @return 实例对象
     */
    @Override
    public PaymentDetail update(PaymentDetail paymentDetail) {
        this.paymentDetailDao.update(paymentDetail);
        return this.queryById(paymentDetail.getPayDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param paymentDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<PaymentDetail> paymentDetailList) {
        return this.paymentDetailDao.updateBatch(paymentDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param payDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer payDetailId) {
        return this.paymentDetailDao.deleteById(payDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.paymentDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
