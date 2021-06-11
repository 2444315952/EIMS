package com.eims.service;

import com.eims.mybatis.entity.Purchase;
import com.eims.vo.form.PurchaseQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * (Purchase)表服务接口
 *
 * @author makejava
 * @since 2021-06-04 18:19:56
 */
public interface PurchaseService {

    /**
     * 通过ID查询单条数据
     *
     * @param purchId 主键
     * @return 实例对象
     */
    Purchase queryById(Integer purchId);

    /**
     * 查询所有数据
     *
     * @param purchaseQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Purchase> queryAll(PurchaseQueryForm purchaseQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseQueryForm
     * @return 对象列表
     */
    PageInfo<Purchase> queryBySearch(PurchaseQueryForm purchaseQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseQueryForm
     * @return 对象列表
     */
    PageInfo<Purchase> queryByScreen(PurchaseQueryForm purchaseQueryForm);

    /**
     * 根据采购单据编号，查询单据条数
     * @return
     */
    int queryCountByDocuNum(Integer workPointId, String purchDocunum);

    /**
     * 新增数据
     *
     * @param purchase 实例对象
     * @return 实例对象
     */
    Purchase insert(Purchase purchase);

    /**
     * 批量新增数据
     *
     * @param PurchaseList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Purchase> PurchaseList);

    /**
     * 修改数据
     *
     * @param purchase 实例对象
     * @return 实例对象
     */
    Purchase update(Purchase purchase);

    /**
     * 通过主键id修改审核状态
     * @param audited
     * @param purchId
     * @return 是否成功
     */
    boolean updateAuditedById(Integer audited,Integer purchId);

    /**
     * 批量修改数据
     *
     * @param purchaseList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Purchase> purchaseList);

    /**
     * 通过主键删除数据
     *
     * @param purchId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer purchId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
