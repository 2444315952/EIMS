package com.eims.vo.form;

import java.math.BigDecimal;
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
public class GatherQueryForm implements Serializable {
    private static final long serialVersionUID = 499051625101891523L;

    private Integer gatherId;

    private Integer companyId;

    private Integer workPointId;

    private String gatherDocunum;

    private Date documentDate;

    private Integer sellId;

    private String sellDocunum;

    private Integer customerId;

    private String customerName;

    private Integer employeeId;

    private String employeeName;

    private BigDecimal gatherAmount;

    private String remark;

    private Integer audited;

    private Integer moneyAccountId;

    private String accountName;

    private Date gatherBirthday;

    private Integer sellType;
    private BigDecimal dateNumber1;
    private BigDecimal dateNumber2;
    private Date currentDate;
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
