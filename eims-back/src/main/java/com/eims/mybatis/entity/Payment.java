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
 * (Payment)实体类
 *
 * @author makejava
 * @since 2021-06-08 11:58:17
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Payment implements Serializable {
    private static final long serialVersionUID = 282463425291545890L;

    private Integer payId;

    private Integer workPointId;

    private String workPointName;

    private String payDocunum;

    private Date documentDate;

    private Integer purchId;

    private String purchDocunum;

    private Integer supplierId;

    private String supplierName;

    private Integer employeeId;

    private String employeeName;

    private BigDecimal paymentAmount;

    private String remark;

    private Integer audited;

    private String clearingForm;
}
