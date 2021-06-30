package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Gather;
import com.eims.vo.form.GatherQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Gather)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-27 20:23:02
 */
@Mapper
public interface GatherDao {

    /**
     * 通过ID查询单条数据
     *
     * @param gatherId 主键
     * @return 实例对象
     */
    Gather queryById(Integer gatherId);

    /**
     * 查询所有数据
     *
     * @param gatherQueryForm 实例对象
     * @return 实例对象
     */
    List<Gather> queryAll(GatherQueryForm gatherQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param gatherQueryForm 实例对象
     * @return 对象列表
     */
    List<Gather> queryOrByPojo(GatherQueryForm gatherQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param gatherQueryForm 实例对象
     * @return 对象列表
     */
    List<Gather> queryAndByPojo(GatherQueryForm gatherQueryForm);

    /**
     * 新增数据
     *
     * @param gather 实例对象
     * @return 影响行数
     */
    int insert(Gather gather);

    /**
     * 批量新增数据
     *
     * @param gatherList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Gather> gatherList);

    /**
     * 修改数据
     *
     * @param gather 实例对象
     * @return 影响行数
     */
    int update(Gather gather);

    /**
     * 批量修改数据
     *
     * @param gatherList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Gather> gatherList);

    /**
     * 通过主键删除数据
     *
     * @param gatherId 主键
     * @return 影响行数
     */
    int deleteById(Integer gatherId);

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
     * @param gatherList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Gather> gatherList);
}

