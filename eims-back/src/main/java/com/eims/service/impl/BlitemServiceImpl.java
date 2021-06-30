package com.eims.service.impl;

import com.eims.mybatis.dao.BaldetailDao;
import com.eims.mybatis.dao.StockOutDao;
import com.eims.mybatis.dao.WarehouseWarrantDao;
import com.eims.mybatis.entity.*;
import com.eims.service.StockOutService;
import com.eims.service.WarehouseWarrantService;
import com.eims.vo.form.BlitemQueryForm;
import com.eims.mybatis.dao.BlitemDao;
import com.eims.service.BlitemService;
import com.eims.vo.form.StockOutQueryForm;
import com.eims.vo.form.WarehouseWarrantQueryForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Blitem)表服务实现类
 *
 * @author makejava
 * @since 2021-06-16 10:32:53
 */
@Service("blitemService")
@Slf4j
public class BlitemServiceImpl implements BlitemService {
    @Resource
    private BlitemDao blitemDao;

    @Resource
    private BaldetailDao baldetailDao;

    @Resource
    private WarehouseWarrantService warehouseWarrantService;

    @Resource
    private WarehouseWarrantDao warehouseWarrantDao;

    @Resource
    private StockOutDao stockOutDao;

    @Resource
    private StockOutService stockOutService;
    /**
     * 通过ID查询单条数据
     *
     * @param blitemId 主键
     * @return 实例对象
     */
    @Override
    public Blitem queryById(Integer blitemId) {
        return this.blitemDao.queryById(blitemId);
    }

    /**
     * 查询所有数据
     *
     * @param blitemQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Blitem> queryAll(BlitemQueryForm blitemQueryForm) {
        Page<Blitem> page = PageHelper.startPage(blitemQueryForm.getPageNum(), blitemQueryForm.getPageSize());
        List<Blitem> blitemList = this.blitemDao.queryAll(blitemQueryForm);
        return new PageInfo<>(blitemList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param blitemQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Blitem> queryBySearch(BlitemQueryForm blitemQueryForm) {
        Page<Blitem> page = PageHelper.startPage(blitemQueryForm.getPageNum(), blitemQueryForm.getPageSize());
        List<Blitem> blitemList = this.blitemDao.queryOrByPojo(blitemQueryForm);
        return new PageInfo<>(blitemList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param blitemQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Blitem> queryByScreen(BlitemQueryForm blitemQueryForm) {
        Page<Blitem> page = PageHelper.startPage(blitemQueryForm.getPageNum(), blitemQueryForm.getPageSize());
        List<Blitem> blitemList = this.blitemDao.queryAndByPojo(blitemQueryForm);
        return new PageInfo<>(blitemList);
    }

    /**
     * 新增数据
     *
     * @param blitem 实例对象
     * @return 实例对象
     */
    @Override
    public Blitem insert(Blitem blitem) {
        this.blitemDao.insert(blitem);
        for (Baldetail baldetail:blitem.getBaldetailList())
            baldetail.setBlitemId(blitem.getBlitemId());
        log.debug("明细是：{}",blitem.getBaldetailList().toString());
        log.debug("大小是：{}",blitem.getBaldetailList().size());
        this.baldetailDao.insertBatch(blitem.getBaldetailList());
        return this.queryById(blitem.getBlitemId());
    }

    /**
     * 批量新增数据
     *
     * @param BlitemList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Blitem> BlitemList) {
        return this.blitemDao.insertBatch(BlitemList) == -1;
    }

    /**
     * 审核盘点单改库存
     * @param blitemId
     * @return
     */
    @Override
    public boolean auditStorage(Integer blitemId){
        Blitem blitem=this.blitemDao.queryById(blitemId);
        blitem.setAudited(1);
        this.blitemDao.update(blitem);
        for (Baldetail baldetail:blitem.getBaldetailList())
            this.blitemDao.auditStorage(baldetail.getPhysicalInventory(),blitem.getWarehouseId(),baldetail.getProductId());
        return true;
    }

