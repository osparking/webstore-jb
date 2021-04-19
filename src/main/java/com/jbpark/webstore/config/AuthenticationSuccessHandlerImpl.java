package com.jbpark.webstore.config;


import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	HttpSession session; 

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String userId = "";
		if (authentication.getPrincipal() instanceof Principal) {
			userId = ((Principal) authentication.getPrincipal()).getName();
		} else {
			userId = ((User) authentication.getPrincipal()).getUsername();
		}
		session.setAttribute("userId", userId);
		response.sendRedirect(request.getContextPath() + "/market/customers");
	}
}
