package com.eims.service;

import com.eims.mybatis.entity.Inventory;
import com.eims.vo.form.InventoryQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Inventory)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:25:30
 */
public interface InventoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param inventoryId 主键
     * @return 实例对象
     */
    Inventory queryById(Integer inventoryId);

    /**
     * 查询所有数据
     *
     * @param inventoryQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Inventory> queryAll(InventoryQueryForm inventoryQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param inventoryQueryForm
     * @return 对象列表
     */
    PageInfo<Inventory> queryBySearch(InventoryQueryForm inventoryQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param inventoryQueryForm
     * @return 对象列表
     */
    PageInfo<Inventory> queryByScreen(InventoryQueryForm inventoryQueryForm);

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    Inventory insert(Inventory inventory);

    /**
     * 批量新增数据
     *
     * @param InventoryList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Inventory> InventoryList);

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    Inventory update(Inventory inventory);

    /**
     * 批量修改数据
     *
     * @param inventoryList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Inventory> inventoryList);

    /**
     * 通过主键删除数据
     *
     * @param inventoryId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer inventoryId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
