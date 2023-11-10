package com.playbaby.bb.admin.common.entity.io;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GuildRptIO extends CommonIO {
    // hard-coded status
    private final int completed = 2;

    // 所屬公會
    private String guild;
    private Integer guildInt;

    // 公會會長
    private String gm;
    private Integer gmInt;

    // 有無公會
    private String guildless;
    private Integer guildlessInt;

    // 主播 ID
    private String uid;
    private Integer uidInt;

    // 排序
    private String sort;
    private String sortStr;

    // 頁面
    private String page;
    private Integer pageInt;
}
