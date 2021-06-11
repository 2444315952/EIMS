package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (ReturnBillsProduct)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:26:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReturnBillsProduct implements Serializable {
    private static final long serialVersionUID = 899972274095381478L;

    private Integer retBpId;

    private Integer sellReturnId;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String productModel;

    private String productUnit;

    private BigDecimal sellPrice;

    private Integer sellQuantity;

    private BigDecimal retBillPayAmount;

    private BigDecimal retBillPaidAmount;
}
