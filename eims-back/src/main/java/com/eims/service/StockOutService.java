package com.eims.service;

import com.eims.mybatis.entity.PurchaseReturn;
import com.eims.mybatis.entity.StockOut;
import com.eims.vo.form.PurchaseReturnQueryForm;
import com.eims.vo.form.StockOutQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (StockOut)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:54
 */
public interface StockOutService {

    /**
     * 通过ID查询单条数据
     *
     * @param stockOutId 主键
     * @return 实例对象
     */
    StockOut queryById(Integer stockOutId);

    /**
     * 查询所有数据
     *
     * @param stockOutQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<StockOut> queryAll(StockOutQueryForm stockOutQueryForm);


    /**
     * 根据查询条件筛选数据
     *
     * @param stockOutQueryForm
     * @return 对象列表
     */
    PageInfo<StockOut> queryByScreen(StockOutQueryForm stockOutQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param stockOutQueryForm
     * @return 对象列表
     */
    PageInfo<StockOut> queryBySearch(StockOutQueryForm stockOutQueryForm);

    /**
     * 搜索框筛选出未出库的数据
     * @param purchaseReturnQueryForm
     * @return
     */
    PageInfo<PurchaseReturn> queryByPurReturn(PurchaseReturnQueryForm purchaseReturnQueryForm);

    /**
     * 新增数据
     *
     * @param stockOut 实例对象
     * @return 实例对象
     */
    StockOut insert(StockOut stockOut);

    /**
     * 批量新增数据
     *
     * @param StockOutList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<StockOut> StockOutList);

    /**
     * 修改数据
     *
     * @param stockOut 实例对象
     * @return 实例对象
     */
    StockOut update(StockOut stockOut);

    /**
     * 审核出库单改变库存
     * @param stockOutId
     * @return
     */
    Boolean auditStorage(Integer stockOutId);


    /**
     * 批量修改数据
     *
     * @param stockOutList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<StockOut> stockOutList);

    /**
     * 通过主键删除数据
     *
     * @param stockOutId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer stockOutId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
