package com.eims.controller;

import com.eims.mybatis.entity.GatherRefund;
import com.eims.vo.form.GatherRefundQueryForm;
import com.eims.service.GatherRefundService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (GatherRefund)表控制层
 *
 * @author makejava
 * @since 2021-06-30 19:36:18
 */
@RestController
public class GatherRefundController {
    /**
     * 服务对象
     */
    @Resource
    private GatherRefundService gatherRefundService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/gatherRefund/one")
    public GatherRefund selectOne(Integer id) {
        return this.gatherRefundService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param gatherRefundQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/gatherRefund")
    public PageInfo<GatherRefund> queryAll(GatherRefundQueryForm gatherRefundQueryForm) {
        gatherRefundQueryForm.setCompanyId(1);
        gatherRefundQueryForm.setWorkPointId(1);
        return this.gatherRefundService.queryAll(gatherRefundQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherRefundQueryForm
     * @return 对象列表
     */
    @GetMapping("/gatherRefund/search")
    public PageInfo<GatherRefund> queryBySearch(GatherRefundQueryForm gatherRefundQueryForm) {
        gatherRefundQueryForm.setCompanyId(1);
        gatherRefundQueryForm.setWorkPointId(1);
        return this.gatherRefundService.queryBySearch(gatherRefundQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherRefundQueryForm
     * @return 对象列表
     */
    @GetMapping("/gatherRefund/screen")
    public PageInfo<GatherRefund> queryByScreen(GatherRefundQueryForm gatherRefundQueryForm) {
        gatherRefundQueryForm.setCompanyId(1);
        gatherRefundQueryForm.setWorkPointId(1);
        return this.gatherRefundService.queryByScreen(gatherRefundQueryForm);
    }

    /**
     * 新增数据
     *
     * @param gatherRefund 实例对象
     * @return 实例对象
     */
    @PostMapping("/gatherRefund")
    public GatherRefund insert(@RequestBody GatherRefund gatherRefund) {
        gatherRefund.setCompanyId(1);
        gatherRefund.setWorkPointId(1);
        return this.gatherRefundService.insert(gatherRefund);
    }

    /**
     * 批量新增数据
     *
     * @param GatherRefundList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/gatherRefund/batch")
    public boolean insertBatch(@RequestBody List<GatherRefund> GatherRefundList) {
        return this.gatherRefundService.insertBatch(GatherRefundList);
    }

    /**
     * 修改数据
     *
     * @param gatherRefund 实例对象
     * @return 实例对象
     */
    @PutMapping("/gatherRefund")
    public GatherRefund update(@RequestBody GatherRefund gatherRefund) {
        return this.gatherRefundService.update(gatherRefund);
    }

    /**
     * 批量修改数据
     *
     * @param gatherRefundList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/gatherRefund/batch")
    public boolean updateBatch(@RequestBody List<GatherRefund> gatherRefundList) {
        return this.gatherRefundService.updateBatch(gatherRefundList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/gatherRefund")
    public boolean deleteById(Integer id) {
        return this.gatherRefundService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/gatherRefund/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.gatherRefundService.deleteBatch(ids);
    }
}
