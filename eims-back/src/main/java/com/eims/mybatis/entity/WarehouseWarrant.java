package com.eims.mybatis.entity;

import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (WarehouseWarrant)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:27:23
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WarehouseWarrant implements Serializable {
    private static final long serialVersionUID = 623303657524880377L;

    private Integer warehouseWarrantId;

    private String warehouseDocunum;

    private Date documentDate;

    private Integer companyId;

    private Integer workPointId;

    private Integer employeeId;

    private String employeeName;

    private Integer audited;

    private Integer warehouseId;

    private String warehouseName;

    private String storageType;

    private Integer purchId;

    private Integer saleReturnId;

    private Integer transferOrderId;

    private Integer blitemId;

    private Integer inventoryQuantity;

    private String documentsNote;
}
