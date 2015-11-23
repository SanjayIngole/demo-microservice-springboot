package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A Demo MicroServices Application.
 * 
 * @author sanjayingole
 *
 */
@SpringBootApplication
public class DemoMicroServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMicroServicesApplication.class, args);
    }
}
