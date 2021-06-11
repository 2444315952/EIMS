package com.eims.service;

import com.eims.mybatis.entity.SellBill;
import com.eims.vo.form.SellBillQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (SellBill)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:47
 */
public interface SellBillService {

    /**
     * 通过ID查询单条数据
     *
     * @param sellId 主键
     * @return 实例对象
     */
    SellBill queryById(Integer sellId);

    /**
     * 查询所有数据
     *
     * @param sellBillQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<SellBill> queryAll(SellBillQueryForm sellBillQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    PageInfo<SellBill> queryBySearch(SellBillQueryForm sellBillQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    PageInfo<SellBill> queryByScreen(SellBillQueryForm sellBillQueryForm);

    /**
     * 新增数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    SellBill insert(SellBill sellBill);

    /**
     * 批量新增数据
     *
     * @param SellBillList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<SellBill> SellBillList);

    /**
     * 修改数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    SellBill update(SellBill sellBill);

    /**
     * 批量修改数据
     *
     * @param sellBillList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<SellBill> sellBillList);

    /**
     * 通过主键删除数据
     *
     * @param sellId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer sellId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
