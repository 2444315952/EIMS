package com.eims.mybatis.dao;

import com.eims.mybatis.entity.WorkPoint;
import com.eims.vo.form.WorkPointQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (WorkPoint)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:27:25
 */
@Mapper
public interface WorkPointDao {

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
    List<WorkPoint> queryAll(WorkPointQueryForm workPointQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param workPointQueryForm 实例对象
     * @return 对象列表
     */
    List<WorkPoint> queryOrByPojo(WorkPointQueryForm workPointQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param workPointQueryForm 实例对象
     * @return 对象列表
     */
    List<WorkPoint> queryAndByPojo(WorkPointQueryForm workPointQueryForm);

    /**
     * 新增数据
     *
     * @param workPoint 实例对象
     * @return 影响行数
     */
    int insert(WorkPoint workPoint);

    /**
     * 批量新增数据
     *
     * @param workPointList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<WorkPoint> workPointList);

    /**
     * 修改数据
     *
     * @param workPoint 实例对象
     * @return 影响行数
     */
    int update(WorkPoint workPoint);

    /**
     * 批量修改数据
     *
     * @param workPointList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<WorkPoint> workPointList);

    /**
     * 通过主键删除数据
     *
     * @param workPointId 主键
     * @return 影响行数
     */
    int deleteById(Integer workPointId);

    /**
     * 通过主键列表删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    int deleteBatch(List<Integer> ids);

    /**
     * 批量对象列表删除数据
     *
     * @param workPointList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<WorkPoint> workPointList);
}

