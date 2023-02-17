package org.example.configurations;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = {SpringConfig.class})
@EnableAutoConfiguration
@ComponentScan("org.example.playwright")
@CucumberContextConfiguration
public class SpringCucumberConfig {
}
