package com.eims.mybatis.dao;

import com.eims.mybatis.entity.SupplierProduct;
import com.eims.vo.form.SupplierProductQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SupplierProduct)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-30 19:40:39
 */
@Mapper
public interface SupplierProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param supplierProductId 主键
     * @return 实例对象
     */
    SupplierProduct queryById(Integer supplierProductId);

    /**
     * 查询所有数据
     *
     * @param supplierProductQueryForm 实例对象
     * @return 实例对象
     */
    List<SupplierProduct> queryAll(SupplierProductQueryForm supplierProductQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param supplierProductQueryForm 实例对象
     * @return 对象列表
     */
    List<SupplierProduct> queryOrByPojo(SupplierProductQueryForm supplierProductQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param supplierProductQueryForm 实例对象
     * @return 对象列表
     */
    List<SupplierProduct> queryAndByPojo(SupplierProductQueryForm supplierProductQueryForm);

    /**
     * 新增数据
     *
     * @param supplierProduct 实例对象
     * @return 影响行数
     */
    int insert(SupplierProduct supplierProduct);

    /**
     * 批量新增数据
     *
     * @param supplierProductList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SupplierProduct> supplierProductList);

    /**
     * 修改数据
     *
     * @param supplierProduct 实例对象
     * @return 影响行数
     */
    int update(SupplierProduct supplierProduct);

    /**
     * 批量修改数据
     *
     * @param supplierProductList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<SupplierProduct> supplierProductList);

    /**
     * 通过主键删除数据
     *
     * @param supplierProductId 主键
     * @return 影响行数
     */
    int deleteById(Integer supplierProductId);

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
     * @param supplierProductList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<SupplierProduct> supplierProductList);
}

