package com.playbaby.bb.admin.core.auth;

import com.playbaby.bb.admin.common.constant.AdminRoles;
import com.playbaby.bb.admin.domain.service.auth.GMSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static com.playbaby.bb.admin.common.util.SessionUtils.getSessionId;

/**
 * This class serves as the helper utility upon successful or failed login
 */

@Component
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {
    private GMSessionService gmSessionService;

    @Autowired
    public AuthenticationListener(GMSessionService gmSessionService) {
        this.gmSessionService = gmSessionService;
    }

    @Override
    public void onApplicationEvent(@NonNull AbstractAuthenticationEvent appEvent) {
        if (appEvent instanceof AuthenticationSuccessEvent)
            onSuccessfulLogin(appEvent);
        else if (appEvent instanceof AuthenticationFailureBadCredentialsEvent)
            onFailedLogin(appEvent);
        else
            onLogout(appEvent);
    }

    private void onSuccessfulLogin(AbstractAuthenticationEvent appEvent) {
        Authentication auth = appEvent.getAuthentication();

        // gmLogin
        if (auth.getAuthorities().contains(AdminRoles.GM_ROLE)) {
            String sessionId = getSessionId();
            String userId = auth.getName();
            gmSessionService.onGmLogin(sessionId, userId);
        }
    }

    @SuppressWarnings("unused")
    private void onFailedLogin(AbstractAuthenticationEvent appEvent) {
    }

    private void onLogout(AbstractAuthenticationEvent appEvent) {
        Authentication auth = appEvent.getAuthentication();

        // gmLogin
        if (auth.getAuthorities().contains(AdminRoles.GM_ROLE)) {
            gmSessionService.onGmLogout(getSessionId());
        }
    }
}
