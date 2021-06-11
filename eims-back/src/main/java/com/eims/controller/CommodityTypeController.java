package com.eims.controller;

import com.eims.mybatis.entity.CommodityType;
import com.eims.vo.form.CommodityTypeQueryForm;
import com.eims.service.CommodityTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (CommodityType)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:24:31
 */
@RestController
public class CommodityTypeController {
    /**
     * 服务对象
     */
    @Resource
    private CommodityTypeService commodityTypeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/commodityType/one")
    public CommodityType selectOne(Integer id) {
        return this.commodityTypeService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param commodityTypeQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/commodityType")
    public PageInfo<CommodityType> queryAll(CommodityTypeQueryForm commodityTypeQueryForm) {
        return this.commodityTypeService.queryAll(commodityTypeQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param commodityTypeQueryForm
     * @return 对象列表
     */
    @GetMapping("/commodityType/search")
    public PageInfo<CommodityType> queryBySearch(CommodityTypeQueryForm commodityTypeQueryForm) {
        return this.commodityTypeService.queryBySearch(commodityTypeQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param commodityTypeQueryForm
     * @return 对象列表
     */
    @GetMapping("/commodityType/screen")
    public PageInfo<CommodityType> queryByScreen(CommodityTypeQueryForm commodityTypeQueryForm) {
        return this.commodityTypeService.queryByScreen(commodityTypeQueryForm);
    }

    /**
     * 新增数据
     *
     * @param commodityType 实例对象
     * @return 实例对象
     */
    @PostMapping("/commodityType")
    public CommodityType insert(@RequestBody CommodityType commodityType) {
        return this.commodityTypeService.insert(commodityType);
    }

    /**
     * 批量新增数据
     *
     * @param CommodityTypeList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/commodityType/batch")
    public boolean insertBatch(@RequestBody List<CommodityType> CommodityTypeList) {
        return this.commodityTypeService.insertBatch(CommodityTypeList);
    }

    /**
     * 修改数据
     *
     * @param commodityType 实例对象
     * @return 实例对象
     */
    @PutMapping("/commodityType")
    public CommodityType update(@RequestBody CommodityType commodityType) {
        return this.commodityTypeService.update(commodityType);
    }

    /**
     * 批量修改数据
     *
     * @param commodityTypeList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/commodityType/batch")
    public boolean updateBatch(@RequestBody List<CommodityType> commodityTypeList) {
        return this.commodityTypeService.updateBatch(commodityTypeList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/commodityType")
    public boolean deleteById(Integer id) {
        return this.commodityTypeService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/commodityType/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.commodityTypeService.deleteBatch(ids);
    }
}
