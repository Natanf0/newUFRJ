package br.com.aval_doc.infra;

import br.com.aval_doc.Entities.UserRole;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class AuthConfig {
    SecurityFilter securityFilter;
    public AuthConfig(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println(UserRole.ALUNO.getValue());
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/ufrj_api/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/ufrj_api/aluno").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ufrj_api/avaliacao").permitAll()

                        .requestMatchers(HttpMethod.GET, "/ufrj_api/aluno").hasAuthority(UserRole.COORDENACAO.getValue())
                        .requestMatchers(HttpMethod.GET, "/ufrj_api/aluno/{id}").hasAuthority(UserRole.ALUNO.getValue())
                        .requestMatchers(HttpMethod.DELETE, "/ufrj_api/aluno/{id}").hasAuthority(UserRole.COORDENACAO.getValue())

                        .requestMatchers(HttpMethod.POST, "/ufrj_api/avaliacao").hasAuthority(UserRole.ALUNO.getValue())
                        .requestMatchers(HttpMethod.PATCH, "/ufrj_api/avaliacao/{id}").hasAuthority(UserRole.ALUNO.getValue())
                        .requestMatchers(HttpMethod.DELETE, "/ufrj_api/avaliacao/{id}").hasAuthority(UserRole.ALUNO.getValue())
                        .requestMatchers(HttpMethod.GET, "/ufrj_api/avaliacao/{id}").hasAuthority(UserRole.ALUNO.getValue())
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
