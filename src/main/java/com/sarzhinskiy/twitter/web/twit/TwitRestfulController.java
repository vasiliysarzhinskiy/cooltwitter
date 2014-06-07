package com.sarzhinskiy.twitter.web.twit;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.sarzhinskiy.twitter.domain.twit.Twit;
import com.sarzhinskiy.twitter.service.TwitService;
import com.sarzhinskiy.twitter.service.UserService;

@Controller
@RequestMapping(value="/restful")
public class TwitRestfulController {
	
	private UserService userService;
	private TwitService twitService;
	
	@Autowired
	private TwitRestfulController(UserService userService, TwitService twitService) {
		this.userService = userService;
		this.twitService = twitService;
	}
	
	@RequestMapping(value = "/users/{userId}/twits", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Twit>> getAllTwitsByUserId(@PathVariable Long userId) {
		ResponseEntity<List<Twit>> responseEntity;
		if (userService.checkIfExist(userId)) {
			List<Twit> twits = twitService.findAllByOwnerId(userId);
			responseEntity = new ResponseEntity<List<Twit>>(twits, HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<List<Twit>>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/users/{userId}/twits/{nTwit}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Twit>> getNTwitsByUserId(@PathVariable Long userId, @PathVariable int nTwit) {
		ResponseEntity<List<Twit>> responseEntity;
		if (userService.checkIfExist(userId)) {
			List<Twit> twits = twitService.findNLastByOwnerId(userId, nTwit);
			responseEntity = new ResponseEntity<List<Twit>>(twits, HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<List<Twit>>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "users/{userId}/twits/{twitId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteTwit(@PathVariable Long userId, @PathVariable Long twitId) {
		System.out.println("RESTFUL: deleteTwit()");
		Twit twit = twitService.findById(twitId);
		ResponseEntity<Boolean> responseEntity;
		if (twit != null && twit.getOwnerId() == userId) {
			twitService.remove(twitId);
			responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		twitService.remove(twitId);
		return responseEntity;
	}
	
	@RequestMapping(value = "users/{userId}/twits/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Twit> createTwit(@RequestBody Twit twit, @PathVariable Long userId, UriComponentsBuilder uriBuilder) {
		ResponseEntity<Twit> responseEntity;
		System.out.println("RESTFUL: createTwit()");
		if (userService.checkIfExist(userId)) {
			twit.setOwnerId(userId);
			twitService.create(twit);
			
			Twit createdTwit = twitService.findById(twit.getId());
			HttpHeaders headers = new HttpHeaders();
			Map<String, String > mapUriVariables = new HashMap<>();
			mapUriVariables.put("userId", userId.toString());
			mapUriVariables.put("twitId", twit.getId().toString());
			headers.setLocation(uriBuilder.path("restful/users/{userId}/twits/{twitId}")
					.buildAndExpand(mapUriVariables).toUri());
			responseEntity = new ResponseEntity<>(createdTwit, headers, HttpStatus.CREATED);
		}
		else {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	
}
