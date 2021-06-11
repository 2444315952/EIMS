package com.eims.mybatis.dao;

import com.eims.mybatis.entity.WarehousingDetail;
import com.eims.vo.form.WarehousingDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (WarehousingDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-08 09:48:36
 */
@Mapper
public interface WarehousingDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param warehousingDetailId 主键
     * @return 实例对象
     */
    WarehousingDetail queryById(Integer warehousingDetailId);

    /**
     * 查询所有数据
     *
     * @param warehousingDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<WarehousingDetail> queryAll(WarehousingDetailQueryForm warehousingDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param warehousingDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<WarehousingDetail> queryOrByPojo(WarehousingDetailQueryForm warehousingDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param warehousingDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<WarehousingDetail> queryAndByPojo(WarehousingDetailQueryForm warehousingDetailQueryForm);

    /**
     * 新增数据
     *
     * @param warehousingDetail 实例对象
     * @return 影响行数
     */
    int insert(WarehousingDetail warehousingDetail);

    /**
     * 批量新增数据
     *
     * @param warehousingDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<WarehousingDetail> warehousingDetailList);

    /**
     * 修改数据
     *
     * @param warehousingDetail 实例对象
     * @return 影响行数
     */
    int update(WarehousingDetail warehousingDetail);

    /**
     * 批量修改数据
     *
     * @param warehousingDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<WarehousingDetail> warehousingDetailList);

    /**
     * 通过主键删除数据
     *
     * @param warehousingDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer warehousingDetailId);

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
     * @param warehousingDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<WarehousingDetail> warehousingDetailList);
}

