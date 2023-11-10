package com.playbaby.bb.admin.common.entity.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GuildAnchorSettingPO {
    private Integer uid;
    private Integer gid;
    private Integer performance;
    private List<GuildAnchorTierPO> anchorTiers;
}
