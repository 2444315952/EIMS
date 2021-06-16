package com.eims.service;

import com.eims.mybatis.entity.TransferDetails;
import com.eims.vo.form.TransferDetailsQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (TransferDetails)表服务接口
 *
 * @author makejava
 * @since 2021-06-16 10:35:20
 */
public interface TransferDetailsService {

    /**
     * 通过ID查询单条数据
     *
     * @param transferDetailId 主键
     * @return 实例对象
     */
    TransferDetails queryById(Integer transferDetailId);

    /**
     * 查询所有数据
     *
     * @param transferDetailsQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<TransferDetails> queryAll(TransferDetailsQueryForm transferDetailsQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param transferDetailsQueryForm
     * @return 对象列表
     */
    PageInfo<TransferDetails> queryBySearch(TransferDetailsQueryForm transferDetailsQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param transferDetailsQueryForm
     * @return 对象列表
     */
    PageInfo<TransferDetails> queryByScreen(TransferDetailsQueryForm transferDetailsQueryForm);

    /**
     * 新增数据
     *
     * @param transferDetails 实例对象
     * @return 实例对象
     */
    TransferDetails insert(TransferDetails transferDetails);

    /**
     * 批量新增数据
     *
     * @param TransferDetailsList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<TransferDetails> TransferDetailsList);

    /**
     * 修改数据
     *
     * @param transferDetails 实例对象
     * @return 实例对象
     */
    TransferDetails update(TransferDetails transferDetails);

    /**
     * 批量修改数据
     *
     * @param transferDetailsList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<TransferDetails> transferDetailsList);

    /**
     * 通过主键删除数据
     *
     * @param transferDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer transferDetailId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
