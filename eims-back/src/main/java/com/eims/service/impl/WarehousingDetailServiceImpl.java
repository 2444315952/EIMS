package com.eims.service.impl;

import com.eims.mybatis.entity.WarehousingDetail;
import com.eims.vo.form.WarehousingDetailQueryForm;
import com.eims.mybatis.dao.WarehousingDetailDao;
import com.eims.service.WarehousingDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (WarehousingDetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:27:24
 */
@Service("warehousingDetailService")
public class WarehousingDetailServiceImpl implements WarehousingDetailService {
    @Resource
    private WarehousingDetailDao warehousingDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param warehousingDetailId 主键
     * @return 实例对象
     */
    @Override
    public WarehousingDetail queryById(Integer warehousingDetailId) {
        return this.warehousingDetailDao.queryById(warehousingDetailId);
    }

    /**
     * 查询所有数据
     *
     * @param warehousingDetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<WarehousingDetail> queryAll(WarehousingDetailQueryForm warehousingDetailQueryForm) {
        Page<WarehousingDetail> page = PageHelper.startPage(warehousingDetailQueryForm.getPageNum(), warehousingDetailQueryForm.getPageSize());
        List<WarehousingDetail> warehousingDetailList = this.warehousingDetailDao.queryAll(warehousingDetailQueryForm);
        return new PageInfo<>(warehousingDetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param warehousingDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<WarehousingDetail> queryBySearch(WarehousingDetailQueryForm warehousingDetailQueryForm) {
        Page<WarehousingDetail> page = PageHelper.startPage(warehousingDetailQueryForm.getPageNum(), warehousingDetailQueryForm.getPageSize());
        List<WarehousingDetail> warehousingDetailList = this.warehousingDetailDao.queryOrByPojo(warehousingDetailQueryForm);
        return new PageInfo<>(warehousingDetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param warehousingDetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<WarehousingDetail> queryByScreen(WarehousingDetailQueryForm warehousingDetailQueryForm) {
        Page<WarehousingDetail> page = PageHelper.startPage(warehousingDetailQueryForm.getPageNum(), warehousingDetailQueryForm.getPageSize());
        List<WarehousingDetail> warehousingDetailList = this.warehousingDetailDao.queryAndByPojo(warehousingDetailQueryForm);
        return new PageInfo<>(warehousingDetailList);
    }

    /**
     * 新增数据
     *
     * @param warehousingDetail 实例对象
     * @return 实例对象
     */
    @Override
    public WarehousingDetail insert(WarehousingDetail warehousingDetail) {
        this.warehousingDetailDao.insert(warehousingDetail);
        return this.queryById(warehousingDetail.getWarehousingDetailId());
    }

    /**
     * 批量新增数据
     *
     * @param WarehousingDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<WarehousingDetail> WarehousingDetailList) {
        return this.warehousingDetailDao.insertBatch(WarehousingDetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param warehousingDetail 实例对象
     * @return 实例对象
     */
    @Override
    public WarehousingDetail update(WarehousingDetail warehousingDetail) {
        this.warehousingDetailDao.update(warehousingDetail);
        return this.queryById(warehousingDetail.getWarehousingDetailId());
    }

    /**
     * 批量修改数据
     *
     * @param warehousingDetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<WarehousingDetail> warehousingDetailList) {
        return this.warehousingDetailDao.updateBatch(warehousingDetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param warehousingDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer warehousingDetailId) {
        return this.warehousingDetailDao.deleteById(warehousingDetailId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.warehousingDetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
