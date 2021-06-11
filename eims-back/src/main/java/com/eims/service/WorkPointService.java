package com.eims.service;

import com.eims.mybatis.entity.WorkPoint;
import com.eims.vo.form.WorkPointQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (WorkPoint)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:27:25
 */
public interface WorkPointService {

    /**
     * 通过ID查询单条数据
     *
     * @param workPointId 主键
     * @return 实例对象
     */
    WorkPoint queryById(Integer workPointId);

    /**
     * 查询所有数据
     *
     * @param workPointQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<WorkPoint> queryAll(WorkPointQueryForm workPointQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param workPointQueryForm
     * @return 对象列表
     */
    PageInfo<WorkPoint> queryBySearch(WorkPointQueryForm workPointQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param workPointQueryForm
     * @return 对象列表
     */
    PageInfo<WorkPoint> queryByScreen(WorkPointQueryForm workPointQueryForm);

    /**
     * 新增数据
     *
     * @param workPoint 实例对象
     * @return 实例对象
     */
    WorkPoint insert(WorkPoint workPoint);

    /**
     * 批量新增数据
     *
     * @param WorkPointList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<WorkPoint> WorkPointList);

    /**
     * 修改数据
     *
     * @param workPoint 实例对象
     * @return 实例对象
     */
    WorkPoint update(WorkPoint workPoint);

    /**
     * 批量修改数据
     *
     * @param workPointList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<WorkPoint> workPointList);

    /**
     * 通过主键删除数据
     *
     * @param workPointId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer workPointId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
