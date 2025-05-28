package com.oracleous.extention_manager.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;
//    private CustomUserDetailsService userDetailsService; // Implement this

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/api/farmers/farmers_registration",
                                "/api/v1/farmers/register",
                                "/api/v1/farmers/verify-token",
                                "/investor/registration",
                                "/local_admin/registration",
                                "/super_admin/registration",
                                "/user/login"
//                                "/api/extension-worker/register"
                        ).permitAll()
                                .requestMatchers("/api/extension-worker/register", "/api/extension-worker/verify", "/error").permitAll()
                                .requestMatchers("/api/extension-worker/pending/**", "/api/extension-worker/approve", "/api/extension-worker/decision").hasRole("ADMIN")

                        .requestMatchers("/api/agri_business/register").hasRole("FARMER")
                        .requestMatchers("/api/agri_business/find").hasRole("FARMER")
                        .requestMatchers("/api/v1/farmers/find-farmer/**").hasRole("FARMER")
//                        .requestMatchers("/api/extension-worker/register", "/api/extension-worker/verify").permitAll()

                        .requestMatchers("/admin/register/initiate").hasRole("SUPER_ADMIN")
                        .requestMatchers(
                                "/admin/register/complete-form",
                                "/admin/register/complete",
                                "/admin/registration-success").permitAll()


                        .requestMatchers("/Admin/view/agriBusiness").hasRole("ADMIN")

                        .requestMatchers("/investor/find_agriBusiness").hasRole("INVESTOR")
                        .requestMatchers("/investor/check_all_farmers").hasRole("INVESTOR")
                        .requestMatchers("/investor/check_agriBusiness_link_with_farmer/").hasRole("INVESTOR")
                        .requestMatchers("/investor/find_investor").hasRole("INVESTOR")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:5173", "https://the-extension-manager-2.onrender.com"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                    config.setAllowCredentials(true);
                    return config;
                }));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}