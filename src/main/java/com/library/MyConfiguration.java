package com.library;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan
public class MyConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/api/**")
            		.allowCredentials(false)
            		.allowedOrigins("*")
            		.allowedMethods("PUT", "POST", "GET", "OPTIONS", "DELETE")
            		.exposedHeaders("Authorization", "Content-Type");
            }
        };
    }
    
    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
            .create()
            .username("devmute")
            .password("devmute#33")
            .url("jdbc:mysql://devmute.cwh2lv5bp1ns.sa-east-1.rds.amazonaws.com:3306/library")
            .driverClassName("com.mysql.jdbc.Driver")
            .build();
    }
}