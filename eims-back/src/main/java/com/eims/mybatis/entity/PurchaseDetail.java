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
 * @since 2021-06-04 18:19:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PurchaseDetail implements Serializable {
    private static final long serialVersionUID = 857422009608760642L;

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
