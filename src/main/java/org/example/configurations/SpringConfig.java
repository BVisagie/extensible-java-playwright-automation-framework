package org.example.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = SpringConfig.BASE_PACKAGE)
@ConfigurationPropertiesScan(basePackages = SpringConfig.BASE_PACKAGE)
@EntityScan(SpringConfig.BASE_PACKAGE)
@Configuration
public class SpringConfig {
    static final String BASE_PACKAGE = "org.example";
}
