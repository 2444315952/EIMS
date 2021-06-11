package com.eims.service;

import com.eims.mybatis.entity.SellOrderBill;
import com.eims.vo.form.SellOrderBillQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (SellOrderBill)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:50
 */
public interface SellOrderBillService {

    /**
     * 通过ID查询单条数据
     *
     * @param sellOrderId 主键
     * @return 实例对象
     */
    SellOrderBill queryById(Integer sellOrderId);

    /**
     * 查询所有数据
     *
     * @param sellOrderBillQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<SellOrderBill> queryAll(SellOrderBillQueryForm sellOrderBillQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    PageInfo<SellOrderBill> queryBySearch(SellOrderBillQueryForm sellOrderBillQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    PageInfo<SellOrderBill> queryByScreen(SellOrderBillQueryForm sellOrderBillQueryForm);

    /**
     * 新增数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    SellOrderBill insert(SellOrderBill sellOrderBill);

    /**
     * 批量新增数据
     *
     * @param SellOrderBillList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<SellOrderBill> SellOrderBillList);

    /**
     * 修改数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    SellOrderBill update(SellOrderBill sellOrderBill);

    /**
     * 批量修改数据
     *
     * @param sellOrderBillList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<SellOrderBill> sellOrderBillList);

    /**
     * 通过主键删除数据
     *
     * @param sellOrderId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer sellOrderId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
