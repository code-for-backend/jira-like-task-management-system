package com.example.task_management_system.config;

import com.example.task_management_system.repository.UserRepository;
import com.example.task_management_system.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(auth->auth.requestMatchers("/h2-console/**")
                .permitAll().requestMatchers(HttpMethod.POST,"/api/accounts").permitAll()
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(token->token.disable())
                .headers(headers->headers.frameOptions().disable()) //so as to access the h2-console
                .sessionManagement(sessions->sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();




    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository)
    {
        return new UserInfoService(userRepository);
    }







}
