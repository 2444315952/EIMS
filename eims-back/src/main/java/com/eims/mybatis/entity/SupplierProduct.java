package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (SupplierProduct)实体类
 *
 * @author makejava
 * @since 2021-06-30 19:40:39
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SupplierProduct implements Serializable {
    private static final long serialVersionUID = 859112105761782341L;

    private Integer supplierProductId;

    private Integer supplierId;

    private Integer productId;

    private BigDecimal productPrice;
}
