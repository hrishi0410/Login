package com.courseEnroll.course.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.courseEnroll.course.security.JwtAuthenticationFilter;
import com.courseEnroll.course.service.CustomUserDetailsService;


    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {

        private final CustomUserDetailsService userDetailsService;
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        public SecurityConfig(CustomUserDetailsService userDetailsService,
                              JwtAuthenticationFilter jwtAuthenticationFilter) {
            this.userDetailsService = userDetailsService;
            this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                            .requestMatchers("/api/admin/**").hasRole("ADMIN")
                            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userDetailsService);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }