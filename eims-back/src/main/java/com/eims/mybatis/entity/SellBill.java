package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (SellBill)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:26:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellBill implements Serializable {
    private static final long serialVersionUID = -21198404183944975L;

    private Integer sellId;

    private Integer companyId;

    private Integer workPointId;

    private String sellDocunum;

    private String billsType;

    private Object sellDate;

    private Integer audited;

    private String paymentTerm;

    private Integer employeeId;

    private String employeeName;

    private Integer customerId;

    private String customerName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal billPayAmount;

    private BigDecimal billPaidAmount;

    private BigDecimal sellDiscounts;

    private String sellRemark;

    private Integer returnState;

    private Integer isRetail;

    private Integer received;

    private String sellAddress;

    private Object sellHirthday;
}
