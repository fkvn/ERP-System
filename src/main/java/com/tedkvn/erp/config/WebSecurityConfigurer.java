package com.tedkvn.erp.config;


// Import adjustments for Spring Security 6:

import com.tedkvn.erp.security.AuthEntryPointJwt;
import com.tedkvn.erp.security.AuthTokenFilter;
import com.tedkvn.erp.security.UserDetailsServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @EnableWebSecurity is no longer needed in Spring security 6
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer {

    // we allow all API calls here and check by using the RequiresAuthentication annotation
    private static final String[] AUTH_WHITELIST =
            {"/api/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
                    "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**",
                    "/actuator/*", "/swagger-ui/**"};

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Spring security 6
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .exceptionHandling(
                        exceptionConfigurer -> exceptionConfigurer.authenticationEntryPoint(
                                unauthorizedHandler)).sessionManagement(
                        sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)).authorizeHttpRequests(
                        (authz) -> authz.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest()
                                .authenticated());

        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
                        .exposedHeaders("Content-Disposition");
            }
        };
    }
}