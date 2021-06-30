package com.eims.service.impl;

import com.eims.mybatis.dao.ReturnBillsProductDao;
import com.eims.mybatis.dao.SellBillDao;
import com.eims.mybatis.dao.SellDetailDao;
import com.eims.mybatis.entity.ReturnBillsProduct;
import com.eims.mybatis.entity.SellBill;
import com.eims.mybatis.entity.SellDetail;
import com.eims.mybatis.entity.SellReturn;
import com.eims.vo.form.SellDetailQueryForm;
import com.eims.vo.form.SellReturnQueryForm;
import com.eims.mybatis.dao.SellReturnDao;
import com.eims.service.SellReturnService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (SellReturn)表服务实现类
 *
 * @author makejava
 * @since 2021-06-12 10:28:44
 */
@Log4j2
@Service("sellReturnService")
public class SellReturnServiceImpl implements SellReturnService {
    @Resource
    private SellReturnDao sellReturnDao;

    @Resource
    private ReturnBillsProductDao returnBillsProductDao;

    @Resource
    private SellBillDao sellBillDao;

    @Resource
    private SellDetailDao sellDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellReturnId 主键
     * @return 实例对象
     */
    @Override
    public SellReturn queryById(Integer sellReturnId) {
        return this.sellReturnDao.queryById(sellReturnId);
    }

    /**
     * 查询所有数据
     *
     * @param sellReturnQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellReturn> queryAll(SellReturnQueryForm sellReturnQueryForm) {
        Page<SellReturn> page = PageHelper.startPage(sellReturnQueryForm.getPageNum(), sellReturnQueryForm.getPageSize());
        List<SellReturn> sellReturnList = this.sellReturnDao.queryAll(sellReturnQueryForm);
        return new PageInfo<>(sellReturnList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellReturn> queryBySearch(SellReturnQueryForm sellReturnQueryForm) {
        sellReturnQueryForm.setCurrentDate(new Date());
        Page<SellReturn> page = PageHelper.startPage(sellReturnQueryForm.getPageNum(), sellReturnQueryForm.getPageSize());
        List<SellReturn> sellReturnList = this.sellReturnDao.queryOrByPojo(sellReturnQueryForm);
        return new PageInfo<>(sellReturnList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellReturn> queryByScreen(SellReturnQueryForm sellReturnQueryForm) {
        Page<SellReturn> page = PageHelper.startPage(sellReturnQueryForm.getPageNum(), sellReturnQueryForm.getPageSize());
        List<SellReturn> sellReturnList = this.sellReturnDao.queryAndByPojo(sellReturnQueryForm);
        return new PageInfo<>(sellReturnList);
    }

    /**
     * 新增数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    @Override
    public SellReturn insert(SellReturn sellReturn) {


        sellReturn.setDocumentDate(new Date());
        sellReturn.setAutited(0);
        this.sellReturnDao.insert(sellReturn);
        log.debug("主键id是:{}",sellReturn.getSellReturnId());
        List<ReturnBillsProduct> returnBillsProductList=sellReturn.getReturnBillsProductList();

        if(returnBillsProductList!=null){
            for(ReturnBillsProduct detail:returnBillsProductList)
                detail.setSellReturnId(sellReturn.getSellReturnId());


            log.debug("详情是是:{}",sellReturn.getReturnBillsProductList());
            Iterator<ReturnBillsProduct> it= returnBillsProductList.iterator();
            while(it.hasNext()) {
                ReturnBillsProduct x = it.next();
                if (x.getProductId() == null) {
                    it.remove();
                }
            }
            log.debug("处理后的订单详情:{}",returnBillsProductList);
            List<SellDetail> sellDetails=new ArrayList<SellDetail>();
            Integer i=0;
            for(ReturnBillsProduct returnBillsProducts:sellReturn.getReturnBillsProductList()){
                i++;
                SellDetail sellDetailone=new SellDetail();
                sellDetailone.setSellDetailId(returnBillsProducts.getSellDetailId());
                sellDetailone.setReturned(1);
                sellDetails.add(sellDetailone);
            }
            //销售单详情更改退货状态
            sellDetailDao.updateBatch(sellDetails);
            SellDetailQueryForm sellDetail=new SellDetailQueryForm();
            sellDetail.setSellId(sellReturn.getSellId());
            Integer aa=sellDetailDao.queryOrByPojo(sellDetail).size();
            System.out.println("前端数量"+i);
            System.out.println("数据库数量"+aa);



            SellBill sellBill=new SellBill();
            sellBill.setSellId(sellReturn.getSellId());
            if(aa>i) {
                sellBill.setReturnState(1);
            }else {
                sellBill.setReturnState(2);
            }
            //销售单更改退货状态

            sellBillDao.update(sellBill);



            returnBillsProductDao.insertBatch(returnBillsProductList);


        }

        return this.queryById(sellReturn.getSellReturnId());
    }

    /**
     * 批量新增数据
     *
     * @param SellReturnList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellReturn> SellReturnList) {
        return this.sellReturnDao.insertBatch(SellReturnList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    @Override
    public SellReturn update(SellReturn sellReturn) {
        this.sellReturnDao.update(sellReturn);
        return this.queryById(sellReturn.getSellReturnId());
    }

    /**
     * 批量修改数据
     *
     * @param sellReturnList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellReturn> sellReturnList) {
        return this.sellReturnDao.updateBatch(sellReturnList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellReturnId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellReturnId) {
        return this.sellReturnDao.deleteById(sellReturnId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellReturnDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
