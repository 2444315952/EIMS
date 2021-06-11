package com.eims.service.impl;

import com.eims.mybatis.entity.ReturnBillsProduct;
import com.eims.vo.form.ReturnBillsProductQueryForm;
import com.eims.mybatis.dao.ReturnBillsProductDao;
import com.eims.service.ReturnBillsProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (ReturnBillsProduct)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:26:46
 */
@Service("returnBillsProductService")
public class ReturnBillsProductServiceImpl implements ReturnBillsProductService {
    @Resource
    private ReturnBillsProductDao returnBillsProductDao;

    /**
     * 通过ID查询单条数据
     *
     * @param retBpId 主键
     * @return 实例对象
     */
    @Override
    public ReturnBillsProduct queryById(Integer retBpId) {
        return this.returnBillsProductDao.queryById(retBpId);
    }

    /**
     * 查询所有数据
     *
     * @param returnBillsProductQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<ReturnBillsProduct> queryAll(ReturnBillsProductQueryForm returnBillsProductQueryForm) {
        Page<ReturnBillsProduct> page = PageHelper.startPage(returnBillsProductQueryForm.getPageNum(), returnBillsProductQueryForm.getPageSize());
        List<ReturnBillsProduct> returnBillsProductList = this.returnBillsProductDao.queryAll(returnBillsProductQueryForm);
        return new PageInfo<>(returnBillsProductList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param returnBillsProductQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<ReturnBillsProduct> queryBySearch(ReturnBillsProductQueryForm returnBillsProductQueryForm) {
        Page<ReturnBillsProduct> page = PageHelper.startPage(returnBillsProductQueryForm.getPageNum(), returnBillsProductQueryForm.getPageSize());
        List<ReturnBillsProduct> returnBillsProductList = this.returnBillsProductDao.queryOrByPojo(returnBillsProductQueryForm);
        return new PageInfo<>(returnBillsProductList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param returnBillsProductQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<ReturnBillsProduct> queryByScreen(ReturnBillsProductQueryForm returnBillsProductQueryForm) {
        Page<ReturnBillsProduct> page = PageHelper.startPage(returnBillsProductQueryForm.getPageNum(), returnBillsProductQueryForm.getPageSize());
        List<ReturnBillsProduct> returnBillsProductList = this.returnBillsProductDao.queryAndByPojo(returnBillsProductQueryForm);
        return new PageInfo<>(returnBillsProductList);
    }

    /**
     * 新增数据
     *
     * @param returnBillsProduct 实例对象
     * @return 实例对象
     */
    @Override
    public ReturnBillsProduct insert(ReturnBillsProduct returnBillsProduct) {
        this.returnBillsProductDao.insert(returnBillsProduct);
        return this.queryById(returnBillsProduct.getRetBpId());
    }

    /**
     * 批量新增数据
     *
     * @param ReturnBillsProductList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<ReturnBillsProduct> ReturnBillsProductList) {
        return this.returnBillsProductDao.insertBatch(ReturnBillsProductList) == -1;
    }

    /**
     * 修改数据
     *
     * @param returnBillsProduct 实例对象
     * @return 实例对象
     */
    @Override
    public ReturnBillsProduct update(ReturnBillsProduct returnBillsProduct) {
        this.returnBillsProductDao.update(returnBillsProduct);
        return this.queryById(returnBillsProduct.getRetBpId());
    }

    /**
     * 批量修改数据
     *
     * @param returnBillsProductList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<ReturnBillsProduct> returnBillsProductList) {
        return this.returnBillsProductDao.updateBatch(returnBillsProductList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param retBpId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer retBpId) {
        return this.returnBillsProductDao.deleteById(retBpId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.returnBillsProductDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
