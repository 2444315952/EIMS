package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (PurchaseReturnDetail)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:25:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PurchaseReturnDetail implements Serializable {
    private static final long serialVersionUID = 305505788704420575L;

    private Integer purchReturnDetailId;

    private Integer purchReturnId;

    private String purchReturnDocunum;

    private Integer purchDetailId;

    private Integer productId;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal purchasePrice;

    private Integer purchaseQuantity;

    private BigDecimal purchaseSubtotal;

    private Integer returned;
}
