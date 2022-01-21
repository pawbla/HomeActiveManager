package com.pawbla.project.home.user.management.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.   EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.pawbla.project.home.user.management.dao")
@EntityScan("com.pawbla.project.home.user.management.models")
public class DbConfiguration {

    private String dialect;
    private String show_sql;
    private String format_sql;

    public DbConfiguration (@Value("${hibernate.dialect}") String dialect,
                                  @Value("${hibernate.show-sql}") String show_sql,
                                  @Value("${hibernate.format-sql}") String format_sql) {
        super();
        this.dialect = dialect;
        this.show_sql = show_sql;
        this.format_sql = format_sql;
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.pawbla.project.home.user.management.models");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", Boolean.valueOf(show_sql));
        properties.put("hibernate.format_sql", Boolean.valueOf(format_sql));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
