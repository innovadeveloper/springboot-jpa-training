package com.training.shell.trainingjpa.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class DataSourceConfig {

    /**
     * link https://spring.io/guides/gs/accessing-data-mysql
     * @return
     */

    /**
     *
     * @return
     */
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/university_db");
        driverManagerDataSource.setUsername("user");
        driverManagerDataSource.setPassword("user_password");
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return driverManagerDataSource;
    }


    /**
     * LocalContainerEntityManagerFactoryBean: Configura el EntityManagerFactory, el cual se encarga de gestionar
     * el contexto de persistencia y las entidades.
     * @param dataSource
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        // build entity manager factory and set orm adapter
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.training.shell.trainingjpa");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());  // using hibernate like adapter

        // build properties for jpa
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "none");   // "create-drop"); //
        jpaProperties.setProperty("hibernate.show_sql", "false");
        jpaProperties.setProperty("hibernate.format_sql", "false");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    /**
     * JpaTransactionManager: Maneja las transacciones en la aplicación JPA, asegurando la correcta gestión y coordinación
     * de las operaciones transaccionales.
     * @param entityManagerFactoryBean
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

}
