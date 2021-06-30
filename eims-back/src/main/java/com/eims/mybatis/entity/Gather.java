package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Gather)实体类
 *
 * @author makejava
 * @since 2021-06-28 23:13:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Gather implements Serializable {
    private static final long serialVersionUID = -40589755172096595L;

    private Integer gatherId;

    private Integer companyId;

    private Integer workPointId;

    private String gatherDocunum;

    private Date documentDate;

    private Integer sellId;

    private String sellDocunum;

    private Integer customerId;

    private String customerName;

    private Integer employeeId;

    private String employeeName;

    private BigDecimal gatherAmount;

    private String remark;

    private Integer audited;

    private Integer moneyAccountId;

    private String accountName;

    private Date gatherBirthday;

    private Integer sellType;
}
