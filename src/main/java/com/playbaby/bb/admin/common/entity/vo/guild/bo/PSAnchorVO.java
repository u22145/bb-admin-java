package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PSAnchorVO {
    private Integer uid;
    private String nickname;
    private Integer guildId;
    private String guildName;
    private String duration;
    private BigDecimal chargePerMin;
    private BigDecimal streamExp;
    private BigDecimal giftExp;
    private BigDecimal total;
    private Timestamp fromDT;
    private Timestamp toDT;
}
