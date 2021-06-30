package com.eims.mybatis.dao;

import com.eims.mybatis.entity.MoneyAccount;
import com.eims.vo.form.MoneyAccountQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (MoneyAccount)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-28 08:48:20
 */
@Mapper
public interface MoneyAccountDao {

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
    List<MoneyAccount> queryAll(MoneyAccountQueryForm moneyAccountQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param moneyAccountQueryForm 实例对象
     * @return 对象列表
     */
    List<MoneyAccount> queryOrByPojo(MoneyAccountQueryForm moneyAccountQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param moneyAccountQueryForm 实例对象
     * @return 对象列表
     */
    List<MoneyAccount> queryAndByPojo(MoneyAccountQueryForm moneyAccountQueryForm);

    /**
     * 新增数据
     *
     * @param moneyAccount 实例对象
     * @return 影响行数
     */
    int insert(MoneyAccount moneyAccount);

    /**
     * 批量新增数据
     *
     * @param moneyAccountList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<MoneyAccount> moneyAccountList);

    /**
     * 修改数据
     *
     * @param moneyAccount 实例对象
     * @return 影响行数
     */
    int update(MoneyAccount moneyAccount);

    /**
     * 批量修改数据
     *
     * @param moneyAccountList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<MoneyAccount> moneyAccountList);

    /**
     * 通过主键删除数据
     *
     * @param moneyAccountId 主键
     * @return 影响行数
     */
    int deleteById(Integer moneyAccountId);

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
     * @param moneyAccountList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<MoneyAccount> moneyAccountList);
}

