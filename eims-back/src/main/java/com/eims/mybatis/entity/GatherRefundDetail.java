package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (GatherRefundDetail)实体类
 *
 * @author makejava
 * @since 2021-06-30 19:36:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class GatherRefundDetail implements Serializable {
    private static final long serialVersionUID = 140157670864333616L;

    private Integer gatherRefundDetailId;

    private Integer gatherRefundId;

    private String gatherRefundDocunum;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal gatherPrice;

    private Integer gatherQuantity;

    private BigDecimal gatherSubtotal;
}
