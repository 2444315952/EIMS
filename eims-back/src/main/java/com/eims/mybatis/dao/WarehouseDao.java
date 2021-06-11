package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Warehouse;
import com.eims.vo.form.WarehouseQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Warehouse)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:27:22
 */
@Mapper
public interface WarehouseDao {

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
    List<Warehouse> queryAll(WarehouseQueryForm warehouseQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param warehouseQueryForm 实例对象
     * @return 对象列表
     */
    List<Warehouse> queryOrByPojo(WarehouseQueryForm warehouseQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param warehouseQueryForm 实例对象
     * @return 对象列表
     */
    List<Warehouse> queryAndByPojo(WarehouseQueryForm warehouseQueryForm);

    /**
     * 新增数据
     *
     * @param warehouse 实例对象
     * @return 影响行数
     */
    int insert(Warehouse warehouse);

    /**
     * 批量新增数据
     *
     * @param warehouseList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Warehouse> warehouseList);

    /**
     * 修改数据
     *
     * @param warehouse 实例对象
     * @return 影响行数
     */
    int update(Warehouse warehouse);

    /**
     * 批量修改数据
     *
     * @param warehouseList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Warehouse> warehouseList);

    /**
     * 通过主键删除数据
     *
     * @param warehouseId 主键
     * @return 影响行数
     */
    int deleteById(Integer warehouseId);

    /**
     * 通过主键列表删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    int deleteBatch(List<Integer> ids);

    /**
     * 批量对象列表删除数据
     *
     * @param warehouseList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Warehouse> warehouseList);
}

