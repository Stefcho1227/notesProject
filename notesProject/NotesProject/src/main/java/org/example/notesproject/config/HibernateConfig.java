package org.example.notesproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class HibernateConfig {
    private final String dbUrl, dbUsername, dbPassword;
    @Autowired
    public HibernateConfig(Environment env) {
        dbUrl = env.getProperty("spring.datasource.url");
        dbUsername = env.getProperty("spring.datasource.username");
        dbPassword = env.getProperty("spring.datasource.password");
    }
    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("org.example.notesproject.models");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
    private Properties hibernateProperties(){
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        return hibernateProperties;
    }
}
