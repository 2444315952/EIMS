package com.eims.service.impl;

import com.eims.mybatis.dao.MoneyAccountDao;
import com.eims.mybatis.dao.SellBillDao;
import com.eims.mybatis.dao.SellReturnDao;
import com.eims.mybatis.entity.Gather;
import com.eims.mybatis.entity.MoneyAccount;
import com.eims.mybatis.entity.SellBill;
import com.eims.mybatis.entity.SellReturn;
import com.eims.vo.form.GatherQueryForm;
import com.eims.mybatis.dao.GatherDao;
import com.eims.service.GatherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Gather)表服务实现类
 *
 * @author makejava
 * @since 2021-06-27 20:23:03
 */
@Service("gatherService")
public class GatherServiceImpl implements GatherService {
    @Resource
    private GatherDao gatherDao;

    @Resource
    private SellBillDao sellBillDao;

    @Resource
    private SellReturnDao sellReturnDao;

    @Resource
    private MoneyAccountDao moneyAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gatherId 主键
     * @return 实例对象
     */
    @Override
    public Gather queryById(Integer gatherId) {
        return this.gatherDao.queryById(gatherId);
    }

    /**
     * 查询所有数据
     *
     * @param gatherQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Gather> queryAll(GatherQueryForm gatherQueryForm) {
        Page<Gather> page = PageHelper.startPage(gatherQueryForm.getPageNum(), gatherQueryForm.getPageSize());
        List<Gather> gatherList = this.gatherDao.queryAll(gatherQueryForm);
        return new PageInfo<>(gatherList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Gather> queryBySearch(GatherQueryForm gatherQueryForm) {
        gatherQueryForm.setCurrentDate(new Date());
        Page<Gather> page = PageHelper.startPage(gatherQueryForm.getPageNum(), gatherQueryForm.getPageSize());
        List<Gather> gatherList = this.gatherDao.queryOrByPojo(gatherQueryForm);
        return new PageInfo<>(gatherList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Gather> queryByScreen(GatherQueryForm gatherQueryForm) {
        Page<Gather> page = PageHelper.startPage(gatherQueryForm.getPageNum(), gatherQueryForm.getPageSize());
        List<Gather> gatherList = this.gatherDao.queryAndByPojo(gatherQueryForm);
        return new PageInfo<>(gatherList);
    }

    /**
     * 新增数据
     *
     * @param gather 实例对象
     * @return 实例对象
     */
    @Override
    public Gather insert(Gather gather) {
        //新增付款单
        gather.setGatherBirthday(new Date());
        gather.setAudited(0);
        this.gatherDao.insert(gather);
        //修改销售单数据
        if(gather.getSellType()==0) {
            //修改销售单数据
            SellBill sellBill = this.sellBillDao.queryById(gather.getSellId());
            System.out.println("sellBill.getReceivedMoney()" + sellBill.getReceivedMoney());
            System.out.println("gather.getGatherAmount()" + gather.getGatherAmount());
            BigDecimal money = sellBill.getReceivedMoney().add(gather.getGatherAmount());
            System.out.println(money);
            sellBill.setReceivedMoney(money);
            if (money.equals(sellBill.getBillPaidAmount())) {
                System.out.println("money" + money);
                System.out.println("sellBill.getBillPaidAmount()" + sellBill.getBillPaidAmount());
                sellBill.setReceived(2);

            } else if (sellBill.getBillPaidAmount().compareTo(money) > 0) {
                System.out.println("money" + money);
                System.out.println("sellBill.getBillPaidAmount()" + sellBill.getBillPaidAmount());
                sellBill.setReceived(1);
            } else if (money.compareTo(sellBill.getBillPaidAmount()) > 0) {
                System.out.println("money" + money);
                System.out.println("sellBill.getBillPaidAmount()" + sellBill.getBillPaidAmount());
                sellBill.setReceived(3);
            }
            System.out.println("sellbill:" + sellBill);
            this.sellBillDao.update(sellBill);
        }else if(gather.getSellType()==1) {
            //修改销售退货单数据
            System.out.println("修改销售退货单数据");
            SellReturn sellBill = this.sellReturnDao.queryById(gather.getSellId());
            System.out.println("退款金额"+gather.getGatherAmount());
            System.out.println("账户金额"+sellBill.getRetPaidAmount());
            BigDecimal money = sellBill.getRetPaidAmount().add(gather.getGatherAmount().negate());
            sellBill.setRetPaidAmount(money);
            System.out.println(money);
            if (money.equals(sellBill.getRetPayAmount())) {
                System.out.println("money" + money);

                sellBill.setRefunded(2);

            } else if (sellBill.getRetPayAmount().compareTo(money) > 0) {
                System.out.println("money" + money);

                sellBill.setRefunded(1);
            } else if (money.compareTo(sellBill.getRetPayAmount()) > 0) {
                System.out.println("money" + money);

                sellBill.setRefunded(3);
            }


            System.out.println("sellbill:" + sellBill);
            this.sellReturnDao.update(sellBill);

        }
        //修改资金账户金额
        MoneyAccount moneyAccount=this.moneyAccountDao.queryById(gather.getMoneyAccountId());
        BigDecimal money1=moneyAccount.getAccountMoney().add(gather.getGatherAmount());
        moneyAccount.setAccountMoney(money1);
        this.moneyAccountDao.update(moneyAccount);
        return this.queryById(gather.getGatherId());
    }

    /**
     * 批量新增数据
     *
     * @param GatherList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Gather> GatherList) {
        return this.gatherDao.insertBatch(GatherList) == -1;
    }

    /**
     * 修改数据
     *
     * @param gather 实例对象
     * @return 实例对象
     */
    @Override
    public Gather update(Gather gather) {
        this.gatherDao.update(gather);
        return this.queryById(gather.getGatherId());
    }

    /**
     * 批量修改数据
     *
     * @param gatherList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Gather> gatherList) {
        return this.gatherDao.updateBatch(gatherList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param gatherId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer gatherId) {
        return this.gatherDao.deleteById(gatherId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.gatherDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
