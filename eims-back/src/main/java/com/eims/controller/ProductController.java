package com.eims.controller;

import com.eims.mybatis.entity.Product;
import com.eims.vo.form.ProductQueryForm;
import com.eims.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Product)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:25:52
 */
@RestController
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/product/one")
    public Product selectOne(Integer id) {
        return this.productService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param productQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/product")
    public PageInfo<Product> queryAll(ProductQueryForm productQueryForm) {
        productQueryForm.setCompanyId(1);
        return this.productService.queryAll(productQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param productQueryForm
     * @return 对象列表
     */
    @GetMapping("/product/search")
    public PageInfo<Product> queryBySearch(ProductQueryForm productQueryForm) {
        productQueryForm.setCompanyId(1);
        return this.productService.queryBySearch(productQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param productQueryForm
     * @return 对象列表
     */
    @GetMapping("/product/screen")
    public PageInfo<Product> queryByScreen(ProductQueryForm productQueryForm) {
        productQueryForm.setCompanyId(1);
        return this.productService.queryByScreen(productQueryForm);
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @PostMapping("/product")
    public Product insert(@RequestBody Product product) {
        product.setCompanyId(1);
        return this.productService.insert(product);
    }

    /**
     * 批量新增数据
     *
     * @param ProductList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/product/batch")
    public boolean insertBatch(@RequestBody List<Product> ProductList) {
        return this.productService.insertBatch(ProductList);
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @PutMapping("/product")
    public Product update(@RequestBody Product product) {
        return this.productService.update(product);
    }

    /**
     * 批量修改数据
     *
     * @param productList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/product/batch")
    public boolean updateBatch(@RequestBody List<Product> productList) {
        return this.productService.updateBatch(productList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/product")
    public boolean deleteById(Integer id) {
        return this.productService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/product/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.productService.deleteBatch(ids);
    }
}
