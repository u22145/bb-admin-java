package com.playbaby.bb.admin.common.entity.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class GuildPO {
    private int id;
    private int masterId;
    private String name;
    private BigDecimal giftCommission;
    private BigDecimal lotteryCommission;
    private BigDecimal privateStreamCommission;
    private BigDecimal streamTicketCommission;
    private BigDecimal ugcCommission;
    private BigDecimal datingCommission;
    private BigDecimal messageCommission;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
