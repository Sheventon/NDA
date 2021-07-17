package ru.itis.authservice.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * created: 16-07-2021 - 15:31
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Configuration
public class DatabaseConfiguration {

    private final Environment env;

    public DatabaseConfiguration(Environment env) {
        this.env = env;
    }

    /**
     * datasource for locale auth database
     * */
    @Bean(name = "authDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource authDataSource() {
        HikariConfig configuration = new HikariConfig();

        configuration.setUsername(env.getProperty("spring.auth.datasource.username"));
        configuration.setPassword(env.getProperty("spring.auth.datasource.password"));
        configuration.setJdbcUrl(env.getProperty("spring.auth.datasource.url"));
        configuration.setDriverClassName(
                env.getProperty("spring.auth.datasource.driver-class-name"));
        configuration.setMaximumPoolSize(
                Integer.parseInt(Objects.requireNonNull(
                        env.getProperty("spring.auth.datasource.hikari.maximum-pool-size"))));

        return new HikariDataSource(configuration);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(authDataSource());
        entityManagerFactory.setPackagesToScan("ru.itis.authservice.models");
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");

        properties.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
        properties.setProperty("hibernate.hbm2ddl.import_files","schema.sql");
        properties.setProperty("hibernate.connection.charSet","UTF-8");
        properties.setProperty("connection.autocommit","true");
        return properties;
    }


    /**
     * datasource for remote users database
     */
    @Bean(name = "usersDataSource")
    public DataSource usersDataSource() {
        HikariConfig configuration = new HikariConfig();

        configuration.setUsername(env.getProperty("spring.user.datasource.username"));
        configuration.setPassword(env.getProperty("spring.user.datasource.password"));
        configuration.setJdbcUrl(env.getProperty("spring.user.datasource.jdbc-url"));
        configuration.setDriverClassName(
                env.getProperty("spring.user.datasource.driver-class-name"));
        configuration.setMaximumPoolSize(
                Integer.parseInt(Objects.requireNonNull(
                        env.getProperty("spring.user.datasource.hikari.maximum-pool-size"))));

        return new HikariDataSource(configuration);
    }

    @Bean("remoteJdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(usersDataSource());
    }

}
