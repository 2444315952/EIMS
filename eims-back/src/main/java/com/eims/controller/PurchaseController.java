package com.eims.controller;

import com.eims.mybatis.entity.Purchase;
import com.eims.vo.form.PurchaseQueryForm;
import com.eims.service.PurchaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Purchase)表控制层
 *
 * @author makejava
 * @since 2021-06-04 18:19:56
 */
@RestController
@Log4j2
public class PurchaseController {
    /**
     * 服务对象
     */
    @Resource
    private PurchaseService purchaseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/purchase/one")
    public Purchase selectOne(Integer id) {
        return this.purchaseService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/purchase")
    public PageInfo<Purchase> queryAll(PurchaseQueryForm purchaseQueryForm) {
        purchaseQueryForm.setWorkPointId(1);
        return this.purchaseService.queryAll(purchaseQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchase/search")
    public PageInfo<Purchase> queryBySearch(PurchaseQueryForm purchaseQueryForm) {
        purchaseQueryForm.setWorkPointId(1);
        return this.purchaseService.queryBySearch(purchaseQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchase/screen")
    public PageInfo<Purchase> queryByScreen(PurchaseQueryForm purchaseQueryForm) {
        purchaseQueryForm.setWorkPointId(1);
        return this.purchaseService.queryByScreen(purchaseQueryForm);
    }

    /**
     * 根据采购单据编号，查询单据条数
     * @return
     */
    @GetMapping("/purchase/docuNumCount")
    public int queryCountByDocuNum(String purchDocunum){
        return this.purchaseService.queryCountByDocuNum(1,purchDocunum);
    }

    /**
     * 新增数据
     *
     * @param purchase 实例对象
     * @return 实例对象
     */
    @PostMapping("/purchase")
    public Purchase insert(@RequestBody Purchase purchase) {
        purchase.setWorkPointId(1);
        purchase.setWorkPointName("示例工作点");
        return this.purchaseService.insert(purchase);
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/purchase/batch")
    public boolean insertBatch(@RequestBody List<Purchase> PurchaseList) {
        return this.purchaseService.insertBatch(PurchaseList);
    }

    /**
     * 修改数据
     *
     * @param purchase 实例对象
     * @return 实例对象
     */
    @PutMapping("/purchase")
    public Purchase update(@RequestBody Purchase purchase) {
        return this.purchaseService.update(purchase);
    }

    /**
     * 批量修改数据
     *
     * @param purchaseList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/purchase/batch")
    public boolean updateBatch(@RequestBody List<Purchase> purchaseList) {
        log.debug(purchaseList.toString());
        return this.purchaseService.updateBatch(purchaseList);
    }

    /**
     * 通过主键id修改审核状态
     * @param audited
     * @param purchId
     * @return 是否成功
     */
    @PutMapping("/purchase/audit")
    public boolean updateAuditedById(Integer audited,Integer purchId){
        return this.purchaseService.updateAuditedById(audited, purchId);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/purchase")
    public boolean deleteById(Integer id) {
        return this.purchaseService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/purchase/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.purchaseService.deleteBatch(ids);
    }
}
