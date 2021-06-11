package com.eims.service;

import com.eims.mybatis.entity.CommodityType;
import com.eims.vo.form.CommodityTypeQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (CommodityType)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:24:30
 */
public interface CommodityTypeService {

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
    PageInfo<CommodityType> queryAll(CommodityTypeQueryForm commodityTypeQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param commodityTypeQueryForm
     * @return 对象列表
     */
    PageInfo<CommodityType> queryBySearch(CommodityTypeQueryForm commodityTypeQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param commodityTypeQueryForm
     * @return 对象列表
     */
    PageInfo<CommodityType> queryByScreen(CommodityTypeQueryForm commodityTypeQueryForm);

    /**
     * 新增数据
     *
     * @param commodityType 实例对象
     * @return 实例对象
     */
    CommodityType insert(CommodityType commodityType);

    /**
     * 批量新增数据
     *
     * @param CommodityTypeList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<CommodityType> CommodityTypeList);

    /**
     * 修改数据
     *
     * @param commodityType 实例对象
     * @return 实例对象
     */
    CommodityType update(CommodityType commodityType);

    /**
     * 批量修改数据
     *
     * @param commodityTypeList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<CommodityType> commodityTypeList);

    /**
     * 通过主键删除数据
     *
     * @param commId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer commId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
