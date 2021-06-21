package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (SellDetail)实体类
 *
 * @author makejava
 * @since 2021-06-15 16:30:21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellDetail implements Serializable {
    private static final long serialVersionUID = 260825914093542422L;

    private Integer sellDetailId;

    private Integer sellId;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String productModel;

    private String productUnit;

    private BigDecimal marketPrice;

    private BigDecimal sellPrice;

    private Integer sellQuantity;

    private BigDecimal detailPayAmount;

    private BigDecimal detailPaidAmount;

    private BigDecimal detailDiscounts;

    private Integer returned;
}
