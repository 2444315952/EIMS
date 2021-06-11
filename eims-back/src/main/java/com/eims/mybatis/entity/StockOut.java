package com.eims.mybatis.entity;

import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (StockOut)实体类
 *
 * @author makejava
 * @since 2021-06-08 09:48:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StockOut implements Serializable {
    private static final long serialVersionUID = 847702488348017038L;

    private Integer stockOutId;

    private String stockOutDocunum;

    private Date documentDate;

    private Integer workPointId;

    private String workPointName;

    private Integer employeeId;

    private String employeeName;

    private Integer audited;

    private Integer warehouseId;

    private String warehouseName;

    private String outboundType;

    private Integer purchReturnId;

    private Integer saleId;

    private Integer transferOrderId;

    private Integer blitemId;

    private Integer inventoryQuantity;

    private String documentsNote;

    private List<OutboundDetail> outboundDetailList;
}
