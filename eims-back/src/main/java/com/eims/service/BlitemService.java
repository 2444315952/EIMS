package com.eims.service;

import com.eims.mybatis.entity.Blitem;
import com.eims.vo.form.BlitemQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Blitem)表服务接口
 *
 * @author makejava
 * @since 2021-06-16 10:32:52
 */
public interface BlitemService {

    /**
     * 通过ID查询单条数据
     *
     * @param blitemId 主键
     * @return 实例对象
     */
    Blitem queryById(Integer blitemId);

    /**
     * 查询所有数据
     *
     * @param blitemQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Blitem> queryAll(BlitemQueryForm blitemQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param blitemQueryForm
     * @return 对象列表
     */
    PageInfo<Blitem> queryBySearch(BlitemQueryForm blitemQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param blitemQueryForm
     * @return 对象列表
     */
    PageInfo<Blitem> queryByScreen(BlitemQueryForm blitemQueryForm);

    /**
     * 新增数据
     *
     * @param blitem 实例对象
     * @return 实例对象
     */
    Blitem insert(Blitem blitem);

    /**
     * 批量新增数据
     *
     * @param BlitemList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Blitem> BlitemList);

    /**
     * 审核盘点单改库存
     * @param blitemId
     * @return
     */
    boolean auditStorage(Integer blitemId);

    Blitem checkStorage(Integer blitemId);

    /**
     * 修改数据
     *
     * @param blitem 实例对象
     * @return 实例对象
     */
    Blitem update(Blitem blitem);

    /**
     * 批量修改数据
     *
     * @param blitemList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Blitem> blitemList);

    /**
     * 通过主键删除数据
     *
     * @param blitemId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer blitemId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
