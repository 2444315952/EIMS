package com.eims.controller;

import com.eims.mybatis.entity.PurchaseReturn;
import com.eims.vo.form.PurchaseReturnQueryForm;
import com.eims.service.PurchaseReturnService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (PurchaseReturn)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:56
 */
@RestController
public class PurchaseReturnController {
    /**
     * 服务对象
     */
    @Resource
    private PurchaseReturnService purchaseReturnService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/purchaseReturn/one")
    public PurchaseReturn selectOne(Integer id) {
        return this.purchaseReturnService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseReturnQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/purchaseReturn")
    public PageInfo<PurchaseReturn> queryAll(PurchaseReturnQueryForm purchaseReturnQueryForm) {
        purchaseReturnQueryForm.setCompanyId(1);
        return this.purchaseReturnService.queryAll(purchaseReturnQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseReturnQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchaseReturn/search")
    public PageInfo<PurchaseReturn> queryBySearch(PurchaseReturnQueryForm purchaseReturnQueryForm) {
        purchaseReturnQueryForm.setCompanyId(1);
        return this.purchaseReturnService.queryBySearch(purchaseReturnQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseReturnQueryForm
     * @return 对象列表
     */
    @GetMapping("/purchaseReturn/screen")
    public PageInfo<PurchaseReturn> queryByScreen(PurchaseReturnQueryForm purchaseReturnQueryForm) {
        purchaseReturnQueryForm.setCompanyId(1);
        return this.purchaseReturnService.queryByScreen(purchaseReturnQueryForm);
    }

    /**
     * 新增数据
     *
     * @param purchaseReturn 实例对象
     * @return 实例对象
     */
    @PostMapping("/purchaseReturn")
    public PurchaseReturn insert(@RequestBody PurchaseReturn purchaseReturn) {
        purchaseReturn.setCompanyId(1);
        purchaseReturn.setWorkPointId(1);
        return this.purchaseReturnService.insert(purchaseReturn);
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseReturnList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/purchaseReturn/batch")
    public boolean insertBatch(@RequestBody List<PurchaseReturn> PurchaseReturnList) {
        return this.purchaseReturnService.insertBatch(PurchaseReturnList);
    }

    /**
     * 修改数据
     *
     * @param purchaseReturn 实例对象
     * @return 实例对象
     */
    @PutMapping("/purchaseReturn")
    public PurchaseReturn update(@RequestBody PurchaseReturn purchaseReturn) {
        return this.purchaseReturnService.update(purchaseReturn);
    }

    /**
     * 批量修改数据
     *
     * @param purchaseReturnList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/purchaseReturn/batch")
    public boolean updateBatch(@RequestBody List<PurchaseReturn> purchaseReturnList) {
        return this.purchaseReturnService.updateBatch(purchaseReturnList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/purchaseReturn")
    public boolean deleteById(Integer id) {
        return this.purchaseReturnService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/purchaseReturn/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.purchaseReturnService.deleteBatch(ids);
    }
}
