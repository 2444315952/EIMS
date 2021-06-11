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
 * @since 2021-06-08 09:48:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WarehousingDetail implements Serializable {
    private static final long serialVersionUID = -50344989476490405L;

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
}
