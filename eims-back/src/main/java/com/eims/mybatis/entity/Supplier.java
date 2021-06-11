package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Supplier)实体类
 *
 * @author makejava
 * @since 2021-06-04 18:20:01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Supplier implements Serializable {
    private static final long serialVersionUID = 262502589937471234L;

    private Integer supplierId;

    private String supplierName;

    private String supplierType;

    private String contact;

    private String contactNumber;

    private String contactAddress;

    private Integer isEnabled;

    private String remark;

    private Integer workPointId;

    private String workPointName;
}
