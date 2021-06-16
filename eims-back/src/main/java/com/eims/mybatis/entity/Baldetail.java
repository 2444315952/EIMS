package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Baldetail)实体类
 *
 * @author makejava
 * @since 2021-06-16 10:34:37
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Baldetail implements Serializable {
    private static final long serialVersionUID = 533288008183727482L;

    private Integer baldetailId;

    private Integer blitemId;

    private String blitemDocunum;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String specModel;

    private String productUnit;

    private BigDecimal purchasePrice;

    private BigDecimal totalCost;

    private Integer inventoryQuantity;

    private Integer physicalInventory;

    private BigDecimal physicalAmount;

    private Integer quantityVariance;

    private BigDecimal differenceAmount;

    private String commodityNote;
}
