package com.eims.controller;

import com.eims.mybatis.entity.PurchaseDetail;
import com.eims.vo.form.PurchaseDetailQueryForm;
import com.eims.service.PurchaseDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (PurchaseDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:54
 */
@RestController
public class PurchaseDetailController {
    /**
     * 服务对象
     */
    @Resource
    private PurchaseDetailService purchaseDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/purchaseDetail/one")
    public PurchaseDetail selectOne(Integer id) {
        return this.purchaseDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/purchaseDetail")
    public PageInfo<PurchaseDetail> queryAll(PurchaseDetailQueryForm purchaseDetailQueryForm) {
        return this.purchaseDetailService.queryAll(purchaseDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchaseDetail/search")
    public PageInfo<PurchaseDetail> queryBySearch(PurchaseDetailQueryForm purchaseDetailQueryForm) {
        return this.purchaseDetailService.queryBySearch(purchaseDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchaseDetail/screen")
    public PageInfo<PurchaseDetail> queryByScreen(PurchaseDetailQueryForm purchaseDetailQueryForm) {
        return this.purchaseDetailService.queryByScreen(purchaseDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param purchaseDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/purchaseDetail")
    public PurchaseDetail insert(@RequestBody PurchaseDetail purchaseDetail) {
        return this.purchaseDetailService.insert(purchaseDetail);
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/purchaseDetail/batch")
    public boolean insertBatch(@RequestBody List<PurchaseDetail> PurchaseDetailList) {
        return this.purchaseDetailService.insertBatch(PurchaseDetailList);
    }

    /**
     * 修改数据
     *
     * @param purchaseDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/purchaseDetail")
    public PurchaseDetail update(@RequestBody PurchaseDetail purchaseDetail) {
        return this.purchaseDetailService.update(purchaseDetail);
    }

    /**
     * 批量修改数据
     *
     * @param purchaseDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/purchaseDetail/batch")
    public boolean updateBatch(@RequestBody List<PurchaseDetail> purchaseDetailList) {
        return this.purchaseDetailService.updateBatch(purchaseDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/purchaseDetail")
    public boolean deleteById(Integer id) {
        return this.purchaseDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/purchaseDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.purchaseDetailService.deleteBatch(ids);
    }
}
