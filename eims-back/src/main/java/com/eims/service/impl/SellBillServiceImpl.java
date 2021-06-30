package com.eims.service.impl;

import com.eims.mybatis.dao.SellDetailDao;
import com.eims.mybatis.dao.SellOrderBillDao;
import com.eims.mybatis.dao.SellOrderDetailDao;
import com.eims.mybatis.entity.SellBill;
import com.eims.mybatis.entity.SellDetail;
import com.eims.mybatis.entity.SellOrderBill;
import com.eims.mybatis.entity.SellOrderDetail;
import com.eims.vo.form.SellBillQueryForm;
import com.eims.mybatis.dao.SellBillDao;
import com.eims.service.SellBillService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * (SellBill)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:26:47
 */
@Service("sellBillService")
@Log4j2
public class SellBillServiceImpl implements SellBillService {
    @Resource
    private SellBillDao sellBillDao;

    @Resource
    private SellDetailDao sellDetailDao;

    @Resource
    private SellOrderBillDao sellOrderBillDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellId 主键
     * @return 实例对象
     */
    @Override
    public SellBill queryById(Integer sellId) {
        return this.sellBillDao.queryById(sellId);
    }

    /**
     * 查询所有数据
     *
     * @param sellBillQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellBill> queryAll(SellBillQueryForm sellBillQueryForm) {
        Page<SellBill> page = PageHelper.startPage(sellBillQueryForm.getPageNum(), sellBillQueryForm.getPageSize());
        List<SellBill> sellBillList = this.sellBillDao.queryAll(sellBillQueryForm);
        return new PageInfo<>(sellBillList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellBill> queryBySearch(SellBillQueryForm sellBillQueryForm) {
        sellBillQueryForm.setCurrentDate(new Date());
        Page<SellBill> page = PageHelper.startPage(sellBillQueryForm.getPageNum(), sellBillQueryForm.getPageSize());
        List<SellBill> sellBillList = this.sellBillDao.queryOrByPojo(sellBillQueryForm);
        return new PageInfo<>(sellBillList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellBill> queryByScreen(SellBillQueryForm sellBillQueryForm) {
        Page<SellBill> page = PageHelper.startPage(sellBillQueryForm.getPageNum(), sellBillQueryForm.getPageSize());
        List<SellBill> sellBillList = this.sellBillDao.queryAndByPojo(sellBillQueryForm);
        log.debug("销售单{}",sellBillList);
        log.debug("销售单长度{}",sellBillList.size());
        log.debug("total的是{}",new PageInfo<>(sellBillList).getTotal());
        return new PageInfo<>(sellBillList);
    }

    /**
     * 新增数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    @Override
    public SellBill insert(SellBill sellBill) {
        sellBill.setSellHirthday(new Date());
        sellBill.setAudited(0);
        this.sellBillDao.insert(sellBill);
        log.debug("主键id是:{}",sellBill.getSellId());
        List<SellDetail> sellDetailList=sellBill.getSellDetails();

        if(sellDetailList!=null){
            for(SellDetail detail:sellDetailList)
                detail.setSellId(sellBill.getSellId());


            log.debug("详情是是:{}",sellBill.getSellDetails());
            Iterator<SellDetail> it= sellDetailList.iterator();
            while(it.hasNext()) {
                SellDetail x = it.next();
                if (x.getProductId() == null) {
                    it.remove();
                }
            }
            log.debug("处理后的订单详情:{}",sellDetailList);
            
            if(sellBill.getSellOrderId()!=null){
                log.debug("SellOrderId的值是:{}",sellBill.getSellOrderId());
                SellOrderBill sellOrderBill=new SellOrderBill();
                sellOrderBill.setSellOrderId(sellBill.getSellOrderId());
                sellOrderBill.setAudited(1);
                sellOrderBillDao.update(sellOrderBill);




            }

            sellDetailDao.insertBatch(sellDetailList);


        }

        return this.queryById(sellBill.getSellId());
    }

    /**
     * 批量新增数据
     *
     * @param SellBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellBill> SellBillList) {
        return this.sellBillDao.insertBatch(SellBillList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellBill 实例对象
     * @return 实例对象
     */
    @Override

    public SellBill update(SellBill sellBill) {



        this.sellBillDao.update(sellBill);


        return this.queryById(sellBill.getSellId());
    }

    @Override
    @Transactional
    public SellBill updateanddetail(SellBill sellBill) {
        log.debug(sellBill.toString());
        this.sellBillDao.update(sellBill);

        List<SellDetail> updateDetails=sellBill.getSellDetails();
        List<SellDetail> addDetails= new ArrayList<>();
        List<Integer> keyIds=new ArrayList();
        for(Iterator<SellDetail> it=updateDetails.iterator();it.hasNext();){
            SellDetail detail=it.next();
            if(detail.getSellDetailId()==null){
                it.remove();
                addDetails.add(detail);

            }else{

                keyIds.add(detail.getSellDetailId());
            }
        }
        //修改明细信息
        if(updateDetails!=null) {
            this.sellDetailDao.updateBatch(updateDetails);
        }
        //删除不存在的明细
        if(keyIds.size()>0){
            Map<String,Object> sellandkeyids=new HashMap<>();
            sellandkeyids.put("sellId",sellBill.getSellId());
            sellandkeyids.put("keyIds",keyIds);
            System.out.println(sellandkeyids);
            sellDetailDao.deleteSellDetilIdAndNotInKeyIds(sellandkeyids);
        }
        System.out.println("-----------------");
        System.out.println("addsize"+addDetails);
        if(addDetails.size()>0) {
            for (SellDetail detail:addDetails)

                detail.setSellId(sellBill.getSellId());
                this.sellDetailDao.insertBatch(addDetails);

        }

        return this.queryById(sellBill.getSellId());
    }

    /**
     * 批量修改数据
     *
     * @param sellBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellBill> sellBillList) {
        return this.sellBillDao.updateBatch(sellBillList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellId) {
        return this.sellBillDao.deleteById(sellId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellBillDao.deleteBatch(ids);
        return ids.size() == row;
    }

    /**
     * 日期查询
     *
     * @param datenumber 日期相差天数
     * @return 影响行数
     */

    @Override
    public PageInfo<SellBill> queryByDate(Integer datenumber) {
//        Page<SellBill> page = PageHelper.startPage(sellBillQueryForm.getPageNum(), sellBillQueryForm.getPageSize());
//        List<SellBill> sellBillList = this.sellBillDao.queryAll(sellBillQueryForm);
        return null;
    }
}
