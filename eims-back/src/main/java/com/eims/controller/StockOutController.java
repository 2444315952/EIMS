package com.eims.controller;

import com.eims.mybatis.entity.StockOut;
import com.eims.vo.form.StockOutQueryForm;
import com.eims.service.StockOutService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (StockOut)表控制层
 *
 * @author makejava
 * @since 2021-06-08 09:48:08
 */
@RestController
public class StockOutController {
    /**
     * 服务对象
     */
    @Resource
    private StockOutService stockOutService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/stockOut/one")
    public StockOut selectOne(Integer id) {
        return this.stockOutService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param stockOutQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/stockOut")
    public PageInfo<StockOut> queryAll(StockOutQueryForm stockOutQueryForm) {
        stockOutQueryForm.setWorkPointId(1);
        return this.stockOutService.queryAll(stockOutQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param stockOutQueryForm
     * @return 对象列表
     */
    @GetMapping("/stockOut/search")
    public PageInfo<StockOut> queryBySearch(StockOutQueryForm stockOutQueryForm) {
        stockOutQueryForm.setWorkPointId(1);
        return this.stockOutService.queryBySearch(stockOutQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param stockOutQueryForm
     * @return 对象列表
     */
    @GetMapping("/stockOut/screen")
    public PageInfo<StockOut> queryByScreen(StockOutQueryForm stockOutQueryForm) {
        stockOutQueryForm.setWorkPointId(1);
        return this.stockOutService.queryByScreen(stockOutQueryForm);
    }

    /**
     * 新增数据
     *
     * @param stockOut 实例对象
     * @return 实例对象
     */
    @PostMapping("/stockOut")
    public StockOut insert(@RequestBody StockOut stockOut) {
        stockOut.setWorkPointId(1);
        stockOut.setWorkPointName("示例工作点");
        return this.stockOutService.insert(stockOut);
    }

    /**
     * 批量新增数据
     *
     * @param StockOutList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/stockOut/batch")
    public boolean insertBatch(@RequestBody List<StockOut> StockOutList) {
        return this.stockOutService.insertBatch(StockOutList);
    }

    /**
     * 修改数据
     *
     * @param stockOut 实例对象
     * @return 实例对象
     */
    @PutMapping("/stockOut")
    public StockOut update(@RequestBody StockOut stockOut) {
        return this.stockOutService.update(stockOut);
    }

    /**
     * 批量修改数据
     *
     * @param stockOutList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/stockOut/batch")
    public boolean updateBatch(@RequestBody List<StockOut> stockOutList) {
        return this.stockOutService.updateBatch(stockOutList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/stockOut")
    public boolean deleteById(Integer id) {
        return this.stockOutService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/stockOut/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.stockOutService.deleteBatch(ids);
    }
}
