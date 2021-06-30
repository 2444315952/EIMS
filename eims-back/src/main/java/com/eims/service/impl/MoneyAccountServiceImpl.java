package com.eims.service.impl;

import com.eims.mybatis.entity.MoneyAccount;
import com.eims.vo.form.MoneyAccountQueryForm;
import com.eims.mybatis.dao.MoneyAccountDao;
import com.eims.service.MoneyAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (MoneyAccount)表服务实现类
 *
 * @author makejava
 * @since 2021-06-28 08:48:22
 */
@Service("moneyAccountService")
public class MoneyAccountServiceImpl implements MoneyAccountService {
    @Resource
    private MoneyAccountDao moneyAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param moneyAccountId 主键
     * @return 实例对象
     */
    @Override
    public MoneyAccount queryById(Integer moneyAccountId) {
        return this.moneyAccountDao.queryById(moneyAccountId);
    }

    /**
     * 查询所有数据
     *
     * @param moneyAccountQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<MoneyAccount> queryAll(MoneyAccountQueryForm moneyAccountQueryForm) {
        Page<MoneyAccount> page = PageHelper.startPage(moneyAccountQueryForm.getPageNum(), moneyAccountQueryForm.getPageSize());
        List<MoneyAccount> moneyAccountList = this.moneyAccountDao.queryAll(moneyAccountQueryForm);
        return new PageInfo<>(moneyAccountList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param moneyAccountQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<MoneyAccount> queryBySearch(MoneyAccountQueryForm moneyAccountQueryForm) {
        Page<MoneyAccount> page = PageHelper.startPage(moneyAccountQueryForm.getPageNum(), moneyAccountQueryForm.getPageSize());
        List<MoneyAccount> moneyAccountList = this.moneyAccountDao.queryOrByPojo(moneyAccountQueryForm);
        return new PageInfo<>(moneyAccountList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param moneyAccountQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<MoneyAccount> queryByScreen(MoneyAccountQueryForm moneyAccountQueryForm) {
        Page<MoneyAccount> page = PageHelper.startPage(moneyAccountQueryForm.getPageNum(), moneyAccountQueryForm.getPageSize());
        List<MoneyAccount> moneyAccountList = this.moneyAccountDao.queryAndByPojo(moneyAccountQueryForm);
        return new PageInfo<>(moneyAccountList);
    }

    /**
     * 新增数据
     *
     * @param moneyAccount 实例对象
     * @return 实例对象
     */
    @Override
    public MoneyAccount insert(MoneyAccount moneyAccount) {
        System.out.println(moneyAccount);
        moneyAccount.setWorkPointId(1);
        moneyAccount.setWorkPointName("示例工作点");
        moneyAccount.setCompanyId(1);
        moneyAccount.setAccountBirthday(new Date());
        this.moneyAccountDao.insert(moneyAccount);
        return this.queryById(moneyAccount.getMoneyAccountId());
    }

    /**
     * 批量新增数据
     *
     * @param MoneyAccountList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<MoneyAccount> MoneyAccountList) {
        return this.moneyAccountDao.insertBatch(MoneyAccountList) == -1;
    }

    /**
     * 修改数据
     *
     * @param moneyAccount 实例对象
     * @return 实例对象
     */
    @Override
    public MoneyAccount update(MoneyAccount moneyAccount) {
        this.moneyAccountDao.update(moneyAccount);
        return this.queryById(moneyAccount.getMoneyAccountId());
    }

    /**
     * 批量修改数据
     *
     * @param moneyAccountList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<MoneyAccount> moneyAccountList) {
        return this.moneyAccountDao.updateBatch(moneyAccountList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param moneyAccountId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer moneyAccountId) {
        return this.moneyAccountDao.deleteById(moneyAccountId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.moneyAccountDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
