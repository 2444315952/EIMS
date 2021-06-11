package com.eims.service.impl;

import com.eims.mybatis.entity.PurchaseReturn;
import com.eims.vo.form.PurchaseReturnQueryForm;
import com.eims.mybatis.dao.PurchaseReturnDao;
import com.eims.service.PurchaseReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (PurchaseReturn)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:25:55
 */
@Service("purchaseReturnService")
public class PurchaseReturnServiceImpl implements PurchaseReturnService {
    @Resource
    private PurchaseReturnDao purchaseReturnDao;

    /**
     * 通过ID查询单条数据
     *
     * @param purchReturnId 主键
     * @return 实例对象
     */
    @Override
    public PurchaseReturn queryById(Integer purchReturnId) {
        return this.purchaseReturnDao.queryById(purchReturnId);
    }

    /**
     * 查询所有数据
     *
     * @param purchaseReturnQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<PurchaseReturn> queryAll(PurchaseReturnQueryForm purchaseReturnQueryForm) {
        Page<PurchaseReturn> page = PageHelper.startPage(purchaseReturnQueryForm.getPageNum(), purchaseReturnQueryForm.getPageSize());
        List<PurchaseReturn> purchaseReturnList = this.purchaseReturnDao.queryAll(purchaseReturnQueryForm);
        return new PageInfo<>(purchaseReturnList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param purchaseReturnQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PurchaseReturn> queryBySearch(PurchaseReturnQueryForm purchaseReturnQueryForm) {
        Page<PurchaseReturn> page = PageHelper.startPage(purchaseReturnQueryForm.getPageNum(), purchaseReturnQueryForm.getPageSize());
        List<PurchaseReturn> purchaseReturnList = this.purchaseReturnDao.queryOrByPojo(purchaseReturnQueryForm);
        return new PageInfo<>(purchaseReturnList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param purchaseReturnQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<PurchaseReturn> queryByScreen(PurchaseReturnQueryForm purchaseReturnQueryForm) {
        Page<PurchaseReturn> page = PageHelper.startPage(purchaseReturnQueryForm.getPageNum(), purchaseReturnQueryForm.getPageSize());
        List<PurchaseReturn> purchaseReturnList = this.purchaseReturnDao.queryAndByPojo(purchaseReturnQueryForm);
        return new PageInfo<>(purchaseReturnList);
    }

    /**
     * 新增数据
     *
     * @param purchaseReturn 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseReturn insert(PurchaseReturn purchaseReturn) {
        this.purchaseReturnDao.insert(purchaseReturn);
        return this.queryById(purchaseReturn.getPurchReturnId());
    }

    /**
     * 批量新增数据
     *
     * @param PurchaseReturnList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<PurchaseReturn> PurchaseReturnList) {
        return this.purchaseReturnDao.insertBatch(PurchaseReturnList) == -1;
    }

    /**
     * 修改数据
     *
     * @param purchaseReturn 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseReturn update(PurchaseReturn purchaseReturn) {
        this.purchaseReturnDao.update(purchaseReturn);
        return this.queryById(purchaseReturn.getPurchReturnId());
    }

    /**
     * 批量修改数据
     *
     * @param purchaseReturnList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<PurchaseReturn> purchaseReturnList) {
        return this.purchaseReturnDao.updateBatch(purchaseReturnList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param purchReturnId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer purchReturnId) {
        return this.purchaseReturnDao.deleteById(purchReturnId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.purchaseReturnDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
