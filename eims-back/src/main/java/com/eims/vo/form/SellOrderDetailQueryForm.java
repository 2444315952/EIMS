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
public class SellOrderDetailQueryForm implements Serializable {
    private static final long serialVersionUID = -70599544473943640L;

    private Integer sellOrderDetailId;

    private Integer sellOrderId;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String productModel;

    private String productUnit;

    private BigDecimal sellPrice;

    private Integer sellQuantity;

    private BigDecimal orderDetailPayAmount;

    private BigDecimal orderDetailPaidAmount;

    private BigDecimal orderDetailDiscounts;

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
