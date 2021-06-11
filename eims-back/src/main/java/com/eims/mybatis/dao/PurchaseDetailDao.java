package com.eims.mybatis.dao;

import com.eims.mybatis.entity.PurchaseDetail;
import com.eims.vo.form.PurchaseDetailQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PurchaseDetail)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:25:54
 */
@Mapper
public interface PurchaseDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param purchDetailId 主键
     * @return 实例对象
     */
    PurchaseDetail queryById(Integer purchDetailId);

    /**
     * 查询所有数据
     *
     * @param purchaseDetailQueryForm 实例对象
     * @return 实例对象
     */
    List<PurchaseDetail> queryAll(PurchaseDetailQueryForm purchaseDetailQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param purchaseDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<PurchaseDetail> queryOrByPojo(PurchaseDetailQueryForm purchaseDetailQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param purchaseDetailQueryForm 实例对象
     * @return 对象列表
     */
    List<PurchaseDetail> queryAndByPojo(PurchaseDetailQueryForm purchaseDetailQueryForm);

    /**
     * 新增数据
     *
     * @param purchaseDetail 实例对象
     * @return 影响行数
     */
    int insert(PurchaseDetail purchaseDetail);

    /**
     * 批量新增数据
     *
     * @param purchaseDetailList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<PurchaseDetail> purchaseDetailList);

    /**
     * 修改数据
     *
     * @param purchaseDetail 实例对象
     * @return 影响行数
     */
    int update(PurchaseDetail purchaseDetail);

    /**
     * 批量修改数据
     *
     * @param purchaseDetailList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<PurchaseDetail> purchaseDetailList);

    /**
     * 通过主键删除数据
     *
     * @param purchDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer purchDetailId);

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
     * @param purchaseDetailList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<PurchaseDetail> purchaseDetailList);
}

