package com.eims.service;

import com.eims.mybatis.entity.PurchaseReturn;
import com.eims.vo.form.PurchaseReturnQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (PurchaseReturn)表服务接口
 *
 * @author makejava
 * @since 2021-06-04 18:19:58
 */
public interface PurchaseReturnService {

    /**
     * 通过ID查询单条数据
     *
     * @param purchReturnId 主键
     * @return 实例对象
     */
    PurchaseReturn queryById(Integer purchReturnId);

    /**
     * 查询所有数据
     *
     * @param purchaseReturnQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<PurchaseReturn> queryAll(PurchaseReturnQueryForm purchaseReturnQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseReturnQueryForm
     * @return 对象列表
     */
    PageInfo<PurchaseReturn> queryBySearch(PurchaseReturnQueryForm purchaseReturnQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseReturnQueryForm
     * @return 对象列表
     */
    PageInfo<PurchaseReturn> queryByScreen(PurchaseReturnQueryForm purchaseReturnQueryForm);

    /**
     * 新增数据
     *
     * @param purchaseReturn 实例对象
     * @return 实例对象
     */
    PurchaseReturn insert(PurchaseReturn purchaseReturn);

    /**
     * 批量新增数据
     *
     * @param PurchaseReturnList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<PurchaseReturn> PurchaseReturnList);

    /**
     * 修改数据
     *
     * @param purchaseReturn 实例对象
     * @return 实例对象
     */
    PurchaseReturn update(PurchaseReturn purchaseReturn);

    /**
     * 批量修改数据
     *
     * @param purchaseReturnList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<PurchaseReturn> purchaseReturnList);

    /**
     * 通过主键删除数据
     *
     * @param purchReturnId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer purchReturnId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
