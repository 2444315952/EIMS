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
public class GatherRefundDetailQueryForm implements Serializable {
    private static final long serialVersionUID = -32994532794539608L;

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
