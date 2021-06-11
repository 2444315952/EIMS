package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Purchase;
import com.eims.vo.form.PurchaseQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Purchase)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-04 18:19:56
 */
@Mapper
public interface PurchaseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param purchId 主键
     * @return 实例对象
     */
    Purchase queryById(Integer purchId);

    /**
     * 查询所有数据
     *
     * @param purchaseQueryForm 实例对象
     * @return 实例对象
     */
    List<Purchase> queryAll(PurchaseQueryForm purchaseQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param purchaseQueryForm 实例对象
     * @return 对象列表
     */
    List<Purchase> queryOrByPojo(PurchaseQueryForm purchaseQueryForm);

    int selectCountByDocuNumAndWorkPointId(@Param("docuNum") String docuNum,@Param("workPointId") Integer workPointId);

    /**
     * 通过实体作为并且条件查询
     *
     * @param purchaseQueryForm 实例对象
     * @return 对象列表
     */
    List<Purchase> queryAndByPojo(PurchaseQueryForm purchaseQueryForm);

    /**
     * 新增数据
     *
     * @param purchase 实例对象
     * @return 影响行数
     */
    int insert(Purchase purchase);

    /**
     * 批量新增数据
     *
     * @param purchaseList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Purchase> purchaseList);

    /**
     * 修改数据
     *
     * @param purchase 实例对象
     * @return 影响行数
     */
    int update(Purchase purchase);

    /**
     * 批量修改数据
     *
     * @param purchaseList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Purchase> purchaseList);

    /**
     * 通过主键id修改审核状态
     * @param audited
     * @param purchId
     * @return
     */
    int updateAuditedById(@Param("audited") Integer audited,@Param("purchId") Integer purchId);

    /**
     * 通过主键删除数据
     *
     * @param purchId 主键
     * @return 影响行数
     */
    int deleteById(Integer purchId);

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
     * @param purchaseList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Purchase> purchaseList);


}

