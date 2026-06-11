package com.jumpstart.food_ordering_system.config;

import org.springframework.context.annotation.Configuration;

/**
 * ApplicationConfig is a configuration class for the Spring Boot application.
 *
 * Classes annotated with @Configuration are used to define Spring Beans
 * and application-wide settings programmatically.
 *
 * Examples of what a config class can contain:
 * - Bean definitions (objects managed by Spring)
 * - Security configuration
 * - CORS settings (allowing frontend apps to call this API)
 * - Custom application settings
 *
 * @Configuration tells Spring to process this class during startup
 * and apply any configuration defined inside it.
 */
@Configuration
public class ApplicationConfig {

    // Future configuration such as CORS, security beans,
    // or custom settings will be added here as the application grows.
}
