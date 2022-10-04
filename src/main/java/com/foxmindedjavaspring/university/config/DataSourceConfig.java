package com.foxmindedjavaspring.university.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("com.foxmindedjavaspring.university")
@PropertySource("classpath:database/database.properties")
public class DataSourceConfig {
    private final String jndiStatus;
    private final String jndiJdbcUrl;
    private final String url;
    private final String driverClassName;
    private final String username;
    private final String password;

    public DataSourceConfig(
            @Value("${datasource.jndi}") String jndiStatus,
            @Value("${datasource.jndi.url}") String jndiJdbcUrl,
            @Value("${datasource.driverClassName}") String driverClassName,
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password) {
        this.jndiStatus = jndiStatus;
        this.jndiJdbcUrl = jndiJdbcUrl;
        this.url = url;
        this.driverClassName = driverClassName;
        this.username = username;
        this.password = password;
    }

    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    @Bean
    public DataSource dataSource() throws NamingException {
        if (jndiStatus.equals("true") || jndiStatus.equals("True")) {
            return (DataSource) new JndiTemplate().lookup(jndiJdbcUrl);  
        } else {
            return hikariDataSource();
        }
    }


    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() 
            throws NamingException {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}