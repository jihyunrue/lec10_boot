package com.gn.spring.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class WebSecurityConfig {
	
	private final DataSource dataSource;
	@Autowired
	public WebSecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> 
				requests.requestMatchers("/").permitAll()
						.requestMatchers("/board/**").authenticated()
						.requestMatchers("/join").permitAll()
						.requestMatchers("/boardUploadImg/**").permitAll()
						.requestMatchers("/chat/**").hasAnyAuthority("USER")
						.requestMatchers("/chatting").hasAnyAuthority("USER"))
						
		.formLogin(login -> 
				login.loginPage("/login")
					 .loginProcessingUrl("/login")
					 .usernameParameter("mem_id")
					 .passwordParameter("mem_pw")
					 .permitAll()
					 .successHandler(new MyLoginSuccessHandler())
					 .failureHandler(new MyLoginFailureHandler()))
			.logout(logout -> logout.permitAll())
			.rememberMe(rememberMe -> 
				rememberMe.rememberMeParameter("remember-me")
						  .tokenValiditySeconds(86400*7)
						  .alwaysRemember(false)
						  .tokenRepository(tokenRepository()))
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web -> 
					web.ignoring()
						.requestMatchers(
								PathRequest.toStaticResources().atCommonLocations()
						));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
}
