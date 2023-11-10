package com.playbaby.bb.admin.common.entity.vo.guild.bo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OverviewVO {
    private String liveDuration; // 緦直播長度
    private BigDecimal ticketExp; // 總門票收益
    private int ticketBuyers; // 總門票購買人數

    private String psDuration; // 總 1v1 直播長度
    private BigDecimal psExp; // 總 1v1 收益
    private int psCnt; // 總 1v1 場次

    private BigDecimal giftExp; // 總禮物打賞收益
    private int gift_buyers; // 總送禮人數

    private BigDecimal lottoExp; // 總彩票流水
    private int lottoCnt; // 總彩票投注
    private int lottoWonCnt; // 總彩票中獎

    private BigDecimal ugcExp; // 總 UGC 收益
    private int ugcCnt; // UGC 人數

    private BigDecimal datingExp; // 總約會電台收益
    private int datingCnt; // 約會電台人數

    private BigDecimal pmExp; // 總私信收益
    private int pmCnt; // 總私信條數
    private int pmSenders; // 總私信人數
}
