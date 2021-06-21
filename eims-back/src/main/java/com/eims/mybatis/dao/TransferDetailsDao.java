package com.eims.mybatis.dao;

import com.eims.mybatis.entity.TransferDetails;
import com.eims.vo.form.TransferDetailsQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TransferDetails)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-16 23:18:25
 */
@Mapper
public interface TransferDetailsDao {

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
    List<TransferDetails> queryAll(TransferDetailsQueryForm transferDetailsQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param transferDetailsQueryForm 实例对象
     * @return 对象列表
     */
    List<TransferDetails> queryOrByPojo(TransferDetailsQueryForm transferDetailsQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param transferDetailsQueryForm 实例对象
     * @return 对象列表
     */
    List<TransferDetails> queryAndByPojo(TransferDetailsQueryForm transferDetailsQueryForm);

    /**
     * 新增数据
     *
     * @param transferDetails 实例对象
     * @return 影响行数
     */
    int insert(TransferDetails transferDetails);

    /**
     * 批量新增数据
     *
     * @param transferDetailsList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<TransferDetails> transferDetailsList);

    /**
     * 修改数据
     *
     * @param transferDetails 实例对象
     * @return 影响行数
     */
    int update(TransferDetails transferDetails);

    /**
     * 批量修改数据
     *
     * @param transferDetailsList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<TransferDetails> transferDetailsList);

    /**
     * 通过主键删除数据
     *
     * @param transferDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer transferDetailId);

    /**
     * 通过调拨单主键删除明细
     * @param transferId
     * @return
     */
    int deleteByFk(Integer transferId);

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
     * @param transferDetailsList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<TransferDetails> transferDetailsList);
}

