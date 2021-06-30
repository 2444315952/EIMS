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

    private Integer transferId;

    private Integer blitemId;

    private Integer inventoryQuantity;

    private String reason;

    private String documentsNote;

    private List<WarehousingDetail> warehousingDetailList;


    public WarehouseWarrant(String warehouseDocunum, Date documentDate, Integer companyId, Integer workPointId, Integer employeeId, String employeeName, Integer audited, Integer warehouseId, String warehouseName, String storageType, Integer transferId) {
        this.warehouseDocunum = warehouseDocunum;
        this.documentDate = documentDate;
        this.companyId = companyId;
        this.workPointId = workPointId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.audited = audited;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.storageType = storageType;
        this.transferId = transferId;
//        this.inventoryQuantity = inventoryQuantity;
    }

//    public WarehouseWarrant(String warehouseDocunum, Date documentDate, Integer companyId, Integer workPointId, Integer employeeId, String employeeName, Integer audited, Integer warehouseId, String warehouseName, String storageType, Integer blitemId) {
//        this.warehouseDocunum = warehouseDocunum;
//        this.documentDate = documentDate;
//        this.companyId = companyId;
//        this.workPointId = workPointId;
//        this.employeeId = employeeId;
//        this.employeeName = employeeName;
//        this.audited = audited;
//        this.warehouseId = warehouseId;
//        this.warehouseName = warehouseName;
//        this.storageType = storageType;
//        this.blitemId = blitemId;
//    }


}
