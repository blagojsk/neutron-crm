package com.neutron.crm.config.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import lombok.Getter;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Getter
@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfig {

    private final DataSource dataSource;

    private final LiquibaseProperties properties;

    public LiquibaseConfig(final DataSource dataSource, final LiquibaseProperties properties) {
        this.dataSource = dataSource;
        this.properties = properties;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new BeanAwareSpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(this.properties.getChangeLog());
        liquibase.setContexts(this.properties.getContexts());
        liquibase.setDefaultSchema(this.properties.getDefaultSchema());
        liquibase.setDropFirst(this.properties.isDropFirst());
        liquibase.setShouldRun(this.properties.isEnabled());
        liquibase.setChangeLogParameters(this.properties.getParameters());
        liquibase.setRollbackFile(this.properties.getRollbackFile());
        return liquibase;
    }
}
