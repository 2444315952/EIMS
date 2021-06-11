package com.eims.service.impl;

import com.eims.mybatis.entity.CommodityType;
import com.eims.vo.form.CommodityTypeQueryForm;
import com.eims.mybatis.dao.CommodityTypeDao;
import com.eims.service.CommodityTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (CommodityType)表服务实现类
 *
 * @author makejava
 * @since 2021-06-08 13:46:21
 */
@Service("commodityTypeService")
public class CommodityTypeServiceImpl implements CommodityTypeService {
    @Resource
    private CommodityTypeDao commodityTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param commId 主键
     * @return 实例对象
     */
    @Override
    public CommodityType queryById(Integer commId) {
        return this.commodityTypeDao.queryById(commId);
    }

    /**
     * 查询所有数据
     *
     * @param commodityTypeQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<CommodityType> queryAll(CommodityTypeQueryForm commodityTypeQueryForm) {
        Page<CommodityType> page = PageHelper.startPage(commodityTypeQueryForm.getPageNum(), commodityTypeQueryForm.getPageSize());
        List<CommodityType> commodityTypeList = this.commodityTypeDao.queryAll(commodityTypeQueryForm);
        return new PageInfo<>(commodityTypeList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param commodityTypeQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<CommodityType> queryBySearch(CommodityTypeQueryForm commodityTypeQueryForm) {
        Page<CommodityType> page = PageHelper.startPage(commodityTypeQueryForm.getPageNum(), commodityTypeQueryForm.getPageSize());
        List<CommodityType> commodityTypeList = this.commodityTypeDao.queryOrByPojo(commodityTypeQueryForm);
        return new PageInfo<>(commodityTypeList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param commodityTypeQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<CommodityType> queryByScreen(CommodityTypeQueryForm commodityTypeQueryForm) {
        Page<CommodityType> page = PageHelper.startPage(commodityTypeQueryForm.getPageNum(), commodityTypeQueryForm.getPageSize());
        List<CommodityType> commodityTypeList = this.commodityTypeDao.queryAndByPojo(commodityTypeQueryForm);
        return new PageInfo<>(commodityTypeList);
    }

    /**
     * 新增数据
     *
     * @param commodityType 实例对象
     * @return 实例对象
     */
    @Override
    public CommodityType insert(CommodityType commodityType) {
        this.commodityTypeDao.insert(commodityType);
        return this.queryById(commodityType.getCommId());
    }

    /**
     * 批量新增数据
     *
     * @param CommodityTypeList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<CommodityType> CommodityTypeList) {
        return this.commodityTypeDao.insertBatch(CommodityTypeList) == -1;
    }

    /**
     * 修改数据
     *
     * @param commodityType 实例对象
     * @return 实例对象
     */
    @Override
    public CommodityType update(CommodityType commodityType) {
        this.commodityTypeDao.update(commodityType);
        return this.queryById(commodityType.getCommId());
    }

    /**
     * 批量修改数据
     *
     * @param commodityTypeList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<CommodityType> commodityTypeList) {
        return this.commodityTypeDao.updateBatch(commodityTypeList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param commId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer commId) {
        return this.commodityTypeDao.deleteById(commId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.commodityTypeDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
