package com.example.demo.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  CustomAuthFilter customAuthFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        // dont authenticate this particular request
        .authorizeRequests().antMatchers("/api/public/**").permitAll().antMatchers("/api/protected/**").authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(
            (req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED : " + ex.getMessage()))
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and();

    // http.authorizeRequests((authz) ->
    // authz.antMatchers("/api/public/**").permitAll().antMatchers("/api/protected/**").authenticated()
    // );

    // Add a filter to validate the tokens with every request
    http.addFilterBefore(customAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
