package com.eims.controller;

import com.eims.mybatis.entity.Transfer;
import com.eims.vo.form.TransferQueryForm;
import com.eims.service.TransferService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Transfer)表控制层
 *
 * @author makejava
 * @since 2021-06-16 10:35:06
 */
@RestController
public class TransferController {
    /**
     * 服务对象
     */
    @Resource
    private TransferService transferService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/transfer/one")
    public Transfer selectOne(Integer id) {
        return this.transferService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param transferQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/transfer")
    public PageInfo<Transfer> queryAll(TransferQueryForm transferQueryForm) {
        transferQueryForm.setWorkPointId(1);
        return this.transferService.queryAll(transferQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param transferQueryForm
     * @return 对象列表
     */
    @GetMapping("/transfer/search")
    public PageInfo<Transfer> queryBySearch(TransferQueryForm transferQueryForm) {
        transferQueryForm.setWorkPointId(1);
        return this.transferService.queryBySearch(transferQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param transferQueryForm
     * @return 对象列表
     */
    @GetMapping("/transfer/screen")
    public PageInfo<Transfer> queryByScreen(TransferQueryForm transferQueryForm) {
        transferQueryForm.setWorkPointId(1);
        return this.transferService.queryByScreen(transferQueryForm);
    }

    /**
     * 新增数据
     *
     * @param transfer 实例对象
     * @return 实例对象
     */
    @PostMapping("/transfer")
    public Transfer insert(@RequestBody Transfer transfer) {
        transfer.setWorkPointId(1);
        //transfer.setWorkPointName("示例工作点");
        return this.transferService.insert(transfer);
    }

    /**
     * 批量新增数据
     *
     * @param TransferList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/transfer/batch")
    public boolean insertBatch(@RequestBody List<Transfer> TransferList) {
        return this.transferService.insertBatch(TransferList);
    }

    /**
     * 修改数据
     *
     * @param transfer 实例对象
     * @return 实例对象
     */
    @PutMapping("/transfer")
    public Transfer update(@RequestBody Transfer transfer) {
        return this.transferService.update(transfer);
    }

    /**
     * 批量修改数据
     *
     * @param transferList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/transfer/batch")
    public boolean updateBatch(@RequestBody List<Transfer> transferList) {
        return this.transferService.updateBatch(transferList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/transfer")
    public boolean deleteById(Integer id) {
        return this.transferService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/transfer/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.transferService.deleteBatch(ids);
    }
}
