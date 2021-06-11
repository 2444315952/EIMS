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
 * (Purchase)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:25:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Purchase implements Serializable {
    private static final long serialVersionUID = -21710743490537465L;

    private Integer purchId;

    private Integer companyId;

    private Integer workPointId;

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
}
