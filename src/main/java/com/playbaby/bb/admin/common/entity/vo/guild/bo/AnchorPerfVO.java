package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnchorPerfVO extends GuildPerfVO {
    private Integer uid;
    private String nickname;
}
