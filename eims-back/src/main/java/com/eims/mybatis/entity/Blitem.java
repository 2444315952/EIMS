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
 * (Blitem)实体类
 *
 * @author makejava
 * @since 2021-06-16 10:32:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Blitem implements Serializable {
    private static final long serialVersionUID = -39045823154767900L;

    private Integer blitemId;

    private String blitemDocunum;

    private Date documentDate;

    private Integer companyId;

    private Integer workPointId;

    private Integer employeeId;

    private String employeeName;

    private Integer warehouseId;

    private String warehouseName;

    private Integer audited;

    private String reason;

    private String documentsNote;

    private List<Baldetail> baldetailList;
}
