package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PMRptVO {
    private Integer uid;
    private String nickname;
    private BigDecimal pmExp;
    private Integer pmCnt;
    private Integer senders;
}
