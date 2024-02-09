package com.neutron.crm.config.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.ResourceLoader;

@NoArgsConstructor
public class BeanAwareSpringLiquibase extends SpringLiquibase {
    private static ResourceLoader applicationContext;

    public static <T> T getBean(Class<T> beanClass) {
        if (applicationContext instanceof ApplicationContext) {
            return ((ApplicationContext) applicationContext).getBean(beanClass);
        }

        throw new ApplicationContextException("Resource loader is not an instance of ApplicationContext");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        super.setResourceLoader(resourceLoader);
        applicationContext = resourceLoader;
    }
}
