package com.playbaby.bb.admin.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playbaby.bb.admin.core.auth.LoginFilter;
import com.playbaby.bb.admin.core.auth.LoginHandler;
import com.playbaby.bb.admin.domain.service.auth.AdminCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Order(2)
@Configuration
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
    private ObjectMapper objectMapper;
    private AdminCredentialService adminCredentialService;
    private LoginHandler loginHandler;

    @Autowired
    public AdminSecurityConfig(ObjectMapper objectMapper,
                               AdminCredentialService adminCredentialService,
                               LoginHandler loginHandler) {
        this.objectMapper = objectMapper;
        this.adminCredentialService = adminCredentialService;
        this.loginHandler = loginHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LoginFilter LoginFilter = new LoginFilter(objectMapper, authenticationManagerBean());
        LoginFilter.setAuthenticationSuccessHandler(loginHandler::loginSuccessHandler);
        LoginFilter.setAuthenticationFailureHandler(loginHandler::loginFailureHandler);
        LoginFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));

        http.csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/guild/**").hasAuthority("ADMIN")
                .and()
                .addFilterBefore(LoginFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(loginHandler::logoutSuccessHandler)
                .and()
                .exceptionHandling();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminCredentialService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/login*", configuration);
        source.registerCorsConfiguration("/logout*", configuration);
        source.registerCorsConfiguration("/gm/login*", configuration);

        return source;
    }
}
