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
 * (SellReturn)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:26:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellReturn implements Serializable {
    private static final long serialVersionUID = -12709186544419951L;

    private Integer sellReturnId;

    private Integer companyId;

    private Integer workPointId;

    private String sellReturnDocunum;

    private Date documentDate;

    private Integer autited;

    private String returnPaymentTerm;

    private Integer employeeId;

    private String employeeName;

    private Integer sellId;

    private Integer customerId;

    private String customerName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal retPayAmount;

    private BigDecimal retPaidAmount;

    private String retSellRemark;

    private Integer refunded;

    private Date sellReturnHirthday;
}
