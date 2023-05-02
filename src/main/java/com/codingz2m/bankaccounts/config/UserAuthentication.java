package com.codingz2m.bankaccounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 
public class UserAuthentication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Authentication
	@Bean
	public UserDetailsService userDetailsManager(PasswordEncoder encoder) {	
		
		UserDetails admin = User
								.withUsername("admin")
								.password(encoder.encode("admin123"))
								.roles("ADMIN")
								.build();
		UserDetails guest = User
				.withUsername("guest")
				.password(encoder.encode("guest123"))
				.roles("GUEST")
				.build();
		
		return new InMemoryUserDetailsManager(admin, guest);
	}
	

	
	@Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
								.authorizeHttpRequests( (authorize) -> authorize
										.requestMatchers("/savingsaccount/welcome", "/h2-console").permitAll()
										.anyRequest().authenticated()
										)
								.headers(headers -> headers.frameOptions().sameOrigin())
								.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();				
		
	
					
    }
	
}
