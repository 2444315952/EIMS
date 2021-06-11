package com.eims.service.impl;

import com.eims.mybatis.entity.Company;
import com.eims.vo.form.CompanyQueryForm;
import com.eims.mybatis.dao.CompanyDao;
import com.eims.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Company)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:24:33
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDao companyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    @Override
    public Company queryById(Integer companyId) {
        return this.companyDao.queryById(companyId);
    }

    /**
     * 查询所有数据
     *
     * @param companyQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Company> queryAll(CompanyQueryForm companyQueryForm) {
        Page<Company> page = PageHelper.startPage(companyQueryForm.getPageNum(), companyQueryForm.getPageSize());
        List<Company> companyList = this.companyDao.queryAll(companyQueryForm);
        return new PageInfo<>(companyList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param companyQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Company> queryBySearch(CompanyQueryForm companyQueryForm) {
        Page<Company> page = PageHelper.startPage(companyQueryForm.getPageNum(), companyQueryForm.getPageSize());
        List<Company> companyList = this.companyDao.queryOrByPojo(companyQueryForm);
        return new PageInfo<>(companyList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param companyQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Company> queryByScreen(CompanyQueryForm companyQueryForm) {
        Page<Company> page = PageHelper.startPage(companyQueryForm.getPageNum(), companyQueryForm.getPageSize());
        List<Company> companyList = this.companyDao.queryAndByPojo(companyQueryForm);
        return new PageInfo<>(companyList);
    }

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public Company insert(Company company) {
        this.companyDao.insert(company);
        return this.queryById(company.getCompanyId());
    }

    /**
     * 批量新增数据
     *
     * @param CompanyList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Company> CompanyList) {
        return this.companyDao.insertBatch(CompanyList) == -1;
    }

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public Company update(Company company) {
        this.companyDao.update(company);
        return this.queryById(company.getCompanyId());
    }

    /**
     * 批量修改数据
     *
     * @param companyList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Company> companyList) {
        return this.companyDao.updateBatch(companyList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer companyId) {
        return this.companyDao.deleteById(companyId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.companyDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
