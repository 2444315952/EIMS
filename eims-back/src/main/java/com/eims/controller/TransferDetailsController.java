package com.eims.controller;

import com.eims.mybatis.entity.TransferDetails;
import com.eims.vo.form.TransferDetailsQueryForm;
import com.eims.service.TransferDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (TransferDetails)表控制层
 *
 * @author makejava
 * @since 2021-06-16 23:18:27
 */
@RestController
public class TransferDetailsController {
    /**
     * 服务对象
     */
    @Resource
    private TransferDetailsService transferDetailsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/transferDetails/one")
    public TransferDetails selectOne(Integer id) {
        return this.transferDetailsService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param transferDetailsQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/transferDetails")
    public PageInfo<TransferDetails> queryAll(TransferDetailsQueryForm transferDetailsQueryForm) {
        //transferDetailsQueryForm.setWorkPointId(1);
        return this.transferDetailsService.queryAll(transferDetailsQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param transferDetailsQueryForm
     * @return 对象列表
     */
    @GetMapping("/transferDetails/search")
    public PageInfo<TransferDetails> queryBySearch(TransferDetailsQueryForm transferDetailsQueryForm) {
        //transferDetailsQueryForm.setWorkPointId(1);
        return this.transferDetailsService.queryBySearch(transferDetailsQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param transferDetailsQueryForm
     * @return 对象列表
     */
    @GetMapping("/transferDetails/screen")
    public PageInfo<TransferDetails> queryByScreen(TransferDetailsQueryForm transferDetailsQueryForm) {
//        transferDetailsQueryForm.
        return this.transferDetailsService.queryByScreen(transferDetailsQueryForm);
    }

    /**
     * 通过调拨单id主键删除明细
     * @param transferId
     * @return
     */
    @DeleteMapping("/transferDetails/deleteByFk")
    public Boolean deleteByFk(Integer transferId){
        return this.transferDetailsService.deleteByFk(transferId);
    }

    /**
     * 新增数据
     *
     * @param transferDetails 实例对象
     * @return 实例对象
     */
    @PostMapping("/transferDetails")
    public TransferDetails insert(@RequestBody TransferDetails transferDetails) {
        //transferDetails.setWorkPointId(1);
        //transferDetails.setWorkPointName("示例工作点");
        return this.transferDetailsService.insert(transferDetails);
    }

    /**
     * 批量新增数据
     *
     * @param TransferDetailsList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/transferDetails/batch")
    public boolean insertBatch(@RequestBody List<TransferDetails> TransferDetailsList) {
        return this.transferDetailsService.insertBatch(TransferDetailsList);
    }

    /**
     * 修改数据
     *
     * @param transferDetails 实例对象
     * @return 实例对象
     */
    @PutMapping("/transferDetails")
    public TransferDetails update(@RequestBody TransferDetails transferDetails) {
        return this.transferDetailsService.update(transferDetails);
    }

    /**
     * 批量修改数据
     *
     * @param transferDetailsList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/transferDetails/batch")
    public boolean updateBatch(@RequestBody List<TransferDetails> transferDetailsList) {
        return this.transferDetailsService.updateBatch(transferDetailsList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/transferDetails")
    public boolean deleteById(Integer id) {
        return this.transferDetailsService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/transferDetails/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.transferDetailsService.deleteBatch(ids);
    }
}
