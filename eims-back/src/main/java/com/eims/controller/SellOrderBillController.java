package com.eims.controller;

import com.eims.mybatis.entity.SellOrderBill;
import com.eims.vo.form.SellOrderBillQueryForm;
import com.eims.service.SellOrderBillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (SellOrderBill)表控制层
 *
 * @author makejava
 * @since 2021-06-20 21:16:25
 */
@RestController
public class SellOrderBillController {
    /**
     * 服务对象
     */
    @Resource
    private SellOrderBillService sellOrderBillService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/sellOrderBill/one")
    public SellOrderBill selectOne(Integer id) {
        return this.sellOrderBillService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param sellOrderBillQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/sellOrderBill")
    public PageInfo<SellOrderBill> queryAll(SellOrderBillQueryForm sellOrderBillQueryForm) {
        sellOrderBillQueryForm.setWorkPointId(1);
        return this.sellOrderBillService.queryAll(sellOrderBillQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellOrderBill/search")
    public PageInfo<SellOrderBill> queryBySearch(SellOrderBillQueryForm sellOrderBillQueryForm) {

        return this.sellOrderBillService.queryBySearch(sellOrderBillQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellOrderBill/screen")
    public PageInfo<SellOrderBill> queryByScreen(SellOrderBillQueryForm sellOrderBillQueryForm) {
        sellOrderBillQueryForm.setWorkPointId(1);
        return this.sellOrderBillService.queryByScreen(sellOrderBillQueryForm);
    }

    /**
     * 新增数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    @PostMapping("/sellOrderBill")
    public SellOrderBill insert(@RequestBody SellOrderBill sellOrderBill) {

        return this.sellOrderBillService.insert(sellOrderBill);

    }

    /**
     * 批量新增数据
     *
     * @param SellOrderBillList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/sellOrderBill/batch")
    public boolean insertBatch(@RequestBody List<SellOrderBill> SellOrderBillList) {
        return this.sellOrderBillService.insertBatch(SellOrderBillList);
    }

    /**
     * 修改数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    @PutMapping("/sellOrderBill")
    public SellOrderBill update(@RequestBody SellOrderBill sellOrderBill) {
        return this.sellOrderBillService.update(sellOrderBill);
    }

    @PutMapping("/sellOrderBill/detail")
    public SellOrderBill updateSellAndDetail(@RequestBody SellOrderBill sellOrderBill) {
        return this.sellOrderBillService.updateSellAndDetail(sellOrderBill);
    }

    /**
     * 批量修改数据
     *
     * @param sellOrderBillList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/sellOrderBill/batch")
    public boolean updateBatch(@RequestBody List<SellOrderBill> sellOrderBillList) {
        return this.sellOrderBillService.updateBatch(sellOrderBillList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/sellOrderBill")
    public boolean deleteById(Integer id) {
        return this.sellOrderBillService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/sellOrderBill/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.sellOrderBillService.deleteBatch(ids);
    }
}
