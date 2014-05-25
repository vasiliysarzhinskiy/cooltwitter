package com.sarzhinskiy.twitter.web.usermanager;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sarzhinskiy.twitter.domain.Language;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserManager userManager;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!userManager.isLoggedIn()) {
			response.sendRedirect("/twitter/");
			return false;
		}
		return true;
	}
}
