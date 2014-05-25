package com.sarzhinskiy.twitter.web.usermanager;

import com.sarzhinskiy.twitter.domain.user.User;

public interface UserManager {
	public User getUser();
	public boolean login(String email, String password);
	public void logout();
	public boolean isLoggedIn();
	
}
