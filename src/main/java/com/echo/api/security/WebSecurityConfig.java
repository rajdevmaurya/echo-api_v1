package com.echo.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/callback/token").permitAll()
                        .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jobs/newest").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jobs", "/api/jobs/**").permitAll()//.hasAnyAuthority(JOBS_CUSTOMER, JOBS_STAFF)
                        .requestMatchers(HttpMethod.GET, "/api/admin", "/api/jobs/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/jobs/search").permitAll()//.hasAnyAuthority(JOBS_CUSTOMER, JOBS_STAFF)
                        
                        .requestMatchers(HttpMethod.GET, "/api/jobs/collection/lookupType").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/orders", "/api/orders/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/orders", "/api/orders/**").permitAll()
                        
                        .requestMatchers("/api/jobs", "/api/jobs/**").permitAll()//.hasAuthority(JOBS_STAFF)
                        .requestMatchers("/auth/authenticate", "/auth/authenticate/**").permitAll()
                        .requestMatchers("/auth/signup", "/auth/signup/**").permitAll()
                        .requestMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
               // .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                 .cors(Customizer.withDefaults())
                 .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    private static final String JOBS_STAFF = "JOBS_STAFF";
    private static final String JOBS_CUSTOMER = "JOBS_CUSTOMER";
}
 