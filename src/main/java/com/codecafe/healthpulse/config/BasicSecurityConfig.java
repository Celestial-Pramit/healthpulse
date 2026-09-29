package com.codecafe.healthpulse.config;

// ─────────────────────────────────────────────────────────────────────────────
// REFERENCE ONLY: Spring Security WITHOUT JWT (HTTP Basic + in-memory users)
//
// To use this instead of the JWT setup:
//   1. Comment out the whole body of SecurityConfig.java (both classes define
//      SecurityFilterChain / passwordEncoder / userDetailsManager beans, so only
//      ONE of them can be active).
//   2. Uncomment everything below.
//   3. Test with:  curl -u admin:admin123 http://localhost:9090/patients
//      (Postman: Authorization tab -> Basic Auth). No login call, no token.
//      /auth/login still exists but is not needed with Basic auth.
// ─────────────────────────────────────────────────────────────────────────────

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class BasicSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // PUBLIC
//                        .requestMatchers(HttpMethod.GET, "/patients/dashboard").permitAll()
//                        // ADMIN ONLY: edit and delete
//                        .requestMatchers(HttpMethod.PUT, "/patients/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/patients/**").hasRole("ADMIN")
//                        // PRIVATE: everything else needs a login
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(basic -> {});
//        return http.build();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails nurse = User.builder()
//                .username("nurse")
//                .password(passwordEncoder().encode("nurse123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, nurse);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
