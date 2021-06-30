package com.eims.mybatis.dao;

import com.eims.mybatis.entity.SellBill;
import com.eims.vo.form.SellBillQueryForm;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (SellBill)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:26:47
 */
@Mapper
public interface SellBillDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sellId 主键
     * @return 实例对象
     */
    SellBill queryById(Integer sellId);

    /**
     * 查询所有数据
     *
     * @param sellBillQueryForm 实例对象
     * @return 实例对象
     */
    List<SellBill> queryAll(SellBillQueryForm sellBillQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param sellBillQueryForm 实例对象
     * @return 对象列表
     */
    List<SellBill> queryOrByPojo(SellBillQueryForm sellBillQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param sellBillQueryForm 实例对象
     * @return 对象列表
     */
    List<SellBill> queryAndByPojo(SellBillQueryForm sellBillQueryForm);

    /**
     * 新增数据
     *
     * @param sellBill 实例对象
     * @return 影响行数
     */
    int insert(SellBill sellBill);

    /**
     * 批量新增数据
     *
     * @param sellBillList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SellBill> sellBillList);

    /**
     * 修改数据
     *
     * @param sellBill 实例对象
     * @return 影响行数
     */
    int update(SellBill sellBill);

    /**
     * 批量修改数据
     *
     * @param sellBillList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<SellBill> sellBillList);

    /**
     * 通过主键删除数据
     *
     * @param sellId 主键
     * @return 影响行数
     */
    int deleteById(Integer sellId);

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
     * @param sellBillList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<SellBill> sellBillList);


}

