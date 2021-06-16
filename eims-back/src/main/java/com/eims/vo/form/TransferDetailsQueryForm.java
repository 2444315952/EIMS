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
public class TransferDetailsQueryForm implements Serializable {
    private static final long serialVersionUID = 238017337683899000L;

    private Integer transferDetailId;

    private Integer transferId;

    private String transferDocunum;

    private Integer productId;

    private String productName;

    private String specModel;

    private String productUnit;

    private Integer availableStock;

    private Integer outboundQuantity;

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
