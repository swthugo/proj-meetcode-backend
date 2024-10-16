package dev.hugosiu.meetCode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class WebSecurityConfig {

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    System.out.println("========== WebSecurityConfig.securityFilterChain invoked! ==========");

    http.cors();
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeHttpRequests(authorize ->
            authorize.requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated());

    http.oauth2ResourceServer().jwt();

    return http.build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

    converter.setJwtGrantedAuthoritiesConverter(jwt ->
            Optional.ofNullable(jwt.getClaimAsStringList("custom_claims"))
                    .stream()
                    .flatMap(Collection::stream)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
    );

    return converter;
  }

}
