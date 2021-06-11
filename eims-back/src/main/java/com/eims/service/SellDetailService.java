package com.eims.service;

import com.eims.mybatis.entity.SellDetail;
import com.eims.vo.form.SellDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (SellDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:48
 */
public interface SellDetailService {

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
    PageInfo<SellDetail> queryAll(SellDetailQueryForm sellDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param sellDetailQueryForm
     * @return 对象列表
     */
    PageInfo<SellDetail> queryBySearch(SellDetailQueryForm sellDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param sellDetailQueryForm
     * @return 对象列表
     */
    PageInfo<SellDetail> queryByScreen(SellDetailQueryForm sellDetailQueryForm);

    /**
     * 新增数据
     *
     * @param sellDetail 实例对象
     * @return 实例对象
     */
    SellDetail insert(SellDetail sellDetail);

    /**
     * 批量新增数据
     *
     * @param SellDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<SellDetail> SellDetailList);

    /**
     * 修改数据
     *
     * @param sellDetail 实例对象
     * @return 实例对象
     */
    SellDetail update(SellDetail sellDetail);

    /**
     * 批量修改数据
     *
     * @param sellDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<SellDetail> sellDetailList);

    /**
     * 通过主键删除数据
     *
     * @param sellDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer sellDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
