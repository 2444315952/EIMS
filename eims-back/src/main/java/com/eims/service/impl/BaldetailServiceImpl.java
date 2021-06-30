package com.eims.service.impl;

import com.eims.mybatis.entity.Baldetail;
import com.eims.vo.form.BaldetailQueryForm;
import com.eims.mybatis.dao.BaldetailDao;
import com.eims.service.BaldetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Baldetail)表服务实现类
 *
 * @author makejava
 * @since 2021-06-16 10:34:38
 */
@Service("baldetailService")
public class BaldetailServiceImpl implements BaldetailService {
    @Resource
    private BaldetailDao baldetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param baldetailId 主键
     * @return 实例对象
     */
    @Override
    public Baldetail queryById(Integer baldetailId) {
        return this.baldetailDao.queryById(baldetailId);
    }

    /**
     * 查询所有数据
     *
     * @param baldetailQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Baldetail> queryAll(BaldetailQueryForm baldetailQueryForm) {
        Page<Baldetail> page = PageHelper.startPage(baldetailQueryForm.getPageNum(), baldetailQueryForm.getPageSize());
        List<Baldetail> baldetailList = this.baldetailDao.queryAll(baldetailQueryForm);
        return new PageInfo<>(baldetailList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param baldetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Baldetail> queryBySearch(BaldetailQueryForm baldetailQueryForm) {
        Page<Baldetail> page = PageHelper.startPage(baldetailQueryForm.getPageNum(), baldetailQueryForm.getPageSize());
        List<Baldetail> baldetailList = this.baldetailDao.queryOrByPojo(baldetailQueryForm);
        return new PageInfo<>(baldetailList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param baldetailQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Baldetail> queryByScreen(BaldetailQueryForm baldetailQueryForm) {
        Page<Baldetail> page = PageHelper.startPage(baldetailQueryForm.getPageNum(), baldetailQueryForm.getPageSize());
        List<Baldetail> baldetailList = this.baldetailDao.queryAndByPojo(baldetailQueryForm);
        return new PageInfo<>(baldetailList);
    }

    /**
     * 新增数据
     *
     * @param baldetail 实例对象
     * @return 实例对象
     */
    @Override
    public Baldetail insert(Baldetail baldetail) {
        this.baldetailDao.insert(baldetail);
        return this.queryById(baldetail.getBaldetailId());
    }

    /**
     * 批量新增数据
     *
     * @param BaldetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Baldetail> BaldetailList) {
        return this.baldetailDao.insertBatch(BaldetailList) == -1;
    }

    /**
     * 修改数据
     *
     * @param baldetail 实例对象
     * @return 实例对象
     */
    @Override
    public Baldetail update(Baldetail baldetail) {
        this.baldetailDao.update(baldetail);
        return this.queryById(baldetail.getBaldetailId());
    }

    /**
     * 批量修改数据
     *
     * @param baldetailList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Baldetail> baldetailList) {
        return this.baldetailDao.updateBatch(baldetailList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param baldetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer baldetailId) {
        return this.baldetailDao.deleteById(baldetailId) > 0;
    }

    /**
     * 通过盘点单主键Id删除明细数据
     * @param blitemId
     * @return
     */
    @Override
    public boolean deleteByFk(Integer blitemId){
        return this.baldetailDao.deleteByFk(blitemId)>0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.baldetailDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
