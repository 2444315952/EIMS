package com.eims.mybatis.dao;

import com.eims.mybatis.entity.StockOut;
import com.eims.vo.form.StockOutQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (StockOut)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-08 09:48:07
 */
@Mapper
public interface StockOutDao {

    /**
     * 通过ID查询单条数据
     *
     * @param stockOutId 主键
     * @return 实例对象
     */
    StockOut queryById(Integer stockOutId);

    /**
     * 查询所有数据
     *
     * @param stockOutQueryForm 实例对象
     * @return 实例对象
     */
    List<StockOut> queryAll(StockOutQueryForm stockOutQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param stockOutQueryForm 实例对象
     * @return 对象列表
     */
    List<StockOut> queryOrByPojo(StockOutQueryForm stockOutQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param stockOutQueryForm 实例对象
     * @return 对象列表
     */
    List<StockOut> queryAndByPojo(StockOutQueryForm stockOutQueryForm);

    /**
     * 新增数据
     *
     * @param stockOut 实例对象
     * @return 影响行数
     */
    int insert(StockOut stockOut);

    /**
     * 批量新增数据
     *
     * @param stockOutList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<StockOut> stockOutList);

    /**
     * 修改数据
     *
     * @param stockOut 实例对象
     * @return 影响行数
     */
    int update(StockOut stockOut);

    /**
     * 批量修改数据
     *
     * @param stockOutList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<StockOut> stockOutList);

    /**
     * 通过主键删除数据
     *
     * @param stockOutId 主键
     * @return 影响行数
     */
    int deleteById(Integer stockOutId);

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
     * @param stockOutList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<StockOut> stockOutList);
}

