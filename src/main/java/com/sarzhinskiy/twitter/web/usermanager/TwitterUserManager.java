package com.sarzhinskiy.twitter.web.usermanager;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.service.UserService;

@Component("userManager")
@Scope(value="session", proxyMode = ScopedProxyMode.INTERFACES)
public class TwitterUserManager implements UserManager, Serializable {

	private static final long serialVersionUID = 1342527748238996859L;
	private User user;
	private boolean loggedIn;
	
	@Autowired
	private UserService userService;
	
	@Override
	public User getUser() {
		return user;
	}

	@Override
	public boolean login(String email, String password) {
		System.out.println("login method()");
		if (userService.checkLogin(email, password)) {
			this.user = userService.findByEmail(email);
			System.out.println(user);
			loggedIn = true;
			return true;
		}
		return false;
	}

	@Override
	public void logout() {
		loggedIn = false;
		user = null;
	}

	@Override
	public boolean isLoggedIn() {
		return loggedIn;
	}

}
