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
public class GuildPerfVO {
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
}
