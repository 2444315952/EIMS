package com.eims.mybatis.dao;

import com.eims.mybatis.entity.SellOrderDetail;
import com.eims.vo.form.SellOrderDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (SellOrderDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:26:51
 */
@Mapper
public interface SellOrderDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sellOrderDetailId 主键
     * @return 实例对象
     */
    SellOrderDetail queryById(Integer sellOrderDetailId);

    /**
     * 查询所有数据
     *
     * @param sellOrderDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<SellOrderDetail> queryAll(SellOrderDetailQueryForm sellOrderDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param sellOrderDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<SellOrderDetail> queryOrByPojo(SellOrderDetailQueryForm sellOrderDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param sellOrderDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<SellOrderDetail> queryAndByPojo(SellOrderDetailQueryForm sellOrderDetailQueryForm);

    /**
     * 新增数据
     *
     * @param sellOrderDetail 实例对象
     * @return 影响行数
     */
    int insert(SellOrderDetail sellOrderDetail);

    /**
     * 批量新增数据
     *
     * @param sellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SellOrderDetail> sellOrderDetailList);

    /**
     * 修改数据
     *
     * @param sellOrderDetail 实例对象
     * @return 影响行数
     */
    int update(SellOrderDetail sellOrderDetail);

    /**
     * 批量修改数据
     *
     * @param sellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<SellOrderDetail> sellOrderDetailList);

    /**
     * 通过主键删除数据
     *
     * @param sellOrderDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer sellOrderDetailId);

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
     * @param sellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<SellOrderDetail> sellOrderDetailList);

    int deleteByPurchIdAndNotInKeyIds( Map<String, Object> sellOrderDetailList);
}

