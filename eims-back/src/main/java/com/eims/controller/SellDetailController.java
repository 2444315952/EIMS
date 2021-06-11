package com.eims.controller;

import com.eims.mybatis.entity.SellDetail;
import com.eims.vo.form.SellDetailQueryForm;
import com.eims.service.SellDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (SellDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:26:49
 */
@RestController
public class SellDetailController {
    /**
     * 服务对象
     */
    @Resource
    private SellDetailService sellDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/sellDetail/one")
    public SellDetail selectOne(Integer id) {
        return this.sellDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param sellDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/sellDetail")
    public PageInfo<SellDetail> queryAll(SellDetailQueryForm sellDetailQueryForm) {
        return this.sellDetailService.queryAll(sellDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellDetail/search")
    public PageInfo<SellDetail> queryBySearch(SellDetailQueryForm sellDetailQueryForm) {
        return this.sellDetailService.queryBySearch(sellDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellDetail/screen")
    public PageInfo<SellDetail> queryByScreen(SellDetailQueryForm sellDetailQueryForm) {
        return this.sellDetailService.queryByScreen(sellDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param sellDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/sellDetail")
    public SellDetail insert(@RequestBody SellDetail sellDetail) {
        return this.sellDetailService.insert(sellDetail);
    }

    /**
     * 批量新增数据
     *
     * @param SellDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/sellDetail/batch")
    public boolean insertBatch(@RequestBody List<SellDetail> SellDetailList) {
        return this.sellDetailService.insertBatch(SellDetailList);
    }

    /**
     * 修改数据
     *
     * @param sellDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/sellDetail")
    public SellDetail update(@RequestBody SellDetail sellDetail) {
        return this.sellDetailService.update(sellDetail);
    }

    /**
     * 批量修改数据
     *
     * @param sellDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/sellDetail/batch")
    public boolean updateBatch(@RequestBody List<SellDetail> sellDetailList) {
        return this.sellDetailService.updateBatch(sellDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/sellDetail")
    public boolean deleteById(Integer id) {
        return this.sellDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/sellDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.sellDetailService.deleteBatch(ids);
    }
}
