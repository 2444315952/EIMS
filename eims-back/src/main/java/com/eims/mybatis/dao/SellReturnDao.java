package com.eims.mybatis.dao;

import com.eims.mybatis.entity.SellReturn;
import com.eims.vo.form.SellReturnQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SellReturn)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:26:52
 */
@Mapper
public interface SellReturnDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sellReturnId 主键
     * @return 实例对象
     */
    SellReturn queryById(Integer sellReturnId);

    /**
     * 查询所有数据
     *
     * @param sellReturnQueryForm 实例对象
     * @return 实例对象
     */
    List<SellReturn> queryAll(SellReturnQueryForm sellReturnQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param sellReturnQueryForm 实例对象
     * @return 对象列表
     */
    List<SellReturn> queryOrByPojo(SellReturnQueryForm sellReturnQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param sellReturnQueryForm 实例对象
     * @return 对象列表
     */
    List<SellReturn> queryAndByPojo(SellReturnQueryForm sellReturnQueryForm);

    /**
     * 新增数据
     *
     * @param sellReturn 实例对象
     * @return 影响行数
     */
    int insert(SellReturn sellReturn);

    /**
     * 批量新增数据
     *
     * @param sellReturnList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SellReturn> sellReturnList);

    /**
     * 修改数据
     *
     * @param sellReturn 实例对象
     * @return 影响行数
     */
    int update(SellReturn sellReturn);

    /**
     * 批量修改数据
     *
     * @param sellReturnList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<SellReturn> sellReturnList);

    /**
     * 通过主键删除数据
     *
     * @param sellReturnId 主键
     * @return 影响行数
     */
    int deleteById(Integer sellReturnId);

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
     * @param sellReturnList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<SellReturn> sellReturnList);
}

