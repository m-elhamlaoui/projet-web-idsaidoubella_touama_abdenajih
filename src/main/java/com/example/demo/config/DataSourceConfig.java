package com.example.demo.config; // ou com.example.demo.config.database

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:h2:file:./data/test1db")
            .username("SA")
            .password("")
            .driverClassName("org.h2.Driver")
            .build();
    }
}