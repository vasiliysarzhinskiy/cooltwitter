package com.sarzhinskiy.twitter.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.service.UserService;

@Controller
@RequestMapping(value="/restful")
public class UserRestfulController {
	
	private UserService userService;
	
	@Autowired
	public UserRestfulController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		System.out.println("RESTFUL: getUserById()");
		User user = userService.findById(id);
		ResponseEntity<User> responseEntity;
		if (user != null) {
			responseEntity = new ResponseEntity<>(user, HttpStatus.OK); 
		}
		else {
			responseEntity = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

}
