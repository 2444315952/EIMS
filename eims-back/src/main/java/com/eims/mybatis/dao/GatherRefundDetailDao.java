package com.eims.mybatis.dao;

import com.eims.mybatis.entity.GatherRefundDetail;
import com.eims.vo.form.GatherRefundDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GatherRefundDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-30 19:36:23
 */
@Mapper
public interface GatherRefundDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param gatherRefundDetailId 主键
     * @return 实例对象
     */
    GatherRefundDetail queryById(Integer gatherRefundDetailId);

    /**
     * 查询所有数据
     *
     * @param gatherRefundDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<GatherRefundDetail> queryAll(GatherRefundDetailQueryForm gatherRefundDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param gatherRefundDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<GatherRefundDetail> queryOrByPojo(GatherRefundDetailQueryForm gatherRefundDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param gatherRefundDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<GatherRefundDetail> queryAndByPojo(GatherRefundDetailQueryForm gatherRefundDetailQueryForm);

    /**
     * 新增数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 影响行数
     */
    int insert(GatherRefundDetail gatherRefundDetail);

    /**
     * 批量新增数据
     *
     * @param gatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<GatherRefundDetail> gatherRefundDetailList);

    /**
     * 修改数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 影响行数
     */
    int update(GatherRefundDetail gatherRefundDetail);

    /**
     * 批量修改数据
     *
     * @param gatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<GatherRefundDetail> gatherRefundDetailList);

    /**
     * 通过主键删除数据
     *
     * @param gatherRefundDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer gatherRefundDetailId);

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
     * @param gatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<GatherRefundDetail> gatherRefundDetailList);
}

