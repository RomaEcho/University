package com.foxmindedjavaspring.university.config;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.foxmindedjavaspring.university.exception.UniversityDataAccessException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.foxmindedjavaspring.university")
@PropertySource({
    "classpath:database/database.properties",
    "classpath:database/jndi.properties",
    "classpath:persistence.properties"
})
public class DataSourceConfig {
    private final Boolean isJndi;
    private final String jndiJdbcUrl;
    private final String url;
    private final String driverClassName;
    private final String username;
    private final String password;
    private final String schemaGeneration;
    private final String dialect;

    public DataSourceConfig(
            @Value("${datasource.jndi}") Boolean isJndi,
            @Value("${datasource.jndi.url}") String jndiJdbcUrl,
            @Value("${datasource.driverClassName}") String driverClassName,
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password,
            @Value("${javax.persistence.schema-generation.database.action}") 
                    String schemaGeneration,
            @Value("${hibernate.dialect}") String dialect) {
        this.isJndi = isJndi;
        this.jndiJdbcUrl = jndiJdbcUrl;
        this.url = url;
        this.driverClassName = driverClassName;
        this.username = username;
        this.password = password;
        this.schemaGeneration = schemaGeneration;
        this.dialect = dialect;
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
    public DataSource dataSource() {
        try {
            if (isJndi) {
                return (DataSource) new JndiTemplate().lookup(jndiJdbcUrl);
            }
        } catch (NamingException e) {
            throw new UniversityDataAccessException(e,
                    "Error while looking up the dataSource {} bound to JNDI",
                    jndiJdbcUrl);
        }
        return hikariDataSource();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.schema-generation.database.action",
                schemaGeneration);
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform(dialect);
        LocalContainerEntityManagerFactoryBean factory = 
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setDataSource(dataSource());
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