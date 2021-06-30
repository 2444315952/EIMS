package com.eims.mybatis.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * (GatherDetail)实体类
 *
 * @author makejava
 * @since 2021-06-25 22:42:18
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class GatherDetail implements Serializable {
    private static final long serialVersionUID = -82590231341516111L;

    private Integer gatherDetailId;

    private Integer gatherId;

    private String gatherDocunum;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal gatherPrice;

    private Integer gatherQuantity;

    private BigDecimal gatherSubtotal;
}
