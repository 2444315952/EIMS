package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (WorkPoint)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:27:25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WorkPoint implements Serializable {
    private static final long serialVersionUID = 511064379826483434L;

    private Integer workPointId;

    private String workPointName;

    private String contactAddress;

    private String contactNumber;

    private String contactEmail;

    private Integer employeeId;

    private String employeeName;

    private Integer companyId;

    private String companyName;
}
