package com.eims.service.impl;

import com.eims.mybatis.dao.GatherRefundDetailDao;
import com.eims.mybatis.entity.GatherRefund;
import com.eims.mybatis.entity.GatherRefundDetail;
import com.eims.vo.form.GatherRefundQueryForm;
import com.eims.mybatis.dao.GatherRefundDao;
import com.eims.service.GatherRefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (GatherRefund)表服务实现类
 *
 * @author makejava
 * @since 2021-06-30 19:36:17
 */
@Service("gatherRefundService")
public class GatherRefundServiceImpl implements GatherRefundService {
    @Resource
    private GatherRefundDao gatherRefundDao;

    @Resource
    private GatherRefundDetailDao gatherRefundDetailDao;
    /**
     * 通过ID查询单条数据
     *
     * @param gatherRefundId 主键
     * @return 实例对象
     */
    @Override
    public GatherRefund queryById(Integer gatherRefundId) {
        return this.gatherRefundDao.queryById(gatherRefundId);
    }

    /**
     * 查询所有数据
     *
     * @param gatherRefundQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<GatherRefund> queryAll(GatherRefundQueryForm gatherRefundQueryForm) {
        Page<GatherRefund> page = PageHelper.startPage(gatherRefundQueryForm.getPageNum(), gatherRefundQueryForm.getPageSize());
        List<GatherRefund> gatherRefundList = this.gatherRefundDao.queryAll(gatherRefundQueryForm);
        return new PageInfo<>(gatherRefundList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param gatherRefundQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<GatherRefund> queryBySearch(GatherRefundQueryForm gatherRefundQueryForm) {
        Page<GatherRefund> page = PageHelper.startPage(gatherRefundQueryForm.getPageNum(), gatherRefundQueryForm.getPageSize());
        List<GatherRefund> gatherRefundList = this.gatherRefundDao.queryOrByPojo(gatherRefundQueryForm);
        return new PageInfo<>(gatherRefundList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param gatherRefundQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<GatherRefund> queryByScreen(GatherRefundQueryForm gatherRefundQueryForm) {
        Page<GatherRefund> page = PageHelper.startPage(gatherRefundQueryForm.getPageNum(), gatherRefundQueryForm.getPageSize());
        List<GatherRefund> gatherRefundList = this.gatherRefundDao.queryAndByPojo(gatherRefundQueryForm);
        return new PageInfo<>(gatherRefundList);
    }

    /**
     * 新增数据
     *
     * @param gatherRefund 实例对象
     * @return 实例对象
     */
    @Override
    public GatherRefund insert(GatherRefund gatherRefund) {
        this.gatherRefundDao.insert(gatherRefund);
        List<GatherRefundDetail> gatherRefundDetailList = gatherRefund.getGatherRefundDetailList();//获取GatherRefund实体类里面的getGatherRefundDetailList()值,get是获取
        if (gatherRefundDetailList != null){                 //判断get过来的paymentDetailList里面是否有值
            for (GatherRefundDetail detail:gatherRefundDetailList)//类型  别名：循环集和变量名
                detail.setGatherRefundDetailId(gatherRefund.getGatherRefundId());//把循环自动新增的id放入 detail.setPaymentId
            gatherRefundDetailDao.insertBatch(gatherRefundDetailList);//批量新增付款单明细集和数据
        }
        return this.queryById(gatherRefund.getGatherRefundId());
    }

    /**
     * 批量新增数据
     *
     * @param GatherRefundList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<GatherRefund> GatherRefundList) {
        return this.gatherRefundDao.insertBatch(GatherRefundList) == -1;
    }

    /**
     * 修改数据
     *
     * @param gatherRefund 实例对象
     * @return 实例对象
     */
    @Override
    public GatherRefund update(GatherRefund gatherRefund) {
        this.gatherRefundDao.update(gatherRefund);
        if (gatherRefund.getGatherRefundDetailList() !=  null){
            this.gatherRefundDetailDao.deleteBatchByEntities(gatherRefund.getGatherRefundDetailList());
            this.gatherRefundDetailDao.insertBatch(gatherRefund.getGatherRefundDetailList());
        }
        return this.queryById(gatherRefund.getGatherRefundId());
    }

    /**
     * 批量修改数据
     *
     * @param gatherRefundList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<GatherRefund> gatherRefundList) {
        return this.gatherRefundDao.updateBatch(gatherRefundList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param gatherRefundId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer gatherRefundId) {
        return this.gatherRefundDao.deleteById(gatherRefundId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.gatherRefundDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
