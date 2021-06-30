package com.eims.controller;

import com.eims.mybatis.entity.GatherDetail;
import com.eims.vo.form.GatherDetailQueryForm;
import com.eims.service.GatherDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (GatherDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-25 22:42:20
 */
@RestController
public class GatherDetailController {
    /**
     * 服务对象
     */
    @Resource
    private GatherDetailService gatherDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/gatherDetail/one")
    public GatherDetail selectOne(Integer id) {
        return this.gatherDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param gatherDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/gatherDetail")
    public PageInfo<GatherDetail> queryAll(GatherDetailQueryForm gatherDetailQueryForm) {

        return this.gatherDetailService.queryAll(gatherDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/gatherDetail/search")
    public PageInfo<GatherDetail> queryBySearch(GatherDetailQueryForm gatherDetailQueryForm) {

        return this.gatherDetailService.queryBySearch(gatherDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/gatherDetail/screen")
    public PageInfo<GatherDetail> queryByScreen(GatherDetailQueryForm gatherDetailQueryForm) {

        return this.gatherDetailService.queryByScreen(gatherDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param gatherDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/gatherDetail")
    public GatherDetail insert(@RequestBody GatherDetail gatherDetail) {

        return this.gatherDetailService.insert(gatherDetail);
    }

    /**
     * 批量新增数据
     *
     * @param GatherDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/gatherDetail/batch")
    public boolean insertBatch(@RequestBody List<GatherDetail> GatherDetailList) {
        return this.gatherDetailService.insertBatch(GatherDetailList);
    }

    /**
     * 修改数据
     *
     * @param gatherDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/gatherDetail")
    public GatherDetail update(@RequestBody GatherDetail gatherDetail) {
        return this.gatherDetailService.update(gatherDetail);
    }

    /**
     * 批量修改数据
     *
     * @param gatherDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/gatherDetail/batch")
    public boolean updateBatch(@RequestBody List<GatherDetail> gatherDetailList) {
        return this.gatherDetailService.updateBatch(gatherDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/gatherDetail")
    public boolean deleteById(Integer id) {
        return this.gatherDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/gatherDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.gatherDetailService.deleteBatch(ids);
    }
}
