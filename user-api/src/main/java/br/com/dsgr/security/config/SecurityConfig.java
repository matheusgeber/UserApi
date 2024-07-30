package br.com.dsgr.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.dsgr.security.services.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	SecurityFilter securityFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/accounts").permitAll()
						.requestMatchers(HttpMethod.POST, "/login").permitAll()
						.requestMatchers(HttpMethod.GET, "/accounts").permitAll()
						.requestMatchers(HttpMethod.GET, "/accounts/{id}").hasAuthority("ROLE_ADMIN")	
						.requestMatchers(HttpMethod.PUT, "/accounts/{id}").hasAuthority("ROLE_ADMIN")	
						.requestMatchers(HttpMethod.PUT, "/accounts/{id}/role").hasAuthority("ROLE_ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/accounts/{id}").hasAuthority("ROLE_MANAGER")
						.anyRequest().authenticated()
				)	
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
