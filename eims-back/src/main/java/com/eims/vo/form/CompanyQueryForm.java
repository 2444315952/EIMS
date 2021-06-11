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
public class CompanyQueryForm implements Serializable {
    private static final long serialVersionUID = -76068410694616808L;

    private Integer companyId;

    private String companyName;

    private String contactAddress;

    private String contactNumber;

    private String contactEmail;

    private Integer employeeId;

    private String employeeName;

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
