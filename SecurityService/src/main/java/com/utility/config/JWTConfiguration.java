package com.utility.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
@Configuration
@EnableWebSecurity
public class JWTConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
    @Autowired
    private JwtAuthenticationFilter jwtFilter;


	private static final String[] WHITE_LIST_URL_STRINGS = {
			"/api/secure/token","/api/secure/signupcustomer",
			"/api/secure/verifyotp","/api/secure/savepassword",
			"/api/secure/signupsupplier"
			
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		
		http.
		csrf()
		.disable()
		.cors()
		.and()
		.authorizeRequests()		
		.antMatchers(WHITE_LIST_URL_STRINGS).permitAll()
		.anyRequest().authenticated()
		
		.and()
		.exceptionHandling().authenticationEntryPoint(null)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.httpBasic();
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .userDetailsService(customUserDetailsService);
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
}
