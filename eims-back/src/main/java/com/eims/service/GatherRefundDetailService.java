package com.eims.service;

import com.eims.mybatis.entity.GatherRefundDetail;
import com.eims.vo.form.GatherRefundDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (GatherRefundDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-30 19:36:24
 */
public interface GatherRefundDetailService {

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
    PageInfo<GatherRefundDetail> queryAll(GatherRefundDetailQueryForm gatherRefundDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherRefundDetailQueryForm
     * @return 对象列表
     */
    PageInfo<GatherRefundDetail> queryBySearch(GatherRefundDetailQueryForm gatherRefundDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherRefundDetailQueryForm
     * @return 对象列表
     */
    PageInfo<GatherRefundDetail> queryByScreen(GatherRefundDetailQueryForm gatherRefundDetailQueryForm);

    /**
     * 新增数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 实例对象
     */
    GatherRefundDetail insert(GatherRefundDetail gatherRefundDetail);

    /**
     * 批量新增数据
     *
     * @param GatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<GatherRefundDetail> GatherRefundDetailList);

    /**
     * 修改数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 实例对象
     */
    GatherRefundDetail update(GatherRefundDetail gatherRefundDetail);

    /**
     * 批量修改数据
     *
     * @param gatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<GatherRefundDetail> gatherRefundDetailList);

    /**
     * 通过主键删除数据
     *
     * @param gatherRefundDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer gatherRefundDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
