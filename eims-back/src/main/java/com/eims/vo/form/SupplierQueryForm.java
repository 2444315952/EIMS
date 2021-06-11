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
public class SupplierQueryForm implements Serializable {
    private static final long serialVersionUID = -36093210348638969L;

    private Integer supplierId;

    private String supplierName;

    private String supplierType;

    private String contact;

    private String contactNumber;

    private String contactAddress;

    private Integer isEnabled;

    private String remark;

    private Integer workPointId;

    private String workPointName;

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
