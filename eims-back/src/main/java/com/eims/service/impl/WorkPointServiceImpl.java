package com.eims.service.impl;

import com.eims.mybatis.entity.WorkPoint;
import com.eims.vo.form.WorkPointQueryForm;
import com.eims.mybatis.dao.WorkPointDao;
import com.eims.service.WorkPointService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (WorkPoint)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:27:25
 */
@Service("workPointService")
public class WorkPointServiceImpl implements WorkPointService {
    @Resource
    private WorkPointDao workPointDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workPointId 主键
     * @return 实例对象
     */
    @Override
    public WorkPoint queryById(Integer workPointId) {
        return this.workPointDao.queryById(workPointId);
    }

    /**
     * 查询所有数据
     *
     * @param workPointQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<WorkPoint> queryAll(WorkPointQueryForm workPointQueryForm) {
        Page<WorkPoint> page = PageHelper.startPage(workPointQueryForm.getPageNum(), workPointQueryForm.getPageSize());
        List<WorkPoint> workPointList = this.workPointDao.queryAll(workPointQueryForm);
        return new PageInfo<>(workPointList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param workPointQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<WorkPoint> queryBySearch(WorkPointQueryForm workPointQueryForm) {
        Page<WorkPoint> page = PageHelper.startPage(workPointQueryForm.getPageNum(), workPointQueryForm.getPageSize());
        List<WorkPoint> workPointList = this.workPointDao.queryOrByPojo(workPointQueryForm);
        return new PageInfo<>(workPointList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param workPointQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<WorkPoint> queryByScreen(WorkPointQueryForm workPointQueryForm) {
        Page<WorkPoint> page = PageHelper.startPage(workPointQueryForm.getPageNum(), workPointQueryForm.getPageSize());
        List<WorkPoint> workPointList = this.workPointDao.queryAndByPojo(workPointQueryForm);
        return new PageInfo<>(workPointList);
    }

    /**
     * 新增数据
     *
     * @param workPoint 实例对象
     * @return 实例对象
     */
    @Override
    public WorkPoint insert(WorkPoint workPoint) {
        this.workPointDao.insert(workPoint);
        return this.queryById(workPoint.getWorkPointId());
    }

    /**
     * 批量新增数据
     *
     * @param WorkPointList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<WorkPoint> WorkPointList) {
        return this.workPointDao.insertBatch(WorkPointList) == -1;
    }

    /**
     * 修改数据
     *
     * @param workPoint 实例对象
     * @return 实例对象
     */
    @Override
    public WorkPoint update(WorkPoint workPoint) {
        this.workPointDao.update(workPoint);
        return this.queryById(workPoint.getWorkPointId());
    }

    /**
     * 批量修改数据
     *
     * @param workPointList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<WorkPoint> workPointList) {
        return this.workPointDao.updateBatch(workPointList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param workPointId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workPointId) {
        return this.workPointDao.deleteById(workPointId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.workPointDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
