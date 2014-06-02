package com.sarzhinskiy.twitter.service;

import java.util.List;

import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.domain.user.UserAdditionalInfo;

public interface UserService {

	public User findById(Long id);
	public User findByEmail(String email);
	public List<User> findAll();
	public boolean create(User user);
	public List<User> findByFullName(String fullname);
	public boolean checkIfExist(Long id);
	public boolean checkIfExist(String email);
	public String findPasswordCipherByEmail(String email);
	public boolean checkLogin(String email, String password);
	public UserAdditionalInfo findUserAdditionalInfo(Long userId);
	public boolean update(User user);
	public boolean updateAdditionalInfo(UserAdditionalInfo userInfo);
	public List<User> findAllObservedUsers(Long userId);
	public boolean addObservedUser(User observerUser, User observedUser); 
	public boolean removeObservedUser(User observerUser, User observedUser); 
}
