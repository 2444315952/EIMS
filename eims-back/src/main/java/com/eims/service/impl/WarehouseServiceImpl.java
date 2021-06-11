package com.eims.service.impl;

import com.eims.mybatis.entity.Warehouse;
import com.eims.vo.form.WarehouseQueryForm;
import com.eims.mybatis.dao.WarehouseDao;
import com.eims.service.WarehouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Warehouse)表服务实现类
 *
 * @author makejava
 * @since 2021-06-04 18:20:02
 */
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseDao warehouseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param warehouseId 主键
     * @return 实例对象
     */
    @Override
    public Warehouse queryById(Integer warehouseId) {
        return this.warehouseDao.queryById(warehouseId);
    }

    /**
     * 查询所有数据
     *
     * @param warehouseQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Warehouse> queryAll(WarehouseQueryForm warehouseQueryForm) {
        Page<Warehouse> page = PageHelper.startPage(warehouseQueryForm.getPageNum(), warehouseQueryForm.getPageSize());
        List<Warehouse> warehouseList = this.warehouseDao.queryAll(warehouseQueryForm);
        return new PageInfo<>(warehouseList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param warehouseQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Warehouse> queryBySearch(WarehouseQueryForm warehouseQueryForm) {
        Page<Warehouse> page = PageHelper.startPage(warehouseQueryForm.getPageNum(), warehouseQueryForm.getPageSize());
        List<Warehouse> warehouseList = this.warehouseDao.queryOrByPojo(warehouseQueryForm);
        return new PageInfo<>(warehouseList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param warehouseQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Warehouse> queryByScreen(WarehouseQueryForm warehouseQueryForm) {
        Page<Warehouse> page = PageHelper.startPage(warehouseQueryForm.getPageNum(), warehouseQueryForm.getPageSize());
        List<Warehouse> warehouseList = this.warehouseDao.queryAndByPojo(warehouseQueryForm);
        return new PageInfo<>(warehouseList);
    }

    /**
     * 新增数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    @Override
    public Warehouse insert(Warehouse warehouse) {
        this.warehouseDao.insert(warehouse);
        return this.queryById(warehouse.getWarehouseId());
    }

    /**
     * 批量新增数据
     *
     * @param WarehouseList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Warehouse> WarehouseList) {
        return this.warehouseDao.insertBatch(WarehouseList) == -1;
    }

    /**
     * 修改数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    @Override
    public Warehouse update(Warehouse warehouse) {
        this.warehouseDao.update(warehouse);
        return this.queryById(warehouse.getWarehouseId());
    }

    /**
     * 批量修改数据
     *
     * @param warehouseList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Warehouse> warehouseList) {
        return this.warehouseDao.updateBatch(warehouseList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param warehouseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer warehouseId) {
        return this.warehouseDao.deleteById(warehouseId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.warehouseDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
