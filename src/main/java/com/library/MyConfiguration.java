package com.library;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
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
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
         HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
         factory.setEntityManagerFactory(emf);
         factory.setJpaProperties(jpaProperties());
         return factory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(sessionFactory);
       return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
       return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties jpaProperties() {
       return new Properties() {
    	   
	         private static final long serialVersionUID = 1L;
	
	         {
	             setProperty("spring.jpa.hibernate.ddl-auto", "create");
	             setProperty("spring.jpa.show-sql", "false");
	             setProperty("spring.jpa.hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
	             setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	             setProperty("spring.jpa.datasource.database-platform", "org.hibernate.dialect.MySQL5Dialect");
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