package com.playbaby.bb.admin.common.entity.io;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuildQueryIO {
    private String page;
    private String guild;
    private String gm;
    private String status;

    private Integer pageInt;
    private Integer guildInt;
    private Integer gmInt;
    private Integer statusInt;
}
