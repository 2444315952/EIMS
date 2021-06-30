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
 * (MoneyAccount)实体类
 *
 * @author makejava
 * @since 2021-06-28 10:43:13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MoneyAccount implements Serializable {
    private static final long serialVersionUID = 601771052161868440L;

    private Integer moneyAccountId;

    private String accountName;

    private BigDecimal accountMoney;

    private BigDecimal accountBeginMoney;

    private Date accountBirthday;

    private Integer isEnabled;

    private Integer companyId;

    private Integer workPointId;

    private String workPointName;

    private String remark;
}
