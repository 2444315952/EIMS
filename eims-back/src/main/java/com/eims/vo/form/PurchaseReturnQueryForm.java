package com.eims.vo.form;

import java.math.BigDecimal;
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
public class PurchaseReturnQueryForm implements Serializable {
    private static final long serialVersionUID = 181355701963242978L;

    private Integer purchReturnId;

    private Integer workPointId;

    private String workPointName;

    private String purchReturnDocunum;

    private Integer purchId;

    private String purchDocunum;

    private Date documentDate;

    private Integer supplierId;

    private String supplierName;

    private Integer employeeId;

    private String employeeName;

    private Integer inRefund;

    private Integer warehouseId;

    private String warehouseName;

    private Integer audited;

    private BigDecimal transactionAmount;

    private BigDecimal refundAmount;

    private Integer isOnline;

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
