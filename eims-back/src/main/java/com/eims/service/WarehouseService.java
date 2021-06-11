package com.eims.service;

import com.eims.mybatis.entity.Warehouse;
import com.eims.vo.form.WarehouseQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Warehouse)表服务接口
 *
 * @author makejava
 * @since 2021-06-04 18:20:02
 */
public interface WarehouseService {

    /**
     * 通过ID查询单条数据
     *
     * @param warehouseId 主键
     * @return 实例对象
     */
    Warehouse queryById(Integer warehouseId);

    /**
     * 查询所有数据
     *
     * @param warehouseQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Warehouse> queryAll(WarehouseQueryForm warehouseQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param warehouseQueryForm
     * @return 对象列表
     */
    PageInfo<Warehouse> queryBySearch(WarehouseQueryForm warehouseQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param warehouseQueryForm
     * @return 对象列表
     */
    PageInfo<Warehouse> queryByScreen(WarehouseQueryForm warehouseQueryForm);

    /**
     * 新增数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    Warehouse insert(Warehouse warehouse);

    /**
     * 批量新增数据
     *
     * @param WarehouseList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Warehouse> WarehouseList);

    /**
     * 修改数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    Warehouse update(Warehouse warehouse);

    /**
     * 批量修改数据
     *
     * @param warehouseList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Warehouse> warehouseList);

    /**
     * 通过主键删除数据
     *
     * @param warehouseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer warehouseId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
