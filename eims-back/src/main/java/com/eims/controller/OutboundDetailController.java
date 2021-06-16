package com.eims.controller;

import com.eims.mybatis.entity.OutboundDetail;
import com.eims.vo.form.OutboundDetailQueryForm;
import com.eims.service.OutboundDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (OutboundDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:32
 */
@RestController
public class OutboundDetailController {
    /**
     * 服务对象
     */
    @Resource
    private OutboundDetailService outboundDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/outboundDetail/one")
    public OutboundDetail selectOne(Integer id) {
        return this.outboundDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param outboundDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/outboundDetail")
    public PageInfo<OutboundDetail> queryAll(OutboundDetailQueryForm outboundDetailQueryForm) {
        return this.outboundDetailService.queryAll(outboundDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param outboundDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/outboundDetail/search")
    public PageInfo<OutboundDetail> queryBySearch(OutboundDetailQueryForm outboundDetailQueryForm) {
        return this.outboundDetailService.queryBySearch(outboundDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param outboundDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/outboundDetail/screen")
    public PageInfo<OutboundDetail> queryByScreen(OutboundDetailQueryForm outboundDetailQueryForm) {
        return this.outboundDetailService.queryByScreen(outboundDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param outboundDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/outboundDetail")
    public OutboundDetail insert(@RequestBody OutboundDetail outboundDetail) {
        return this.outboundDetailService.insert(outboundDetail);
    }

    /**
     * 批量新增数据
     *
     * @param OutboundDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/outboundDetail/batch")
    public boolean insertBatch(@RequestBody List<OutboundDetail> OutboundDetailList) {
        return this.outboundDetailService.insertBatch(OutboundDetailList);
    }

    /**
     * 修改数据
     *
     * @param outboundDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/outboundDetail")
    public OutboundDetail update(@RequestBody OutboundDetail outboundDetail) {
        return this.outboundDetailService.update(outboundDetail);
    }

    /**
     * 批量修改数据
     *
     * @param outboundDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/outboundDetail/batch")
    public boolean updateBatch(@RequestBody List<OutboundDetail> outboundDetailList) {
        return this.outboundDetailService.updateBatch(outboundDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/outboundDetail")
    public boolean deleteById(Integer id) {
        return this.outboundDetailService.deleteById(id);
    }

    /**
     * 通过出库单i删除明细
     * @param id
     * @return
     */
    @DeleteMapping("/outboundDetail/deleteFk")
    public boolean deletefk(Integer id){
        return this.outboundDetailService.deleteFk(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/outboundDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.outboundDetailService.deleteBatch(ids);
    }
}
