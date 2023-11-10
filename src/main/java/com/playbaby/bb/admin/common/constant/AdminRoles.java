package com.playbaby.bb.admin.common.constant;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class AdminRoles {
    public static final SimpleGrantedAuthority ADMIN_ROLE = new SimpleGrantedAuthority("ADMIN");
    public static final SimpleGrantedAuthority GM_ROLE = new SimpleGrantedAuthority("GM");

    // prevent instantiation
    private AdminRoles() {}
}
