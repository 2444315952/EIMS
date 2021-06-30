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
 * (SellBill)实体类
 *
 * @author makejava
 * @since 2021-06-15 23:54:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellBill implements Serializable {
    private static final long serialVersionUID = -16668585167695035L;

    private Integer sellId;

    private Integer companyId;

    private Integer workPointId;

    private String sellDocunum;

    private String billsType;

    private Date sellDate;

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

    private Integer outStorage;

    private List<SellDetail> sellDetailList;

    private Date sellHirthday;

    private List<SellDetail> sellDetails;

    private Integer sellOrderId;

    private BigDecimal receivedMoney;

}
