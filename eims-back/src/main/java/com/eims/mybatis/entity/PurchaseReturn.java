package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (PurchaseReturn)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:25:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PurchaseReturn implements Serializable {
    private static final long serialVersionUID = -73569023166102862L;

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

    private Integer inRefund;

    private Integer warehouseId;

    private String warehouseName;

    private Integer audited;

    private BigDecimal transactionAmount;

    private BigDecimal refundAmount;

    private Integer isOnline;
}
