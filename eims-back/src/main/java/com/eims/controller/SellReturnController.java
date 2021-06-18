package com.eims.controller;

import com.eims.mybatis.entity.SellReturn;
import com.eims.vo.form.SellReturnQueryForm;
import com.eims.service.SellReturnService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (SellReturn)表控制层
 *
 * @author makejava
 * @since 2021-06-12 10:28:45
 */
@RestController
@Log4j2
public class SellReturnController {
    /**
     * 服务对象
     */
    @Resource
    private SellReturnService sellReturnService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/sellReturn/one")
    public SellReturn selectOne(Integer id) {
        return this.sellReturnService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param sellReturnQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/sellReturn")
    public PageInfo<SellReturn> queryAll(SellReturnQueryForm sellReturnQueryForm) {
        sellReturnQueryForm.setCompanyId(1);
        return this.sellReturnService.queryAll(sellReturnQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellReturn/search")
    public PageInfo<SellReturn> queryBySearch(SellReturnQueryForm sellReturnQueryForm) {
        sellReturnQueryForm.setCompanyId(1);
        return this.sellReturnService.queryBySearch(sellReturnQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    @GetMapping("/sellReturn/screen")
    public PageInfo<SellReturn> queryByScreen(SellReturnQueryForm sellReturnQueryForm) {
        sellReturnQueryForm.setCompanyId(1);
        return this.sellReturnService.queryByScreen(sellReturnQueryForm);
    }

    /**
     * 新增数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    @PostMapping("/sellReturn")
    public SellReturn insert(@RequestBody SellReturn sellReturn) {
        System.out.println("-----------sellreturn:"+sellReturn);

        return this.sellReturnService.insert(sellReturn);
//        return null;
    }

    /**
     * 批量新增数据
     *
     * @param SellReturnList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/sellReturn/batch")
    public boolean insertBatch(@RequestBody List<SellReturn> SellReturnList) {
        return this.sellReturnService.insertBatch(SellReturnList);
    }

    /**
     * 修改数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    @PutMapping("/sellReturn")
    public SellReturn update(@RequestBody SellReturn sellReturn) {
        System.out.println("-----------sellreturn:"+sellReturn);
//        return this.sellReturnService.update(sellReturn);
        return  null;
    }

    /**
     * 批量修改数据
     *
     * @param sellReturnList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/sellReturn/batch")
    public boolean updateBatch(@RequestBody List<SellReturn> sellReturnList) {
        return this.sellReturnService.updateBatch(sellReturnList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/sellReturn")
    public boolean deleteById(Integer id) {
        return this.sellReturnService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/sellReturn/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.sellReturnService.deleteBatch(ids);
    }
}
