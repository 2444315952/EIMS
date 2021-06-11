package com.eims.service;

import com.eims.mybatis.entity.WarehousingDetail;
import com.eims.vo.form.WarehousingDetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (WarehousingDetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-08 09:48:36
 */
public interface WarehousingDetailService {

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
    PageInfo<WarehousingDetail> queryAll(WarehousingDetailQueryForm warehousingDetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param warehousingDetailQueryForm
     * @return 对象列表
     */
    PageInfo<WarehousingDetail> queryBySearch(WarehousingDetailQueryForm warehousingDetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param warehousingDetailQueryForm
     * @return 对象列表
     */
    PageInfo<WarehousingDetail> queryByScreen(WarehousingDetailQueryForm warehousingDetailQueryForm);

    /**
     * 新增数据
     *
     * @param warehousingDetail 实例对象
     * @return 实例对象
     */
    WarehousingDetail insert(WarehousingDetail warehousingDetail);

    /**
     * 批量新增数据
     *
     * @param WarehousingDetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<WarehousingDetail> WarehousingDetailList);

    /**
     * 修改数据
     *
     * @param warehousingDetail 实例对象
     * @return 实例对象
     */
    WarehousingDetail update(WarehousingDetail warehousingDetail);

    /**
     * 批量修改数据
     *
     * @param warehousingDetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<WarehousingDetail> warehousingDetailList);

    /**
     * 通过主键删除数据
     *
     * @param warehousingDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer warehousingDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
