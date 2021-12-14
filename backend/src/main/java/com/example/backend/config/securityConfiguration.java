package com.example.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.backend.service.auth.ApplicationUserDetailsService;

@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;
	@Autowired
	private ApplicationRequestFilter applicationRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(applicationUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin();
		http.csrf().disable().authorizeRequests()
//		.antMatchers("/**/authenticate",
//			 "/**/odcmanager/api/v1/**/",
//			 "/v2/api-docs",
//			 "/swagger-resources",
//			 "/swagger-resources/**",
//			 "/configuration/ui",
//			 "/configuration/security",
//			 "/swagger-ui.html",
//			 "/webjars/**",
//			 "/v3/api-docs/**",
//			 "/swagger-ui/**").permitAll()
		.anyRequest().permitAll()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
