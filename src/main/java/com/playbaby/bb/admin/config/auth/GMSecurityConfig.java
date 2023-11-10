package com.playbaby.bb.admin.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playbaby.bb.admin.core.auth.AuthEntryPoint;
import com.playbaby.bb.admin.core.auth.LoginFilter;
import com.playbaby.bb.admin.core.auth.LoginHandler;
import com.playbaby.bb.admin.domain.service.auth.GMCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Order(1)
@Configuration
public class GMSecurityConfig extends WebSecurityConfigurerAdapter {
    private ObjectMapper objectMapper;
    private GMCredentialService gmCredentialService;
    private LoginHandler loginHandler;
    private AuthEntryPoint authEntryPoint;

    @Autowired
    public GMSecurityConfig(ObjectMapper objectMapper,
                            GMCredentialService gmCredentialService,
                            LoginHandler loginHandler,
                            AuthEntryPoint authEntryPoint) {
        this.objectMapper = objectMapper;
        this.gmCredentialService = gmCredentialService;
        this.loginHandler = loginHandler;
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LoginFilter LoginFilter = new LoginFilter(objectMapper, authenticationManagerBean());
        LoginFilter.setAuthenticationSuccessHandler(loginHandler::loginSuccessHandler);
        LoginFilter.setAuthenticationFailureHandler(loginHandler::loginFailureHandler);
        LoginFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/gm/login", "POST"));

        http.csrf().disable()
                .cors()
                .and()
                .requestMatchers().antMatchers("/gm/**")
                .and()
                .authorizeRequests()
                .anyRequest().hasAuthority("GM")
                .and()
                .addFilterBefore(LoginFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(loginHandler::logoutSuccessHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .maximumSessions(1);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(gmCredentialService);
    }
}
