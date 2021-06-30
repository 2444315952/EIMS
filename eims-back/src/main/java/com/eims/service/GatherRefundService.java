package com.eims.service;

import com.eims.mybatis.entity.GatherRefund;
import com.eims.vo.form.GatherRefundQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (GatherRefund)表服务接口
 *
 * @author makejava
 * @since 2021-06-30 19:36:17
 */
public interface GatherRefundService {

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
    PageInfo<GatherRefund> queryAll(GatherRefundQueryForm gatherRefundQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherRefundQueryForm
     * @return 对象列表
     */
    PageInfo<GatherRefund> queryBySearch(GatherRefundQueryForm gatherRefundQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherRefundQueryForm
     * @return 对象列表
     */
    PageInfo<GatherRefund> queryByScreen(GatherRefundQueryForm gatherRefundQueryForm);

    /**
     * 新增数据
     *
     * @param gatherRefund 实例对象
     * @return 实例对象
     */
    GatherRefund insert(GatherRefund gatherRefund);

    /**
     * 批量新增数据
     *
     * @param GatherRefundList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<GatherRefund> GatherRefundList);

    /**
     * 修改数据
     *
     * @param gatherRefund 实例对象
     * @return 实例对象
     */
    GatherRefund update(GatherRefund gatherRefund);

    /**
     * 批量修改数据
     *
     * @param gatherRefundList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<GatherRefund> gatherRefundList);

    /**
     * 通过主键删除数据
     *
     * @param gatherRefundId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer gatherRefundId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
