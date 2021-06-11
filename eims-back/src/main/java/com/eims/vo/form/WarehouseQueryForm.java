package com.eims.vo.form;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WarehouseQueryForm implements Serializable {
    private static final long serialVersionUID = -33075736779200279L;

    private Integer warehouseId;

    private String warehouseName;

    private Integer companyId;

    private Integer employeeId;

    private String employeeName;

    private String phone;

    private String warehouseAddress;

    private Integer defaultWarehouse;

    private Integer state;

    private String remark;

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
