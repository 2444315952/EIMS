package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (SellReturn)实体类
 *
 * @author makejava
 * @since 2021-06-12 10:28:42
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellReturn implements Serializable {
    private static final long serialVersionUID = -29578549145302434L;

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

    private String sellDocunum;

    private Integer customerId;

    private String customerName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal retPayAmount;

    private BigDecimal retPaidAmount;

    private String retSellRemark;

    private Integer refunded;

    private Integer inStorage;

    private Date sellReturnHirthday;

    private List<ReturnBillsProduct> returnBillsProductList;

}
