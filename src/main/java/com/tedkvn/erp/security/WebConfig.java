//package com.tedkvn.erp.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@EnableWebMvc
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//
//    //    @Override
//    //    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    //        registry.addResourceHandler("/swagger-ui/**").addResourceLocations
//    //        ("classpath:/META-INF/resources/swagger-ui.html");
//    //        registry.addResourceHandler("/webjars/**").addResourceLocations
//    //        ("classpath:/META-INF/resources/webjars/");
//    //    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
//                .exposedHeaders("Content-Disposition");
//    }
//
//}