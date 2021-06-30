package com.eims;

import com.eims.mybatis.dao.StockOutDao;
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
    private StockOutDao stockOutDao;
    public void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        String stockOutDocunum="CKD-"+date+"-"+"";
        System.out.println("出库单信息+++++++++++");
        StockOutQueryForm stockOutQueryForm = new StockOutQueryForm();
        stockOutQueryForm.setStockOutDocunum(stockOutDocunum);
        List<StockOut> stockOuts = this.stockOutDao.queryOrByPojo(stockOutQueryForm);
        int size = stockOuts.size();
        String aa=String.format("%05d",size);
        System.out.println(aa+"补0");
        System.out.println(stockOutDocunum);
    }

    @Test
    public void test1(){

        //return stockOutDocunum;
    }
}
