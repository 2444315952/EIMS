package com.eims.mybatis.entity;

import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (WarehouseWarrant)实体类
 *
 * @author makejava
 * @since 2021-06-08 09:48:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WarehouseWarrant implements Serializable {
    private static final long serialVersionUID = -42613448795635719L;

    private Integer warehouseWarrantId;

    private String warehouseDocunum;

    private Date documentDate;

    private Integer workPointId;

    private String workPointName;

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

    private List<WarehousingDetail> warehousingDetailList;
}
