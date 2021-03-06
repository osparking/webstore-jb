package com.jbpark.webstore.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jbpark.webstore.domain.UserWS;
import com.jbpark.webstore.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		List<UserWS> users = userService.getAllUsers();
		for (UserWS u : users) {
			if ("admin".equals(u.getUsername())) {
				auth.inMemoryAuthentication().withUser(u.getUsername()).password(u.getPassword()).roles("USER",
						"ADMIN");
			} else if ("custrep".equals(u.getUsername())) {
				auth.inMemoryAuthentication().withUser(u.getUsername()).password(u.getPassword()).roles("USER",
						"SERVICE");
			} else {
				auth.inMemoryAuthentication().withUser(u.getUsername()).password(u.getPassword()).roles("USER");
			}
		}
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin()
			.loginPage("/login")
			.usernameParameter("userId")
			.passwordParameter("password")
			.defaultSuccessUrl("/market/products")
			.successHandler(authenticationSuccessHandler)
			.failureUrl("/login?error");
		
		httpSecurity.logout().logoutSuccessUrl("/login?logout");
		httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/**/products/add")
				.access("hasRole('ADMIN')").antMatchers("/**/customers/add").access("hasRole('SERVICE')")
				.antMatchers("/**/market/**").access("hasRole('USER')"); // url?????? add??? ????????? ????????? admin?????? ??????, market?????????
																			// url??? ????????? ????????? ?????? ???????????? ????????? ????????? ??????
		httpSecurity.csrf().disable();
	}
}
