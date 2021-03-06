package com.eims.vo.form;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WarehouseWarrantQueryForm implements Serializable {
    private static final long serialVersionUID = 840507691053095819L;

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
