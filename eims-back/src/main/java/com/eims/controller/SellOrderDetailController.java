package com.eims.controller;

import com.eims.mybatis.entity.SellOrderDetail;
import com.eims.vo.form.SellOrderDetailQueryForm;
import com.eims.service.SellOrderDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (SellOrderDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:26:51
 */
@RestController
public class SellOrderDetailController {
    /**
     * 服务对象
     */
    @Resource
    private SellOrderDetailService sellOrderDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/sellOrderDetail/one")
    public SellOrderDetail selectOne(Integer id) {
        return this.sellOrderDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param sellOrderDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/sellOrderDetail")
    public PageInfo<SellOrderDetail> queryAll(SellOrderDetailQueryForm sellOrderDetailQueryForm) {
        return this.sellOrderDetailService.queryAll(sellOrderDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellOrderDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellOrderDetail/search")
    public PageInfo<SellOrderDetail> queryBySearch(SellOrderDetailQueryForm sellOrderDetailQueryForm) {
        return this.sellOrderDetailService.queryBySearch(sellOrderDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellOrderDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellOrderDetail/screen")
    public PageInfo<SellOrderDetail> queryByScreen(SellOrderDetailQueryForm sellOrderDetailQueryForm) {
        return this.sellOrderDetailService.queryByScreen(sellOrderDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param sellOrderDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/sellOrderDetail")
    public SellOrderDetail insert(@RequestBody SellOrderDetail sellOrderDetail) {
        return this.sellOrderDetailService.insert(sellOrderDetail);
    }

    /**
     * 批量新增数据
     *
     * @param SellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/sellOrderDetail/batch")
    public boolean insertBatch(@RequestBody List<SellOrderDetail> SellOrderDetailList) {
        return this.sellOrderDetailService.insertBatch(SellOrderDetailList);
    }

    /**
     * 修改数据
     *
     * @param sellOrderDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/sellOrderDetail")
    public SellOrderDetail update(@RequestBody SellOrderDetail sellOrderDetail) {
        return this.sellOrderDetailService.update(sellOrderDetail);
    }

    /**
     * 批量修改数据
     *
     * @param sellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/sellOrderDetail/batch")
    public boolean updateBatch(@RequestBody List<SellOrderDetail> sellOrderDetailList) {
        return this.sellOrderDetailService.updateBatch(sellOrderDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/sellOrderDetail")
    public boolean deleteById(Integer id) {
        return this.sellOrderDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/sellOrderDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.sellOrderDetailService.deleteBatch(ids);
    }
}
