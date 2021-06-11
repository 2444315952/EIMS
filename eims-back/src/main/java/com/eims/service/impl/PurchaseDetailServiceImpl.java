package com.eims.service.impl;

import com.eims.mybatis.entity.PurchaseDetail;
import com.eims.vo.form.PurchaseDetailQueryForm;
import com.eims.mybatis.dao.PurchaseDetailDao;
import com.eims.service.PurchaseDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (PurchaseDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-04 18:19:57
 */
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
    @Resource
    private PurchaseDetailDao purchaseDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param purchDetailId 主键
     * @return 实例对象
     */
    @Override
    public PurchaseDetail queryById(Integer purchDetailId) {
        return this.purchaseDetailDao.queryById(purchDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<PurchaseDetail> queryAll(PurchaseDetailQueryForm purchaseDetailQueryForm) {
        Page<PurchaseDetail> page = PageHelper.startPage(purchaseDetailQueryForm.getPageNum(), purchaseDetailQueryForm.getPageSize());
        List<PurchaseDetail> purchaseDetailList = this.purchaseDetailDao.queryAll(purchaseDetailQueryForm);
        return new PageInfo<>(purchaseDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PurchaseDetail> queryBySearch(PurchaseDetailQueryForm purchaseDetailQueryForm) {
        Page<PurchaseDetail> page = PageHelper.startPage(purchaseDetailQueryForm.getPageNum(), purchaseDetailQueryForm.getPageSize());
        List<PurchaseDetail> purchaseDetailList = this.purchaseDetailDao.queryOrByPojo(purchaseDetailQueryForm);
        return new PageInfo<>(purchaseDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PurchaseDetail> queryByScreen(PurchaseDetailQueryForm purchaseDetailQueryForm) {
        Page<PurchaseDetail> page = PageHelper.startPage(purchaseDetailQueryForm.getPageNum(), purchaseDetailQueryForm.getPageSize());
        List<PurchaseDetail> purchaseDetailList = this.purchaseDetailDao.queryAndByPojo(purchaseDetailQueryForm);
        return new PageInfo<>(purchaseDetailList);
    }

    /**
     * 新增数据
     *
     * @param purchaseDetail 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseDetail insert(PurchaseDetail purchaseDetail) {
        this.purchaseDetailDao.insert(purchaseDetail);
        return this.queryById(purchaseDetail.getPurchDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<PurchaseDetail> PurchaseDetailList) {
        return this.purchaseDetailDao.insertBatch(PurchaseDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param purchaseDetail 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseDetail update(PurchaseDetail purchaseDetail) {
        this.purchaseDetailDao.update(purchaseDetail);
        return this.queryById(purchaseDetail.getPurchDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param purchaseDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<PurchaseDetail> purchaseDetailList) {
        return this.purchaseDetailDao.updateBatch(purchaseDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param purchDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer purchDetailId) {
        return this.purchaseDetailDao.deleteById(purchDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.purchaseDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
