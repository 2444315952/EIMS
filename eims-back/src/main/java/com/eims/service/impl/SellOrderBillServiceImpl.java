package com.eims.service.impl;

import com.eims.mybatis.entity.SellOrderBill;
import com.eims.vo.form.SellOrderBillQueryForm;
import com.eims.mybatis.dao.SellOrderBillDao;
import com.eims.service.SellOrderBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (SellOrderBill)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:26:50
 */
@Service("sellOrderBillService")
public class SellOrderBillServiceImpl implements SellOrderBillService {
    @Resource
    private SellOrderBillDao sellOrderBillDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellOrderId 主键
     * @return 实例对象
     */
    @Override
    public SellOrderBill queryById(Integer sellOrderId) {
        return this.sellOrderBillDao.queryById(sellOrderId);
    }

    /**
     * 查询所有数据
     *
     * @param sellOrderBillQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellOrderBill> queryAll(SellOrderBillQueryForm sellOrderBillQueryForm) {
        Page<SellOrderBill> page = PageHelper.startPage(sellOrderBillQueryForm.getPageNum(), sellOrderBillQueryForm.getPageSize());
        List<SellOrderBill> sellOrderBillList = this.sellOrderBillDao.queryAll(sellOrderBillQueryForm);
        return new PageInfo<>(sellOrderBillList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellOrderBill> queryBySearch(SellOrderBillQueryForm sellOrderBillQueryForm) {
        Page<SellOrderBill> page = PageHelper.startPage(sellOrderBillQueryForm.getPageNum(), sellOrderBillQueryForm.getPageSize());
        List<SellOrderBill> sellOrderBillList = this.sellOrderBillDao.queryOrByPojo(sellOrderBillQueryForm);
        return new PageInfo<>(sellOrderBillList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellOrderBill> queryByScreen(SellOrderBillQueryForm sellOrderBillQueryForm) {
        Page<SellOrderBill> page = PageHelper.startPage(sellOrderBillQueryForm.getPageNum(), sellOrderBillQueryForm.getPageSize());
        List<SellOrderBill> sellOrderBillList = this.sellOrderBillDao.queryAndByPojo(sellOrderBillQueryForm);
        return new PageInfo<>(sellOrderBillList);
    }

    /**
     * 新增数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    @Override
    public SellOrderBill insert(SellOrderBill sellOrderBill) {
        this.sellOrderBillDao.insert(sellOrderBill);
        return this.queryById(sellOrderBill.getSellOrderId());
    }

    /**
     * 批量新增数据
     *
     * @param SellOrderBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellOrderBill> SellOrderBillList) {
        return this.sellOrderBillDao.insertBatch(SellOrderBillList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    @Override
    public SellOrderBill update(SellOrderBill sellOrderBill) {
        this.sellOrderBillDao.update(sellOrderBill);
        return this.queryById(sellOrderBill.getSellOrderId());
    }

    /**
     * 批量修改数据
     *
     * @param sellOrderBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellOrderBill> sellOrderBillList) {
        return this.sellOrderBillDao.updateBatch(sellOrderBillList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellOrderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellOrderId) {
        return this.sellOrderBillDao.deleteById(sellOrderId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellOrderBillDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
