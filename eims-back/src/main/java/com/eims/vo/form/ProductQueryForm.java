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
public class ProductQueryForm implements Serializable {
    private static final long serialVersionUID = 141610055551882790L;

    private Integer productId;

    private String productNumber;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal referCost;

    private BigDecimal marketPrice;

    private Integer commId;

    private Integer minimumStock;

    private Integer isEnabled;

    private String remake;

    private Integer companyId;

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
