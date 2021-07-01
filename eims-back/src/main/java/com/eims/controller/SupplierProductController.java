package com.eims.controller;

import com.eims.mybatis.entity.SupplierProduct;
import com.eims.vo.form.SupplierProductQueryForm;
import com.eims.service.SupplierProductService;
import com.eims.vo.table.SupplierProductTable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (SupplierProduct)表控制层
 *
 * @author makejava
 * @since 2021-06-30 19:40:40
 */
@RestController
public class SupplierProductController {
    /**
     * 服务对象
     */
    @Resource
    private SupplierProductService supplierProductService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/supplierProduct/one")
    public SupplierProduct selectOne(Integer id) {
        return this.supplierProductService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param supplierProductQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/supplierProduct")
    public PageInfo<SupplierProduct> queryAll(SupplierProductQueryForm supplierProductQueryForm) {
        return this.supplierProductService.queryAll(supplierProductQueryForm);
    }
    @GetMapping("/supplier/table")
    public List<SupplierProductTable> SupplierProductTableQuery(Integer supplierId){
        return this.supplierProductService.SupplierProductTableQuery(supplierId);
    }
    /**
     * 根据查询条件搜索数据
     *
     * @param supplierProductQueryForm
     * @return 对象列表
     */
    @GetMapping("/supplierProduct/search")
    public PageInfo<SupplierProduct> queryBySearch(SupplierProductQueryForm supplierProductQueryForm) {
        return this.supplierProductService.queryBySearch(supplierProductQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param supplierProductQueryForm
     * @return 对象列表
     */
    @GetMapping("/supplierProduct/screen")
    public PageInfo<SupplierProduct> queryByScreen(SupplierProductQueryForm supplierProductQueryForm) {
        return this.supplierProductService.queryByScreen(supplierProductQueryForm);
    }

    /**
     * 新增数据
     *
     * @param supplierProduct 实例对象
     * @return 实例对象
     */
    @PostMapping("/supplierProduct")
    public SupplierProduct insert(@RequestBody SupplierProduct supplierProduct) {
        return this.supplierProductService.insert(supplierProduct);
    }

    /**
     * 批量新增数据
     *
     * @param SupplierProductList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/supplierProduct/batch")
    public boolean insertBatch(@RequestBody List<SupplierProduct> SupplierProductList) {
        return this.supplierProductService.insertBatch(SupplierProductList);
    }

    /**
     * 修改数据
     *
     * @param supplierProduct 实例对象
     * @return 实例对象
     */
    @PutMapping("/supplierProduct")
    public SupplierProduct update(@RequestBody SupplierProduct supplierProduct) {
        return this.supplierProductService.update(supplierProduct);
    }

    /**
     * 批量修改数据
     *
     * @param supplierProductList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/supplierProduct/batch")
    public boolean updateBatch(@RequestBody List<SupplierProduct> supplierProductList) {
        return this.supplierProductService.updateBatch(supplierProductList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/supplierProduct")
    public boolean deleteById(Integer id) {
        return this.supplierProductService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/supplierProduct/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.supplierProductService.deleteBatch(ids);
    }
}
