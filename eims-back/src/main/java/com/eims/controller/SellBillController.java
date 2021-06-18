package com.eims.controller;

import com.eims.mybatis.entity.SellBill;
import com.eims.vo.form.SellBillQueryForm;
import com.eims.service.SellBillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (SellBill)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:26:47
 */
@RestController
public class SellBillController {
    /**
     * 服务对象
     */
    @Resource
    private SellBillService sellBillService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/sellBill/one")
    public SellBill selectOne(Integer id) {
        return this.sellBillService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param sellBillQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/sellBill")
    public PageInfo<SellBill> queryAll(SellBillQueryForm sellBillQueryForm) {
        sellBillQueryForm.setCompanyId(1);
        return this.sellBillService.queryAll(sellBillQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellBill/search")
    public PageInfo<SellBill> queryBySearch(SellBillQueryForm sellBillQueryForm) {

        System.out.println("条件查询"+sellBillQueryForm);
        return this.sellBillService.queryBySearch(sellBillQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellBill/screen")
    public PageInfo<SellBill> queryByScreen(SellBillQueryForm sellBillQueryForm) {
        System.out.println(sellBillQueryForm);
        return this.sellBillService.queryByScreen(sellBillQueryForm);
    }

    /**
     * 新增数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    @PostMapping("/sellBill")
    public SellBill insert(@RequestBody SellBill sellBill) {
        System.out.println("-------新增------"+sellBill);
        return this.sellBillService.insert(sellBill);

    }

    /**
     * 批量新增数据
     *
     * @param SellBillList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/sellBill/batch")
    public boolean insertBatch(@RequestBody List<SellBill> SellBillList) {
        return this.sellBillService.insertBatch(SellBillList);
    }

    /**
     * 修改数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    @PutMapping("/sellBill")
    public SellBill update(@RequestBody SellBill sellBill) {
        System.out.println(sellBill);
        return this.sellBillService.update(sellBill);


    }


    /**
     * 修改数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    @PutMapping("/sellBill/detail")
    public SellBill updateSellAndDetail(@RequestBody SellBill sellBill) {
        System.out.println("-------修改-------"+sellBill);
        return this.sellBillService.updateanddetail(sellBill);

    }

    /**
     * 批量修改数据
     *
     * @param sellBillList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/sellBill/batch")
    public boolean updateBatch(@RequestBody List<SellBill> sellBillList) {
        return this.sellBillService.updateBatch(sellBillList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/sellBill")
    public boolean deleteById(Integer id) {
        return this.sellBillService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/sellBill/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.sellBillService.deleteBatch(ids);
    }
}
