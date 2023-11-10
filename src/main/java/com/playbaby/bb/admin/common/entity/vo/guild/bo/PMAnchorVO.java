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
public class PMAnchorVO {
    private Integer uid;
    private BigDecimal pmExp;
    private Timestamp createdAt;
}
