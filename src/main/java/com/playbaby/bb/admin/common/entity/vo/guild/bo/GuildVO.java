package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
public class GuildVO {
    private Integer id;
    private Integer masterId;
    private String name;
    private BigDecimal giftCommission;
    private BigDecimal lotteryCommission;
    private BigDecimal privateStreamCommission;
    private BigDecimal streamTicketCommission;
    private BigDecimal ugcCommission;
    private BigDecimal datingCommission;
    private BigDecimal messageCommission;
    private Integer status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
