package com.eims.service;

import com.eims.mybatis.entity.OutboundDetail;
import com.eims.vo.form.OutboundDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (OutboundDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-08 09:47:42
 */
public interface OutboundDetailService {

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
    PageInfo<OutboundDetail> queryAll(OutboundDetailQueryForm outboundDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param outboundDetailQueryForm
     * @return 对象列表
     */
    PageInfo<OutboundDetail> queryBySearch(OutboundDetailQueryForm outboundDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param outboundDetailQueryForm
     * @return 对象列表
     */
    PageInfo<OutboundDetail> queryByScreen(OutboundDetailQueryForm outboundDetailQueryForm);

    /**
     * 新增数据
     *
     * @param outboundDetail 实例对象
     * @return 实例对象
     */
    OutboundDetail insert(OutboundDetail outboundDetail);

    /**
     * 批量新增数据
     *
     * @param OutboundDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<OutboundDetail> OutboundDetailList);

    /**
     * 修改数据
     *
     * @param outboundDetail 实例对象
     * @return 实例对象
     */
    OutboundDetail update(OutboundDetail outboundDetail);

    /**
     * 批量修改数据
     *
     * @param outboundDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<OutboundDetail> outboundDetailList);

    /**
     * 通过主键删除数据
     *
     * @param outboundDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer outboundDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
