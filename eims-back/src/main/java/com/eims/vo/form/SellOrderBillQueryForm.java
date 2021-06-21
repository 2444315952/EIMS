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
public class SellOrderBillQueryForm implements Serializable {
    private static final long serialVersionUID = 163706239401956171L;

    private Integer sellOrderId;

    private Integer companyId;

    private Integer workPointId;

    private String sellOrderDocunum;

    private Date sellOrderDate;

    private Integer audited;

    private Integer employeeId;

    private String employeeName;

    private Integer customerId;

    private String customerName;

    private Integer warehouseId;

    private String warehouseName;

    private BigDecimal orderPayAmount;

    private BigDecimal orderPaidAmount;

    private BigDecimal orderSellDiscounts;

    private String sellOrderRemark;

    private String sellOrderAddress;

    private Date sellOrderHirthday;

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
