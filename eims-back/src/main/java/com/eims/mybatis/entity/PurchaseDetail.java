package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (PurchaseDetail)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:25:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PurchaseDetail implements Serializable {
    private static final long serialVersionUID = 240694043322578986L;

    private Integer purchDetailId;

    private Integer purchId;

    private String purchDocunum;

    private Integer productId;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal purchasePrice;

    private Integer purchaseQuantity;

    private BigDecimal purchaseSubtotal;

    private Integer returned;

    private Integer paid;
}
