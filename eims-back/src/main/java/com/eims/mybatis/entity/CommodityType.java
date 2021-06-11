package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (CommodityType)实体类
 *
 * @author makejava
 * @since 2021-06-08 13:45:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CommodityType implements Serializable {
    private static final long serialVersionUID = 371730293784637265L;

    private Integer commId;

    private String commTypeName;

    private List<Product> products;
}
