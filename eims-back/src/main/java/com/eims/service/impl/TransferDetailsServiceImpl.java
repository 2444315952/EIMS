package com.eims.service.impl;

import com.eims.mybatis.entity.TransferDetails;
import com.eims.vo.form.TransferDetailsQueryForm;
import com.eims.mybatis.dao.TransferDetailsDao;
import com.eims.service.TransferDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (TransferDetails)表服务实现类
 *
 * @author makejava
 * @since 2021-06-16 10:35:20
 */
@Service("transferDetailsService")
public class TransferDetailsServiceImpl implements TransferDetailsService {
    @Resource
    private TransferDetailsDao transferDetailsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param transferDetailId 主键
     * @return 实例对象
     */
    @Override
    public TransferDetails queryById(Integer transferDetailId) {
        return this.transferDetailsDao.queryById(transferDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param transferDetailsQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<TransferDetails> queryAll(TransferDetailsQueryForm transferDetailsQueryForm) {
        Page<TransferDetails> page = PageHelper.startPage(transferDetailsQueryForm.getPageNum(), transferDetailsQueryForm.getPageSize());
        List<TransferDetails> transferDetailsList = this.transferDetailsDao.queryAll(transferDetailsQueryForm);
        return new PageInfo<>(transferDetailsList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param transferDetailsQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<TransferDetails> queryBySearch(TransferDetailsQueryForm transferDetailsQueryForm) {
        Page<TransferDetails> page = PageHelper.startPage(transferDetailsQueryForm.getPageNum(), transferDetailsQueryForm.getPageSize());
        List<TransferDetails> transferDetailsList = this.transferDetailsDao.queryOrByPojo(transferDetailsQueryForm);
        return new PageInfo<>(transferDetailsList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param transferDetailsQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<TransferDetails> queryByScreen(TransferDetailsQueryForm transferDetailsQueryForm) {
        Page<TransferDetails> page = PageHelper.startPage(transferDetailsQueryForm.getPageNum(), transferDetailsQueryForm.getPageSize());
        List<TransferDetails> transferDetailsList = this.transferDetailsDao.queryAndByPojo(transferDetailsQueryForm);
        return new PageInfo<>(transferDetailsList);
    }

    /**
     * 新增数据
     *
     * @param transferDetails 实例对象
     * @return 实例对象
     */
    @Override
    public TransferDetails insert(TransferDetails transferDetails) {
        this.transferDetailsDao.insert(transferDetails);
        return this.queryById(transferDetails.getTransferDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param TransferDetailsList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<TransferDetails> TransferDetailsList) {
        return this.transferDetailsDao.insertBatch(TransferDetailsList) == -1;
    }

    /**
     * 修改数据
     *
     * @param transferDetails 实例对象
     * @return 实例对象
     */
    @Override
    public TransferDetails update(TransferDetails transferDetails) {
        this.transferDetailsDao.update(transferDetails);
        return this.queryById(transferDetails.getTransferDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param transferDetailsList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<TransferDetails> transferDetailsList) {
        return this.transferDetailsDao.updateBatch(transferDetailsList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param transferDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer transferDetailId) {
        return this.transferDetailsDao.deleteById(transferDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.transferDetailsDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
