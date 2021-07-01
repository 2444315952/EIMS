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
public class InventoryQueryForm implements Serializable {
    private static final long serialVersionUID = 608460021531409783L;

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
