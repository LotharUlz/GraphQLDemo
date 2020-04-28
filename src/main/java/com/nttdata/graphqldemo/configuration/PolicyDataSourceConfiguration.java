package com.nttdata.graphqldemo.configuration;

import javax.sql.DataSource;

import com.nttdata.graphqldemo.model.policy.Policy;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.*;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.nttdata.graphqldemo.repository.policy",
        entityManagerFactoryRef = "policyEntityManagerFactory",
        transactionManagerRef= "policyTransactionManager"
)
public class PolicyDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.policy")
    public DataSourceProperties policyDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.policy.configuration")
    public DataSource policyDataSource() {
        return policyDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "policyEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean policyEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(policyDataSource())
                .packages(Policy.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager policyTransactionManager(
            final @Qualifier("policyEntityManagerFactory") LocalContainerEntityManagerFactoryBean policyEntityManagerFactory) {
        return new JpaTransactionManager(policyEntityManagerFactory.getObject());
    }
}
