package com.eims.controller;

import com.eims.mybatis.entity.ReturnBillsProduct;
import com.eims.vo.form.ReturnBillsProductQueryForm;
import com.eims.service.ReturnBillsProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (ReturnBillsProduct)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:26:46
 */
@RestController
public class ReturnBillsProductController {
    /**
     * 服务对象
     */
    @Resource
    private ReturnBillsProductService returnBillsProductService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/returnBillsProduct/one")
    public ReturnBillsProduct selectOne(Integer id) {
        return this.returnBillsProductService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param returnBillsProductQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/returnBillsProduct")
    public PageInfo<ReturnBillsProduct> queryAll(ReturnBillsProductQueryForm returnBillsProductQueryForm) {
        return this.returnBillsProductService.queryAll(returnBillsProductQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param returnBillsProductQueryForm
     * @return 对象列表
     */
    @GetMapping("/returnBillsProduct/search")
    public PageInfo<ReturnBillsProduct> queryBySearch(ReturnBillsProductQueryForm returnBillsProductQueryForm) {
        return this.returnBillsProductService.queryBySearch(returnBillsProductQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param returnBillsProductQueryForm
     * @return 对象列表
     */
    @GetMapping("/returnBillsProduct/screen")
    public PageInfo<ReturnBillsProduct> queryByScreen(ReturnBillsProductQueryForm returnBillsProductQueryForm) {
        return this.returnBillsProductService.queryByScreen(returnBillsProductQueryForm);
    }

    /**
     * 新增数据
     *
     * @param returnBillsProduct 实例对象
     * @return 实例对象
     */
    @PostMapping("/returnBillsProduct")
    public ReturnBillsProduct insert(@RequestBody ReturnBillsProduct returnBillsProduct) {
        return this.returnBillsProductService.insert(returnBillsProduct);
    }

    /**
     * 批量新增数据
     *
     * @param ReturnBillsProductList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/returnBillsProduct/batch")
    public boolean insertBatch(@RequestBody List<ReturnBillsProduct> ReturnBillsProductList) {
        return this.returnBillsProductService.insertBatch(ReturnBillsProductList);
    }

    /**
     * 修改数据
     *
     * @param returnBillsProduct 实例对象
     * @return 实例对象
     */
    @PutMapping("/returnBillsProduct")
    public ReturnBillsProduct update(@RequestBody ReturnBillsProduct returnBillsProduct) {
        return this.returnBillsProductService.update(returnBillsProduct);
    }

    /**
     * 批量修改数据
     *
     * @param returnBillsProductList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/returnBillsProduct/batch")
    public boolean updateBatch(@RequestBody List<ReturnBillsProduct> returnBillsProductList) {
        return this.returnBillsProductService.updateBatch(returnBillsProductList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/returnBillsProduct")
    public boolean deleteById(Integer id) {
        return this.returnBillsProductService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/returnBillsProduct/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.returnBillsProductService.deleteBatch(ids);
    }
}
