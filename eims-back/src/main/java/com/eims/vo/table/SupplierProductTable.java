package com.eims.vo.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class SupplierProductTable {
    private Integer supplierProductId;
    private Integer supplierId;
    private Integer productId;
    private String productPrice;
    private String productName;
    private String specModel;
    private String productUnit;
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
