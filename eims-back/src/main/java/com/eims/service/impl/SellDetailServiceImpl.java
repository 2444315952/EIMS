package com.eims.service.impl;

import com.eims.mybatis.entity.SellDetail;
import com.eims.vo.form.SellDetailQueryForm;
import com.eims.mybatis.dao.SellDetailDao;
import com.eims.service.SellDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (SellDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:26:48
 */
@Service("sellDetailService")
public class SellDetailServiceImpl implements SellDetailService {
    @Resource
    private SellDetailDao sellDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellDetailId 主键
     * @return 实例对象
     */
    @Override
    public SellDetail queryById(Integer sellDetailId) {
        return this.sellDetailDao.queryById(sellDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param sellDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellDetail> queryAll(SellDetailQueryForm sellDetailQueryForm) {
        Page<SellDetail> page = PageHelper.startPage(sellDetailQueryForm.getPageNum(), sellDetailQueryForm.getPageSize());
        List<SellDetail> sellDetailList = this.sellDetailDao.queryAll(sellDetailQueryForm);
        return new PageInfo<>(sellDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellDetail> queryBySearch(SellDetailQueryForm sellDetailQueryForm) {
        Page<SellDetail> page = PageHelper.startPage(sellDetailQueryForm.getPageNum(), sellDetailQueryForm.getPageSize());
        List<SellDetail> sellDetailList = this.sellDetailDao.queryOrByPojo(sellDetailQueryForm);
        return new PageInfo<>(sellDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellDetail> queryByScreen(SellDetailQueryForm sellDetailQueryForm) {
        Page<SellDetail> page = PageHelper.startPage(sellDetailQueryForm.getPageNum(), sellDetailQueryForm.getPageSize());
        List<SellDetail> sellDetailList = this.sellDetailDao.queryAndByPojo(sellDetailQueryForm);
        return new PageInfo<>(sellDetailList);
    }

    /**
     * 新增数据
     *
     * @param sellDetail 实例对象
     * @return 实例对象
     */
    @Override
    public SellDetail insert(SellDetail sellDetail) {
        this.sellDetailDao.insert(sellDetail);
        return this.queryById(sellDetail.getSellDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param SellDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellDetail> SellDetailList) {
        return this.sellDetailDao.insertBatch(SellDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellDetail 实例对象
     * @return 实例对象
     */
    @Override
    public SellDetail update(SellDetail sellDetail) {
        this.sellDetailDao.update(sellDetail);
        return this.queryById(sellDetail.getSellDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param sellDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellDetail> sellDetailList) {
        return this.sellDetailDao.updateBatch(sellDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellDetailId) {
        return this.sellDetailDao.deleteById(sellDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
