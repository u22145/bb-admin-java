package com.playbaby.bb.admin.common.entity.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class UserPO {
    private Integer id;
    private String nickname;
    private Long mobile;
    private String areaCode;
    private String email;
    private String password;
    private Integer level;
    private Integer score;
    private Integer age;
    private Integer gender;
    private String realname;
    private String avatar;
    private Integer country;
    private String province;
    private String city;
    private String county;
    private Integer sociatyId;
    private Timestamp joinSociaty;
    private Integer shareRate;
    private Integer isSociaty;
    private Integer isAnchor;
    private Integer isStar;
    private Integer isRecommend;
    private Integer isChatRecommend;
    private Integer isFeatured;
    private Integer isOpenGrabNotice;
    private Integer isCertified;
    private Integer videoOn;
    private BigDecimal videoFee;
    private Integer pmOn;
    private BigDecimal pmFee;
    private Integer msgNotice;
    private String imAccid;
    private Integer upperUid;
    private Integer smUid;
    private String inviteCode;
    private Integer isAgent;
    private Timestamp joinAgent;
    private Integer channelId;
    private Integer partnerId;
    private Integer platform;
    private Timestamp joinDate;
    private Long joinIp;
    private Integer active;
    private Timestamp activeTime;
    private Timestamp vipExpire;
    private Timestamp svipExpire;
    private Boolean status;
    private Integer isBlack;
    private Integer isFake;
    private BigDecimal eurcBalance;
    private BigDecimal eurcFreeze;
    private BigDecimal eurcLock;
    private Integer depositTotal;
    private BigDecimal msqBalance;
    private BigDecimal msqFreeze;
    private BigDecimal msqLock;
    private BigDecimal money;
}
