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
public class PaymentDetailQueryForm implements Serializable {
    private static final long serialVersionUID = 148509979541631562L;

    private Integer payDetailId;

    private Integer paymentId;

    private String payDocunum;

    private String productName;

    private String productPicture;

    private String specModel;

    private String productUnit;

    private BigDecimal paymentPrice;

    private Integer paymentQuantity;

    private BigDecimal paymentSubtotal;

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
