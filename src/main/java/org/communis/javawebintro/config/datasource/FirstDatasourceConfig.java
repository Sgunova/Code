package org.communis.javawebintro.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"org.communis.javawebintro.first.repository"},
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTransactionManager"
)
public class FirstDatasourceConfig {
    @Autowired
    private Environment env;

    /**
     * Configure datasource
     *
     * @return
     */
    @Bean(name = "firstDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource firstDatasourceEntityDatasource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Configure entity manager
     *
     * @return
     */
    @Bean(name = "firstEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean firstDatasourceEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("firstDatasource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("org.communis.javawebintro.first.entity")
                .persistenceUnit("first")
                .build();
    }

    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager firstDatasourceTransactionManager(@Qualifier("firstEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

}
