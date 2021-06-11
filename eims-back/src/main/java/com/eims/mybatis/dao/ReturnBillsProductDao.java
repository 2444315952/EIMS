package com.eims.mybatis.dao;

import com.eims.mybatis.entity.ReturnBillsProduct;
import com.eims.vo.form.ReturnBillsProductQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ReturnBillsProduct)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:26:46
 */
@Mapper
public interface ReturnBillsProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param retBpId 主键
     * @return 实例对象
     */
    ReturnBillsProduct queryById(Integer retBpId);

    /**
     * 查询所有数据
     *
     * @param returnBillsProductQueryForm 实例对象
     * @return 实例对象
     */
    List<ReturnBillsProduct> queryAll(ReturnBillsProductQueryForm returnBillsProductQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param returnBillsProductQueryForm 实例对象
     * @return 对象列表
     */
    List<ReturnBillsProduct> queryOrByPojo(ReturnBillsProductQueryForm returnBillsProductQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param returnBillsProductQueryForm 实例对象
     * @return 对象列表
     */
    List<ReturnBillsProduct> queryAndByPojo(ReturnBillsProductQueryForm returnBillsProductQueryForm);

    /**
     * 新增数据
     *
     * @param returnBillsProduct 实例对象
     * @return 影响行数
     */
    int insert(ReturnBillsProduct returnBillsProduct);

    /**
     * 批量新增数据
     *
     * @param returnBillsProductList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<ReturnBillsProduct> returnBillsProductList);

    /**
     * 修改数据
     *
     * @param returnBillsProduct 实例对象
     * @return 影响行数
     */
    int update(ReturnBillsProduct returnBillsProduct);

    /**
     * 批量修改数据
     *
     * @param returnBillsProductList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<ReturnBillsProduct> returnBillsProductList);

    /**
     * 通过主键删除数据
     *
     * @param retBpId 主键
     * @return 影响行数
     */
    int deleteById(Integer retBpId);

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
     * @param returnBillsProductList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<ReturnBillsProduct> returnBillsProductList);
}

