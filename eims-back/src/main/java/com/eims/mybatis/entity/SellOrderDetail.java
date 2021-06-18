package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (SellOrderDetail)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:26:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellOrderDetail implements Serializable {
    private static final long serialVersionUID = -68143995582290830L;

    private Integer sellOrderDetailId;

    private Integer sellOrderId;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String productModel;

    private String productUnit;

    private BigDecimal sellPrice;

    private Integer sellQuantity;

    private BigDecimal orderDetailPayAmount;

    private BigDecimal orderDetailPaidAmount;

    private BigDecimal orderDetailDiscounts;

    private Integer returned;

    private SellOrderBill sellOrderBill;
}
