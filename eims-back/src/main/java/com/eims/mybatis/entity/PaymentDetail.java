package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (PaymentDetail)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:25:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PaymentDetail implements Serializable {
    private static final long serialVersionUID = -34962535330072142L;

    private Integer payDetailId;

    private Integer paymentId;

    private String payDocunum;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal paymentPrice;

    private Integer paymentQuantity;

    private BigDecimal paymentSubtotal;
}
