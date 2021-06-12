package com.eims.service.impl;

import com.eims.mybatis.entity.SellReturn;
import com.eims.vo.form.SellReturnQueryForm;
import com.eims.mybatis.dao.SellReturnDao;
import com.eims.service.SellReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (SellReturn)表服务实现类
 *
 * @author makejava
 * @since 2021-06-12 10:28:44
 */
@Service("sellReturnService")
public class SellReturnServiceImpl implements SellReturnService {
    @Resource
    private SellReturnDao sellReturnDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sellReturnId 主键
     * @return 实例对象
     */
    @Override
    public SellReturn queryById(Integer sellReturnId) {
        return this.sellReturnDao.queryById(sellReturnId);
    }

    /**
     * 查询所有数据
     *
     * @param sellReturnQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<SellReturn> queryAll(SellReturnQueryForm sellReturnQueryForm) {
        Page<SellReturn> page = PageHelper.startPage(sellReturnQueryForm.getPageNum(), sellReturnQueryForm.getPageSize());
        List<SellReturn> sellReturnList = this.sellReturnDao.queryAll(sellReturnQueryForm);
        return new PageInfo<>(sellReturnList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellReturn> queryBySearch(SellReturnQueryForm sellReturnQueryForm) {
        Page<SellReturn> page = PageHelper.startPage(sellReturnQueryForm.getPageNum(), sellReturnQueryForm.getPageSize());
        List<SellReturn> sellReturnList = this.sellReturnDao.queryOrByPojo(sellReturnQueryForm);
        return new PageInfo<>(sellReturnList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param sellReturnQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<SellReturn> queryByScreen(SellReturnQueryForm sellReturnQueryForm) {
        Page<SellReturn> page = PageHelper.startPage(sellReturnQueryForm.getPageNum(), sellReturnQueryForm.getPageSize());
        List<SellReturn> sellReturnList = this.sellReturnDao.queryAndByPojo(sellReturnQueryForm);
        return new PageInfo<>(sellReturnList);
    }

    /**
     * 新增数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    @Override
    public SellReturn insert(SellReturn sellReturn) {
        this.sellReturnDao.insert(sellReturn);
        return this.queryById(sellReturn.getSellReturnId());
    }

    /**
     * 批量新增数据
     *
     * @param SellReturnList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<SellReturn> SellReturnList) {
        return this.sellReturnDao.insertBatch(SellReturnList) == -1;
    }

    /**
     * 修改数据
     *
     * @param sellReturn 实例对象
     * @return 实例对象
     */
    @Override
    public SellReturn update(SellReturn sellReturn) {
        this.sellReturnDao.update(sellReturn);
        return this.queryById(sellReturn.getSellReturnId());
    }

    /**
     * 批量修改数据
     *
     * @param sellReturnList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<SellReturn> sellReturnList) {
        return this.sellReturnDao.updateBatch(sellReturnList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param sellReturnId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sellReturnId) {
        return this.sellReturnDao.deleteById(sellReturnId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.sellReturnDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
