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
public class PurchaseDetailQueryForm implements Serializable {
    private static final long serialVersionUID = 559545131757694124L;

    private Integer purchDetailId;

    private Integer purchId;

    private String purchDocunum;

    private Integer productId;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal purchasePrice;

    private Integer purchaseQuantity;

    private BigDecimal purchaseSubtotal;

    private Integer returned;

    private Integer paid;

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
