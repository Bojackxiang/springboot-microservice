package com.alex.restfulwebservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {

    // filtered all request
    http.authorizeHttpRequests(auth -> {
      auth.anyRequest().authenticated();
    });

    // if a request is not authenticated, show page
    http.httpBasic(Customizer.withDefaults());

    // disable csrf
    http.csrf(csrf -> csrf.disable());


    return http.build();

  }
}
