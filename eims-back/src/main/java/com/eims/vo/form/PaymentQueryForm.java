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
public class PaymentQueryForm implements Serializable {
    private static final long serialVersionUID = 114195602165644079L;

    private Integer payId;

    private Integer companyId;

    private Integer workPointId;

    private String payDocunum;

    private Date documentDate;

    private Integer purchId;

    private String purchDocunum;

    private Integer supplierId;

    private String supplierName;

    private Integer employeeId;

    private String employeeName;

    private BigDecimal paymentAmount;

    private String remark;

    private Integer audited;

    private String clearingForm;

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
