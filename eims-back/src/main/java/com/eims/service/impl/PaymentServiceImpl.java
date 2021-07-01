package com.eims.service.impl;

import com.eims.mybatis.dao.PaymentDetailDao;
import com.eims.mybatis.entity.Payment;
import com.eims.mybatis.entity.PaymentDetail;
import com.eims.vo.form.PaymentQueryForm;
import com.eims.mybatis.dao.PaymentDao;
import com.eims.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Payment)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:25:33
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Resource
    private PaymentDetailDao paymentDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param payId 主键
     * @return 实例对象
     */
    @Override
    public Payment queryById(Integer payId) {
        return this.paymentDao.queryById(payId);
    }

    /**
     * 查询所有数据
     *
     * @param paymentQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Payment> queryAll(PaymentQueryForm paymentQueryForm) {
        Page<Payment> page = PageHelper.startPage(paymentQueryForm.getPageNum(), paymentQueryForm.getPageSize());
        List<Payment> paymentList = this.paymentDao.queryAll(paymentQueryForm);
        return new PageInfo<>(paymentList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param paymentQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Payment> queryBySearch(PaymentQueryForm paymentQueryForm) {
        Page<Payment> page = PageHelper.startPage(paymentQueryForm.getPageNum(), paymentQueryForm.getPageSize());
        List<Payment> paymentList = this.paymentDao.queryOrByPojo(paymentQueryForm);
        return new PageInfo<>(paymentList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param paymentQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Payment> queryByScreen(PaymentQueryForm paymentQueryForm) {
        Page<Payment> page = PageHelper.startPage(paymentQueryForm.getPageNum(), paymentQueryForm.getPageSize());
        List<Payment> paymentList = this.paymentDao.queryAndByPojo(paymentQueryForm);
        return new PageInfo<>(paymentList);
    }

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment insert(Payment payment) {
        this.paymentDao.insert(payment);
        List<PaymentDetail> paymentDetailList = payment.getPaymentDetailList();//获取payment实体类里面的PaymentDetailList()值,get是获取
        if (paymentDetailList != null){                 //判断get过来的paymentDetailList里面是否有值
            for (PaymentDetail detail:paymentDetailList)//类型  别名：循环集和变量名
                detail.setPaymentId(payment.getPayId());//把循环自动新增的id放入 detail.setPaymentId
            paymentDetailDao.insertBatch(paymentDetailList);//批量新增付款单明细集和数据
        }
        return this.queryById(payment.getPayId());
    }

    /**
     * 批量新增数据
     *
     * @param PaymentList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Payment> PaymentList) {
        return this.paymentDao.insertBatch(PaymentList) == -1;
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment update(Payment payment) {
        this.paymentDao.update(payment);
        if (payment.getPaymentDetailList() !=  null){
            this.paymentDetailDao.deleteBatchByEntities(payment.getPaymentDetailList());
            this.paymentDetailDao.insertBatch(payment.getPaymentDetailList());
        }
        return this.queryById(payment.getPayId());
    }

    /**
     * 批量修改数据
     *
     * @param paymentList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Payment> paymentList) {
        return this.paymentDao.updateBatch(paymentList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param payId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer payId) {
        return this.paymentDao.deleteById(payId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.paymentDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
