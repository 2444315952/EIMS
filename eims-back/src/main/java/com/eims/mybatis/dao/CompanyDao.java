package com.eims.mybatis.dao;

import com.eims.mybatis.entity.Company;
import com.eims.vo.form.CompanyQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Company)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-11 21:24:33
 */
@Mapper
public interface CompanyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    Company queryById(Integer companyId);

    /**
     * 查询所有数据
     *
     * @param companyQueryForm 实例对象
     * @return 实例对象
     */
    List<Company> queryAll(CompanyQueryForm companyQueryForm);

    /**
     * 通过实体作为或者条件查询
     *
     * @param companyQueryForm 实例对象
     * @return 对象列表
     */
    List<Company> queryOrByPojo(CompanyQueryForm companyQueryForm);

    /**
     * 通过实体作为并且条件查询
     *
     * @param companyQueryForm 实例对象
     * @return 对象列表
     */
    List<Company> queryAndByPojo(CompanyQueryForm companyQueryForm);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 影响行数
     */
    int insert(Company company);

    /**
     * 批量新增数据
     *
     * @param companyList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Company> companyList);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 影响行数
     */
    int update(Company company);

    /**
     * 批量修改数据
     *
     * @param companyList 实例对象列表
     * @return 影响行数
     */
    int updateBatch(List<Company> companyList);

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 影响行数
     */
    int deleteById(Integer companyId);

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
     * @param companyList 实例对象列表
     * @return 影响行数
     */
    int deleteBatchByEntities(List<Company> companyList);
}

