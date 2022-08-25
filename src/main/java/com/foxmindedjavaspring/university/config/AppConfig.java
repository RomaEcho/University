package com.foxmindedjavaspring.university.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("src.main.java.com.foxmindedjavaspring.university")
@PropertySource("database.properties")
public class AppConfig {
    private final String url;
    private final String driverClass;
    private final String username;
    private final String password;

    public AppConfig(@Value("${driverclass}") String driverClass,
            @Value("${url}") String url,
            @Value("${username}") String username,
            @Value("${password}") String password) {
        this.url = url;
        this.driverClass = driverClass;
        this.username = username;
        this.password = password;
    }

    @Bean(name = "hikariDataSource")
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClass);
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
