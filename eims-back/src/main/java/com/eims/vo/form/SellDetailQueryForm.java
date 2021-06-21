package com.eims.vo.form;

import java.math.BigDecimal;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SellDetailQueryForm implements Serializable {
    private static final long serialVersionUID = -79562653794784244L;

    private Integer sellDetailId;

    private Integer sellId;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String productModel;

    private String productUnit;

    private BigDecimal marketPrice;

    private BigDecimal sellPrice;

    private Integer sellQuantity;

    private BigDecimal detailPayAmount;

    private BigDecimal detailPaidAmount;

    private BigDecimal detailDiscounts;

    private Integer returned;

    private Integer pageNum;    //当前页
    private Integer pageSize;   //页大小

    public Integer getPageNum() {
        if (pageNum == null)
            return 1;
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null)
            return 0;
        return pageSize;
    }
}
