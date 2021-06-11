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
 * @since 2021-06-11 21:26:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellDetail implements Serializable {
    private static final long serialVersionUID = -88732530574934580L;

    private Integer sellDetailId;

    private Integer sellId;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String productModel;

    private String productUnit;

    private BigDecimal sellPrice;

    private Integer sellQuantity;

    private BigDecimal detailPayAmount;

    private BigDecimal detailPaidAmount;

    private BigDecimal detail_Discounts;

    private Integer returned;
}
