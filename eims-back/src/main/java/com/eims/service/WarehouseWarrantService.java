package com.eims.service;

import com.eims.mybatis.entity.Purchase;
import com.eims.mybatis.entity.SellReturn;
import com.eims.mybatis.entity.WarehouseWarrant;
import com.eims.vo.form.PurchaseQueryForm;
import com.eims.vo.form.SellReturnQueryForm;
import com.eims.vo.form.WarehouseWarrantQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (WarehouseWarrant)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:27:23
 */
public interface WarehouseWarrantService {

    /**
     * 通过ID查询单条数据
     *
     * @param warehouseWarrantId 主键
     * @return 实例对象
     */
    WarehouseWarrant queryById(Integer warehouseWarrantId);

    /**
     * 查询所有数据
     *
     * @param warehouseWarrantQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<WarehouseWarrant> queryAll(WarehouseWarrantQueryForm warehouseWarrantQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param warehouseWarrantQueryForm
     * @return 对象列表
     */
    PageInfo<WarehouseWarrant> queryBySearch(WarehouseWarrantQueryForm warehouseWarrantQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param warehouseWarrantQueryForm
     * @return 对象列表
     */
    PageInfo<WarehouseWarrant> queryByScreen(WarehouseWarrantQueryForm warehouseWarrantQueryForm);

    /**
     * 新增数据
     *
     * @param warehouseWarrant 实例对象
     * @return 实例对象
     */
    WarehouseWarrant insert(WarehouseWarrant warehouseWarrant);

    /**
     * 批量新增数据
     *
     * @param WarehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<WarehouseWarrant> WarehouseWarrantList);

    /**
     * 修改数据
     *
     * @param warehouseWarrant 实例对象
     * @return 实例对象
     */
    WarehouseWarrant update(WarehouseWarrant warehouseWarrant);

    /**
     * 通过实体作为或者条件查询已入库的数据
     * @param purchaseQueryForm
     * @return
     */
    PageInfo<Purchase> queryPurchaseInStorage(PurchaseQueryForm purchaseQueryForm);

    /**
     * 搜索框筛选已入库的销售退货单
     * @param sellReturnQueryForm
     * @return
     */
    PageInfo<SellReturn> querySellInStorage(SellReturnQueryForm sellReturnQueryForm);

    /**
     * 批量修改数据
     *
     * @param warehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<WarehouseWarrant> warehouseWarrantList);

    /**
     * 通过主键删除数据
     *
     * @param warehouseWarrantId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer warehouseWarrantId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
