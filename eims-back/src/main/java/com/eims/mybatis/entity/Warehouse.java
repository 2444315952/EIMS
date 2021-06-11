package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (Warehouse)实体类
 *
 * @author makejava
 * @since 2021-06-04 18:20:02
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Warehouse implements Serializable {
    private static final long serialVersionUID = -37476911742302263L;

    private Integer warehouseId;

    private String warehouseName;

    private Integer workPointId;

    private String workPointName;

    private Integer employeeId;

    private String employeeName;

    private String phone;

    private String warehouseAddress;

    private Integer defaultWarehouse;

    private Integer state;

    private String remark;
}
