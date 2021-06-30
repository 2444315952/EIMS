package com.eims.mybatis.dao;

import com.eims.mybatis.entity.GatherDetail;
import com.eims.vo.form.GatherDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GatherDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-25 22:42:19
 */
@Mapper
public interface GatherDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param gatherDetailId 主键
     * @return 实例对象
     */
    GatherDetail queryById(Integer gatherDetailId);

    /**
     * 查询所有数据
     *
     * @param gatherDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<GatherDetail> queryAll(GatherDetailQueryForm gatherDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param gatherDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<GatherDetail> queryOrByPojo(GatherDetailQueryForm gatherDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param gatherDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<GatherDetail> queryAndByPojo(GatherDetailQueryForm gatherDetailQueryForm);

    /**
     * 新增数据
     *
     * @param gatherDetail 实例对象
     * @return 影响行数
     */
    int insert(GatherDetail gatherDetail);

    /**
     * 批量新增数据
     *
     * @param gatherDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<GatherDetail> gatherDetailList);

    /**
     * 修改数据
     *
     * @param gatherDetail 实例对象
     * @return 影响行数
     */
    int update(GatherDetail gatherDetail);

    /**
     * 批量修改数据
     *
     * @param gatherDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<GatherDetail> gatherDetailList);

    /**
     * 通过主键删除数据
     *
     * @param gatherDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer gatherDetailId);

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
     * @param gatherDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<GatherDetail> gatherDetailList);
}

