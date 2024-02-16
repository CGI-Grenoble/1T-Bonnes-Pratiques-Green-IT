package polytech.projets10.g1._1tbonnespratiquesgreenit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/unauthenticated", "/oauth2/**", "/login/**", "/h2-console/**").permitAll()
                        .anyRequest().fullyAuthenticated()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                .logout((logout) -> logout.logoutSuccessUrl("http://localhost:8080/realms/1t-bonnes-pratiques/protocol/openid-connect/logout?post_logout_redirect_uri=http://localhost:8081/&client_id=green-it-app"));
        http.oauth2Client(Customizer.withDefaults());
        http.oauth2Login(c -> c.tokenEndpoint((tokenEndpoint) -> c.userInfoEndpoint(Customizer.withDefaults())));
        return http.build();
    }
}
