package com.eims.controller;

import com.eims.mybatis.entity.PurchaseReturnDetail;
import com.eims.vo.form.PurchaseReturnDetailQueryForm;
import com.eims.service.PurchaseReturnDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (PurchaseReturnDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:57
 */
@RestController
public class PurchaseReturnDetailController {
    /**
     * 服务对象
     */
    @Resource
    private PurchaseReturnDetailService purchaseReturnDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/purchaseReturnDetail/one")
    public PurchaseReturnDetail selectOne(Integer id) {
        return this.purchaseReturnDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseReturnDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/purchaseReturnDetail")
    public PageInfo<PurchaseReturnDetail> queryAll(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm) {
        return this.purchaseReturnDetailService.queryAll(purchaseReturnDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseReturnDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchaseReturnDetail/search")
    public PageInfo<PurchaseReturnDetail> queryBySearch(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm) {
        return this.purchaseReturnDetailService.queryBySearch(purchaseReturnDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseReturnDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchaseReturnDetail/screen")
    public PageInfo<PurchaseReturnDetail> queryByScreen(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm) {
        return this.purchaseReturnDetailService.queryByScreen(purchaseReturnDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/purchaseReturnDetail")
    public PurchaseReturnDetail insert(@RequestBody PurchaseReturnDetail purchaseReturnDetail) {
        return this.purchaseReturnDetailService.insert(purchaseReturnDetail);
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/purchaseReturnDetail/batch")
    public boolean insertBatch(@RequestBody List<PurchaseReturnDetail> PurchaseReturnDetailList) {
        return this.purchaseReturnDetailService.insertBatch(PurchaseReturnDetailList);
    }

    /**
     * 修改数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/purchaseReturnDetail")
    public PurchaseReturnDetail update(@RequestBody PurchaseReturnDetail purchaseReturnDetail) {
        return this.purchaseReturnDetailService.update(purchaseReturnDetail);
    }

    /**
     * 批量修改数据
     *
     * @param purchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/purchaseReturnDetail/batch")
    public boolean updateBatch(@RequestBody List<PurchaseReturnDetail> purchaseReturnDetailList) {
        return this.purchaseReturnDetailService.updateBatch(purchaseReturnDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/purchaseReturnDetail")
    public boolean deleteById(Integer id) {
        return this.purchaseReturnDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/purchaseReturnDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.purchaseReturnDetailService.deleteBatch(ids);
    }
}