    @Transactional
    @Override
    public Blitem checkStorage(Integer blitemId){
        Blitem blitem=this.blitemDao.queryById(blitemId);
        blitem.setAudited(1);
        System.out.println("盘点单信息是：");
        System.out.println(blitem);
        this.blitemDao.update(blitem);
        WarehouseWarrant warehouseWarrant=new WarehouseWarrant(this.getWarehouseDocunum(),blitem.getDocumentDate(),blitem.getCompanyId(),blitem.getWorkPointId(),blitem.getEmployeeId(),blitem.getEmployeeName(),blitem.getAudited(),blitem.getWarehouseId(),blitem.getWarehouseName(),"盘盈入库",blitem.getBlitemId());
        StockOut stockOut=new StockOut(this.getStockOutDocuNum(),blitem.getDocumentDate(),blitem.getCompanyId(),blitem.getWorkPointId(),blitem.getEmployeeId(),blitem.getEmployeeName(),1,blitem.getWarehouseId(),blitem.getWarehouseName(),"盘亏出库",blitem.getBlitemId());

        //总入库数量
        Integer QuantityTotal=0;
        //总出库数量
        Integer DeliveryTotal=0;

        System.out.println("盘点数量为"+blitem.getBaldetailList().get(0).getPhysicalInventory());
        System.out.println("调拨数量"+blitem.getBaldetailList().get(0).getInventory());
        for (Baldetail baldetail:blitem.getBaldetailList()){
            System.out.println("盘点数量：------------");
            System.out.println(baldetail.getPhysicalInventory());
            System.out.println("库存数量为：+++++++++++++");
            System.out.println(baldetail.getInventory());

//            if(baldetail.getInventory()==baldetail.getPhysicalInventory())
//                continue;

            //如果盘点数量大于库存数量就进行入库处理
            if(baldetail.getPhysicalInventory()>baldetail.getInventory()){
                //新增入库单记录
                //数量差额
                Integer warequantityTotal=baldetail.getPhysicalInventory()-baldetail.getInventory();//入库数量=盘点数量-库存数量
                WarehousingDetail warehousingDetail=new WarehousingDetail(warehouseWarrant.getWarehouseWarrantId(),warehouseWarrant.getWarehouseDocunum(),baldetail.getProductId(),baldetail.getProductPicture(),baldetail.getProductName(),baldetail.getSpecModel(),baldetail.getProductUnit(),warequantityTotal);
                System.out.println("入库数量为：------------");
                System.out.println(warequantityTotal);
                System.out.println("入库单详情信息是：");
                System.out.println(warehousingDetail);
                //如果入库单详情为空，就新建一个详情来存储新增的入库单详情数据
                if(warehouseWarrant.getWarehousingDetailList()==null)
                    warehouseWarrant.setWarehousingDetailList(new ArrayList<WarehousingDetail>());
                warehouseWarrant.getWarehousingDetailList().add(warehousingDetail);
                QuantityTotal += warequantityTotal;

                System.out.println("入库信息：-------------");
                System.out.println(warehouseWarrant.toString());

                System.out.println("入库数量为：------------");
                System.out.println( warehouseWarrant.getInventoryQuantity());

                //如果库存数量大于盘点数量，进行出库处理
            }else if (baldetail.getPhysicalInventory()<baldetail.getInventory()){
                Integer outQuantity=baldetail.getInventory()-baldetail.getPhysicalInventory();//出库数量=库存数量-盘点数量
                OutboundDetail outboundDetail=new OutboundDetail(stockOut.getStockOutId(),stockOut.getStockOutDocunum(),baldetail.getProductId(),baldetail.getProductPicture(),baldetail.getProductName(),baldetail.getSpecModel(),baldetail.getProductUnit(),outQuantity);
                System.out.println("出库数量为：==============");
                System.out.println(outQuantity);
                //如果出库单详情为没有就新建一个详情来新的出库单详情来存储数据
                if (stockOut.getOutboundDetailList()==null)
                    stockOut.setOutboundDetailList(new ArrayList<OutboundDetail>());
                stockOut.getOutboundDetailList().add(outboundDetail);
                DeliveryTotal+=outQuantity;

            }
        }


        System.out.println("入库单的值为"+warehouseWarrant.toString());
        stockOut.setDeliveryQuantity(DeliveryTotal);
        this.stockOutService.insert(stockOut);
        System.out.println("出库单的值为"+stockOut.toString());
        this.stockOutService.auditStorage(stockOut.getStockOutId());

        warehouseWarrant.setInventoryQuantity(QuantityTotal);
        this.warehouseWarrantService.insert(warehouseWarrant);
        this.warehouseWarrantService.auditStorage(warehouseWarrant.getWarehouseWarrantId());
        return this.queryById(blitemId);
    }


    //设置入库单
    public  String getWarehouseDocunum(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        String warehouseDocunum="RKD-"+date+"-";
        WarehouseWarrantQueryForm warehouseWarrantQueryForm=new WarehouseWarrantQueryForm();
        warehouseWarrantQueryForm.setCompanyId(1);
        warehouseWarrantQueryForm.setWarehouseDocunum(warehouseDocunum);
        List<WarehouseWarrant> warrants=this.warehouseWarrantDao.queryOrByPojo(warehouseWarrantQueryForm);
        int size = warrants.size();
        String docuNumSequence=String.format("%05d",size+1);
        return warehouseDocunum+docuNumSequence;
    }

    //设置出库单
    public String getStockOutDocuNum(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        String stockOutDocunum="CKD-"+date+"-";
        StockOutQueryForm stockOutQueryForm = new StockOutQueryForm();
        stockOutQueryForm.setCompanyId(1);
        stockOutQueryForm.setStockOutDocunum(stockOutDocunum);
        List<StockOut> stockOuts = this.stockOutDao.queryOrByPojo(stockOutQueryForm);
        int size = stockOuts.size();
        String docuNumSequence=String.format("%05d",size+1);
        System.out.println("出库单信息+++++++++++");
        System.out.println(stockOutDocunum+docuNumSequence);
        return stockOutDocunum+docuNumSequence;
    }

    /**
     * 修改数据
     *
     * @param blitem 实例对象
     * @return 实例对象
     */
    @Override
    public Blitem update(Blitem blitem) {
        this.blitemDao.update(blitem);
        if(blitem.getBaldetailList()!=null){
            this.baldetailDao.deleteByFk(blitem.getBlitemId());
            for(Baldetail baldetail:blitem.getBaldetailList())
                baldetail.setBlitemId(blitem.getBlitemId());
        }
        this.baldetailDao.insertBatch(blitem.getBaldetailList());
        return this.queryById(blitem.getBlitemId());
    }

    /**
     * 批量修改数据
     *
     * @param blitemList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Blitem> blitemList) {
        return this.blitemDao.updateBatch(blitemList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param blitemId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer blitemId) {
        return this.blitemDao.deleteById(blitemId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.blitemDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
