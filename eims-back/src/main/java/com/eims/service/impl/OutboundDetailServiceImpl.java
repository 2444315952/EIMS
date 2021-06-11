package com.eims.service.impl;

import com.eims.mybatis.entity.OutboundDetail;
import com.eims.vo.form.OutboundDetailQueryForm;
import com.eims.mybatis.dao.OutboundDetailDao;
import com.eims.service.OutboundDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (OutboundDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-08 09:47:42
 */
@Service("outboundDetailService")
public class OutboundDetailServiceImpl implements OutboundDetailService {
    @Resource
    private OutboundDetailDao outboundDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param outboundDetailId 主键
     * @return 实例对象
     */
    @Override
    public OutboundDetail queryById(Integer outboundDetailId) {
        return this.outboundDetailDao.queryById(outboundDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param outboundDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<OutboundDetail> queryAll(OutboundDetailQueryForm outboundDetailQueryForm) {
        Page<OutboundDetail> page = PageHelper.startPage(outboundDetailQueryForm.getPageNum(), outboundDetailQueryForm.getPageSize());
        List<OutboundDetail> outboundDetailList = this.outboundDetailDao.queryAll(outboundDetailQueryForm);
        return new PageInfo<>(outboundDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param outboundDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<OutboundDetail> queryBySearch(OutboundDetailQueryForm outboundDetailQueryForm) {
        Page<OutboundDetail> page = PageHelper.startPage(outboundDetailQueryForm.getPageNum(), outboundDetailQueryForm.getPageSize());
        List<OutboundDetail> outboundDetailList = this.outboundDetailDao.queryOrByPojo(outboundDetailQueryForm);
        return new PageInfo<>(outboundDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param outboundDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<OutboundDetail> queryByScreen(OutboundDetailQueryForm outboundDetailQueryForm) {
        Page<OutboundDetail> page = PageHelper.startPage(outboundDetailQueryForm.getPageNum(), outboundDetailQueryForm.getPageSize());
        List<OutboundDetail> outboundDetailList = this.outboundDetailDao.queryAndByPojo(outboundDetailQueryForm);
        return new PageInfo<>(outboundDetailList);
    }

    /**
     * 新增数据
     *
     * @param outboundDetail 实例对象
     * @return 实例对象
     */
    @Override
    public OutboundDetail insert(OutboundDetail outboundDetail) {
        this.outboundDetailDao.insert(outboundDetail);
        return this.queryById(outboundDetail.getOutboundDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param OutboundDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<OutboundDetail> OutboundDetailList) {
        return this.outboundDetailDao.insertBatch(OutboundDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param outboundDetail 实例对象
     * @return 实例对象
     */
    @Override
    public OutboundDetail update(OutboundDetail outboundDetail) {
        this.outboundDetailDao.update(outboundDetail);
        return this.queryById(outboundDetail.getOutboundDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param outboundDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<OutboundDetail> outboundDetailList) {
        return this.outboundDetailDao.updateBatch(outboundDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param outboundDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer outboundDetailId) {
        return this.outboundDetailDao.deleteById(outboundDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.outboundDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
