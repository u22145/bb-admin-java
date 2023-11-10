package com.playbaby.bb.admin.common.entity.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class GuildAnchorTierPO {
    private Integer id;
    private Integer uid;
    private Integer goalCount;
    private BigDecimal giftCommission;
    private BigDecimal lotteryCommission;
    private BigDecimal privateStreamCommission;
    private BigDecimal streamTicketCommission;
    private BigDecimal ugcCommission;
    private BigDecimal datingCommission;
    private BigDecimal messageCommission;
}
