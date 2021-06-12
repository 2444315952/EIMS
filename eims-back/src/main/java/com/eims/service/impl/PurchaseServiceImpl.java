package com.eims.service.impl;

import com.eims.mybatis.dao.PurchaseDetailDao;
import com.eims.mybatis.entity.Purchase;
import com.eims.mybatis.entity.PurchaseDetail;
import com.eims.vo.form.PurchaseQueryForm;
import com.eims.mybatis.dao.PurchaseDao;
import com.eims.service.PurchaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Purchase)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:25:53
 */
@Service("purchaseService")
@Log4j2
public class PurchaseServiceImpl implements PurchaseService {
    @Resource
    private PurchaseDao purchaseDao;

    @Resource
    private PurchaseDetailDao purchaseDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param purchId 主键
     * @return 实例对象
     */
    @Override
    public Purchase queryById(Integer purchId) {
        return this.purchaseDao.queryById(purchId);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Purchase> queryAll(PurchaseQueryForm purchaseQueryForm) {
        Page<Purchase> page = PageHelper.startPage(purchaseQueryForm.getPageNum(), purchaseQueryForm.getPageSize());
        List<Purchase> purchaseList = this.purchaseDao.queryAll(purchaseQueryForm);
        return new PageInfo<>(purchaseList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Purchase> queryBySearch(PurchaseQueryForm purchaseQueryForm) {
        Page<Purchase> page = PageHelper.startPage(purchaseQueryForm.getPageNum(), purchaseQueryForm.getPageSize());
        List<Purchase> purchaseList = this.purchaseDao.queryOrByPojo(purchaseQueryForm);
        return new PageInfo<>(purchaseList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Purchase> queryByScreen(PurchaseQueryForm purchaseQueryForm) {
        Page<Purchase> page = PageHelper.startPage(purchaseQueryForm.getPageNum(), purchaseQueryForm.getPageSize());
        List<Purchase> purchaseList = this.purchaseDao.queryAndByPojo(purchaseQueryForm);
        return new PageInfo<>(purchaseList);
    }

    /**
     * 新增数据
     *
     * @param purchase 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public Purchase insert(Purchase purchase) {
        this.purchaseDao.insert(purchase);

        //新增采购单明细
        List<PurchaseDetail> purchaseDetailList = purchase.getPurchaseDetailList();
        if(purchaseDetailList!=null){
            for(PurchaseDetail detail:purchaseDetailList)
                detail.setPurchId(purchase.getPurchId());
            purchaseDetailDao.insertBatch(purchaseDetailList);
        }

        return this.queryById(purchase.getPurchId());
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Purchase> PurchaseList) {
        return this.purchaseDao.insertBatch(PurchaseList) == -1;
    }

    /**
     * 修改数据
     *
     * @param purchase 实例对象
     * @return 实例对象
     */
    @Override
    public Purchase update(Purchase purchase) {
        this.purchaseDao.update(purchase);
        return this.queryById(purchase.getPurchId());
    }

    /**
     * 修改采购单以明细数据
     *
     * @param purchase 实例对象（包含明细列表）
     * @return 实例对象
     */
    @Override
    @Transactional
    public Purchase updatePurchAndDetail(Purchase purchase) {
        if(purchase.getAudited()==1)
            return null;

        this.purchaseDao.update(purchase);

        List<PurchaseDetail> updateDetails = purchase.getPurchaseDetailList();
        List<PurchaseDetail> addDetails = new ArrayList<>();

        //之前的明细中，现在存在的明细id列表
        List<Integer> keyIds = new ArrayList();
        keyIds.add(0);  //定义初始条件值，存在的明细id列表，为空时，可以删除全部

        for(Iterator<PurchaseDetail> it = updateDetails.iterator(); it.hasNext();){
            PurchaseDetail detail = it.next();

            //purchDetailId未null时为要新增的明细数据，不为null为要修改的数据
            if (detail.getPurchDetailId() == null) {
                it.remove();
                addDetails.add(detail);
            } else {
                keyIds.add(detail.getPurchDetailId());
            }
        }

        log.debug("要修改的明细是：{}",updateDetails.toString());
        //修改明细信息
        if(updateDetails.size()>0)
            this.purchaseDetailDao.updateBatch(updateDetails);

        //删除不存在的明细
        if(keyIds.size()>0){
            Map<String,Object> purchIdAndKeyIds = new HashMap<>();
            purchIdAndKeyIds.put("purchId",purchase.getPurchId());
            purchIdAndKeyIds.put("keyIds",keyIds);
            purchaseDetailDao.deleteByPurchIdAndNotInKeyIds(purchIdAndKeyIds);
        }

        //新增新明细
        if(addDetails.size()>0){
            for(PurchaseDetail detail:addDetails)
                detail.setPurchId(purchase.getPurchId());
            this.purchaseDetailDao.insertBatch(addDetails);
        }

        return this.queryById(purchase.getPurchId());
    }

    /**
     * 批量修改数据
     *
     * @param purchaseList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Purchase> purchaseList) {
        return this.purchaseDao.updateBatch(purchaseList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param purchId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer purchId) {
        return this.purchaseDao.deleteById(purchId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.purchaseDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
