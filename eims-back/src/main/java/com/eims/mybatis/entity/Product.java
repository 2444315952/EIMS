package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:25:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Product implements Serializable {
    private static final long serialVersionUID = -89997552698457092L;

    private Integer productId;

    private String productNumber;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal referCost;

    private BigDecimal marketPrice;

    private Integer commId;

    private Integer minimumStock;

    private Integer isEnabled;

    private String remake;

    private Integer companyId;
}
