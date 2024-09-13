package com.training.shell.trainingjpa.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    /**
     * link https://spring.io/guides/gs/accessing-data-mysql
     * @return
     */
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/university_db");
        driverManagerDataSource.setUsername("user");
        driverManagerDataSource.setPassword("user_password");
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return driverManagerDataSource;
    }

}
