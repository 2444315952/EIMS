package com.eims.service.impl;

import com.eims.mybatis.dao.*;
import com.eims.mybatis.entity.*;
import com.eims.service.StockOutService;
import com.eims.service.WarehouseWarrantService;
import com.eims.vo.form.StockOutQueryForm;
import com.eims.vo.form.TransferQueryForm;
import com.eims.service.TransferService;
import com.eims.vo.form.WarehouseWarrantQueryForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Transfer)表服务实现类
 *
 * @author makejava
 * @since 2021-06-16 10:35:06
 */
@Service("transferService")
@Slf4j
public class TransferServiceImpl implements TransferService {
    @Resource
    private TransferDao transferDao;

    @Resource
    private TransferDetailsDao transferDetailsDao;

    @Resource
    private WarehouseWarrantDao warehouseWarrantDao;

    @Resource
    private WarehousingDetailDao warehousingDetailDao;

    @Resource
    private OutboundDetailDao outboundDetailDao;

    @Resource
    private StockOutDao stockOutDao;


    @Resource
    private StockOutService stockOutService;

    @Resource
    private WarehouseWarrantService warehouseWarrantService;
    /**
     * 通过ID查询单条数据
     *
     * @param transferId 主键
     * @return 实例对象
     */
    @Override
    public Transfer queryById(Integer transferId) {
        return this.transferDao.queryById(transferId);
    }

    /**
     * 查询所有数据
     *
     * @param transferQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Transfer> queryAll(TransferQueryForm transferQueryForm) {
        Page<Transfer> page = PageHelper.startPage(transferQueryForm.getPageNum(), transferQueryForm.getPageSize());
        List<Transfer> transferList = this.transferDao.queryAll(transferQueryForm);
        return new PageInfo<>(transferList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param transferQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Transfer> queryBySearch(TransferQueryForm transferQueryForm) {
        System.out.println(transferQueryForm);
        Page<Transfer> page = PageHelper.startPage(transferQueryForm.getPageNum(), transferQueryForm.getPageSize());
        List<Transfer> transferList = this.transferDao.queryOrByPojo(transferQueryForm);
        return new PageInfo<>(transferList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param transferQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Transfer> queryByScreen(TransferQueryForm transferQueryForm) {
        Page<Transfer> page = PageHelper.startPage(transferQueryForm.getPageNum(), transferQueryForm.getPageSize());
        List<Transfer> transferList = this.transferDao.queryAndByPojo(transferQueryForm);
        return new PageInfo<>(transferList);
    }

    /**
     * 新增数据
     *
     * @param transfer 实例对象
     * @return 实例对象
     */
    @Override
    public Transfer insert(Transfer transfer) {
        transfer.setDocumentDate(new Date());
        this.transferDao.insert(transfer);
        log.debug("调拨单：{}",transfer);
        for(TransferDetails details:transfer.getTransferDetailsList())
            details.setTransferId(transfer.getTransferId());

        log.debug("明细是：{}",transfer.getTransferDetailsList().toString());
        log.debug("大小是：{}",transfer.getTransferDetailsList().size());
        this.transferDetailsDao.insertBatch(transfer.getTransferDetailsList());
        return this.queryById(transfer.getTransferId());
    }

    /**
     * 批量新增数据
     *
     * @param TransferList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Transfer> TransferList) {
        return this.transferDao.insertBatch(TransferList) == -1;
    }

    /**
     * 修改数据
     *
     * @param transfer 实例对象
     * @return 实例对象
     */
    @Override
    public Transfer update(Transfer transfer) {
        this.transferDao.update(transfer);
        //this.warehouseWarrantDao.insert()
        //判断明细是否有数据
        if(transfer.getTransferDetailsList() !=null){
            //有的话先删除原有的明细数据
            this.transferDetailsDao.deleteByFk(transfer.getTransferId());

            //再新增新的明细数据
            for(TransferDetails details:transfer.getTransferDetailsList())
                details.setTransferId(transfer.getTransferId());
            this.transferDetailsDao.insertBatch(transfer.getTransferDetailsList());
        }
        return this.queryById(transfer.getTransferId());
    }


