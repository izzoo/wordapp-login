package com.example.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAsync
@EnableScheduling
public class WebMvcConfig implements WebMvcConfigurer {
    /** Annotations config Stuff ... **/
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            if (!registry.hasMappingForPattern("/webjars/**")) {
//                registry.addResourceHandler("/webjars/**").addResourceLocations(
//                        "classpath:/META-INF/resources/webjars/");
//            }
//
//            if (!registry.hasMappingForPattern("/**")) {
//                registry.addResourceHandler("/**").addResourceLocations(
//                        "classpath:/resources/static/");
//            }
//
//
//
    //}

//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/");
//    }

    }