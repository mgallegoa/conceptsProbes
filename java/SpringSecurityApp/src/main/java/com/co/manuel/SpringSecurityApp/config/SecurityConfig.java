package com.co.manuel.SpringSecurityApp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@EnableMethodSecurity: to allow configurations with annotations
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  // Example to configure the security without annotations in the FilterChain
  // @Bean
  // public SecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity)
  // {
  //
  // return httpSecurity
  // // For the csrf in forms
  // .csrf(csrf -> csrf.disable())
  // // Only with user and password
  // .httpBasic(Customizer.withDefaults())
  // // To manage stateless sessions
  // .sessionManagement(session ->
  // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  // // Configuration the endpoints
  // .authorizeHttpRequests(http -> {
  // // Public endpoints
  // http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
  // // Secured endpoints
  // http.requestMatchers(HttpMethod.GET,
  // "/auth/hello-secured").hasAuthority("READ");
  // // Rest of endpoints: denyAll more secure and authenticated just with send
  // // user/pass
  // http.anyRequest().denyAll();
  //
  // })
  // .build();
  // }
  //

  @Bean
  public SecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity) {

    return httpSecurity
        // For the csrf in forms
        .csrf(csrf -> csrf.disable())
        // Only with user and password
        .httpBasic(Customizer.withDefaults())
        // To manage stateless sessions
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider(userDetailsService());
    daoProvider.setPasswordEncoder(passwordEncoder());
    return daoProvider;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    List<UserDetails> userDetails = new ArrayList<>();

    userDetails.add(User.withUsername("manuel")
        .password("123")
        .roles("ADMIN")
        .authorities("READ", "CREATE")
        .build());

    userDetails.add(User.withUsername("joe")
        .password("321")
        .roles("USER")
        .authorities("READ")
        .build());
    return new InMemoryUserDetailsManager(userDetails);

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // use BCryptPasswordEncoder() in production;
    return NoOpPasswordEncoder.getInstance();
  }

}
