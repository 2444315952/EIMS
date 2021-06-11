package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (OutboundDetail)实体类
 *
 * @author makejava
 * @since 2021-06-08 09:47:42
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OutboundDetail implements Serializable {
    private static final long serialVersionUID = 610795005462477680L;

    private Integer outboundDetailId;

    private Integer stockOutId;

    private String stockOutDocunum;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String specModel;

    private String productUnit;

    private Integer deliveryQuantity;

    private String commodityNote;
}
