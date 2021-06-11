package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Customer)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:24:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Customer implements Serializable {
    private static final long serialVersionUID = 532020568021174046L;

    private Integer customerId;

    private String customerName;

    private String customerType;

    private String customerLevel;

    private String contact;

    private String contactAddress;

    private String contactNumber;

    private String contactEmaill;

    private Integer isEnabled;

    private String remark;

    private Integer companyId;
}
