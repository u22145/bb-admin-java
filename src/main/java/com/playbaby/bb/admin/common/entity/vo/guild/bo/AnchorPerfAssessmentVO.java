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
public class AnchorPerfAssessmentVO {
    private Integer uid;
    private String nickname;
    private String date;
    private BigDecimal total;
    private Integer performance;
    private Integer reached;
}
