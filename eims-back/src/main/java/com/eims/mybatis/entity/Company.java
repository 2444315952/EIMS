package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Company)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:24:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Company implements Serializable {
    private static final long serialVersionUID = -49029247477266056L;

    private Integer companyId;

    private String companyName;

    private String contactAddress;

    private String contactNumber;

    private String contactEmail;

    private Integer employeeId;

    private String employeeName;
}
