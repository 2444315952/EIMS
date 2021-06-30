package com.eims.vo.form;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TransferQueryForm implements Serializable {
    private static final long serialVersionUID = -75973938875186562L;

    private Integer transferId;

    private String transferDocunum;

    private Date documentDate;

    private Date outboundDate;

    private Date warehousingDate;

    private Integer companyId;

    private Integer workPointId;

    private Integer employeeId;

    private String employeeName;

    private Integer foldWarehouseId;

    private String foldWarehouseName;

    private Integer exportWarehouseId;

    private String exportWarehouseName;

    private Integer audited;

    private Integer transferState;

    private String reason;

    private String documentsNote;

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
