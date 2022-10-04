package com.foxmindedjavaspring.university.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;

@Configuration
@ComponentScan("com.foxmindedjavaspring.university")
@PropertySource("classpath:database/persistence-jndi.properties")
public class PersistenceJNDIConfig {
    private final Environment environment;

    public PersistenceJNDIConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(
                    environment.getProperty("jdbc.url"));
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() 
            throws NamingException {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}


