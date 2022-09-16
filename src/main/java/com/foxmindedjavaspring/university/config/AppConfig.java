package com.foxmindedjavaspring.university.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
// @PropertySource("classpath:database.properties")
@PropertySource("database.properties")
// @ComponentScan("src.main.java.com.foxmindedjavaspring.university")
@ComponentScan("com.foxmindedjavaspring.university")
public class AppConfig {
    private final String url;
    private final String driverClassName;
    private final String username;
    private final String password;

    public AppConfig(
            @Value("${datasource.driverClassName}") String driverClassName,
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password) {
        this.url = url;
        this.driverClassName = driverClassName;
        this.username = username;
        this.password = password;
    }

    @Bean(name = "hikariDataSource")
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(hikariDataSource());
    }
}


