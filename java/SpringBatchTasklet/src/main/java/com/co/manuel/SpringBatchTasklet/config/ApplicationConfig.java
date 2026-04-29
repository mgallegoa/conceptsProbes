package com.co.manuel.SpringBatchTasklet.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@EnableBatchProcessing
public class ApplicationConfig {

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://mysql:3306/SpringBatchTaskletdb?CreateDataBaseIfNotExtis=true");
    dataSource.setUsername("root");
    dataSource.setPassword("manuelpass");
    return dataSource;
  }

  @Bean
  public DataSourceInitializer batchInitializer(DataSource dataSource) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(
        new ClassPathResource("org/springframework/batch/core/schema-mysql.sql"));

    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(populator);

    return initializer;
  }
}
