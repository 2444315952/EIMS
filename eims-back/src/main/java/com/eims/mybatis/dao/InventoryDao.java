package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Inventory;
import com.eims.vo.form.InventoryQueryForm;
import com.eims.vo.table.InventoryTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Inventory)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:25:30
 */
@Mapper
public interface InventoryDao {

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
    List<Inventory> queryAll(InventoryQueryForm inventoryQueryForm);

    /**
     * 查询完整库存信息
     * @param inventoryQueryForm
     * @return
     */
    List<InventoryTable> queryInventoryVo(InventoryQueryForm inventoryQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param inventoryQueryForm 实例对象
     * @return 对象列表
     */
    List<Inventory> queryOrByPojo(InventoryQueryForm inventoryQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param inventoryQueryForm 实例对象
     * @return 对象列表
     */
    List<Inventory> queryAndByPojo(InventoryQueryForm inventoryQueryForm);

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 影响行数
     */
    int insert(Inventory inventory);

    /**
     * 批量新增数据
     *
     * @param inventoryList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Inventory> inventoryList);

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 影响行数
     */
    int update(Inventory inventory);


    /**
     * 批量修改数据
     *
     * @param inventoryList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Inventory> inventoryList);

    /**
     * 通过主键删除数据
     *
     * @param inventoryId 主键
     * @return 影响行数
     */
    int deleteById(Integer inventoryId);

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
     * @param inventoryList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Inventory> inventoryList);
}

