package com.playbaby.bb.admin.common.entity.io;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuildIO {
    private Integer id;
    private String name;
    private Integer masterId;
    private String giftCommission;
    private String lotteryCommission;
    private String privateStreamCommission;
    private String streamTicketCommission;
    private String ugcCommission;
    private String datingCommission;
    private String messageCommission;
    private Integer status;
}
