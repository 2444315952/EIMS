package com.eims.service.impl;

import com.eims.mybatis.entity.PurchaseReturnDetail;
import com.eims.vo.form.PurchaseReturnDetailQueryForm;
import com.eims.mybatis.dao.PurchaseReturnDetailDao;
import com.eims.service.PurchaseReturnDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (PurchaseReturnDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-04 18:20:00
 */
@Service("purchaseReturnDetailService")
public class PurchaseReturnDetailServiceImpl implements PurchaseReturnDetailService {
    @Resource
    private PurchaseReturnDetailDao purchaseReturnDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param purchReturnDetailId 主键
     * @return 实例对象
     */
    @Override
    public PurchaseReturnDetail queryById(Integer purchReturnDetailId) {
        return this.purchaseReturnDetailDao.queryById(purchReturnDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseReturnDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<PurchaseReturnDetail> queryAll(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm) {
        Page<PurchaseReturnDetail> page = PageHelper.startPage(purchaseReturnDetailQueryForm.getPageNum(), purchaseReturnDetailQueryForm.getPageSize());
        List<PurchaseReturnDetail> purchaseReturnDetailList = this.purchaseReturnDetailDao.queryAll(purchaseReturnDetailQueryForm);
        return new PageInfo<>(purchaseReturnDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseReturnDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PurchaseReturnDetail> queryBySearch(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm) {
        Page<PurchaseReturnDetail> page = PageHelper.startPage(purchaseReturnDetailQueryForm.getPageNum(), purchaseReturnDetailQueryForm.getPageSize());
        List<PurchaseReturnDetail> purchaseReturnDetailList = this.purchaseReturnDetailDao.queryOrByPojo(purchaseReturnDetailQueryForm);
        return new PageInfo<>(purchaseReturnDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseReturnDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PurchaseReturnDetail> queryByScreen(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm) {
        Page<PurchaseReturnDetail> page = PageHelper.startPage(purchaseReturnDetailQueryForm.getPageNum(), purchaseReturnDetailQueryForm.getPageSize());
        List<PurchaseReturnDetail> purchaseReturnDetailList = this.purchaseReturnDetailDao.queryAndByPojo(purchaseReturnDetailQueryForm);
        return new PageInfo<>(purchaseReturnDetailList);
    }

    /**
     * 新增数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseReturnDetail insert(PurchaseReturnDetail purchaseReturnDetail) {
        this.purchaseReturnDetailDao.insert(purchaseReturnDetail);
        return this.queryById(purchaseReturnDetail.getPurchReturnDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<PurchaseReturnDetail> PurchaseReturnDetailList) {
        return this.purchaseReturnDetailDao.insertBatch(PurchaseReturnDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseReturnDetail update(PurchaseReturnDetail purchaseReturnDetail) {
        this.purchaseReturnDetailDao.update(purchaseReturnDetail);
        return this.queryById(purchaseReturnDetail.getPurchReturnDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param purchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<PurchaseReturnDetail> purchaseReturnDetailList) {
        return this.purchaseReturnDetailDao.updateBatch(purchaseReturnDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param purchReturnDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer purchReturnDetailId) {
        return this.purchaseReturnDetailDao.deleteById(purchReturnDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.purchaseReturnDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
