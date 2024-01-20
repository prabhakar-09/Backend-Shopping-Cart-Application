package com.dailycodespring.CloudGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}
	
//	@Bean
//	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
//		return factory -> factory.configureDefault(
//				id -> new Resilience4JConfigBuilder(id)
//					.circuitBreakerConfig(
//							CircuitBreakerConfig.ofDefaults()).build()					
//				);
//	}

    // Define a customizer bean to configure the circuit breaker
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                // Configure your circuit breaker settings as needed
//                .slidingWindowSize(10)
//                .permittedNumberOfCallsInHalfOpenState(5)
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(java.time.Duration.ofMillis(10000))
//                .build();
//
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .build());
//    }

}
