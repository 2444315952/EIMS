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
public class ReturnBillsProductQueryForm implements Serializable {
    private static final long serialVersionUID = 853952562868782037L;

    private Integer retBpId;

    private Integer sellReturnId;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String productModel;

    private String productUnit;

    private BigDecimal sellPrice;

    private Integer sellQuantity;

    private BigDecimal retBillPayAmount;

    private BigDecimal retBillPaidAmount;

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