    @Override
    public Transfer checkStorage(Integer transferId){
        Transfer transfer = transferDao.queryById(transferId);
        transfer.setAudited(1);
        System.out.println("信息是：");
        System.out.println(transfer);
        this.transferDao.update(transfer);


        //新增出库单记录
        StockOut stockOut = new StockOut(this.getStockOutDocuNum(),transfer.getOutboundDate(),transfer.getCompanyId(),transfer.getWorkPointId(),transfer.getEmployeeId(),transfer.getEmployeeName(),1,transfer.getFoldWarehouseId(),transfer.getFoldWarehouseName(),"调拨出库",transfer.getTransferId());

        Integer quantityTotal = 0;

        for(TransferDetails details:transfer.getTransferDetailsList()) {

            OutboundDetail outboundDetail = new OutboundDetail(stockOut.getStockOutId(),stockOut.getStockOutDocunum(),details.getProductId(),details.getProductName(),details.getSpecModel(),details.getProductUnit(),details.getOutboundQuantity());
            System.out.println("详情信息是：");
            System.out.println(outboundDetail.toString());
            log.debug("----------------{}",outboundDetail);

            //如果出库单详情为空就new一个OutboundDetail来存储新增的数据
            if(stockOut.getOutboundDetailList() == null)
                stockOut.setOutboundDetailList(new ArrayList<OutboundDetail>());

            stockOut.getOutboundDetailList().add(outboundDetail);

            //计算总出库数量
            quantityTotal+=outboundDetail.getDeliveryQuantity();
        }

        stockOut.setDeliveryQuantity(quantityTotal);
        this.stockOutService.insert(stockOut);
        this.stockOutService.auditStorage(stockOut.getStockOutId());

        //新增入库单记录
        WarehouseWarrant warehouseWarrant=new WarehouseWarrant(this.getWarehouseDocunum(),transfer.getWarehousingDate(),transfer.getCompanyId(),transfer.getWorkPointId(),transfer.getEmployeeId(),transfer.getEmployeeName(),1,transfer.getExportWarehouseId(),transfer.getExportWarehouseName(),"调拨入库",transfer.getTransferId());
        Integer WarequantityTotal=0;
        for (TransferDetails details:transfer.getTransferDetailsList()){

            WarehousingDetail warehousingDetail=new WarehousingDetail(warehouseWarrant.getWarehouseWarrantId(),warehouseWarrant.getWarehouseDocunum(),details.getProductId(),details.getProductName(),details.getSpecModel(),details.getProductUnit(),details.getOutboundQuantity());
            System.out.println("入库单详情信息是：");
            System.out.println(warehousingDetail);

            //如果入库单详情为空，就新建一个详情来存储新增的入库单详情数据
            if(warehouseWarrant.getWarehousingDetailList()==null)
                warehouseWarrant.setWarehousingDetailList(new ArrayList<WarehousingDetail>());
            warehouseWarrant.getWarehousingDetailList().add(warehousingDetail);
            System.out.println("入库信息：-------------");
            System.out.println(warehouseWarrant.toString());

            //计算总的入库数量
            WarequantityTotal+=warehousingDetail.getInventoryQuantity();

        }
        warehouseWarrant.setInventoryQuantity(WarequantityTotal);
        this.warehouseWarrantService.insert(warehouseWarrant);
        this.warehouseWarrantService.auditStorage(warehouseWarrant.getWarehouseWarrantId());
        return this.queryById(transfer.getTransferId());
    }

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
     * 批量修改数据
     *
     * @param transferList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Transfer> transferList) {
        return this.transferDao.updateBatch(transferList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param transferId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer transferId) {
        return this.transferDao.deleteById(transferId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.transferDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
