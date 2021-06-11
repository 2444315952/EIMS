package com.eims.mybatis.dao;

import com.eims.mybatis.entity.WarehouseWarrant;
import com.eims.vo.form.WarehouseWarrantQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (WarehouseWarrant)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-08 09:48:20
 */
@Mapper
public interface WarehouseWarrantDao {

    /**
     * 通过ID查询单条数据
     *
     * @param warehouseWarrantId 主键
     * @return 实例对象
     */
    WarehouseWarrant queryById(Integer warehouseWarrantId);

    /**
     * 查询所有数据
     *
     * @param warehouseWarrantQueryForm 实例对象
     * @return 实例对象
     */
    List<WarehouseWarrant> queryAll(WarehouseWarrantQueryForm warehouseWarrantQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param warehouseWarrantQueryForm 实例对象
     * @return 对象列表
     */
    List<WarehouseWarrant> queryOrByPojo(WarehouseWarrantQueryForm warehouseWarrantQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param warehouseWarrantQueryForm 实例对象
     * @return 对象列表
     */
    List<WarehouseWarrant> queryAndByPojo(WarehouseWarrantQueryForm warehouseWarrantQueryForm);

    /**
     * 新增数据
     *
     * @param warehouseWarrant 实例对象
     * @return 影响行数
     */
    int insert(WarehouseWarrant warehouseWarrant);

    /**
     * 批量新增数据
     *
     * @param warehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<WarehouseWarrant> warehouseWarrantList);

    /**
     * 修改数据
     *
     * @param warehouseWarrant 实例对象
     * @return 影响行数
     */
    int update(WarehouseWarrant warehouseWarrant);

    /**
     * 批量修改数据
     *
     * @param warehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<WarehouseWarrant> warehouseWarrantList);

    /**
     * 通过主键删除数据
     *
     * @param warehouseWarrantId 主键
     * @return 影响行数
     */
    int deleteById(Integer warehouseWarrantId);

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
     * @param warehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<WarehouseWarrant> warehouseWarrantList);
}

