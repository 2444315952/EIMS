package com.eims.controller;

import com.eims.mybatis.entity.Company;
import com.eims.vo.form.CompanyQueryForm;
import com.eims.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Company)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:24:33
 */
@RestController
public class CompanyController {
    /**
     * 服务对象
     */
    @Resource
    private CompanyService companyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/company/one")
    public Company selectOne(Integer id) {
        return this.companyService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param companyQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/company")
    public PageInfo<Company> queryAll(CompanyQueryForm companyQueryForm) {
        companyQueryForm.setCompanyId(1);
        return this.companyService.queryAll(companyQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param companyQueryForm
     * @return 对象列表
     */
    @GetMapping("/company/search")
    public PageInfo<Company> queryBySearch(CompanyQueryForm companyQueryForm) {
        companyQueryForm.setCompanyId(1);
        return this.companyService.queryBySearch(companyQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param companyQueryForm
     * @return 对象列表
     */
    @GetMapping("/company/screen")
    public PageInfo<Company> queryByScreen(CompanyQueryForm companyQueryForm) {
        companyQueryForm.setCompanyId(1);
        return this.companyService.queryByScreen(companyQueryForm);
    }

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @PostMapping("/company")
    public Company insert(@RequestBody Company company) {
        company.setCompanyId(1);
        return this.companyService.insert(company);
    }

    /**
     * 批量新增数据
     *
     * @param CompanyList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/company/batch")
    public boolean insertBatch(@RequestBody List<Company> CompanyList) {
        return this.companyService.insertBatch(CompanyList);
    }

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @PutMapping("/company")
    public Company update(@RequestBody Company company) {
        return this.companyService.update(company);
    }

    /**
     * 批量修改数据
     *
     * @param companyList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/company/batch")
    public boolean updateBatch(@RequestBody List<Company> companyList) {
        return this.companyService.updateBatch(companyList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/company")
    public boolean deleteById(Integer id) {
        return this.companyService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/company/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.companyService.deleteBatch(ids);
    }
}
