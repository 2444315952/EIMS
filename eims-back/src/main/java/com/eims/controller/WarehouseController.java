package com.eims.controller;

import com.eims.mybatis.entity.Warehouse;
import com.eims.vo.form.WarehouseQueryForm;
import com.eims.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Warehouse)表控制层
 *
 * @author makejava
 * @since 2021-06-04 18:20:02
 */
@RestController
public class WarehouseController {
    /**
     * 服务对象
     */
    @Resource
    private WarehouseService warehouseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/warehouse/one")
    public Warehouse selectOne(Integer id) {
        return this.warehouseService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param warehouseQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/warehouse")
    public PageInfo<Warehouse> queryAll(WarehouseQueryForm warehouseQueryForm) {
        warehouseQueryForm.setWorkPointId(1);
        return this.warehouseService.queryAll(warehouseQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param warehouseQueryForm
     * @return 对象列表
     */
    @GetMapping("/warehouse/search")
    public PageInfo<Warehouse> queryBySearch(WarehouseQueryForm warehouseQueryForm) {
        warehouseQueryForm.setWorkPointId(1);
        return this.warehouseService.queryBySearch(warehouseQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param warehouseQueryForm
     * @return 对象列表
     */
    @GetMapping("/warehouse/screen")
    public PageInfo<Warehouse> queryByScreen(WarehouseQueryForm warehouseQueryForm) {
        warehouseQueryForm.setWorkPointId(1);
        return this.warehouseService.queryByScreen(warehouseQueryForm);
    }

    /**
     * 新增数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    @PostMapping("/warehouse")
    public Warehouse insert(@RequestBody Warehouse warehouse) {
        warehouse.setWorkPointId(1);
        warehouse.setWorkPointName("示例工作点");
        return this.warehouseService.insert(warehouse);
    }

    /**
     * 批量新增数据
     *
     * @param WarehouseList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/warehouse/batch")
    public boolean insertBatch(@RequestBody List<Warehouse> WarehouseList) {
        return this.warehouseService.insertBatch(WarehouseList);
    }

    /**
     * 修改数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    @PutMapping("/warehouse")
    public Warehouse update(@RequestBody Warehouse warehouse) {
        return this.warehouseService.update(warehouse);
    }

    /**
     * 批量修改数据
     *
     * @param warehouseList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/warehouse/batch")
    public boolean updateBatch(@RequestBody List<Warehouse> warehouseList) {
        return this.warehouseService.updateBatch(warehouseList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/warehouse")
    public boolean deleteById(Integer id) {
        return this.warehouseService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/warehouse/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.warehouseService.deleteBatch(ids);
    }
}
