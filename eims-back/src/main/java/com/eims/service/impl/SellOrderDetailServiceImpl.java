package com.eims.service.impl;

import com.eims.mybatis.entity.SellOrderDetail;
import com.eims.vo.form.SellOrderDetailQueryForm;
import com.eims.mybatis.dao.SellOrderDetailDao;
import com.eims.service.SellOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (SellOrderDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-20 21:17:39
 */
@Service("sellOrderDetailService")
public class SellOrderDetailServiceImpl implements SellOrderDetailService {
    @Resource
    private SellOrderDetailDao sellOrderDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellOrderDetailId 主键
     * @return 实例对象
     */
    @Override
    public SellOrderDetail queryById(Integer sellOrderDetailId) {
        return this.sellOrderDetailDao.queryById(sellOrderDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param sellOrderDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellOrderDetail> queryAll(SellOrderDetailQueryForm sellOrderDetailQueryForm) {
        Page<SellOrderDetail> page = PageHelper.startPage(sellOrderDetailQueryForm.getPageNum(), sellOrderDetailQueryForm.getPageSize());
        List<SellOrderDetail> sellOrderDetailList = this.sellOrderDetailDao.queryAll(sellOrderDetailQueryForm);
        return new PageInfo<>(sellOrderDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellOrderDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellOrderDetail> queryBySearch(SellOrderDetailQueryForm sellOrderDetailQueryForm) {
        Page<SellOrderDetail> page = PageHelper.startPage(sellOrderDetailQueryForm.getPageNum(), sellOrderDetailQueryForm.getPageSize());
        List<SellOrderDetail> sellOrderDetailList = this.sellOrderDetailDao.queryOrByPojo(sellOrderDetailQueryForm);
        return new PageInfo<>(sellOrderDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellOrderDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellOrderDetail> queryByScreen(SellOrderDetailQueryForm sellOrderDetailQueryForm) {
        Page<SellOrderDetail> page = PageHelper.startPage(sellOrderDetailQueryForm.getPageNum(), sellOrderDetailQueryForm.getPageSize());
        List<SellOrderDetail> sellOrderDetailList = this.sellOrderDetailDao.queryAndByPojo(sellOrderDetailQueryForm);
        return new PageInfo<>(sellOrderDetailList);
    }

    /**
     * 新增数据
     *
     * @param sellOrderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public SellOrderDetail insert(SellOrderDetail sellOrderDetail) {
        this.sellOrderDetailDao.insert(sellOrderDetail);
        return this.queryById(sellOrderDetail.getSellOrderDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param SellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellOrderDetail> SellOrderDetailList) {
        return this.sellOrderDetailDao.insertBatch(SellOrderDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellOrderDetail 实例对象
     * @return 实例对象
     */
    @Override
    public SellOrderDetail update(SellOrderDetail sellOrderDetail) {
        this.sellOrderDetailDao.update(sellOrderDetail);
        return this.queryById(sellOrderDetail.getSellOrderDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param sellOrderDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellOrderDetail> sellOrderDetailList) {
        return this.sellOrderDetailDao.updateBatch(sellOrderDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellOrderDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellOrderDetailId) {
        return this.sellOrderDetailDao.deleteById(sellOrderDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellOrderDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
