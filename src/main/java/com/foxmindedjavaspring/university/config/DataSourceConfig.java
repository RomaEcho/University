package com.foxmindedjavaspring.university.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.foxmindedjavaspring.university")
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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.schema-generation.database.action",
                datasource.getSchemaGenerationStrategy());
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform(datasource.getHibernateDialect());
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setDataSource(hikariDataSource());
        factory.setPackagesToScan("com.foxmindedjavaspring.university");
        factory.setJpaPropertyMap(properties);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager() {
        return new JpaTransactionManager(entityManagerFactoryBean().getObject());
    }
}