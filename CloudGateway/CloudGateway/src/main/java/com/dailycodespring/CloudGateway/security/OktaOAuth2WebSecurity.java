//package com.dailycodespring.CloudGateway.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class OktaOAuth2WebSecurity {
//
//	@Bean
//	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
//		http 
//			.authorizeExchange() // Deprecated
//			.anyExchange()
//			.authenticated()
//			.and() // Deprecated
//			.oauth2Login() // Deprecated
//			.and() // Deprecated
//			.oauth2ResourceServer() // Deprecated
//			.jwt(); // Deprecated
//		
//		return http.build();
//	}
//	
//}
