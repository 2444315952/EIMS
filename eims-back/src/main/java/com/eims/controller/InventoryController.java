package com.eims.controller;

import com.eims.mybatis.entity.Inventory;
import com.eims.service.WarehouseWarrantService;
import com.eims.vo.form.InventoryQueryForm;
import com.eims.service.InventoryService;
import com.eims.vo.table.InventoryTable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Inventory)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:30
 */
@RestController
public class InventoryController {
    /**
     * 服务对象
     */
    @Resource
    private InventoryService inventoryService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/inventory/one")
    public Inventory selectOne(Integer id) {
        return this.inventoryService.queryById(id);
    }

    /**
     * 查询完整库存信息
     * @param inventoryQueryForm
     * @return
     */
    @GetMapping("/inventory/all")
    public PageInfo<InventoryTable> queryInventoryVo(InventoryQueryForm inventoryQueryForm){
        inventoryQueryForm.setCompanyId(1);
        return this.inventoryService.queryInventoryVo(inventoryQueryForm);
    }

    /**
     * 查询所有数据
     *
     * @param inventoryQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/inventory")
    public PageInfo<Inventory> queryAll(InventoryQueryForm inventoryQueryForm) {
        inventoryQueryForm.setCompanyId(1);
        return this.inventoryService.queryAll(inventoryQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param inventoryQueryForm
     * @return 对象列表
     */
    @GetMapping("/inventory/search")
    public PageInfo<Inventory> queryBySearch(InventoryQueryForm inventoryQueryForm) {
        inventoryQueryForm.setCompanyId(1);
        return this.inventoryService.queryBySearch(inventoryQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param inventoryQueryForm
     * @return 对象列表
     */
    @GetMapping("/inventory/screen")
    public PageInfo<Inventory> queryByScreen(InventoryQueryForm inventoryQueryForm) {
        inventoryQueryForm.setCompanyId(1);
        return this.inventoryService.queryByScreen(inventoryQueryForm);
    }

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @PostMapping("/inventory")
    public Inventory insert(@RequestBody Inventory inventory) {
        inventory.setCompanyId(1);
        return this.inventoryService.insert(inventory);
    }

    /**
     * 批量新增数据
     *
     * @param InventoryList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/inventory/batch")
    public boolean insertBatch(@RequestBody List<Inventory> InventoryList) {
        return this.inventoryService.insertBatch(InventoryList);
    }

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @PutMapping("/inventory")
    public Inventory update(@RequestBody Inventory inventory) {
        return this.inventoryService.update(inventory);
    }



    /**
     * 批量修改数据
     *
     * @param inventoryList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/inventory/batch")
    public boolean updateBatch(@RequestBody List<Inventory> inventoryList) {
        return this.inventoryService.updateBatch(inventoryList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/inventory")
    public boolean deleteById(Integer id) {
        return this.inventoryService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/inventory/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.inventoryService.deleteBatch(ids);
    }
}
