package com.eims.service;

import com.eims.mybatis.entity.SellOrderDetail;
import com.eims.vo.form.SellOrderDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (SellOrderDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:51
 */
public interface SellOrderDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param sellOrderDetailId 主键
     * @return 实例对象
     */
    SellOrderDetail queryById(Integer sellOrderDetailId);

    /**
     * 查询所有数据
     *
     * @param sellOrderDetailQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<SellOrderDetail> queryAll(SellOrderDetailQueryForm sellOrderDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param sellOrderDetailQueryForm
     * @return 对象列表
     */
    PageInfo<SellOrderDetail> queryBySearch(SellOrderDetailQueryForm sellOrderDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param sellOrderDetailQueryForm
     * @return 对象列表
     */
    PageInfo<SellOrderDetail> queryByScreen(SellOrderDetailQueryForm sellOrderDetailQueryForm);

    /**
     * 新增数据
     *
     * @param sellOrderDetail 实例对象
     * @return 实例对象
     */
    SellOrderDetail insert(SellOrderDetail sellOrderDetail);

    /**
     * 批量新增数据
     *
     * @param SellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<SellOrderDetail> SellOrderDetailList);

    /**
     * 修改数据
     *
     * @param sellOrderDetail 实例对象
     * @return 实例对象
     */
    SellOrderDetail update(SellOrderDetail sellOrderDetail);

    /**
     * 批量修改数据
     *
     * @param sellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<SellOrderDetail> sellOrderDetailList);

    /**
     * 通过主键删除数据
     *
     * @param sellOrderDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer sellOrderDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
