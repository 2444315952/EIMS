package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Blitem;
import com.eims.vo.form.BlitemQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Blitem)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-16 10:32:52
 */
@Mapper
public interface BlitemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param blitemId 主键
     * @return 实例对象
     */
    Blitem queryById (Integer blitemId);

    /**
     * 查询所有数据
     *
     * @param blitemQueryForm 实例对象
     * @return 实例对象
     */
    List<Blitem> queryAll(BlitemQueryForm blitemQueryForm);

    /**
     * 审核盘点单改库存
     * @param physicalInventory
     * @param warehouseId
     * @param productId
     * @return
     */
    int auditStorage(@Param("physicalInventory") Integer physicalInventory, @Param("warehouseId") Integer warehouseId,@Param("productId") Integer productId);

    /**
     * 通过实体作为或者条件查询
     *
     * @param blitemQueryForm 实例对象
     * @return 对象列表
     */
    List<Blitem> queryOrByPojo(BlitemQueryForm blitemQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param blitemQueryForm 实例对象
     * @return 对象列表
     */
    List<Blitem> queryAndByPojo(BlitemQueryForm blitemQueryForm);

    /**
     * 新增数据
     *
     * @param blitem 实例对象
     * @return 影响行数
     */
    int insert(Blitem blitem);

    /**
     * 批量新增数据
     *
     * @param blitemList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Blitem> blitemList);

    /**
     * 修改数据
     *
     * @param blitem 实例对象
     * @return 影响行数
     */
    int update(Blitem blitem);

    /**
     * 批量修改数据
     *
     * @param blitemList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Blitem> blitemList);

    /**
     * 通过主键删除数据
     *
     * @param blitemId 主键
     * @return 影响行数
     */
    int deleteById(Integer blitemId);

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
     * @param blitemList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Blitem> blitemList);
}

