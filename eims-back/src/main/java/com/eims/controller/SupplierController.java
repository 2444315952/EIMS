package com.eims.controller;

import com.eims.mybatis.entity.Supplier;
import com.eims.vo.form.SupplierQueryForm;
import com.eims.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Supplier)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:26:55
 */
@RestController
public class SupplierController {
    /**
     * 服务对象
     */
    @Resource
    private SupplierService supplierService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/supplier/one")
    public Supplier selectOne(Integer id) {
        return this.supplierService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param supplierQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/supplier")
    public PageInfo<Supplier> queryAll(SupplierQueryForm supplierQueryForm) {
        supplierQueryForm.setCompanyId(1);
        return this.supplierService.queryAll(supplierQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param supplierQueryForm
     * @return 对象列表
     */
    @GetMapping("/supplier/search")
    public PageInfo<Supplier> queryBySearch(SupplierQueryForm supplierQueryForm) {
        supplierQueryForm.setCompanyId(1);
        return this.supplierService.queryBySearch(supplierQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param supplierQueryForm
     * @return 对象列表
     */
    @GetMapping("/supplier/screen")
    public PageInfo<Supplier> queryByScreen(SupplierQueryForm supplierQueryForm) {
        supplierQueryForm.setCompanyId(1);
        return this.supplierService.queryByScreen(supplierQueryForm);
    }

    /**
     * 新增数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    @PostMapping("/supplier")
    public Supplier insert(@RequestBody Supplier supplier) {
        supplier.setCompanyId(1);
        return this.supplierService.insert(supplier);
    }

    /**
     * 批量新增数据
     *
     * @param SupplierList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/supplier/batch")
    public boolean insertBatch(@RequestBody List<Supplier> SupplierList) {
        return this.supplierService.insertBatch(SupplierList);
    }

    /**
     * 修改数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    @PutMapping("/supplier")
    public Supplier update(@RequestBody Supplier supplier) {
        return this.supplierService.update(supplier);
    }

    /**
     * 批量修改数据
     *
     * @param supplierList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/supplier/batch")
    public boolean updateBatch(@RequestBody List<Supplier> supplierList) {
        return this.supplierService.updateBatch(supplierList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/supplier")
    public boolean deleteById(Integer id) {
        return this.supplierService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/supplier/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.supplierService.deleteBatch(ids);
    }
}
