package com.eims.service.impl;

import com.eims.mybatis.entity.StockOut;
import com.eims.vo.form.StockOutQueryForm;
import com.eims.mybatis.dao.StockOutDao;
import com.eims.service.StockOutService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (StockOut)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:26:54
 */
@Service("stockOutService")
public class StockOutServiceImpl implements StockOutService {
    @Resource
    private StockOutDao stockOutDao;

    /**
     * 通过ID查询单条数据
     *
     * @param stockOutId 主键
     * @return 实例对象
     */
    @Override
    public StockOut queryById(Integer stockOutId) {
        return this.stockOutDao.queryById(stockOutId);
    }

    /**
     * 查询所有数据
     *
     * @param stockOutQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<StockOut> queryAll(StockOutQueryForm stockOutQueryForm) {
        Page<StockOut> page = PageHelper.startPage(stockOutQueryForm.getPageNum(), stockOutQueryForm.getPageSize());
        List<StockOut> stockOutList = this.stockOutDao.queryAll(stockOutQueryForm);
        return new PageInfo<>(stockOutList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param stockOutQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<StockOut> queryBySearch(StockOutQueryForm stockOutQueryForm) {
        Page<StockOut> page = PageHelper.startPage(stockOutQueryForm.getPageNum(), stockOutQueryForm.getPageSize());
        List<StockOut> stockOutList = this.stockOutDao.queryOrByPojo(stockOutQueryForm);
        return new PageInfo<>(stockOutList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param stockOutQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<StockOut> queryByScreen(StockOutQueryForm stockOutQueryForm) {
        Page<StockOut> page = PageHelper.startPage(stockOutQueryForm.getPageNum(), stockOutQueryForm.getPageSize());
        List<StockOut> stockOutList = this.stockOutDao.queryAndByPojo(stockOutQueryForm);
        return new PageInfo<>(stockOutList);
    }

    /**
     * 新增数据
     *
     * @param stockOut 实例对象
     * @return 实例对象
     */
    @Override
    public StockOut insert(StockOut stockOut) {
        this.stockOutDao.insert(stockOut);
        return this.queryById(stockOut.getStockOutId());
    }

    /**
     * 批量新增数据
     *
     * @param StockOutList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<StockOut> StockOutList) {
        return this.stockOutDao.insertBatch(StockOutList) == -1;
    }

    /**
     * 修改数据
     *
     * @param stockOut 实例对象
     * @return 实例对象
     */
    @Override
    public StockOut update(StockOut stockOut) {
        this.stockOutDao.update(stockOut);
        return this.queryById(stockOut.getStockOutId());
    }

    /**
     * 批量修改数据
     *
     * @param stockOutList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<StockOut> stockOutList) {
        return this.stockOutDao.updateBatch(stockOutList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param stockOutId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer stockOutId) {
        return this.stockOutDao.deleteById(stockOutId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.stockOutDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
