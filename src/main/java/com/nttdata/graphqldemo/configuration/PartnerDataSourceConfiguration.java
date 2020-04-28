package com.nttdata.graphqldemo.configuration;

import javax.sql.DataSource;

import com.nttdata.graphqldemo.model.partner.User;
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
@EnableJpaRepositories(basePackages = "com.nttdata.graphqldemo.repository.partner",
        entityManagerFactoryRef = "partnerEntityManagerFactory",
        transactionManagerRef= "partnerTransactionManager"
)
public class PartnerDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.partner")
    public DataSourceProperties partnerDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.partner.configuration")
    public DataSource partnerDataSource() {
        return partnerDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "partnerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean partnerEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(partnerDataSource())
                .packages(User.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager partnerTransactionManager(
            final @Qualifier("partnerEntityManagerFactory") LocalContainerEntityManagerFactoryBean partnerEntityManagerFactory) {
        return new JpaTransactionManager(partnerEntityManagerFactory.getObject());
    }
}
