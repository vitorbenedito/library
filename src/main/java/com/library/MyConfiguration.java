package com.library;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
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
    public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

       sessionFactory.setDataSource(dataSource());
       sessionFactory.setHibernateProperties(hibernateProperties());

       return sessionFactory;
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

    Properties hibernateProperties() {
       return new Properties() {
         private static final long serialVersionUID = 1L;

         {
             setProperty("hibernate.hbm2ddl.auto", "update");
             setProperty("hibernate.show_sql", "false");
             setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
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