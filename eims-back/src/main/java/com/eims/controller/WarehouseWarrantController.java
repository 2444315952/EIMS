package com.eims.controller;

import com.eims.mybatis.entity.WarehouseWarrant;
import com.eims.vo.form.WarehouseWarrantQueryForm;
import com.eims.service.WarehouseWarrantService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (WarehouseWarrant)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:27:23
 */
@RestController
public class WarehouseWarrantController {
    /**
     * 服务对象
     */
    @Resource
    private WarehouseWarrantService warehouseWarrantService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/warehouseWarrant/one")
    public WarehouseWarrant selectOne(Integer id) {
        return this.warehouseWarrantService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param warehouseWarrantQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/warehouseWarrant")
    public PageInfo<WarehouseWarrant> queryAll(WarehouseWarrantQueryForm warehouseWarrantQueryForm) {
        warehouseWarrantQueryForm.setCompanyId(1);
        return this.warehouseWarrantService.queryAll(warehouseWarrantQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param warehouseWarrantQueryForm
     * @return 对象列表
     */
    @GetMapping("/warehouseWarrant/search")
    public PageInfo<WarehouseWarrant> queryBySearch(WarehouseWarrantQueryForm warehouseWarrantQueryForm) {
        warehouseWarrantQueryForm.setCompanyId(1);
        return this.warehouseWarrantService.queryBySearch(warehouseWarrantQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param warehouseWarrantQueryForm
     * @return 对象列表
     */
    @GetMapping("/warehouseWarrant/screen")
    public PageInfo<WarehouseWarrant> queryByScreen(WarehouseWarrantQueryForm warehouseWarrantQueryForm) {
        warehouseWarrantQueryForm.setCompanyId(1);
        return this.warehouseWarrantService.queryByScreen(warehouseWarrantQueryForm);
    }

    /**
     * 新增数据
     *
     * @param warehouseWarrant 实例对象
     * @return 实例对象
     */
    @PostMapping("/warehouseWarrant")
    public WarehouseWarrant insert(@RequestBody WarehouseWarrant warehouseWarrant) {
        warehouseWarrant.setCompanyId(1);
        warehouseWarrant.setWorkPointId(1);
        return this.warehouseWarrantService.insert(warehouseWarrant);
    }

    /**
     * 批量新增数据
     *
     * @param WarehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/warehouseWarrant/batch")
    public boolean insertBatch(@RequestBody List<WarehouseWarrant> WarehouseWarrantList) {
        return this.warehouseWarrantService.insertBatch(WarehouseWarrantList);
    }

    /**
     * 修改数据
     *
     * @param warehouseWarrant 实例对象
     * @return 实例对象
     */
    @PutMapping("/warehouseWarrant")
    public WarehouseWarrant update(@RequestBody WarehouseWarrant warehouseWarrant) {
        return this.warehouseWarrantService.update(warehouseWarrant);
    }

    /**
     * 批量修改数据
     *
     * @param warehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/warehouseWarrant/batch")
    public boolean updateBatch(@RequestBody List<WarehouseWarrant> warehouseWarrantList) {
        return this.warehouseWarrantService.updateBatch(warehouseWarrantList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/warehouseWarrant")
    public boolean deleteById(Integer id) {
        return this.warehouseWarrantService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/warehouseWarrant/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.warehouseWarrantService.deleteBatch(ids);
    }
}
