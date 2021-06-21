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
public class SellBillQueryForm implements Serializable {
    private static final long serialVersionUID = 399854273831313422L;

    private Integer sellId;

    private Integer companyId;

    private Integer workPointId;

    private String sellDocunum;

    private String billsType;

    private Date sellDate;

    private Integer audited;

    private String paymentTerm;

    private Integer employeeId;

    private String employeeName;

    private Integer customerId;

    private String customerName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal billPayAmount;

    private BigDecimal billPaidAmount;

    private BigDecimal sellDiscounts;

    private String sellRemark;

    private Integer returnState;

    private Integer isRetail;

    private Integer received;

    private String sellAddress;

    private Date sellHirthday;

    private Integer outStorage;

    private Integer outStorage;

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
