package com.eims.service;

import com.eims.mybatis.entity.Transfer;
import com.eims.vo.form.TransferQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (Transfer)表服务接口
 *
 * @author makejava
 * @since 2021-06-16 10:35:06
 */
public interface TransferService {

    /**
     * 通过ID查询单条数据
     *
     * @param transferId 主键
     * @return 实例对象
     */
    Transfer queryById(Integer transferId);

    /**
     * 查询所有数据
     *
     * @param transferQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<Transfer> queryAll(TransferQueryForm transferQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param transferQueryForm
     * @return 对象列表
     */
    PageInfo<Transfer> queryBySearch(TransferQueryForm transferQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param transferQueryForm
     * @return 对象列表
     */
    PageInfo<Transfer> queryByScreen(TransferQueryForm transferQueryForm);

    /**
     * 新增数据
     *
     * @param transfer 实例对象
     * @return 实例对象
     */
    Transfer insert(Transfer transfer);

    /**
     * 批量新增数据
     *
     * @param TransferList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<Transfer> TransferList);

    /**
     * 修改数据
     *
     * @param transfer 实例对象
     * @return 实例对象
     */
    Transfer update(Transfer transfer);

    Transfer checkStorage(Integer transferId);
    /**
     * 批量修改数据
     *
     * @param transferList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<Transfer> transferList);

    /**
     * 通过主键删除数据
     *
     * @param transferId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer transferId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
