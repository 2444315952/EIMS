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
public class WorkPointQueryForm implements Serializable {
    private static final long serialVersionUID = 742039556756137354L;

    private Integer workPointId;

    private String workPointName;

    private String contactAddress;

    private String contactNumber;

    private String contactEmail;

    private Integer employeeId;

    private String employeeName;

    private Integer companyId;

    private String companyName;

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
