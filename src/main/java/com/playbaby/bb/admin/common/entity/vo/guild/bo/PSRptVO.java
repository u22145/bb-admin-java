package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PSRptVO {
    private Integer uid;
    private String nickname;
    private String durationTotal;
    private BigDecimal giftTotal;
    private BigDecimal streamTotal;
    private BigDecimal total;
    private Integer streamCnt;
}
