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
 * @since 2021-06-08 13:46:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Customer implements Serializable {
    private static final long serialVersionUID = -66665677501551859L;

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

    private Integer workPointId;

    private String workPointName;
}
