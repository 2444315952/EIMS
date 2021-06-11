package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Supplier;
import com.eims.vo.form.SupplierQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Supplier)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-04 18:20:01
 */
@Mapper
public interface SupplierDao {

    /**
     * 通过ID查询单条数据
     *
     * @param supplierId 主键
     * @return 实例对象
     */
    Supplier queryById(Integer supplierId);

    /**
     * 查询所有数据
     *
     * @param supplierQueryForm 实例对象
     * @return 实例对象
     */
    List<Supplier> queryAll(SupplierQueryForm supplierQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param supplierQueryForm 实例对象
     * @return 对象列表
     */
    List<Supplier> queryOrByPojo(SupplierQueryForm supplierQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param supplierQueryForm 实例对象
     * @return 对象列表
     */
    List<Supplier> queryAndByPojo(SupplierQueryForm supplierQueryForm);

    /**
     * 新增数据
     *
     * @param supplier 实例对象
     * @return 影响行数
     */
    int insert(Supplier supplier);

    /**
     * 批量新增数据
     *
     * @param supplierList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Supplier> supplierList);

    /**
     * 修改数据
     *
     * @param supplier 实例对象
     * @return 影响行数
     */
    int update(Supplier supplier);

    /**
     * 批量修改数据
     *
     * @param supplierList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Supplier> supplierList);

    /**
     * 通过主键删除数据
     *
     * @param supplierId 主键
     * @return 影响行数
     */
    int deleteById(Integer supplierId);

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
     * @param supplierList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Supplier> supplierList);
}

