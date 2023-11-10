package com.playbaby.bb.admin.common.entity.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class AdminUserPO {
    private Integer id;
    private Integer uid;
    private String username;
    private String password;
    private String usercode;
    private Long mobile;
    private Integer areaCode;
    private Boolean isSuperAdmin;
    private Boolean status;
    private Timestamp loginTime;
    private Long loginIp;
    private String description;
    private String avatar;
    private Timestamp uptime;
}
