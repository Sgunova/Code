package org.communis.javawebintro.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }

    @Bean(name = "liquibase")
    @Primary
    public SpringLiquibase primaryLiquibase(@Qualifier("dataSource") DataSource dataSource) {
        return springLiquibase(dataSource, defaultLiquibaseProperties());
    }

    @Bean(name = "liquibase2")
    public SpringLiquibase firstLiquibase(@Qualifier("firstDatasource") DataSource dataSource) {
        return springLiquibase(dataSource, firstDataSourceLiquibaseProperties());
    }

    @Bean(name = "liquibase3")
    public SpringLiquibase secondLiquibase(@Qualifier("secondDatasource") DataSource dataSource) {
        return springLiquibase(dataSource, secondDataSourceLiquibaseProperties());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.liquibase")
    public LiquibaseProperties defaultLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.first.liquibase")
    public LiquibaseProperties firstDataSourceLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second.liquibase")
    public LiquibaseProperties secondDataSourceLiquibaseProperties() {
        return new LiquibaseProperties();
    }
}
