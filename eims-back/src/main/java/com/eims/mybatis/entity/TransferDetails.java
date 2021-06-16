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
 * @since 2021-06-16 10:35:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TransferDetails implements Serializable {
    private static final long serialVersionUID = -46571860870952259L;

    private Integer transferDetailId;

    private Integer transferId;

    private String transferDocunum;

    private Integer productId;

    private String productName;

    private String specModel;

    private String productUnit;

    private Integer availableStock;

    private Integer outboundQuantity;

    private String commodityNote;
}
