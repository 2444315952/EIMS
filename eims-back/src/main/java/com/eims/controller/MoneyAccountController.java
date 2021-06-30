package com.eims.controller;

import com.eims.mybatis.entity.MoneyAccount;
import com.eims.vo.form.MoneyAccountQueryForm;
import com.eims.service.MoneyAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (MoneyAccount)表控制层
 *
 * @author makejava
 * @since 2021-06-28 08:48:22
 */
@RestController
public class MoneyAccountController {
    /**
     * 服务对象
     */
    @Resource
    private MoneyAccountService moneyAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/moneyAccount/one")
    public MoneyAccount selectOne(Integer id) {
        return this.moneyAccountService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @param moneyAccountQueryForm 实例对象
     * @return 实例对象
     */
    @GetMapping("/moneyAccount")
    public PageInfo<MoneyAccount> queryAll(MoneyAccountQueryForm moneyAccountQueryForm) {
        moneyAccountQueryForm.setWorkPointId(1);
        return this.moneyAccountService.queryAll(moneyAccountQueryForm);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param moneyAccountQueryForm
     * @return 对象列表
     */
    @GetMapping("/moneyAccount/search")
    public PageInfo<MoneyAccount> queryBySearch(MoneyAccountQueryForm moneyAccountQueryForm) {
        moneyAccountQueryForm.setWorkPointId(1);
        return this.moneyAccountService.queryBySearch(moneyAccountQueryForm);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param moneyAccountQueryForm
     * @return 对象列表
     */
    @GetMapping("/moneyAccount/screen")
    public PageInfo<MoneyAccount> queryByScreen(MoneyAccountQueryForm moneyAccountQueryForm) {
        moneyAccountQueryForm.setWorkPointId(1);
        return this.moneyAccountService.queryByScreen(moneyAccountQueryForm);
    }

    /**
     * 新增数据
     *
     * @param moneyAccount 实例对象
     * @return 实例对象
     */
    @PostMapping("/moneyAccount")
    public MoneyAccount insert(@RequestBody MoneyAccount moneyAccount) {

        return this.moneyAccountService.insert(moneyAccount);
    }

    /**
     * 批量新增数据
     *
     * @param MoneyAccountList 实例对象列表
     * @return 影响行数
     */
    @PostMapping("/moneyAccount/batch")
    public boolean insertBatch(@RequestBody List<MoneyAccount> MoneyAccountList) {
        return this.moneyAccountService.insertBatch(MoneyAccountList);
    }

    /**
     * 修改数据
     *
     * @param moneyAccount 实例对象
     * @return 实例对象
     */
    @PutMapping("/moneyAccount")
    public MoneyAccount update(@RequestBody MoneyAccount moneyAccount) {
        return this.moneyAccountService.update(moneyAccount);
    }

    /**
     * 批量修改数据
     *
     * @param moneyAccountList 实例对象列表
     * @return 影响行数
     */
    @PutMapping("/moneyAccount/batch")
    public boolean updateBatch(@RequestBody List<MoneyAccount> moneyAccountList) {
        return this.moneyAccountService.updateBatch(moneyAccountList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/moneyAccount")
    public boolean deleteById(Integer id) {
        return this.moneyAccountService.deleteById(id);
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 是否成功
     */
    @DeleteMapping("/moneyAccount/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return this.moneyAccountService.deleteBatch(ids);
    }
}
