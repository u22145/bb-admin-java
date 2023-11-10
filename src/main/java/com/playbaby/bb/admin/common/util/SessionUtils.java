package com.playbaby.bb.admin.common.util;

import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtils {

    public static String getSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}
