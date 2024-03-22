//package com.tedkvn.erp.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration
// .EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration
// .WebSecurityConfigurerAdapter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//    //    @Autowired
//    //    private AuthEntryPointJwt unauthorizedHandler;
//
//    @Override
//    public void configure(
//            org.springframework.security.config.annotation.web.builders.WebSecurity web)
//            throws Exception {
//        web.ignoring().mvcMatchers("/swagger-ui/**", "/swagger-ui.html/**", "/configuration/**",
//                "/swagger-resources/**", "/v2/api-docs");
//    }
//
//    //    @Override
//    //    protected void configure(HttpSecurity http) throws Exception {
//    //        http.cors().and().csrf().disable().exceptionHandling()
//    //                .authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
//    //                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//    //                .authorizeRequests()
//    //                .antMatchers("/api/**").permitAll().anyRequest().authenticated();
//    //        //		.antMatchers("/api/**").permitAll()
//    //
//    //        //        http.addFilterBefore(authenticationJwtTokenFilter(),
//    //        //                UsernamePasswordAuthenticationFilter.class);
//    //    }
//
//    //	cors
//    @Bean
//    public WebMvcConfigurer corsConfigure() {
//        return new WebMvcConfigurer() {
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
//                        .exposedHeaders("Content-Disposition");
//            }
//        };
//    }
//}
