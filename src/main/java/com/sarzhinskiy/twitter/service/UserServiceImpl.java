package com.sarzhinskiy.twitter.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.domain.user.UserAdditionalInfo;
import com.sarzhinskiy.twitter.encryption.Encrypt;
import com.sarzhinskiy.twitter.repository.dao.UserDAO;

@Service("UserService")
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 857538848410013296L;
	private UserDAO repository;
	
	@Autowired
	public UserServiceImpl(UserDAO repository) {
		this.repository = repository;
	}
	
	@Override
	public User findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean create(User user) {
		return repository.create(user);
	}

	@Override
	public List<User> findByFullName(String fullName) {
		return repository.findByFullName(fullName);
	}

	@Override
	public boolean checkIfExist(Long id) {
		if (id == null) {
			return false;
		}
		User user = repository.findById(id);
		return (user != null);
	}
	
	@Override
	public boolean checkIfExist(String email) {
		if (email == null) {
			return false;
		}
		User user = repository.findByEmail(email);
		return (user != null);
	}

	@Override
	public String findPasswordCipherByEmail(String email) {
		return repository.findPasswordCipherByEmail(email);
	}
	
	@Override
	public boolean checkLogin(String email, String password) {
		boolean isExistEmail = checkIfExist(email);
		if (!isExistEmail) {
			return false;
		}
		String passwordCipher = Encrypt.encryptByMDFive(password);
		String findedPasswordCipher = repository.findPasswordCipherByEmail(email);
		return (passwordCipher.equals(findedPasswordCipher));
	}
	
	@Override
	public UserAdditionalInfo findUserAdditionalInfo(Long userId) {
		UserAdditionalInfo userInfo = repository.findUserAdditionalInfoById(userId);
		return userInfo;
	}
	
	@Override
	public boolean update(User user) {
		return repository.update(user);
	}
	
	@Override
	public boolean updateAdditionalInfo(UserAdditionalInfo userInfo) {
		return repository.updateAdditionalInfo(userInfo);
	}
	
	@Override
	public List<User> findAllObservedUsers(Long userId) {
		return repository.findAllObservedUser(userId);
	}

	@Override
	public boolean addObservedUser(User observerUser, User observedUser) {
		return repository.addObservedUser(observerUser, observedUser);
	}

	@Override
	public boolean removeObservedUser(User observerUser, User observedUser) {
		return removeObservedUser(observerUser, observedUser);
	}


}
