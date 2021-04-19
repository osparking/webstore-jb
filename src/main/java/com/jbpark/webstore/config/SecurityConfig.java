package com.jbpark.webstore.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jbpark.webstore.domain.User;
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
		List<User> users = userService.getAllUsers();
		for (User u : users) {
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
				.antMatchers("/**/market/**").access("hasRole('USER')"); // url끝이 add로 끝나면 권한을 admin에게 주고, market이라는
																			// url이 중간에 있으면 일반 유저에게 권한을 준다는 의미
		httpSecurity.csrf().disable();
	}
}
