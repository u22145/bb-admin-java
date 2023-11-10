package com.playbaby.bb.admin.domain.service.auth;

public interface GMSessionService {
    String getGuildId();
    void onGmLogin(String sessionId, String gmId);
    void onGmLogout(String sessionId);
}
