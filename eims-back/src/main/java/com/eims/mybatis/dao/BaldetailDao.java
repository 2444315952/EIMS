package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Baldetail;
import com.eims.vo.form.BaldetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Baldetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-16 10:34:37
 */
@Mapper
public interface BaldetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param baldetailId 主键
     * @return 实例对象
     */
    Baldetail queryById(Integer baldetailId);

    /**
     * 查询所有数据
     *
     * @param baldetailQueryForm 实例对象
     * @return 实例对象
     */
    List<Baldetail> queryAll(BaldetailQueryForm baldetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param baldetailQueryForm 实例对象
     * @return 对象列表
     */
    List<Baldetail> queryOrByPojo(BaldetailQueryForm baldetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param baldetailQueryForm 实例对象
     * @return 对象列表
     */
    List<Baldetail> queryAndByPojo(BaldetailQueryForm baldetailQueryForm);

    /**
     * 新增数据
     *
     * @param baldetail 实例对象
     * @return 影响行数
     */
    int insert(Baldetail baldetail);

    /**
     * 批量新增数据
     *
     * @param baldetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Baldetail> baldetailList);

    /**
     * 修改数据
     *
     * @param baldetail 实例对象
     * @return 影响行数
     */
    int update(Baldetail baldetail);

    /**
     * 批量修改数据
     *
     * @param baldetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Baldetail> baldetailList);

    /**
     * 通过主键删除数据
     *
     * @param baldetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer baldetailId);

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
     * @param baldetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Baldetail> baldetailList);
}

