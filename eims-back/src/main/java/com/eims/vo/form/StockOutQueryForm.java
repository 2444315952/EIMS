package com.eims.vo.form;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StockOutQueryForm implements Serializable {
    private static final long serialVersionUID = 391721129107337492L;

    private Integer stockOutId;

    private String stockOutDocunum;

    private Date documentDate;

    private Integer companyId;

    private Integer workPointId;

    private Integer employeeId;

    private String employeeName;

    private Integer audited;

    private Integer warehouseId;

    private String warehouseName;

    private String outboundType;

    private Integer purchReturnId;

    private Integer saleId;

    private Integer transferOrderId;

    private Integer blitemId;

    private Integer inventoryQuantity;

    private String documentsNote;

    private Integer pageNum;    //当前页
    private Integer pageSize;   //页大小

    public Integer getPageNum() {
        if (pageNum == null)
            return 1;
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null)
            return 0;
        return pageSize;
    }
}
