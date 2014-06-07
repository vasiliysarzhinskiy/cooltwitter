package com.sarzhinskiy.twitter.domain.user;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {

	private List<User> users;

	public Users() {
	}
	
	public Users(List<User> users) {
		this.users = users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<User> getUsers() {
		return users;
	}

}
