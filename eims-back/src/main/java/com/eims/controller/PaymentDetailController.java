package com.eims.controller;

import com.eims.mybatis.entity.PaymentDetail;
import com.eims.vo.form.PaymentDetailQueryForm;
import com.eims.service.PaymentDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (PaymentDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:34
 */
@RestController
public class PaymentDetailController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentDetailService paymentDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/paymentDetail/one")
    public PaymentDetail selectOne(Integer id) {
        return this.paymentDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param paymentDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/paymentDetail")
    public PageInfo<PaymentDetail> queryAll(PaymentDetailQueryForm paymentDetailQueryForm) {
        return this.paymentDetailService.queryAll(paymentDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param paymentDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/paymentDetail/search")
    public PageInfo<PaymentDetail> queryBySearch(PaymentDetailQueryForm paymentDetailQueryForm) {
        return this.paymentDetailService.queryBySearch(paymentDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param paymentDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/paymentDetail/screen")
    public PageInfo<PaymentDetail> queryByScreen(PaymentDetailQueryForm paymentDetailQueryForm) {
        return this.paymentDetailService.queryByScreen(paymentDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param paymentDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/paymentDetail")
    public PaymentDetail insert(@RequestBody PaymentDetail paymentDetail) {
        return this.paymentDetailService.insert(paymentDetail);
    }

    /**
     * 批量新增数据
     *
     * @param PaymentDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/paymentDetail/batch")
    public boolean insertBatch(@RequestBody List<PaymentDetail> PaymentDetailList) {
        return this.paymentDetailService.insertBatch(PaymentDetailList);
    }

    /**
     * 修改数据
     *
     * @param paymentDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/paymentDetail")
    public PaymentDetail update(@RequestBody PaymentDetail paymentDetail) {
        return this.paymentDetailService.update(paymentDetail);
    }

    /**
     * 批量修改数据
     *
     * @param paymentDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/paymentDetail/batch")
    public boolean updateBatch(@RequestBody List<PaymentDetail> paymentDetailList) {
        return this.paymentDetailService.updateBatch(paymentDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/paymentDetail")
    public boolean deleteById(Integer id) {
        return this.paymentDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/paymentDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.paymentDetailService.deleteBatch(ids);
    }
}
