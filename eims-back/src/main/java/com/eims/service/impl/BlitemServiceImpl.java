package com.eims.service.impl;

import com.eims.mybatis.entity.Blitem;
import com.eims.vo.form.BlitemQueryForm;
import com.eims.mybatis.dao.BlitemDao;
import com.eims.service.BlitemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Blitem)表服务实现类
 *
 * @author makejava
 * @since 2021-06-16 10:32:53
 */
@Service("blitemService")
public class BlitemServiceImpl implements BlitemService {
    @Resource
    private BlitemDao blitemDao;

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
     * 修改数据
     *
     * @param blitem 实例对象
     * @return 实例对象
     */
    @Override
    public Blitem update(Blitem blitem) {
        this.blitemDao.update(blitem);
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
