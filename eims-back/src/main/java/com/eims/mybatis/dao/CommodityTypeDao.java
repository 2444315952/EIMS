package com.eims.mybatis.dao;

import com.eims.mybatis.entity.CommodityType;
import com.eims.vo.form.CommodityTypeQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (CommodityType)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:24:30
 */
@Mapper
public interface CommodityTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param commId 主键
     * @return 实例对象
     */
    CommodityType queryById(Integer commId);

    /**
     * 查询所有数据
     *
     * @param commodityTypeQueryForm 实例对象
     * @return 实例对象
     */
    List<CommodityType> queryAll(CommodityTypeQueryForm commodityTypeQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param commodityTypeQueryForm 实例对象
     * @return 对象列表
     */
    List<CommodityType> queryOrByPojo(CommodityTypeQueryForm commodityTypeQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param commodityTypeQueryForm 实例对象
     * @return 对象列表
     */
    List<CommodityType> queryAndByPojo(CommodityTypeQueryForm commodityTypeQueryForm);

    /**
     * 新增数据
     *
     * @param commodityType 实例对象
     * @return 影响行数
     */
    int insert(CommodityType commodityType);

    /**
     * 批量新增数据
     *
     * @param commodityTypeList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<CommodityType> commodityTypeList);

    /**
     * 修改数据
     *
     * @param commodityType 实例对象
     * @return 影响行数
     */
    int update(CommodityType commodityType);

    /**
     * 批量修改数据
     *
     * @param commodityTypeList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<CommodityType> commodityTypeList);

    /**
     * 通过主键删除数据
     *
     * @param commId 主键
     * @return 影响行数
     */
    int deleteById(Integer commId);

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
     * @param commodityTypeList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<CommodityType> commodityTypeList);
}

