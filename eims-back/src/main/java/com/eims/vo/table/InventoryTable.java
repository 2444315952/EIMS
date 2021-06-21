package com.eims.vo.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 智能补货列表vo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class InventoryTable {

    private static final long serialVersionUID = 993760056117052908L;

    private Integer inventoryId;

    private Integer companyId;

    private Integer warehouseId;

    private String warehouseName;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String specModel;

    private String productUnit;

    private BigDecimal purchasePrice;

    private Integer inventory;

    private BigDecimal marketPrice;

    private BigDecimal totalCost;

    private Integer minimumStock;

    private Integer sellOnOrder;    //销售在订量

    private Integer purchOnOrder;   //采购在订量

    private Integer recommendQuantity;  //建议采购量

    public Integer getSellOnOrder() {
        if(this.sellOnOrder==null)
            return 0;
        return sellOnOrder;
    }

    public Integer getPurchOnOrder() {
        if(this.purchOnOrder==null)
            return 0;
        return purchOnOrder;
    }

}
