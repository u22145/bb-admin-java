package com.playbaby.bb.admin.domain.service.auth;

import com.playbaby.bb.admin.common.constant.AdminRoles;
import com.playbaby.bb.admin.common.db.mapper.AdminMapper;
import com.playbaby.bb.admin.common.entity.po.AdminUserPO;
import com.playbaby.bb.admin.common.entity.vo.auth.AdminAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminCredentialService implements UserDetailsService {
    private AdminMapper adminMapper;
    private static final List<SimpleGrantedAuthority> ROLES = Arrays.asList(AdminRoles.ADMIN_ROLE);

    @Autowired
    public AdminCredentialService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUserPO adminUserPO = adminMapper.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("admin " + username + " is not a recognized admin")
        );
        if (!adminUserPO.getStatus())
            throw new UsernameNotFoundException("this account " + username + " has been locked");

        return new AdminAuthVO(username, adminUserPO.getPassword(), ROLES);
    }
}
