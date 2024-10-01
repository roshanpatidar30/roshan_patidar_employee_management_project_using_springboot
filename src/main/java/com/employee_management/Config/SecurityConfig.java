package com.employee_management.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws 	Exception {
		http
		.csrf().disable()
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/api/employees/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/employees/**").hasAnyRole("NORMAL","ADMIN")
            .anyRequest().authenticated()
        )
        .httpBasic();
    return http.build();
	}
	
	
	
	@Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build());
        userDetailsManager.createUser(User.withUsername("normal")
            .password(passwordEncoder().encode("normal"))
            .roles("NORMAL")
            .build());
        return userDetailsManager;
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
    }
}