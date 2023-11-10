package com.playbaby.bb.admin.common.entity.io;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GuildAnchorTierIO {
    private Integer id;
    private Integer goalCount;
    private String giftCommission;
    private String lotteryCommission;
    private String privateStreamCommission;
    private String streamTicketCommission;
    private String ugcCommission;
    private String datingCommission;
    private String messageCommission;
}
