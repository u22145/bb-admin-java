package com.playbaby.bb.admin.common.entity.vo.guild.soc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GMAnchorVO {
    private Integer uid;
    private String nickname;
    private Integer tierId;
    private BigDecimal giftCommission;
    private BigDecimal lotteryCommission;
    private BigDecimal privateStreamCommission;
    private BigDecimal streamTicketCommission;
    private BigDecimal ugcCommission;
    private BigDecimal datingCommission;
    private BigDecimal messageCommission;
    private Integer performance;
    private Integer perfReached;
    private Integer perfMissed;
    // 0 = 離線
    // 1 = 在線
    // 2 = 正在直播
    private Integer status;
}
