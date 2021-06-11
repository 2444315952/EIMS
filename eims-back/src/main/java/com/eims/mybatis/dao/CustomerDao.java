package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Customer;
import com.eims.vo.form.CustomerQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Customer)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-08 13:46:48
 */
@Mapper
public interface CustomerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param customerId 主键
     * @return 实例对象
     */
    Customer queryById(Integer customerId);

    /**
     * 查询所有数据
     *
     * @param customerQueryForm 实例对象
     * @return 实例对象
     */
    List<Customer> queryAll(CustomerQueryForm customerQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param customerQueryForm 实例对象
     * @return 对象列表
     */
    List<Customer> queryOrByPojo(CustomerQueryForm customerQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param customerQueryForm 实例对象
     * @return 对象列表
     */
    List<Customer> queryAndByPojo(CustomerQueryForm customerQueryForm);

    List<Customer> queryAllXyj( @Param("customerName") String customerName,@Param("workPointId") int workPointId,@Param("is_enabled") String is_enabled );

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int insert(Customer customer);

    /**
     * 批量新增数据
     *
     * @param customerList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Customer> customerList);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int update(Customer customer);

    /**
     * 批量修改数据
     *
     * @param customerList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Customer> customerList);

    int updateStop(List<Integer> customerId);

    int updateOpen(List<Integer> customerId);

    /**
     * 通过主键删除数据
     *
     * @param customerId 主键
     * @return 影响行数
     */
    int deleteById(Integer customerId);

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
     * @param customerList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Customer> customerList);
}

