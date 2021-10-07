package com.appgate.test.operators.service.config;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfiguration {

  @Bean
  public RateLimiterRegistry rateLimiterRegistry() {
    RateLimiterConfig defaultConfiguration = RateLimiterConfig.custom()
        .limitForPeriod(20)
        .limitRefreshPeriod(Duration.ofSeconds(1))
        .timeoutDuration(Duration.ofSeconds(1))
        .build();

    RateLimiterRegistry registry = RateLimiterRegistry.of(defaultConfiguration);
    return registry;
  }

}
