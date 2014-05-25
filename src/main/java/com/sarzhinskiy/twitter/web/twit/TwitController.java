package com.sarzhinskiy.twitter.web.twit;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sarzhinskiy.twitter.domain.twit.Twit;
import com.sarzhinskiy.twitter.domain.user.User;
import com.sarzhinskiy.twitter.service.TwitService;
import com.sarzhinskiy.twitter.service.UserService;
import com.sarzhinskiy.twitter.web.usermanager.UserManager;

@Controller
public class TwitController {

	private TwitService twitService;
	private UserService userService;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	public TwitController(TwitService twitService, UserService userService) {
		this.twitService = twitService;
		this.userService = userService;
	}
	
	@RequestMapping(value="/new_twit")
	public String creationTwit(Model model) {
		model.addAttribute("twitOption", "new_twit");
		return "TwitsPage";
	}
	
	@RequestMapping(value="/my_twits")
	public String showMyTwits(Model model) {
		model.addAttribute("twitOption", "my_twits");
		List<Twit> twits = twitService.findAllByOwnerId(userManager.getUser().getId());
		model.addAttribute("myTwits", twits);
		return "TwitsPage";
	}
	
	@RequestMapping(value="/friends_twits")
	public String showFriendsTwits(Model model) {
		model.addAttribute("twitOption", "friends_twits");
		List<User> users = userService.findAllObservedUsers(userManager.getUser().getId());
		model.addAttribute("friends", users);
		
		
		return "TwitsPage";
	}
	
	@RequestMapping(value="/createTwit")
	public String saveCreatedTwit(Model model, @RequestParam String createdTwit) {
		Twit twit = new Twit();
		twit.setOwnerId(userManager.getUser().getId());
		twit.setText(createdTwit);
		twitService.create(twit);
		
		model.addAttribute("twitOption", "my_twits");
		return "redirect:/my_twits";
	}
	
	@RequestMapping(value="/selectFriendForTwits")
	public String selectFriendForDisplayingTwits(RedirectAttributes redirectAttributes, 
			Model model, @RequestParam String selectedFriendId) {
		model.addAttribute("twitOption", "friends_twits");
		List<User> users = userService.findAllObservedUsers(userManager.getUser().getId());
		model.addAttribute("friends", users);
		
		Long friendId = Long.parseLong(selectedFriendId);
		List<Twit> twits = twitService.findAllByOwnerId(friendId);
		redirectAttributes.addFlashAttribute("friend", userService.findById(friendId));
		redirectAttributes.addFlashAttribute("friendTwits", twits);
		
//		model.addAttribute("friend", userService.findById(friendId));
//		model.addAttribute("friendTwits", twits);
		
		return "redirect:/friends_twits";
	}
	
}
