package com.eims.controller;

import com.eims.mybatis.entity.Gather;
import com.eims.vo.form.GatherQueryForm;
import com.eims.service.GatherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Gather)表控制层
 *
 * @author makejava
 * @since 2021-06-27 20:23:03
 */
@RestController
public class GatherController {
    /**
     * 服务对象
     */
    @Resource
    private GatherService gatherService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/gather/one")
    public Gather selectOne(Integer id) {
        return this.gatherService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param gatherQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/gather")
    public PageInfo<Gather> queryAll(GatherQueryForm gatherQueryForm) {
        gatherQueryForm.setWorkPointId(1);
        return this.gatherService.queryAll(gatherQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherQueryForm
     * @return 对象列表
     */
    @GetMapping("/gather/search")
    public PageInfo<Gather> queryBySearch(GatherQueryForm gatherQueryForm) {
        gatherQueryForm.setWorkPointId(1);
        return this.gatherService.queryBySearch(gatherQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherQueryForm
     * @return 对象列表
     */
    @GetMapping("/gather/screen")
    public PageInfo<Gather> queryByScreen(GatherQueryForm gatherQueryForm) {
        gatherQueryForm.setWorkPointId(1);
        return this.gatherService.queryByScreen(gatherQueryForm);
    }

    /**
     * 新增数据
     *
     * @param gather 实例对象
     * @return 实例对象
     */
    @PostMapping("/gather")
    public Gather insert(@RequestBody Gather gather) {
        gather.setWorkPointId(1);
        System.out.println(gather);

        return this.gatherService.insert(gather);
    }

    /**
     * 批量新增数据
     *
     * @param GatherList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/gather/batch")
    public boolean insertBatch(@RequestBody List<Gather> GatherList) {
        return this.gatherService.insertBatch(GatherList);
    }

    /**
     * 修改数据
     *
     * @param gather 实例对象
     * @return 实例对象
     */
    @PutMapping("/gather")
    public Gather update(@RequestBody Gather gather) {
        return this.gatherService.update(gather);
    }

    /**
     * 批量修改数据
     *
     * @param gatherList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/gather/batch")
    public boolean updateBatch(@RequestBody List<Gather> gatherList) {
        return this.gatherService.updateBatch(gatherList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/gather")
    public boolean deleteById(Integer id) {
        return this.gatherService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/gather/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.gatherService.deleteBatch(ids);
    }
}
