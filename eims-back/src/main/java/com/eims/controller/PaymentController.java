package com.eims.controller;

import com.eims.mybatis.entity.Payment;
import com.eims.vo.form.PaymentQueryForm;
import com.eims.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Payment)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:33
 */
@RestController
public class PaymentController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentService paymentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/payment/one")
    public Payment selectOne(Integer id) {
        return this.paymentService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param paymentQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/payment")
    public PageInfo<Payment> queryAll(PaymentQueryForm paymentQueryForm) {
        paymentQueryForm.setCompanyId(1);
        return this.paymentService.queryAll(paymentQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param paymentQueryForm
     * @return 对象列表
     */
    @GetMapping("/payment/search")
    public PageInfo<Payment> queryBySearch(PaymentQueryForm paymentQueryForm) {
        paymentQueryForm.setCompanyId(1);
        return this.paymentService.queryBySearch(paymentQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param paymentQueryForm
     * @return 对象列表
     */
    @GetMapping("/payment/screen")
    public PageInfo<Payment> queryByScreen(PaymentQueryForm paymentQueryForm) {
        paymentQueryForm.setCompanyId(1);
        return this.paymentService.queryByScreen(paymentQueryForm);
    }

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @PostMapping("/payment")
    public Payment insert(@RequestBody Payment payment) {
        payment.setCompanyId(1);
        payment.setWorkPointId(1);
        return this.paymentService.insert(payment);
    }

    /**
     * 批量新增数据
     *
     * @param PaymentList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/payment/batch")
    public boolean insertBatch(@RequestBody List<Payment> PaymentList) {
        return this.paymentService.insertBatch(PaymentList);
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @PutMapping("/payment")
    public Payment update(@RequestBody Payment payment) {
        return this.paymentService.update(payment);
    }

    /**
     * 批量修改数据
     *
     * @param paymentList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/payment/batch")
    public boolean updateBatch(@RequestBody List<Payment> paymentList) {
        return this.paymentService.updateBatch(paymentList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/payment")
    public boolean deleteById(Integer id) {
        return this.paymentService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/payment/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.paymentService.deleteBatch(ids);
    }
}
