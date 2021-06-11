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
public class PurchaseQueryForm implements Serializable {
    private static final long serialVersionUID = -68202022466670926L;

    private Integer purchId;

    private Integer workPointId;

    private String workPointName;

    private String purchDocunum;

    private Date documentDate;

    private Integer supplierId;

    private String supplierName;

    private Integer employeeId;

    private String employeeName;

    private Integer inStorage;

    private Integer warehouseId;

    private String warehouseName;

    private Integer audited;

    private Integer paymentStatus;

    private Integer returnState;

    private BigDecimal transactionAmount;

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
