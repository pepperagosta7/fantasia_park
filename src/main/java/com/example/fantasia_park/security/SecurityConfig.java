package com.example.fantasia_park.security;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.fantasia_park.model.User;
import com.example.fantasia_park.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user == null){
                throw new UsernameNotFoundException("User not found");
            }
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), 
                    user.getPassword(), 
                    authorities);
        };
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/css/**", "/js/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/creatures", true) // Redirect in caso di login avvenuto con successo
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login?logout")
                .permitAll())
            .authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
