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

import com.foxmindedjavaspring.university.exception.UniversityDataAcessException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("com.foxmindedjavaspring.university")
@PropertySource({
    "classpath:database/database.properties",
    "classpath:database/jndi.properties"
})
public class DataSourceConfig {
    private final Boolean isJndi;
    private final String jndiJdbcUrl;
    private final String url;
    private final String driverClassName;
    private final String username;
    private final String password;

    public DataSourceConfig(
            @Value("${datasource.jndi}") Boolean isJndi,
            @Value("${datasource.jndi.url}") String jndiJdbcUrl,
            @Value("${datasource.driverClassName}") String driverClassName,
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password) {
        this.isJndi = isJndi;
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
    public DataSource dataSource() {
        try {
            if (isJndi) {
                return (DataSource) new JndiTemplate().lookup(jndiJdbcUrl);  
            } 
        } catch (NamingException e) {
            throw new UniversityDataAcessException(e, 
                    "Error while looking up the dataSource {} bound to JNDI", 
                    jndiJdbcUrl);
        }
        return hikariDataSource();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}