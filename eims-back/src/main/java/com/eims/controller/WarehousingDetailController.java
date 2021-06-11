package com.eims.controller;

import com.eims.mybatis.entity.WarehousingDetail;
import com.eims.vo.form.WarehousingDetailQueryForm;
import com.eims.service.WarehousingDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (WarehousingDetail)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:27:24
 */
@RestController
public class WarehousingDetailController {
    /**
     * 服务对象
     */
    @Resource
    private WarehousingDetailService warehousingDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/warehousingDetail/one")
    public WarehousingDetail selectOne(Integer id) {
        return this.warehousingDetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param warehousingDetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/warehousingDetail")
    public PageInfo<WarehousingDetail> queryAll(WarehousingDetailQueryForm warehousingDetailQueryForm) {
        return this.warehousingDetailService.queryAll(warehousingDetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param warehousingDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/warehousingDetail/search")
    public PageInfo<WarehousingDetail> queryBySearch(WarehousingDetailQueryForm warehousingDetailQueryForm) {
        return this.warehousingDetailService.queryBySearch(warehousingDetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param warehousingDetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/warehousingDetail/screen")
    public PageInfo<WarehousingDetail> queryByScreen(WarehousingDetailQueryForm warehousingDetailQueryForm) {
        return this.warehousingDetailService.queryByScreen(warehousingDetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param warehousingDetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/warehousingDetail")
    public WarehousingDetail insert(@RequestBody WarehousingDetail warehousingDetail) {
        return this.warehousingDetailService.insert(warehousingDetail);
    }

    /**
     * 批量新增数据
     *
     * @param WarehousingDetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/warehousingDetail/batch")
    public boolean insertBatch(@RequestBody List<WarehousingDetail> WarehousingDetailList) {
        return this.warehousingDetailService.insertBatch(WarehousingDetailList);
    }

    /**
     * 修改数据
     *
     * @param warehousingDetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/warehousingDetail")
    public WarehousingDetail update(@RequestBody WarehousingDetail warehousingDetail) {
        return this.warehousingDetailService.update(warehousingDetail);
    }

    /**
     * 批量修改数据
     *
     * @param warehousingDetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/warehousingDetail/batch")
    public boolean updateBatch(@RequestBody List<WarehousingDetail> warehousingDetailList) {
        return this.warehousingDetailService.updateBatch(warehousingDetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/warehousingDetail")
    public boolean deleteById(Integer id) {
        return this.warehousingDetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/warehousingDetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.warehousingDetailService.deleteBatch(ids);
    }
}
