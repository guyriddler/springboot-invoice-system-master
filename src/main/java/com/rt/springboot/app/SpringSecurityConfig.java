package com.rt.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rt.springboot.app.models.service.JpaUserDetailsService;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Main security filter chain configuration
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
						.requestMatchers("/", "/css/**", "/js/**", "/images/**", "/list", "/locale", "/api/**", "/signup", "/actuator/**").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll()
						.defaultSuccessUrl("/", true)
				)
				.logout(logout -> logout
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutSuccessUrl("/login?logout")
						.permitAll()
				)
				.exceptionHandling(ex -> ex
						.accessDeniedPage("/error_403")
				)
				.rememberMe(rememberMe -> rememberMe
						.userDetailsService(userDetailsService)
						.tokenValiditySeconds(86400) // 24 hours - you can adjust this
				);

		return http.build();
	}

	// Authentication provider configuration
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	// Authentication manager bean (if needed elsewhere in your application)
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
