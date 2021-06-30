package com.eims.controller;

import com.eims.mybatis.entity.GatherRefundDetail;
import com.eims.vo.form.GatherRefundDetailQueryForm;
import com.eims.service.GatherRefundDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (GatherRefundDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-30 19:36:26
 */
@RestController
public class GatherRefundDetailController {
    /**
     * 服务对象
     */
    @Resource
    private GatherRefundDetailService gatherRefundDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/gatherRefundDetail/one")
    public GatherRefundDetail selectOne(Integer id) {
        return this.gatherRefundDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param gatherRefundDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/gatherRefundDetail")
    public PageInfo<GatherRefundDetail> queryAll(GatherRefundDetailQueryForm gatherRefundDetailQueryForm) {
        return this.gatherRefundDetailService.queryAll(gatherRefundDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherRefundDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/gatherRefundDetail/search")
    public PageInfo<GatherRefundDetail> queryBySearch(GatherRefundDetailQueryForm gatherRefundDetailQueryForm) {
        return this.gatherRefundDetailService.queryBySearch(gatherRefundDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherRefundDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/gatherRefundDetail/screen")
    public PageInfo<GatherRefundDetail> queryByScreen(GatherRefundDetailQueryForm gatherRefundDetailQueryForm) {
        return this.gatherRefundDetailService.queryByScreen(gatherRefundDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/gatherRefundDetail")
    public GatherRefundDetail insert(@RequestBody GatherRefundDetail gatherRefundDetail) {
        return this.gatherRefundDetailService.insert(gatherRefundDetail);
    }

    /**
     * 批量新增数据
     *
     * @param GatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/gatherRefundDetail/batch")
    public boolean insertBatch(@RequestBody List<GatherRefundDetail> GatherRefundDetailList) {
        return this.gatherRefundDetailService.insertBatch(GatherRefundDetailList);
    }

    /**
     * 修改数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/gatherRefundDetail")
    public GatherRefundDetail update(@RequestBody GatherRefundDetail gatherRefundDetail) {
        return this.gatherRefundDetailService.update(gatherRefundDetail);
    }

    /**
     * 批量修改数据
     *
     * @param gatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/gatherRefundDetail/batch")
    public boolean updateBatch(@RequestBody List<GatherRefundDetail> gatherRefundDetailList) {
        return this.gatherRefundDetailService.updateBatch(gatherRefundDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/gatherRefundDetail")
    public boolean deleteById(Integer id) {
        return this.gatherRefundDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/gatherRefundDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.gatherRefundDetailService.deleteBatch(ids);
    }
}
