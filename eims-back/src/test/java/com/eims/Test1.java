package com.eims;

import com.eims.mybatis.dao.InventoryDao;
import com.eims.mybatis.dao.StockOutDao;
import com.eims.mybatis.entity.Inventory;
import com.eims.mybatis.entity.StockOut;
import com.eims.vo.form.StockOutQueryForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@SpringBootTest
public class Test1 {
    private InventoryDao inventoryDao;
    public void main(String[] args) {

    }

    @Test
    public void test1(){
        Inventory inventory = this.inventoryDao.queryProduct(10, 100, 100);
        System.out.println("新增库存信息为：");
        System.out.println(inventory);
        //return stockOutDocunum;
    }
}
