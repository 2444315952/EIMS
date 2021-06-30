package com.eims.service.impl;

import com.eims.mybatis.dao.InventoryDao;
import com.eims.mybatis.dao.PurchaseDao;
import com.eims.mybatis.dao.WarehousingDetailDao;
import com.eims.mybatis.entity.*;
import com.eims.vo.form.PurchaseQueryForm;
import com.eims.vo.form.SellReturnQueryForm;
import com.eims.vo.form.WarehouseWarrantQueryForm;
import com.eims.mybatis.dao.WarehouseWarrantDao;
import com.eims.service.WarehouseWarrantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (WarehouseWarrant)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:27:23
 */
@Service("warehouseWarrantService")
@Slf4j
public class WarehouseWarrantServiceImpl implements WarehouseWarrantService {
    @Resource
    private WarehouseWarrantDao warehouseWarrantDao;

    @Resource
    private WarehousingDetailDao warehousingDetailDao;

    @Resource
    private InventoryDao inventoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param warehouseWarrantId 主键
     * @return 实例对象
     */
    @Override
    public WarehouseWarrant queryById(Integer warehouseWarrantId) {
        return this.warehouseWarrantDao.queryById(warehouseWarrantId);
    }

    /**
     * 查询所有数据
     *
     * @param warehouseWarrantQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<WarehouseWarrant> queryAll(WarehouseWarrantQueryForm warehouseWarrantQueryForm) {
        Page<WarehouseWarrant> page = PageHelper.startPage(warehouseWarrantQueryForm.getPageNum(), warehouseWarrantQueryForm.getPageSize());
        List<WarehouseWarrant> warehouseWarrantList = this.warehouseWarrantDao.queryAll(warehouseWarrantQueryForm);
        return new PageInfo<>(warehouseWarrantList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param warehouseWarrantQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<WarehouseWarrant> queryBySearch(WarehouseWarrantQueryForm warehouseWarrantQueryForm) {
        Page<WarehouseWarrant> page = PageHelper.startPage(warehouseWarrantQueryForm.getPageNum(), warehouseWarrantQueryForm.getPageSize());
        List<WarehouseWarrant> warehouseWarrantList = this.warehouseWarrantDao.queryOrByPojo(warehouseWarrantQueryForm);
        return new PageInfo<>(warehouseWarrantList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param warehouseWarrantQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<WarehouseWarrant> queryByScreen(WarehouseWarrantQueryForm warehouseWarrantQueryForm) {
        Page<WarehouseWarrant> page = PageHelper.startPage(warehouseWarrantQueryForm.getPageNum(), warehouseWarrantQueryForm.getPageSize());
        List<WarehouseWarrant> warehouseWarrantList = this.warehouseWarrantDao.queryAndByPojo(warehouseWarrantQueryForm);
        return new PageInfo<>(warehouseWarrantList);
    }

    /**
     * 新增数据
     *
     * @param warehouseWarrant 实例对象
     * @return 实例对象
     */
    @Override
    public WarehouseWarrant insert(WarehouseWarrant warehouseWarrant) {
        log.debug("入库单是：{}",warehouseWarrant.toString());

        this.warehouseWarrantDao.insert(warehouseWarrant);

        //设置入库单明细的入库单id
        if(warehouseWarrant.getWarehousingDetailList()!=null){
            for(WarehousingDetail detail:warehouseWarrant.getWarehousingDetailList())
                detail.setWarehouseWarrantId(warehouseWarrant.getWarehouseWarrantId());
            //新增明细
            this.warehousingDetailDao.insertBatch(warehouseWarrant.getWarehousingDetailList());
        }

        return this.queryById(warehouseWarrant.getWarehouseWarrantId());
    }


    /**
     *采购单筛选已入库的数据
     * @param purchaseQueryForm
     * @return
     */
    @Override
    public PageInfo<Purchase> queryPurchaseInStorage(PurchaseQueryForm purchaseQueryForm) {
        Page<Purchase> page = PageHelper.startPage(purchaseQueryForm.getPageNum(), purchaseQueryForm.getPageSize());
        List<Purchase> purchaseList = this.warehouseWarrantDao.queryPurchaseInStorage(purchaseQueryForm);
        return new PageInfo<>(purchaseList);
    }

    /**
     * 销售退货单筛选已入库的数据
     * @param sellReturnQueryForm
     * @return
     */
    @Override
    public PageInfo<SellReturn> querySellInStorage(SellReturnQueryForm sellReturnQueryForm){
        Page<SellReturn> page =PageHelper.startPage(sellReturnQueryForm.getPageNum(),sellReturnQueryForm.getPageSize());
        List<SellReturn> returnList=this.warehouseWarrantDao.querySellInStorage(sellReturnQueryForm);
        return new PageInfo<>(returnList);
    }

    /**
     * 批量新增数据
     *
     * @param WarehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<WarehouseWarrant> WarehouseWarrantList) {
        return this.warehouseWarrantDao.insertBatch(WarehouseWarrantList) == -1;
    }

    /**
     * 修改数据
     *
     * @param warehouseWarrant 实例对象
     * @return 实例对象
     */
    @Override
    public WarehouseWarrant update(WarehouseWarrant warehouseWarrant) {
        //修改明细
        this.warehouseWarrantDao.update(warehouseWarrant);

        //判断明细是否为空，明细不为空就执行修改明细数据
        if(warehouseWarrant.getWarehousingDetailList()!=null){
            //先删除
            this.warehousingDetailDao.deleteFk(warehouseWarrant.getWarehouseWarrantId());

            //再新增
            for(WarehousingDetail detail :warehouseWarrant.getWarehousingDetailList())
                detail.setWarehouseWarrantId(warehouseWarrant.getWarehouseWarrantId());
            this.warehousingDetailDao.insertBatch(warehouseWarrant.getWarehousingDetailList());
        }
        return this.queryById(warehouseWarrant.getWarehouseWarrantId());
    }


    /**
     * 审核入库单改变库存
     * @param warehouseWarrantId
     * @return
     */
     public boolean auditStorage(Integer warehouseWarrantId) {
         System.out.println("入库单id:");
         System.out.println(warehouseWarrantId);
         Inventory inventory=new Inventory();
        //查询单个拿到详情数据
         WarehouseWarrant warehouseWarrant= this.warehouseWarrantDao.queryById(warehouseWarrantId);
         warehouseWarrant.setAudited(1);
         this.warehouseWarrantDao.update(warehouseWarrant);
         //从详情数据里面拿入库数量和id
         for(WarehousingDetail warehousingDetail:warehouseWarrant.getWarehousingDetailList())
            this.warehouseWarrantDao.auditStorage(warehousingDetail.getInventoryQuantity(),warehouseWarrant.getWarehouseId(),warehousingDetail.getProductId());
         this.inventoryDao.queryProduct(inventory.getCompanyId(),inventory.getWarehouseId(),inventory.getProductId());
         if (inventory.getProductId()!=warehouseWarrant.getWarehousingDetailList().get(0).getProductId()){

         }
         return true;
     }

    /**
     * 批量修改数据
     *
     * @param warehouseWarrantList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<WarehouseWarrant> warehouseWarrantList) {
        return this.warehouseWarrantDao.updateBatch(warehouseWarrantList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param warehouseWarrantId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer warehouseWarrantId) {
        this.warehouseWarrantDao.deleteById(warehouseWarrantId);
        return this.warehousingDetailDao.deleteFk(warehouseWarrantId)>0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.warehouseWarrantDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
