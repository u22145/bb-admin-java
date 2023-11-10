package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualifiedPerfVO{
    private Integer uid;
    private String nickname;
    private BigDecimal total;
    private BigDecimal perfLimit;
    private Boolean qualified;
}
