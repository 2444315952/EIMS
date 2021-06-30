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
 * (SellOrderBill)实体类
 *
 * @author makejava
 * @since 2021-06-20 21:16:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellOrderBill implements Serializable {
    private static final long serialVersionUID = -73577319084832813L;

    private Integer sellOrderId;

    private Integer companyId;

    private Integer workPointId;

    private String sellOrderDocunum;

    private Date sellOrderDate;

    private Integer audited;

    private Integer employeeId;

    private String employeeName;

    private Integer customerId;

    private String customerName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal orderPayAmount;

    private BigDecimal orderPaidAmount;

    private BigDecimal orderSellDiscounts;

    private String sellOrderRemark;

    private String sellOrderAddress;

    private Date sellOrderHirthday;

    private List<SellOrderDetail> sellOrderDetailList;
}
