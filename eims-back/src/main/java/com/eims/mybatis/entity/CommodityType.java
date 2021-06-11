package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (CommodityType)实体类
 *
 * @author makejava
 * @since 2021-06-11 21:24:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CommodityType implements Serializable {
    private static final long serialVersionUID = 680650457937232836L;

    private Integer commId;

    private String commTypeName;
}
