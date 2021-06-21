package com.eims.service.impl;

import com.eims.mybatis.entity.Inventory;
import com.eims.mybatis.entity.Product;
import com.eims.vo.form.InventoryQueryForm;
import com.eims.mybatis.dao.InventoryDao;
import com.eims.service.InventoryService;
import com.eims.vo.table.InventoryTable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (Inventory)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 21:25:30
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
    @Resource
    private InventoryDao inventoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param inventoryId 主键
     * @return 实例对象
     */
    @Override
    public Inventory queryById(Integer inventoryId) {
        return this.inventoryDao.queryById(inventoryId);
    }


    /**
     * 查询所有库存信息
     * @param inventoryQueryForm
     * @return
     */
    @Override
    public PageInfo<InventoryTable> queryInventoryVo(InventoryQueryForm inventoryQueryForm) {
        Page<InventoryTable> page=PageHelper.startPage(inventoryQueryForm.getPageNum(),inventoryQueryForm.getPageSize());
        List<InventoryTable> inventoryList=this.inventoryDao.queryInventoryVo(inventoryQueryForm);
        return new PageInfo<>(inventoryList);
    }

    /**
     * 查询所有数据
     *
     * @param inventoryQueryForm 实例对象
     * @return 实例对象
     */
    @Override
    public PageInfo<Inventory> queryAll(InventoryQueryForm inventoryQueryForm) {
        Page<Inventory> page = PageHelper.startPage(inventoryQueryForm.getPageNum(), inventoryQueryForm.getPageSize());
        List<Inventory> inventoryList = this.inventoryDao.queryAll(inventoryQueryForm);
        return new PageInfo<>(inventoryList);
    }

    /**
     * 根据查询条件搜索数据
     *
     * @param inventoryQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Inventory> queryBySearch(InventoryQueryForm inventoryQueryForm) {
        Page<Inventory> page = PageHelper.startPage(inventoryQueryForm.getPageNum(), inventoryQueryForm.getPageSize());
        List<Inventory> inventoryList = this.inventoryDao.queryOrByPojo(inventoryQueryForm);
        return new PageInfo<>(inventoryList);
    }

    /**
     * 根据查询条件筛选数据
     *
     * @param inventoryQueryForm
     * @return 对象列表
     */
    @Override
    public PageInfo<Inventory> queryByScreen(InventoryQueryForm inventoryQueryForm) {
        Page<Inventory> page = PageHelper.startPage(inventoryQueryForm.getPageNum(), inventoryQueryForm.getPageSize());
        List<Inventory> inventoryList = this.inventoryDao.queryAndByPojo(inventoryQueryForm);
        return new PageInfo<>(inventoryList);
    }

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @Override
    public Inventory insert(Inventory inventory) {
        this.inventoryDao.insert(inventory);
        return this.queryById(inventory.getInventoryId());
    }

    /**
     * 批量新增数据
     *
     * @param InventoryList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean insertBatch(List<Inventory> InventoryList) {
        return this.inventoryDao.insertBatch(InventoryList) == -1;
    }

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @Override
    public Inventory update(Inventory inventory) {
        this.inventoryDao.update(inventory);
        return this.queryById(inventory.getInventoryId());
    }


    /**
     * 批量修改数据
     *
     * @param inventoryList 实例对象列表
     * @return 影响行数
     */
    @Override
    public boolean updateBatch(List<Inventory> inventoryList) {
        return this.inventoryDao.updateBatch(inventoryList) == -1;
    }

    /**
     * 通过主键删除数据
     *
     * @param inventoryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer inventoryId) {
        return this.inventoryDao.deleteById(inventoryId) > 0;
    }

    /**
     * 批量删除数据
     *
     * @param ids 主键列表
     * @return 影响行数
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        int row = this.inventoryDao.deleteBatch(ids);
        return ids.size() == row;
    }
}
