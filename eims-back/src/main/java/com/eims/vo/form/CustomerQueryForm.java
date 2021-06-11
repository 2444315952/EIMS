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
public class CustomerQueryForm implements Serializable {
    private static final long serialVersionUID = -40788327199376577L;

    private Integer customerId;

    private String customerName;

    private String customerType;

    private String customerLevel;

    private String contact;

    private String contactAddress;

    private String contactNumber;

    private String contactEmaill;

    private Integer isEnabled;

    private String remark;

    private Integer companyId;

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
