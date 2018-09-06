package com.daxia.mud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/res/**").addResourceLocations("/res/").setCachePeriod(36000000);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(36000000);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(36000000);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(36000000);
        registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(36000000);
        super.addResourceHandlers(registry);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8081");
            }
        };
    }

}