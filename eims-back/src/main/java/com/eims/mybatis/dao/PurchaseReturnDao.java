package com.eims.mybatis.dao;

import com.eims.mybatis.entity.PurchaseReturn;
import com.eims.vo.form.PurchaseReturnQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PurchaseReturn)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-04 18:19:58
 */
@Mapper
public interface PurchaseReturnDao {

    /**
     * 通过ID查询单条数据
     *
     * @param purchReturnId 主键
     * @return 实例对象
     */
    PurchaseReturn queryById(Integer purchReturnId);

    /**
     * 查询所有数据
     *
     * @param purchaseReturnQueryForm 实例对象
     * @return 实例对象
     */
    List<PurchaseReturn> queryAll(PurchaseReturnQueryForm purchaseReturnQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param purchaseReturnQueryForm 实例对象
     * @return 对象列表
     */
    List<PurchaseReturn> queryOrByPojo(PurchaseReturnQueryForm purchaseReturnQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param purchaseReturnQueryForm 实例对象
     * @return 对象列表
     */
    List<PurchaseReturn> queryAndByPojo(PurchaseReturnQueryForm purchaseReturnQueryForm);

    /**
     * 新增数据
     *
     * @param purchaseReturn 实例对象
     * @return 影响行数
     */
    int insert(PurchaseReturn purchaseReturn);

    /**
     * 批量新增数据
     *
     * @param purchaseReturnList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<PurchaseReturn> purchaseReturnList);

    /**
     * 修改数据
     *
     * @param purchaseReturn 实例对象
     * @return 影响行数
     */
    int update(PurchaseReturn purchaseReturn);

    /**
     * 批量修改数据
     *
     * @param purchaseReturnList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<PurchaseReturn> purchaseReturnList);

    /**
     * 通过主键删除数据
     *
     * @param purchReturnId 主键
     * @return 影响行数
     */
    int deleteById(Integer purchReturnId);

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
     * @param purchaseReturnList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<PurchaseReturn> purchaseReturnList);
}

