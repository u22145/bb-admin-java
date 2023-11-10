package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LiveAnchorVO {
    private Integer uid;
    private String nickname;
    private Integer guildId;
    private String guildName;
    private String duration;
    private BigDecimal ticketPrice;
    private Integer ticketBuyers;
    private BigDecimal ticketExp;
    private Integer giftBuyers;
    private BigDecimal giftExp;
    private BigDecimal lottoExp;
    private BigDecimal total;
    private Integer viewers;
    private Integer peakViewers;
    private Timestamp startedAt;
    private Timestamp closedAt;
}
