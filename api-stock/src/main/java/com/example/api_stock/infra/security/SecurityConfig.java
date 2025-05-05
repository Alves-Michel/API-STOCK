package com.example.api_stock.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categories/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/subCategory/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sales").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/list").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/categories/delete/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categories/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/subCategory/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/busca").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/delete/{id_user}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/products/delete/{id_product}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/subCategory/delete/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/products/update/{id_product}").permitAll()







                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}