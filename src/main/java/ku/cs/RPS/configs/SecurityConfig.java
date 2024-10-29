package ku.cs.RPS.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Be cautious with CSRF
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/css/**", "/js/**", "/login").permitAll() // Allow public access to these paths
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(login -> login
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/home", true) // Redirect after successful login
                        .permitAll() // Allow everyone to access login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Logout endpoint
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID") // Delete session cookies on logout
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession() // Manage session fixation
                        .maximumSessions(1) // One session per user
                        .expiredUrl("/login?expired") // Redirect to login if session expires
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("c96f5a5fe8ffb7a896735b7f2ef22fe9") // Use a unique key
                        .tokenValiditySeconds(86400) // Token validity for 1 day (in seconds)
                );
        ;


        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailService);

        return provider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

}
