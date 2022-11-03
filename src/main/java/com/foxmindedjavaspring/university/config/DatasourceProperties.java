package com.foxmindedjavaspring.university.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "datasource")
public class DatasourceProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private String schemaGenerationStrategy;
    private String hibernateDialect;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchemaGenerationStrategy() {
        return schemaGenerationStrategy;
    }

    public void setSchemaGenerationStrategy(String schemaGenerationStrategy) {
        this.schemaGenerationStrategy = schemaGenerationStrategy;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public void setHibernateDialect(String hibernateDialect) {
        this.hibernateDialect = hibernateDialect;
    }
}
