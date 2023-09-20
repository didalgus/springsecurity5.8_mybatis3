package org.example.config;

import org.example.handler.CustomLoginFailureHandler;
import org.example.handler.CustomLoginSuccessHandler;
import org.example.service.CustomUserDetailsService;
import org.example.service.SimplePasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Profile("prod")
@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity
public class SecurityConfigProd {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers("/users/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/board/form/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .requestMatchers("/board/form/**").authenticated()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .anyRequest().permitAll();

        http.headers()
                .defaultsDisabled()
                .frameOptions().sameOrigin()
                .cacheControl().disable()
                .xssProtection().headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK);

        http.csrf();

        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);

        http.formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/login/process")
                .usernameParameter("id")
                .passwordParameter("pwd")
                .successHandler(new CustomLoginSuccessHandler())
                .failureHandler(new CustomLoginFailureHandler());

        http.logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("SESSION");


        http.exceptionHandling()
                .accessDeniedPage("/error/403");

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }
}
