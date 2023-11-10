package com.playbaby.bb.admin.domain.service.auth;

import com.playbaby.bb.admin.common.constant.AdminRoles;
import com.playbaby.bb.admin.common.db.mapper.UserMapper;
import com.playbaby.bb.admin.common.entity.po.UserPO;
import com.playbaby.bb.admin.common.entity.vo.auth.GMAuthVO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GMCredentialService implements UserDetailsService {
    private UserMapper userMapper;
    private static final List<SimpleGrantedAuthority> ROLES = Arrays.asList(AdminRoles.GM_ROLE);

    public GMCredentialService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Integer id = Integer.parseInt(username);
            UserPO userPO = userMapper.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("user " + username + " is not a registered user")
            );
            Integer isGM = userPO.getIsSociaty();
            if (isGM == null || isGM != 1)
                throw new UsernameNotFoundException("user " + username + " is not a guild master");

            return new GMAuthVO(username, userPO.getPassword(), ROLES);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("user " + username + " is not a registered user");
        }
    }
}
