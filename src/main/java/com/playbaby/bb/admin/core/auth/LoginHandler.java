package com.playbaby.bb.admin.core.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playbaby.bb.admin.common.entity.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginHandler {
    private ObjectMapper objectMapper;

    public LoginHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void loginSuccessHandler(@SuppressWarnings("unused") HttpServletRequest request,
                                    HttpServletResponse response,
                                    Authentication authentication) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Result.OK);
    }

    @SuppressWarnings("unused")
    public void loginFailureHandler(HttpServletRequest request,
                                    HttpServletResponse response,
                                    AuthenticationException e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Result.of(e.getLocalizedMessage()));
    }

    @SuppressWarnings("unused")
    public void logoutSuccessHandler(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Authentication authentication) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Result.OK);
    }
}
