package com.eims.vo.form;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BaldetailQueryForm implements Serializable {
    private static final long serialVersionUID = -80747446602261652L;

    private Integer baldetailId;

    private Integer blitemId;

    private String blitemDocunum;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String specModel;

    private String productUnit;

    private BigDecimal purchasePrice;

    private BigDecimal totalCost;

    private Integer inventoryQuantity;

    private Integer physicalInventory;

    private BigDecimal physicalAmount;

    private Integer quantityVariance;

    private BigDecimal differenceAmount;

    private String commodityNote;

    private Integer pageNum;    //当前页
    private Integer pageSize;   //页大小

    public Integer getPageNum() {
        if (pageNum == null)
            return 1;
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null)
            return 0;
        return pageSize;
    }
}
