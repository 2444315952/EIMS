package com.eims.mybatis.dao;

import com.eims.mybatis.entity.PurchaseReturnDetail;
import com.eims.vo.form.PurchaseReturnDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PurchaseReturnDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-04 18:19:59
 */
@Mapper
public interface PurchaseReturnDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param purchReturnDetailId 主键
     * @return 实例对象
     */
    PurchaseReturnDetail queryById(Integer purchReturnDetailId);

    /**
     * 查询所有数据
     *
     * @param purchaseReturnDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<PurchaseReturnDetail> queryAll(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param purchaseReturnDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<PurchaseReturnDetail> queryOrByPojo(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param purchaseReturnDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<PurchaseReturnDetail> queryAndByPojo(PurchaseReturnDetailQueryForm purchaseReturnDetailQueryForm);

    /**
     * 新增数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 影响行数
     */
    int insert(PurchaseReturnDetail purchaseReturnDetail);

    /**
     * 批量新增数据
     *
     * @param purchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<PurchaseReturnDetail> purchaseReturnDetailList);

    /**
     * 修改数据
     *
     * @param purchaseReturnDetail 实例对象
     * @return 影响行数
     */
    int update(PurchaseReturnDetail purchaseReturnDetail);

    /**
     * 批量修改数据
     *
     * @param purchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<PurchaseReturnDetail> purchaseReturnDetailList);

    /**
     * 通过主键删除数据
     *
     * @param purchReturnDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer purchReturnDetailId);

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
     * @param purchaseReturnDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<PurchaseReturnDetail> purchaseReturnDetailList);
}

