package com.eims.vo.form;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OutboundDetailQueryForm implements Serializable {
    private static final long serialVersionUID = 245402006053770180L;

    private Integer outboundDetailId;

    private Integer stockOutId;

    private String stockOutDocunum;

    private Integer productId;

    private String productPicture;

    private String productName;

    private String specModel;

    private String productUnit;

    private Integer deliveryQuantity;

    private String commodityNote;

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
