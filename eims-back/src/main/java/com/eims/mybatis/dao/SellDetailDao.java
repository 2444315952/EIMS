package com.eims.mybatis.dao;

import com.eims.mybatis.entity.SellDetail;
import com.eims.vo.form.SellDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (SellDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:26:48
 */
@Mapper
public interface SellDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sellDetailId 主键
     * @return 实例对象
     */
    SellDetail queryById(Integer sellDetailId);

    /**
     * 查询所有数据
     *
     * @param sellDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<SellDetail> queryAll(SellDetailQueryForm sellDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param sellDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<SellDetail> queryOrByPojo(SellDetailQueryForm sellDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param sellDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<SellDetail> queryAndByPojo(SellDetailQueryForm sellDetailQueryForm);

    /**
     * 新增数据
     *
     * @param sellDetail 实例对象
     * @return 影响行数
     */
    int insert(SellDetail sellDetail);

    /**
     * 批量新增数据
     *
     * @param sellDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SellDetail> sellDetailList);

    /**
     * 修改数据
     *
     * @param sellDetail 实例对象
     * @return 影响行数
     */
    int update(SellDetail sellDetail);

    /**
     * 批量修改数据
     *
     * @param sellDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<SellDetail> sellDetailList);

    /**
     * 通过主键删除数据
     *
     * @param sellDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer sellDetailId);

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
     * @param sellDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<SellDetail> sellDetailList);


    int deleteSellDetilIdAndNotInKeyIds( Map<String, Object> sellDetailList);
}

