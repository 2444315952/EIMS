package com.eims.mybatis.dao;

import com.eims.mybatis.entity.GatherRefund;
import com.eims.vo.form.GatherRefundQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GatherRefund)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-30 19:36:16
 */
@Mapper
public interface GatherRefundDao {

    /**
     * 通过ID查询单条数据
     *
     * @param gatherRefundId 主键
     * @return 实例对象
     */
    GatherRefund queryById(Integer gatherRefundId);

    /**
     * 查询所有数据
     *
     * @param gatherRefundQueryForm 实例对象
     * @return 实例对象
     */
    List<GatherRefund> queryAll(GatherRefundQueryForm gatherRefundQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param gatherRefundQueryForm 实例对象
     * @return 对象列表
     */
    List<GatherRefund> queryOrByPojo(GatherRefundQueryForm gatherRefundQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param gatherRefundQueryForm 实例对象
     * @return 对象列表
     */
    List<GatherRefund> queryAndByPojo(GatherRefundQueryForm gatherRefundQueryForm);

    /**
     * 新增数据
     *
     * @param gatherRefund 实例对象
     * @return 影响行数
     */
    int insert(GatherRefund gatherRefund);

    /**
     * 批量新增数据
     *
     * @param gatherRefundList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<GatherRefund> gatherRefundList);

    /**
     * 修改数据
     *
     * @param gatherRefund 实例对象
     * @return 影响行数
     */
    int update(GatherRefund gatherRefund);

    /**
     * 批量修改数据
     *
     * @param gatherRefundList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<GatherRefund> gatherRefundList);

    /**
     * 通过主键删除数据
     *
     * @param gatherRefundId 主键
     * @return 影响行数
     */
    int deleteById(Integer gatherRefundId);

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
     * @param gatherRefundList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<GatherRefund> gatherRefundList);
}

