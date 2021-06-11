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
 * @since 2021-06-11 21:25:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OutboundDetail implements Serializable {
    private static final long serialVersionUID = -15646642819201222L;

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
