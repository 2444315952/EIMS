package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Employee)实体类
 *
 * @author makejava
 * @since 2021-06-04 18:19:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Employee implements Serializable {
    private static final long serialVersionUID = -45958457098339918L;

    private Integer employeeId;

    private String employeeName;

    private String employeePhone;

    private String employeePwd;

    private Integer onJob;

    private Integer workPointId;

    private String workPointName;
}
