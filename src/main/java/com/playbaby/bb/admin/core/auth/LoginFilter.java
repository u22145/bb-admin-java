package com.playbaby.bb.admin.core.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playbaby.bb.admin.common.entity.io.LoginIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";

    private ObjectMapper objectMapper;
    private AuthenticationManager authManager;

    public LoginFilter(ObjectMapper objectMapper, AuthenticationManager authManager) {
        this.objectMapper = objectMapper;
        this.authManager = authManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            final String requestBody = IOUtils.toString(request.getReader());
            LoginIO loginIO = objectMapper.readValue(requestBody, LoginIO.class);
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(loginIO.getAccount(), loginIO.getPassword());
            setDetails(request, token);

            return authManager.authenticate(token);
        } catch (IOException e) {
            log.error(ERROR_MESSAGE, e);
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }
    }
}
