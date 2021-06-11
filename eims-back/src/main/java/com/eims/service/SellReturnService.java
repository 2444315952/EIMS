package com.eims.service;

import com.eims.mybatis.entity.SellReturn;
import com.eims.vo.form.SellReturnQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (SellReturn)表服务接口
 *
 * @author makejava
 * @since 2021-06-11 21:26:52
 */
public interface SellReturnService {

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
    PageInfo<SellReturn> queryAll(SellReturnQueryForm sellReturnQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    PageInfo<SellReturn> queryBySearch(SellReturnQueryForm sellReturnQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    PageInfo<SellReturn> queryByScreen(SellReturnQueryForm sellReturnQueryForm);

    /**
     * 新增数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    SellReturn insert(SellReturn sellReturn);

    /**
     * 批量新增数据
     *
     * @param SellReturnList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<SellReturn> SellReturnList);

    /**
     * 修改数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    SellReturn update(SellReturn sellReturn);

    /**
     * 批量修改数据
     *
     * @param sellReturnList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<SellReturn> sellReturnList);

    /**
     * 通过主键删除数据
     *
     * @param sellReturnId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer sellReturnId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
