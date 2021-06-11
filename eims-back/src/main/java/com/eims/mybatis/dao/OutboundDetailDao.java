package com.eims.mybatis.dao;

import com.eims.mybatis.entity.OutboundDetail;
import com.eims.vo.form.OutboundDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (OutboundDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-08 09:47:42
 */
@Mapper
public interface OutboundDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param outboundDetailId 主键
     * @return 实例对象
     */
    OutboundDetail queryById(Integer outboundDetailId);

    /**
     * 查询所有数据
     *
     * @param outboundDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<OutboundDetail> queryAll(OutboundDetailQueryForm outboundDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param outboundDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<OutboundDetail> queryOrByPojo(OutboundDetailQueryForm outboundDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param outboundDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<OutboundDetail> queryAndByPojo(OutboundDetailQueryForm outboundDetailQueryForm);

    /**
     * 新增数据
     *
     * @param outboundDetail 实例对象
     * @return 影响行数
     */
    int insert(OutboundDetail outboundDetail);

    /**
     * 批量新增数据
     *
     * @param outboundDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<OutboundDetail> outboundDetailList);

    /**
     * 修改数据
     *
     * @param outboundDetail 实例对象
     * @return 影响行数
     */
    int update(OutboundDetail outboundDetail);

    /**
     * 批量修改数据
     *
     * @param outboundDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<OutboundDetail> outboundDetailList);

    /**
     * 通过主键删除数据
     *
     * @param outboundDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer outboundDetailId);

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
     * @param outboundDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<OutboundDetail> outboundDetailList);
}

