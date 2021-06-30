package com.eims.service;

import com.eims.mybatis.entity.Gather;
import com.eims.vo.form.GatherQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Gather)表服务接口
 *
 * @author makejava
 * @since 2021-06-27 20:23:02
 */
public interface GatherService {

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
    PageInfo<Gather> queryAll(GatherQueryForm gatherQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherQueryForm
     * @return 对象列表
     */
    PageInfo<Gather> queryBySearch(GatherQueryForm gatherQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherQueryForm
     * @return 对象列表
     */
    PageInfo<Gather> queryByScreen(GatherQueryForm gatherQueryForm);

    /**
     * 新增数据
     *
     * @param gather 实例对象
     * @return 实例对象
     */
    Gather insert(Gather gather);

    /**
     * 批量新增数据
     *
     * @param GatherList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Gather> GatherList);

    /**
     * 修改数据
     *
     * @param gather 实例对象
     * @return 实例对象
     */
    Gather update(Gather gather);

    /**
     * 批量修改数据
     *
     * @param gatherList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Gather> gatherList);

    /**
     * 通过主键删除数据
     *
     * @param gatherId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer gatherId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
