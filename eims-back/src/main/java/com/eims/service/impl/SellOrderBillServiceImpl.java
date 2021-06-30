package com.eims.service.impl;

import com.eims.mybatis.dao.SellOrderDetailDao;
import com.eims.mybatis.entity.SellDetail;
import com.eims.mybatis.entity.SellOrderBill;
import com.eims.mybatis.entity.SellOrderDetail;
import com.eims.vo.form.SellOrderBillQueryForm;
import com.eims.mybatis.dao.SellOrderBillDao;
import com.eims.service.SellOrderBillService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * (SellOrderBill)表服务实现类
 *
 * @author makejava
 * @since 2021-06-20 21:16:23
 */
@Log4j2
@Service("sellOrderBillService")
public class SellOrderBillServiceImpl implements SellOrderBillService {
    @Resource
    private SellOrderBillDao sellOrderBillDao;
    @Resource
    private SellOrderDetailDao sellOrderDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellOrderId 主键
     * @return 实例对象
     */
    @Override
    public SellOrderBill queryById(Integer sellOrderId) {
        return this.sellOrderBillDao.queryById(sellOrderId);
    }

    /**
     * 查询所有数据
     *
     * @param sellOrderBillQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellOrderBill> queryAll(SellOrderBillQueryForm sellOrderBillQueryForm) {
        Page<SellOrderBill> page = PageHelper.startPage(sellOrderBillQueryForm.getPageNum(), sellOrderBillQueryForm.getPageSize());
        List<SellOrderBill> sellOrderBillList = this.sellOrderBillDao.queryAll(sellOrderBillQueryForm);
        return new PageInfo<>(sellOrderBillList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellOrderBill> queryBySearch(SellOrderBillQueryForm sellOrderBillQueryForm) {
        Page<SellOrderBill> page = PageHelper.startPage(sellOrderBillQueryForm.getPageNum(), sellOrderBillQueryForm.getPageSize());
        List<SellOrderBill> sellOrderBillList = this.sellOrderBillDao.queryOrByPojo(sellOrderBillQueryForm);
        return new PageInfo<>(sellOrderBillList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellOrderBillQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellOrderBill> queryByScreen(SellOrderBillQueryForm sellOrderBillQueryForm) {
        Page<SellOrderBill> page = PageHelper.startPage(sellOrderBillQueryForm.getPageNum(), sellOrderBillQueryForm.getPageSize());
        List<SellOrderBill> sellOrderBillList = this.sellOrderBillDao.queryAndByPojo(sellOrderBillQueryForm);
        return new PageInfo<>(sellOrderBillList);
    }

    /**
     * 新增数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    @Override
    public SellOrderBill insert(SellOrderBill sellOrderBill) {

        sellOrderBill.setSellOrderHirthday(new Date());
        this.sellOrderBillDao.insert(sellOrderBill);



        log.debug("主键id是:{}",sellOrderBill.getSellOrderId());
        List<SellOrderDetail> sellDetailList=sellOrderBill.getSellOrderDetailList();

        if(sellDetailList!=null){
            for(SellOrderDetail detail:sellDetailList)
                detail.setSellOrderId(sellOrderBill.getSellOrderId());


            log.debug("详情是是:{}",sellOrderBill.getSellOrderDetailList());
            Iterator<SellOrderDetail> it= sellDetailList.iterator();
            while(it.hasNext()) {
                SellOrderDetail x = it.next();
                if (x.getProductId() == null) {
                    it.remove();
                }
            }
            log.debug("处理后的订单详情:{}",sellDetailList);

            sellOrderDetailDao.insertBatch(sellDetailList);


        }
        return this.queryById(sellOrderBill.getSellOrderId());
    }

    /**
     * 批量新增数据
     *
     * @param SellOrderBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellOrderBill> SellOrderBillList) {
        return this.sellOrderBillDao.insertBatch(SellOrderBillList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellOrderBill 实例对象
     * @return 实例对象
     */
    @Override
    public SellOrderBill update(SellOrderBill sellOrderBill) {
        this.sellOrderBillDao.update(sellOrderBill);
        return this.queryById(sellOrderBill.getSellOrderId());
    }

    @Override
    @Transactional
    public SellOrderBill updateSellAndDetail(SellOrderBill sellOrderBill) {
        log.debug(sellOrderBill.toString());

        this.sellOrderBillDao.update(sellOrderBill);




        List<SellOrderDetail> updateDetails=sellOrderBill.getSellOrderDetailList();
        List<SellOrderDetail> addDetails= new ArrayList<>();
        List<Integer> keyIds=new ArrayList();
        for(Iterator<SellOrderDetail> it=updateDetails.iterator();it.hasNext();){
            SellOrderDetail detail=it.next();
            if(detail.getSellOrderDetailId()==null){
                it.remove();
                addDetails.add(detail);

            }else{

                keyIds.add(detail.getSellOrderDetailId());
            }
        }
        //修改明细信息
        if(updateDetails!=null) {
            this.sellOrderDetailDao.updateBatch(updateDetails);
        }
        //删除不存在的明细
        if(keyIds.size()>0){
            Map<String,Object> sellandkeyids=new HashMap<>();
            sellandkeyids.put("sellOrderId",sellOrderBill.getSellOrderId());
            sellandkeyids.put("keyIds",keyIds);
            System.out.println(sellandkeyids);
            sellOrderDetailDao.deleteSellOrderDetilIdAndNotInKeyIds(sellandkeyids);
        }
        System.out.println("-----------------");
        System.out.println("addsize"+addDetails);
        if(addDetails.size()>0) {
            for (SellOrderDetail detail:addDetails)

                detail.setSellOrderId(sellOrderBill.getSellOrderId());
            this.sellOrderDetailDao.insertBatch(addDetails);

        }

        return this.queryById(sellOrderBill.getSellOrderId());
    }

    /**
     * 批量修改数据
     *
     * @param sellOrderBillList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellOrderBill> sellOrderBillList) {
        return this.sellOrderBillDao.updateBatch(sellOrderBillList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellOrderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellOrderId) {
        return this.sellOrderBillDao.deleteById(sellOrderId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellOrderBillDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
