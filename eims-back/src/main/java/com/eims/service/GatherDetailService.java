package com.eims.service;

import com.eims.mybatis.entity.GatherDetail;
import com.eims.vo.form.GatherDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (GatherDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-25 22:42:19
 */
public interface GatherDetailService {

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
    PageInfo<GatherDetail> queryAll(GatherDetailQueryForm gatherDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherDetailQueryForm
     * @return 对象列表
     */
    PageInfo<GatherDetail> queryBySearch(GatherDetailQueryForm gatherDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherDetailQueryForm
     * @return 对象列表
     */
    PageInfo<GatherDetail> queryByScreen(GatherDetailQueryForm gatherDetailQueryForm);

    /**
     * 新增数据
     *
     * @param gatherDetail 实例对象
     * @return 实例对象
     */
    GatherDetail insert(GatherDetail gatherDetail);

    /**
     * 批量新增数据
     *
     * @param GatherDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<GatherDetail> GatherDetailList);

    /**
     * 修改数据
     *
     * @param gatherDetail 实例对象
     * @return 实例对象
     */
    GatherDetail update(GatherDetail gatherDetail);

    /**
     * 批量修改数据
     *
     * @param gatherDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<GatherDetail> gatherDetailList);

    /**
     * 通过主键删除数据
     *
     * @param gatherDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer gatherDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
