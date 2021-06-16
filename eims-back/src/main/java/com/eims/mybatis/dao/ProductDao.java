package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Product;
import com.eims.vo.form.ProductQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Product)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:25:52
 */
@Mapper
public interface ProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    Product queryById(Integer productId);

    /**
     * 查询所有数据
     *
     * @param productQueryForm 实例对象
     * @return 实例对象
     */
    List<Product> queryAll(ProductQueryForm productQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param productQueryForm 实例对象
     * @return 对象列表
     */
    List<Product> queryOrByPojo(ProductQueryForm productQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param productQueryForm 实例对象
     * @return 对象列表
     */
    List<Product> queryAndByPojo(ProductQueryForm productQueryForm);


    List<Product> queryByAllLike(@Param("comm_type_name") String comm_type_name,@Param("product_name") String product_name,@Param("is_enabled") String is_enabled) ;


    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int insert(Product product);

    /**
     * 批量新增数据
     *
     * @param productList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Product> productList);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int update(Product product);

    /**
     * 批量修改数据
     *
     * @param productList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Product> productList);

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 影响行数
     */
    int deleteById(Integer productId);

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
     * @param productList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Product> productList);
}

