package com.eims.service.impl;

import com.eims.mybatis.entity.SellBill;
import com.eims.vo.form.SellBillQueryForm;
import com.eims.mybatis.dao.SellBillDao;
import com.eims.service.SellBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (SellBill)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:26:47
 */
@Service("sellBillService")
public class SellBillServiceImpl implements SellBillService {
    @Resource
    private SellBillDao sellBillDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellId 主键
     * @return 实例对象
     */
    @Override
    public SellBill queryById(Integer sellId) {
        return this.sellBillDao.queryById(sellId);
    }

    /**
     * 查询所有数据
     *
     * @param sellBillQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellBill> queryAll(SellBillQueryForm sellBillQueryForm) {
        Page<SellBill> page = PageHelper.startPage(sellBillQueryForm.getPageNum(), sellBillQueryForm.getPageSize());
        List<SellBill> sellBillList = this.sellBillDao.queryAll(sellBillQueryForm);
        return new PageInfo<>(sellBillList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellBill> queryBySearch(SellBillQueryForm sellBillQueryForm) {
        Page<SellBill> page = PageHelper.startPage(sellBillQueryForm.getPageNum(), sellBillQueryForm.getPageSize());
        List<SellBill> sellBillList = this.sellBillDao.queryOrByPojo(sellBillQueryForm);
        return new PageInfo<>(sellBillList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellBill> queryByScreen(SellBillQueryForm sellBillQueryForm) {
        Page<SellBill> page = PageHelper.startPage(sellBillQueryForm.getPageNum(), sellBillQueryForm.getPageSize());
        List<SellBill> sellBillList = this.sellBillDao.queryAndByPojo(sellBillQueryForm);
        return new PageInfo<>(sellBillList);
    }

    /**
     * 新增数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    @Override
    public SellBill insert(SellBill sellBill) {
        this.sellBillDao.insert(sellBill);
        return this.queryById(sellBill.getSellId());
    }

    /**
     * 批量新增数据
     *
     * @param SellBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellBill> SellBillList) {
        return this.sellBillDao.insertBatch(SellBillList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    @Override
    public SellBill update(SellBill sellBill) {
        this.sellBillDao.update(sellBill);
        return this.queryById(sellBill.getSellId());
    }

    /**
     * 批量修改数据
     *
     * @param sellBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellBill> sellBillList) {
        return this.sellBillDao.updateBatch(sellBillList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellId) {
        return this.sellBillDao.deleteById(sellId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellBillDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
