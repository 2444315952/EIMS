package com.eims.mybatis.dao;

import com.eims.mybatis.entity.SellOrderBill;
import com.eims.vo.form.SellOrderBillQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SellOrderBill)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:26:50
 */
@Mapper
public interface SellOrderBillDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sellOrderId 主键
     * @return 实例对象
     */
    SellOrderBill queryById(Integer sellOrderId);

    /**
     * 查询所有数据
     *
     * @param sellOrderBillQueryForm 实例对象
     * @return 实例对象
     */
    List<SellOrderBill> queryAll(SellOrderBillQueryForm sellOrderBillQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param sellOrderBillQueryForm 实例对象
     * @return 对象列表
     */
    List<SellOrderBill> queryOrByPojo(SellOrderBillQueryForm sellOrderBillQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param sellOrderBillQueryForm 实例对象
     * @return 对象列表
     */
    List<SellOrderBill> queryAndByPojo(SellOrderBillQueryForm sellOrderBillQueryForm);

    /**
     * 新增数据
     *
     * @param sellOrderBill 实例对象
     * @return 影响行数
     */
    int insert(SellOrderBill sellOrderBill);

    /**
     * 批量新增数据
     *
     * @param sellOrderBillList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SellOrderBill> sellOrderBillList);

    /**
     * 修改数据
     *
     * @param sellOrderBill 实例对象
     * @return 影响行数
     */
    int update(SellOrderBill sellOrderBill);

    /**
     * 批量修改数据
     *
     * @param sellOrderBillList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<SellOrderBill> sellOrderBillList);

    /**
     * 通过主键删除数据
     *
     * @param sellOrderId 主键
     * @return 影响行数
     */
    int deleteById(Integer sellOrderId);

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
     * @param sellOrderBillList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<SellOrderBill> sellOrderBillList);
}
