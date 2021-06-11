package com.eims.vo.form;

import java.math.BigDecimal;
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
public class SellReturnQueryForm implements Serializable {
    private static final long serialVersionUID = -98760550896267950L;

    private Integer sellReturnId;

    private Integer companyId;

    private Integer workPointId;

    private String sellReturnDocunum;

    private Date documentDate;

    private Integer autited;

    private String returnPaymentTerm;

    private Integer employeeId;

    private String employeeName;

    private Integer sellId;

    private Integer customerId;

    private String customerName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal retPayAmount;

    private BigDecimal retPaidAmount;

    private String retSellRemark;

    private Integer refunded;

    private Date sellReturnHirthday;

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
