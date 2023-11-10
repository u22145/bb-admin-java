package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuildCommissionVO {
    private Integer guildId;
    private String guildName;
    private Integer uid;
    private String nickname;
    private BigDecimal giftExp;
    private BigDecimal ticketExp;
    private BigDecimal psExp;
    private BigDecimal lottoExp;
    private BigDecimal ugcExp;
    private BigDecimal datingExp;
    private BigDecimal pmExp;
    private BigDecimal total;
    private BigDecimal guildCommission;
    private BigDecimal anchorCommission;
    private BigDecimal totalCommission;
}
