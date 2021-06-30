package com.eims.service;

import com.eims.mybatis.entity.MoneyAccount;
import com.eims.vo.form.MoneyAccountQueryForm;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * (MoneyAccount)表服务接口
 *
 * @author makejava
 * @since 2021-06-28 08:48:21
 */
public interface MoneyAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param moneyAccountId 主键
     * @return 实例对象
     */
    MoneyAccount queryById(Integer moneyAccountId);

    /**
     * 查询所有数据
     *
     * @param moneyAccountQueryForm 实例对象
     * @return 实例对象
     */
    PageInfo<MoneyAccount> queryAll(MoneyAccountQueryForm moneyAccountQueryForm);

    /**
     * 根据查询条件搜索数据
     *
     * @param moneyAccountQueryForm
     * @return 对象列表
     */
    PageInfo<MoneyAccount> queryBySearch(MoneyAccountQueryForm moneyAccountQueryForm);

    /**
     * 根据查询条件筛选数据
     *
     * @param moneyAccountQueryForm
     * @return 对象列表
     */
    PageInfo<MoneyAccount> queryByScreen(MoneyAccountQueryForm moneyAccountQueryForm);

    /**
     * 新增数据
     *
     * @param moneyAccount 实例对象
     * @return 实例对象
     */
    MoneyAccount insert(MoneyAccount moneyAccount);

    /**
     * 批量新增数据
     *
     * @param MoneyAccountList 实例对象列表
     * @return 影响行数
     */
    boolean insertBatch(List<MoneyAccount> MoneyAccountList);

    /**
     * 修改数据
     *
     * @param moneyAccount 实例对象
     * @return 实例对象
     */
    MoneyAccount update(MoneyAccount moneyAccount);

    /**
     * 批量修改数据
     *
     * @param moneyAccountList 实例对象列表
     * @return 影响行数
     */
    boolean updateBatch(List<MoneyAccount> moneyAccountList);

    /**
     * 通过主键删除数据
     *
     * @param moneyAccountId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer moneyAccountId);

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    boolean deleteBatch(List<Integer> ids);
}
