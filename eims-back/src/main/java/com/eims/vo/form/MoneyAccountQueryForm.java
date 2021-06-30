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
public class MoneyAccountQueryForm implements Serializable {
    private static final long serialVersionUID = 164759673355595131L;

    private Integer moneyAccountId;

    private String accountName;

    private BigDecimal accountMoney;

    private BigDecimal accountBeginMoney;

    private Date accountBirthday;

    private Integer isEnabled;

    private Integer companyId;

    private Integer workPointId;

    private String workPointName;

    private String remark;

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
