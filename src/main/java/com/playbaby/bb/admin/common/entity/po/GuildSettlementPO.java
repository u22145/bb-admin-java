package com.playbaby.bb.admin.common.entity.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuildSettlementPO {
    private String settledFor;
    private String from;
    private String to;
}
