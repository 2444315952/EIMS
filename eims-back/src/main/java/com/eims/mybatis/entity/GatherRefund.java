package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (GatherRefund)实体类
 *
 * @author makejava
 * @since 2021-06-30 19:36:15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class GatherRefund implements Serializable {
    private static final long serialVersionUID = 794244537760959632L;

    private Integer gatherRefundId;

    private Integer companyId;

    private Integer workPointId;

    private String gatherRefundDocunum;

    private Date documentDate;

    private Integer purchId;

    private String purchDocunum;

    private Integer supplierId;

    private String supplierName;

    private Integer employeeId;

    private String employeeName;

    private BigDecimal gatherAmount;

    private String remark;

    private Integer audited;

    private String clearingForm;

    private List<GatherRefundDetail> gatherRefundDetailList;
}
