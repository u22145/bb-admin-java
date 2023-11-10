package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GMGuildPerfVO {
    private Integer guildId;
    private String guildName;
    private BigDecimal giftExp;
    private BigDecimal ticketExp;
    private BigDecimal psExp;
    private BigDecimal lottoExp;
    private BigDecimal ugcExp;
    private BigDecimal pmExp;
    private BigDecimal total;
    private BigDecimal commission;
    private BigDecimal anchorCommission;
}
