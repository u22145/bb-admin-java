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
public class AggregationVO {
    private Integer guildId;
    private String guildName;
    private Integer gmId;
    private String gmName;
    private String liveDuration;
    private BigDecimal ticketExp;
    private Integer ticketBuyers;
    private BigDecimal giftExp;
    private Integer giftBuyers;
    private String psDuration;
    private BigDecimal psExp;
    private BigDecimal lottoExp;
    private BigDecimal ugcExp;
    private BigDecimal pmExp;
    private Integer pmCnt;
    private BigDecimal total;
}
