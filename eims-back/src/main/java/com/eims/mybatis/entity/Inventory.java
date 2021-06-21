package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Inventory)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:25:30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Inventory implements Serializable {
    private static final long serialVersionUID = 993760056117052908L;

    private Integer inventoryId;

    private Integer companyId;

    private Integer warehouseId;

    private String warehouseName;

    private Integer productId;

    private String productName;

    private BigDecimal purchasePrice;

    private Integer inventory;

    private BigDecimal marketPrice;

    private BigDecimal totalCost;

    private Integer minimumStock;

    private Product product;
}
