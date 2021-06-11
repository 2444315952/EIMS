package com.eims.controller;

import com.eims.mybatis.entity.WorkPoint;
import com.eims.vo.form.WorkPointQueryForm;
import com.eims.service.WorkPointService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (WorkPoint)表控制层
 *
 * @author makejava
 * @since 2021-06-11 21:27:25
 */
@RestController
public class WorkPointController {
    /**
     * 服务对象
     */
    @Resource
    private WorkPointService workPointService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/workPoint/one")
    public WorkPoint selectOne(Integer id) {
        return this.workPointService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param workPointQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/workPoint")
    public PageInfo<WorkPoint> queryAll(WorkPointQueryForm workPointQueryForm) {
        workPointQueryForm.setCompanyId(1);
        return this.workPointService.queryAll(workPointQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param workPointQueryForm
     * @return 对象列表
     */
    @GetMapping("/workPoint/search")
    public PageInfo<WorkPoint> queryBySearch(WorkPointQueryForm workPointQueryForm) {
        workPointQueryForm.setCompanyId(1);
        return this.workPointService.queryBySearch(workPointQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param workPointQueryForm
     * @return 对象列表
     */
    @GetMapping("/workPoint/screen")
    public PageInfo<WorkPoint> queryByScreen(WorkPointQueryForm workPointQueryForm) {
        workPointQueryForm.setCompanyId(1);
        return this.workPointService.queryByScreen(workPointQueryForm);
    }

    /**
     * 新增数据
     *
     * @param workPoint 实例对象
     * @return 实例对象
     */
    @PostMapping("/workPoint")
    public WorkPoint insert(@RequestBody WorkPoint workPoint) {
        workPoint.setWorkPointId(1);
        workPoint.setCompanyId(1);
        return this.workPointService.insert(workPoint);
    }

    /**
     * 批量新增数据
     *
     * @param WorkPointList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/workPoint/batch")
    public boolean insertBatch(@RequestBody List<WorkPoint> WorkPointList) {
        return this.workPointService.insertBatch(WorkPointList);
    }

    /**
     * 修改数据
     *
     * @param workPoint 实例对象
     * @return 实例对象
     */
    @PutMapping("/workPoint")
    public WorkPoint update(@RequestBody WorkPoint workPoint) {
        return this.workPointService.update(workPoint);
    }

    /**
     * 批量修改数据
     *
     * @param workPointList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/workPoint/batch")
    public boolean updateBatch(@RequestBody List<WorkPoint> workPointList) {
        return this.workPointService.updateBatch(workPointList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/workPoint")
    public boolean deleteById(Integer id) {
        return this.workPointService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/workPoint/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.workPointService.deleteBatch(ids);
    }
}
