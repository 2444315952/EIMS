package com.eims.controller;

import com.eims.mybatis.entity.Baldetail;
import com.eims.vo.form.BaldetailQueryForm;
import com.eims.service.BaldetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Baldetail)表控制层
 *
 * @author makejava
 * @since 2021-06-16 10:34:38
 */
@RestController
public class BaldetailController {
    /**
     * 服务对象
     */
    @Resource
    private BaldetailService baldetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/baldetail/one")
    public Baldetail selectOne(Integer id) {
        return this.baldetailService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param baldetailQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/baldetail")
    public PageInfo<Baldetail> queryAll(BaldetailQueryForm baldetailQueryForm) {
        //baldetailQueryForm.setWorkPointId(1);
        return this.baldetailService.queryAll(baldetailQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param baldetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/baldetail/search")
    public PageInfo<Baldetail> queryBySearch(BaldetailQueryForm baldetailQueryForm) {
        //baldetailQueryForm.setWorkPointId(1);
        return this.baldetailService.queryBySearch(baldetailQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param baldetailQueryForm
     * @return 对象列表
     */
    @GetMapping("/baldetail/screen")
    public PageInfo<Baldetail> queryByScreen(BaldetailQueryForm baldetailQueryForm) {
        //baldetailQueryForm.setWorkPointId(1);
        return this.baldetailService.queryByScreen(baldetailQueryForm);
    }

    /**
     * 新增数据
     *
     * @param baldetail 实例对象
     * @return 实例对象
     */
    @PostMapping("/baldetail")
    public Baldetail insert(@RequestBody Baldetail baldetail) {
        //baldetail.setWorkPointId(1);
        //baldetail.setWorkPointName("示例工作点");
        return this.baldetailService.insert(baldetail);
    }

    /**
     * 批量新增数据
     *
     * @param BaldetailList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/baldetail/batch")
    public boolean insertBatch(@RequestBody List<Baldetail> BaldetailList) {
        return this.baldetailService.insertBatch(BaldetailList);
    }

    /**
     * 修改数据
     *
     * @param baldetail 实例对象
     * @return 实例对象
     */
    @PutMapping("/baldetail")
    public Baldetail update(@RequestBody Baldetail baldetail) {
        return this.baldetailService.update(baldetail);
    }

    /**
     * 批量修改数据
     *
     * @param baldetailList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/baldetail/batch")
    public boolean updateBatch(@RequestBody List<Baldetail> baldetailList) {
        return this.baldetailService.updateBatch(baldetailList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/baldetail")
    public boolean deleteById(Integer id) {
        return this.baldetailService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/baldetail/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.baldetailService.deleteBatch(ids);
    }
}
