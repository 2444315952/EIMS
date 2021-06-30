package com.eims.service.impl;

import com.eims.mybatis.entity.GatherDetail;
import com.eims.vo.form.GatherDetailQueryForm;
import com.eims.mybatis.dao.GatherDetailDao;
import com.eims.service.GatherDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (GatherDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-25 22:42:20
 */
@Service("gatherDetailService")
public class GatherDetailServiceImpl implements GatherDetailService {
    @Resource
    private GatherDetailDao gatherDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gatherDetailId 主键
     * @return 实例对象
     */
    @Override
    public GatherDetail queryById(Integer gatherDetailId) {
        return this.gatherDetailDao.queryById(gatherDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param gatherDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<GatherDetail> queryAll(GatherDetailQueryForm gatherDetailQueryForm) {
        Page<GatherDetail> page = PageHelper.startPage(gatherDetailQueryForm.getPageNum(), gatherDetailQueryForm.getPageSize());
        List<GatherDetail> gatherDetailList = this.gatherDetailDao.queryAll(gatherDetailQueryForm);
        return new PageInfo<>(gatherDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<GatherDetail> queryBySearch(GatherDetailQueryForm gatherDetailQueryForm) {
        Page<GatherDetail> page = PageHelper.startPage(gatherDetailQueryForm.getPageNum(), gatherDetailQueryForm.getPageSize());
        List<GatherDetail> gatherDetailList = this.gatherDetailDao.queryOrByPojo(gatherDetailQueryForm);
        return new PageInfo<>(gatherDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<GatherDetail> queryByScreen(GatherDetailQueryForm gatherDetailQueryForm) {
        Page<GatherDetail> page = PageHelper.startPage(gatherDetailQueryForm.getPageNum(), gatherDetailQueryForm.getPageSize());
        List<GatherDetail> gatherDetailList = this.gatherDetailDao.queryAndByPojo(gatherDetailQueryForm);
        return new PageInfo<>(gatherDetailList);
    }

    /**
     * 新增数据
     *
     * @param gatherDetail 实例对象
     * @return 实例对象
     */
    @Override
    public GatherDetail insert(GatherDetail gatherDetail) {
        this.gatherDetailDao.insert(gatherDetail);
        return this.queryById(gatherDetail.getGatherDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param GatherDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<GatherDetail> GatherDetailList) {
        return this.gatherDetailDao.insertBatch(GatherDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param gatherDetail 实例对象
     * @return 实例对象
     */
    @Override
    public GatherDetail update(GatherDetail gatherDetail) {
        this.gatherDetailDao.update(gatherDetail);
        return this.queryById(gatherDetail.getGatherDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param gatherDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<GatherDetail> gatherDetailList) {
        return this.gatherDetailDao.updateBatch(gatherDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param gatherDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer gatherDetailId) {
        return this.gatherDetailDao.deleteById(gatherDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.gatherDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
