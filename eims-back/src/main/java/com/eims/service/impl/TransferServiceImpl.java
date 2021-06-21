package com.eims.service.impl;

import com.eims.mybatis.dao.TransferDetailsDao;
import com.eims.mybatis.entity.OutboundDetail;
import com.eims.mybatis.entity.Transfer;
import com.eims.mybatis.entity.TransferDetails;
import com.eims.vo.form.TransferQueryForm;
import com.eims.mybatis.dao.TransferDao;
import com.eims.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
