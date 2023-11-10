package com.playbaby.bb.admin.domain.service.auth.impl;

import com.playbaby.bb.admin.common.db.mapper.UserMapper;
import com.playbaby.bb.admin.common.db.redis.LoginRedisOp;
import com.playbaby.bb.admin.common.util.SessionUtils;
import com.playbaby.bb.admin.domain.service.auth.GMSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GMSessionServiceImpl implements GMSessionService {
    private UserMapper userMapper;
    private LoginRedisOp loginRedisOp;

    @Autowired
    public GMSessionServiceImpl(UserMapper userMapper,
                                LoginRedisOp loginRedisOp) {
        this.userMapper = userMapper;
        this.loginRedisOp = loginRedisOp;
    }

    @Override
    public String getGuildId() {
        String sessionId = SessionUtils.getSessionId();

        return sessionId != null ? loginRedisOp.getGMGuildId(sessionId) : null;
    }

    @Override
    public void onGmLogin(String sessionId, String gmId) {
        Integer guildId = userMapper.findGuildIdByUID(Integer.parseInt(gmId));
        if (guildId == null)
            throw new IllegalArgumentException("GM ID " + gmId + " is not associated with any guild");

        loginRedisOp.saveGMGuildId(sessionId, String.valueOf(guildId));
    }

    @Override
    public void onGmLogout(String sessionId) {
        loginRedisOp.removeGMSession(sessionId);
    }
}
