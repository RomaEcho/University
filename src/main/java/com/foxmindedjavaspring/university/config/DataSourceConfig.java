package com.foxmindedjavaspring.university.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.foxmindedjavaspring.university")
@EnableJpaRepositories(basePackages = "com.foxmindedjavaspring.university.repository")
public class DataSourceConfig {
    private final DatasourceProperties datasource;

    public DataSourceConfig(DatasourceProperties datasourceProperties) {
        this.datasource = datasourceProperties;
    }

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(datasource.getDriverClassName());
        config.setJdbcUrl(datasource.getUrl());
        config.setUsername(datasource.getUsername());
        config.setPassword(datasource.getPassword());
        return new HikariDataSource(config);
    }
}