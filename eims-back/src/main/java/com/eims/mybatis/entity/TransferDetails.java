package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (TransferDetails)实体类
 *
 * @author makejava
 * @since 2021-06-16 23:18:23
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TransferDetails implements Serializable {
    private static final long serialVersionUID = -39105314241844604L;

    private Integer transferDetailId;

    private Integer transferId;

    private String transferDocunum;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String specModel;

    private String productUnit;

    private Integer inventory;

    private Integer outboundQuantity;

    private String commodityNote;
}
