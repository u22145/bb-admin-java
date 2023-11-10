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
public class LiveRptVO {
    private Integer uid;
    private String nickname;
    private String durationTotal;
    private BigDecimal ticketExp;
    private Integer ticketBuyers;
    private BigDecimal giftExp;
    private Integer giftBuyers;
    private BigDecimal lotteryExp;
    private Integer viewers;
    private BigDecimal avgViewers;
    private BigDecimal total;
}
