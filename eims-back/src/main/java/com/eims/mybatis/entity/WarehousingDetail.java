package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (WarehousingDetail)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:27:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WarehousingDetail implements Serializable {
    private static final long serialVersionUID = -22864983386125198L;

    private Integer warehousingDetailId;

    private Integer warehouseWarrantId;

    private String warehouseDocunum;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String specModel;

    private String productUnit;

    private Integer inventoryQuantity;

    private String commodityNote;

    public WarehousingDetail(Integer warehouseWarrantId, String warehouseDocunum, Integer productId, String productPicture, String productName, String specModel, String productUnit, Integer inventoryQuantity) {
        this.warehouseWarrantId = warehouseWarrantId;
        this.warehouseDocunum = warehouseDocunum;
        this.productId = productId;
        this.productPicture = productPicture;
        this.productName = productName;
        this.specModel = specModel;
        this.productUnit = productUnit;
        this.inventoryQuantity = inventoryQuantity;
    }
}
