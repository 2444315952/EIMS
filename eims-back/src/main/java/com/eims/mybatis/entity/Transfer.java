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
 * (Transfer)实体类
 *
 * @author makejava
 * @since 2021-06-16 10:35:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Transfer implements Serializable {
    private static final long serialVersionUID = -69046216943385519L;

    private Integer transferId;

    private String transferDocunum;

    private Date documentDate;

    private Integer companyId;

    private Integer workPointId;

    private Integer employeeId;

    private String employeeName;

    private Integer foldWarehouseId;

    private String foldWarehouseName;

    private Integer exportWarehouseId;

    private String exportWarehouseName;

    private Integer audited;

    private Integer transferState;

    private String documentsNote;

    private List<TransferDetails> transferDetailsList;
}
