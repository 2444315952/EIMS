package com.eims.controller;

import com.eims.mybatis.entity.Blitem;
import com.eims.vo.form.BlitemQueryForm;
import com.eims.service.BlitemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Blitem)表控制层
 *
 * @author makejava
 * @since 2021-06-16 10:32:54
 */
@RestController
public class BlitemController {
    /**
     * 服务对象
     */
    @Resource
    private BlitemService blitemService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/blitem/one")
    public Blitem selectOne(Integer id) {
        return this.blitemService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param blitemQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/blitem")
    public PageInfo<Blitem> queryAll(BlitemQueryForm blitemQueryForm) {
        blitemQueryForm.setWorkPointId(1);
        return this.blitemService.queryAll(blitemQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param blitemQueryForm
     * @return 对象列表
     */
    @GetMapping("/blitem/search")
    public PageInfo<Blitem> queryBySearch(BlitemQueryForm blitemQueryForm) {
        blitemQueryForm.setWorkPointId(1);
        return this.blitemService.queryBySearch(blitemQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param blitemQueryForm
     * @return 对象列表
     */
    @GetMapping("/blitem/screen")
    public PageInfo<Blitem> queryByScreen(BlitemQueryForm blitemQueryForm) {
        blitemQueryForm.setWorkPointId(1);
        return this.blitemService.queryByScreen(blitemQueryForm);
    }

    /**
     * 新增数据
     *
     * @param blitem 实例对象
     * @return 实例对象
     */
    @PostMapping("/blitem")
    public Blitem insert(@RequestBody Blitem blitem) {
        blitem.setWorkPointId(1);
        //blitem.setWorkPointName("示例工作点");
        return this.blitemService.insert(blitem);
    }

    /**
     * 批量新增数据
     *
     * @param BlitemList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/blitem/batch")
    public boolean insertBatch(@RequestBody List<Blitem> BlitemList) {
        return this.blitemService.insertBatch(BlitemList);
    }

    /**
     * 修改数据
     *
     * @param blitem 实例对象
     * @return 实例对象
     */
    @PutMapping("/blitem")
    public Blitem update(@RequestBody Blitem blitem) {
        return this.blitemService.update(blitem);
    }

    /**
     * 批量修改数据
     *
     * @param blitemList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/blitem/batch")
    public boolean updateBatch(@RequestBody List<Blitem> blitemList) {
        return this.blitemService.updateBatch(blitemList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/blitem")
    public boolean deleteById(Integer id) {
        return this.blitemService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/blitem/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.blitemService.deleteBatch(ids);
    }
}
