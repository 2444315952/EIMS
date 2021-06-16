package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Transfer;
import com.eims.vo.form.TransferQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Transfer)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-16 10:35:05
 */
@Mapper
public interface TransferDao {

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
    List<Transfer> queryAll(TransferQueryForm transferQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param transferQueryForm 实例对象
     * @return 对象列表
     */
    List<Transfer> queryOrByPojo(TransferQueryForm transferQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param transferQueryForm 实例对象
     * @return 对象列表
     */
    List<Transfer> queryAndByPojo(TransferQueryForm transferQueryForm);

    /**
     * 新增数据
     *
     * @param transfer 实例对象
     * @return 影响行数
     */
    int insert(Transfer transfer);

    /**
     * 批量新增数据
     *
     * @param transferList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Transfer> transferList);

    /**
     * 修改数据
     *
     * @param transfer 实例对象
     * @return 影响行数
     */
    int update(Transfer transfer);

    /**
     * 批量修改数据
     *
     * @param transferList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Transfer> transferList);

    /**
     * 通过主键删除数据
     *
     * @param transferId 主键
     * @return 影响行数
     */
    int deleteById(Integer transferId);

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
     * @param transferList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Transfer> transferList);
}

