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
    private static final long serialVersionUID = -91153354029915708L;

    private Integer purchReturnId;

    private Integer companyId;

    private Integer workPointId;

    private String purchReturnDocunum;

    private Integer purchId;

    private String purchDocunum;

    private Date documentDate;

    private Integer supplierId;

    private String supplierName;

    private Integer employeeId;

    private String employeeName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal transactionAmount;

    private BigDecimal refundAmount;

    private Integer audited;

    private Integer inRefund;

    private Integer isOut;

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
