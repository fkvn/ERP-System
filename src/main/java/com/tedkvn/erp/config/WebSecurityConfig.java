package com.tedkvn.erp.config;


// Import adjustments for Spring Security 6:

import com.tedkvn.erp.security.AuthEntryPointJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @EnableWebSecurity is no longer needed in Spring security 6
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private static final String[] AUTH_WHITELIST =
            {"/api/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
                    "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**",
                    "/actuator/*", "/swagger-ui/**"};

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean // Spring security 6
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/auth/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .exceptionHandling(
                        exceptionConfigurer -> exceptionConfigurer.authenticationEntryPoint(
                                unauthorizedHandler)).sessionManagement(
                        sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)).authorizeHttpRequests(
                        (authz) -> authz.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest()
                                .authenticated());

        return http.build();
    }


    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
                        .exposedHeaders("Content-Disposition");
            }
        };
    }
}