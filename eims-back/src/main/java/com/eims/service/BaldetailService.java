package com.eims.service;

import com.eims.mybatis.entity.Baldetail;
import com.eims.vo.form.BaldetailQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Baldetail)表服务接口
 *
 * @author makejava
 * @since 2021-06-16 10:34:38
 */
public interface BaldetailService {

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
    PageInfo<Baldetail> queryAll(BaldetailQueryForm baldetailQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param baldetailQueryForm
     * @return 对象列表
     */
    PageInfo<Baldetail> queryBySearch(BaldetailQueryForm baldetailQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param baldetailQueryForm
     * @return 对象列表
     */
    PageInfo<Baldetail> queryByScreen(BaldetailQueryForm baldetailQueryForm);

    /**
     * 新增数据
     *
     * @param baldetail 实例对象
     * @return 实例对象
     */
    Baldetail insert(Baldetail baldetail);

    /**
     * 批量新增数据
     *
     * @param BaldetailList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Baldetail> BaldetailList);

    /**
     * 修改数据
     *
     * @param baldetail 实例对象
     * @return 实例对象
     */
    Baldetail update(Baldetail baldetail);

    /**
     * 批量修改数据
     *
     * @param baldetailList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Baldetail> baldetailList);

    /**
     * 通过主键删除数据
     *
     * @param baldetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer baldetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
