package com.eims.service.impl;

import com.eims.mybatis.entity.Purchase;
import com.eims.vo.form.PurchaseQueryForm;
import com.eims.mybatis.dao.PurchaseDao;
import com.eims.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Purchase)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:25:53
 */
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
    @Resource
    private PurchaseDao purchaseDao;

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
    public Purchase insert(Purchase purchase) {
        this.purchaseDao.insert(purchase);
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
