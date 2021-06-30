package com.eims.service.impl;

import com.eims.mybatis.entity.GatherRefundDetail;
import com.eims.vo.form.GatherRefundDetailQueryForm;
import com.eims.mybatis.dao.GatherRefundDetailDao;
import com.eims.service.GatherRefundDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (GatherRefundDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-30 19:36:24
 */
@Service("gatherRefundDetailService")
public class GatherRefundDetailServiceImpl implements GatherRefundDetailService {
    @Resource
    private GatherRefundDetailDao gatherRefundDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gatherRefundDetailId 主键
     * @return 实例对象
     */
    @Override
    public GatherRefundDetail queryById(Integer gatherRefundDetailId) {
        return this.gatherRefundDetailDao.queryById(gatherRefundDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param gatherRefundDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<GatherRefundDetail> queryAll(GatherRefundDetailQueryForm gatherRefundDetailQueryForm) {
        Page<GatherRefundDetail> page = PageHelper.startPage(gatherRefundDetailQueryForm.getPageNum(), gatherRefundDetailQueryForm.getPageSize());
        List<GatherRefundDetail> gatherRefundDetailList = this.gatherRefundDetailDao.queryAll(gatherRefundDetailQueryForm);
        return new PageInfo<>(gatherRefundDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherRefundDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<GatherRefundDetail> queryBySearch(GatherRefundDetailQueryForm gatherRefundDetailQueryForm) {
        Page<GatherRefundDetail> page = PageHelper.startPage(gatherRefundDetailQueryForm.getPageNum(), gatherRefundDetailQueryForm.getPageSize());
        List<GatherRefundDetail> gatherRefundDetailList = this.gatherRefundDetailDao.queryOrByPojo(gatherRefundDetailQueryForm);
        return new PageInfo<>(gatherRefundDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherRefundDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<GatherRefundDetail> queryByScreen(GatherRefundDetailQueryForm gatherRefundDetailQueryForm) {
        Page<GatherRefundDetail> page = PageHelper.startPage(gatherRefundDetailQueryForm.getPageNum(), gatherRefundDetailQueryForm.getPageSize());
        List<GatherRefundDetail> gatherRefundDetailList = this.gatherRefundDetailDao.queryAndByPojo(gatherRefundDetailQueryForm);
        return new PageInfo<>(gatherRefundDetailList);
    }

    /**
     * 新增数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 实例对象
     */
    @Override
    public GatherRefundDetail insert(GatherRefundDetail gatherRefundDetail) {
        this.gatherRefundDetailDao.insert(gatherRefundDetail);
        return this.queryById(gatherRefundDetail.getGatherRefundDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param GatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<GatherRefundDetail> GatherRefundDetailList) {
        return this.gatherRefundDetailDao.insertBatch(GatherRefundDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param gatherRefundDetail 实例对象
     * @return 实例对象
     */
    @Override
    public GatherRefundDetail update(GatherRefundDetail gatherRefundDetail) {
        this.gatherRefundDetailDao.update(gatherRefundDetail);
        return this.queryById(gatherRefundDetail.getGatherRefundDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param gatherRefundDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<GatherRefundDetail> gatherRefundDetailList) {
        return this.gatherRefundDetailDao.updateBatch(gatherRefundDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param gatherRefundDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer gatherRefundDetailId) {
        return this.gatherRefundDetailDao.deleteById(gatherRefundDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.gatherRefundDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
