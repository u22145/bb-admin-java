package com.playbaby.bb.admin.common.entity.io;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GuildAnchorSettingIO {
    private Integer uid;
    private Integer performance;
    private List<GuildAnchorTierIO> anchorTiers;
}
